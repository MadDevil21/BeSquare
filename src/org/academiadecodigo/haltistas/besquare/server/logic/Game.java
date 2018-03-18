package org.academiadecodigo.haltistas.besquare.server.logic;

public class Game {

    private LogicGrid grid;
    private CollisionDetector collisionDetector;
    private ActivationDetector activationDetector;
    private InputHandler inputHandler;
    private Levels level;


    public String process(String fromClient) {

        if (fromClient.equals("1")) {
            return "x x x x 30 0 30 0";
        }

        if (fromClient.equals("-1")) {

        }
        return "x x x x -30 0 -30 0";
    }


}
