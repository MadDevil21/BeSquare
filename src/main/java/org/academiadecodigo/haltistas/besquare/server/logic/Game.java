package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.Status;
import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.Server;
import org.academiadecodigo.haltistas.besquare.server.environment.Button;
import org.academiadecodigo.haltistas.besquare.server.environment.Door;
import org.academiadecodigo.haltistas.besquare.server.environment.KeyColor;
import org.academiadecodigo.haltistas.besquare.server.environment.Token;
import org.academiadecodigo.haltistas.besquare.server.logic.helpers.CollisionHelper;

import java.io.IOException;

public class Game {

    private Status status;
    private LogicGrid grid;
    private Levels level;
    private Server server;

    public Game(Server server) {
        this.server = server;
    }

    public void init() {

        // TODO change levels
        this.level = Levels.LEVEL_1;

        status = Status.NEW_LEVEL;

        loadNewLevel(level);
    }

    private void loadNewLevel(Levels level) {

        grid = new LogicGrid();

        try {
            grid.load(level);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int initialP1X = grid.getPlayer1().getCol();
        int initialP1Y = grid.getPlayer1().getRow();
        int initialP2X = grid.getPlayer2().getCol();
        int initialP2Y = grid.getPlayer2().getRow();

        int[] positions = {initialP1X, initialP1Y, initialP2X, initialP2Y};

        String initialBroadcast = OutputHandler.buildPacket(status, level, positions);
        server.broadcast(initialBroadcast);

        String tokenBroadcast = "";


        for (Integer tokenIndex : grid.getTokenMap().keySet()) {
            Token token = grid.getTokenMap().get(tokenIndex);

            int tokenCol = token.getCol();
            int tokenRow = token.getRow();

            tokenBroadcast = OutputHandler.tokenPacketBuilder(1, tokenCol, tokenRow);

            server.broadcast(tokenBroadcast);
        }

        String buttonBroadcast = "";

        for (KeyColor key : grid.getButtonMap().keySet()) {
            Button button = grid.getButtonMap().get(key);

            int buttonCol = button.getCol();
            int buttonRow = button.getRow();

            buttonBroadcast = OutputHandler.buildInteractivePacket(0, 0, 0, buttonCol,buttonRow);

            server.broadcast(buttonBroadcast);
        }

        String doorBroadcast = "";

        for (KeyColor key : grid.getDoorMap ().keySet()) {
            Door door = grid.getDoorMap().get(key);

            int doorCol = door.getCol();
            int doorRow = door.getRow();

            doorBroadcast = OutputHandler.buildInteractivePacket(0, 1, 0, doorCol, doorRow);

            server.broadcast(doorBroadcast);
        }

        gameLoop();
    }

    private void gameLoop() {

        status = Status.GAME;
    }

    public synchronized String process(int playerId, String fromClient) {

        Action selectedAction = InputHandler.interpret(fromClient);

        if (selectedAction.equals(Action.RESET_LEVEL)) {
            loadNewLevel(level);

        }

        int[] positions = grid.verifyAction(playerId, selectedAction);

        KeyColor color = CollisionHelper.activatorCollisions(playerId, grid);

        if (color != null) {
            checkDoors(color);
            //For testing, this only works with default Red doors and buttons
            String activatedButtonBroadcast = OutputHandler.buildInteractivePacket(0, 0, 1,0,0);
            server.broadcast(activatedButtonBroadcast);
        }

        int tokenIndex = CollisionHelper.tokenCollisions(playerId, grid);

        if (tokenIndex != -1) {
            String eatenTokenBroadcast = OutputHandler.tokenPacketBuilder(0, tokenIndex);
            server.broadcast(eatenTokenBroadcast);

        }

        if (grid.levelWon()) {

            level = nextLevel();

            if (level != null) {

                status = Status.NEW_LEVEL;
                loadNewLevel(level);
                return null;
            }

        }

        return OutputHandler.buildPacket(status, level, positions);
    }

    private Levels nextLevel() {

        Levels nextLevel = null;

        // TODO if there are no more levels, the game should end

        for (Levels levels : Levels.values()) {

            if (level.ordinal() == levels.ordinal()) {
                nextLevel = Levels.values()[level.ordinal() + 1];
            }
        }

        return nextLevel;
    }

    private void checkDoors(KeyColor key) {
        if (grid.getDoorMap().containsKey(key)) {

            grid.getDoorMap().get(key).open();

        }

    }

}
