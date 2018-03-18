package org.academiadecodigo.haltistas.besquare.server.logic;

public class Game {

    private LogicGrid grid;
    private CollisionDetector collisionDetector;
    private ActivationDetector activationDetector;
    private InputHandler inputHandler;
    private Levels level;

    public Game() {
        this.grid = new LogicGrid();
        this.collisionDetector = new CollisionDetector();
        this.activationDetector = new ActivationDetector();
        this.inputHandler = new InputHandler();
    }

    public void init() {
        this.level = Levels.LEVEL_1;

    }

    public String process(int playerId, String fromClient) {

        inputHandler.interpret(playerId, fromClient);

        if (fromClient.equals("1")) {
            return "x x x x 30 0 30 0";
        }

        if (fromClient.equals("-1")) {

        }
        return "x x x x -30 0 -30 0";
    }


}
