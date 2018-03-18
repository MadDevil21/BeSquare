package org.academiadecodigo.haltistas.besquare.server.logic;

public class Game {

    private LogicGrid grid;
    private CollisionDetector collisionDetector;
    private ActivationDetector activationDetector;
    private InputHandler inputHandler;
    private Levels level;




    public String process(String fromClient){
        return  "x x x x 300 100 700 300";
    }


}
