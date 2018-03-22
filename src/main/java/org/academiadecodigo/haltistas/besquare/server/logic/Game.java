package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.Status;
import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.Server;
import org.academiadecodigo.haltistas.besquare.server.environment.Token;
import org.academiadecodigo.haltistas.besquare.server.logic.helpers.CollisionHelper;
import org.academiadecodigo.haltistas.besquare.server.logic.timer.Gravity;

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

            System.out.println(tokenBroadcast);
            tokenBroadcast = OutputHandler.tokenPacketBuilder(1, tokenCol, tokenRow);

            server.broadcast(tokenBroadcast);
        }

        gameLoop();
    }

    private void gameLoop() {

        status = Status.GAME;
        Gravity gravity = new Gravity();

        gravity.setGame(this);
        grid.setupGravity(gravity);
    }

    public synchronized void process(int playerId, String fromClient) {

        Action selectedAction = InputHandler.interpret(fromClient);
        System.out.println("at server reset level: " + selectedAction);

        if (selectedAction.equals(Action.RESET_LEVEL)) {
            System.out.println("at server reset level:  IF " + selectedAction);
            loadNewLevel(level);

        }

        int[] positions = grid.verifyAction(playerId, selectedAction);

        processThisShit(positions);

    }

    public void processThisShit(int[] positions) {
        checkPlayerToken(grid.getPlayer1().getId());
        checkPlayerToken(grid.getPlayer2().getId());

        if (grid.levelWon()) {

            level = nextLevel();

            if (level != null) {

                status = Status.NEW_LEVEL;
                loadNewLevel(level);
                return;
            }

        }

        server.broadcast(OutputHandler.buildPacket(status, level, positions));
    }

    private void checkPlayerToken(int playerId) {
        int tokenIndex = CollisionHelper.tokenCollisions(playerId, grid);

        if (tokenIndex != -1) {
            String eatenTokenBroadcast = OutputHandler.tokenPacketBuilder(0, tokenIndex);
            server.broadcast(eatenTokenBroadcast);

        }
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

}
