package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.Server;

import java.io.IOException;

public class Game {

    private LogicGrid grid;
    private GameState gameState;
    private Levels level;
    private Server server;

    public Game(Server server) {
        this.server = server;
    }

    public void init() {

        // TODO change levels
        this.level = Levels.LEVEL_1;

        gameState = GameState.NEW_LEVEL;

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

        String initialBroadcast = OutputHandler.buildPacket(gameState, level, positions);

        server.broadcast(initialBroadcast);

        gameLoop();
    }

    private void gameLoop() {

        gameState = GameState.GAME;
    }

    public synchronized String process(int playerId, String fromClient) {

        Action selectedAction = InputHandler.interpret(fromClient);

        int[] positions = grid.verifyAction(playerId, selectedAction);

        if (grid.levelWon()){

            level = nextLevel();

            if(level!= null) {

                gameState = GameState.NEW_LEVEL;
                loadNewLevel(level);
            }

        }

        return OutputHandler.buildPacket(gameState, level, positions);
    }

    private Levels nextLevel(){

        Levels nextLevel = null;

        // TODO if there are no more levels, the game should end

        for (Levels levels : Levels.values()) {

            if (level.ordinal() == levels.ordinal()){
                nextLevel = Levels.values()[level.ordinal()+1];
            }
        }

        return nextLevel;
    }
}
