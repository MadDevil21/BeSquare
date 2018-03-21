package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.util.Message;

public class InputHandler {

    public static Action interpret(String fromClient) {

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
                System.err.println(Message.ERR_HALP_STRING);
        }

        return selectedAction;
    }
}
