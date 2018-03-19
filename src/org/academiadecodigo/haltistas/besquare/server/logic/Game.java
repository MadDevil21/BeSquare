package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.client.Action;

public class Game {

    private LogicGrid grid;
    private CollisionDetector collisionDetector;
    private ActivationDetector activationDetector;
    private GameState gameState;
    private Levels level;

    public Game() {
        this.grid = new LogicGrid();
        this.collisionDetector = new CollisionDetector();
        this.activationDetector = new ActivationDetector();
    }

    public void init() {
        this.level = Levels.LEVEL_1;

    }

    public String process(int playerId, String fromClient) {

        Action selectedAction = InputHandler.interpret(fromClient);

        int[] positions = grid.verifyAction(playerId, selectedAction, collisionDetector);

        OutputHandler.buildPacket(gameState, level, positions);

        if (fromClient.equals("1")) {
            return "x x x x 30 0 30 0";
        }

        if (fromClient.equals("-1")) {

        }
        return "x x x x -30 0 -30 0";
    }


}
