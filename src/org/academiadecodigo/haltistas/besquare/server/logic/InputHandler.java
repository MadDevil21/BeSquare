package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;

public class InputHandler {


    public Action interpret(String fromClient) {

        Action selectedAction = null;

        switch (fromClient) {

            case "M R":
                selectedAction = Action.MOVE_RIGHT;
                break;

            case "M L":
                selectedAction = Action.MOVE_LEFT;
                break;

            case "J R":
                selectedAction = Action.JUMP_RIGHT;
                break;

            case "J L":
                selectedAction = Action.JUMP_LEFT;
                break;

                default:
                    System.err.println("Received invalid fromClient HALP string");

        }

        return selectedAction;
    }
}
