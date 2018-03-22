package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.Status;
import org.academiadecodigo.haltistas.besquare.client.event.Event;
import org.academiadecodigo.haltistas.besquare.client.event.GameEvent;
import org.academiadecodigo.haltistas.besquare.client.event.NewLevelEvent;
import org.academiadecodigo.haltistas.besquare.client.event.TokenEvent;
import org.academiadecodigo.haltistas.besquare.client.timer.Gravity;
import org.academiadecodigo.haltistas.besquare.client.timer.GravityAnimation;
import org.academiadecodigo.haltistas.besquare.util.Message;

import java.util.Timer;

public class TaskManager {

    private GameField field;
    private Event event;
    private GravityAnimation gravityAnimation;

    TaskManager(GameField gameField) {

        this.field = gameField;
        gravityAnimation = new GravityAnimation();
        new Thread(gravityAnimation).start();

    }

    protected void interpret(String fromServer) {

        System.out.println(Message.HALP_BROADCAST + fromServer);
        String[] instructions = fromServer.split(" ");

        Status currentStatus = stringToStatus(instructions[0]);

        switch (currentStatus) {
            case NEW_LEVEL:
                event = new NewLevelEvent(instructions);
                break;

            case GAME:
                event = new GameEvent(instructions);
                break;

            case TOKEN:
                event = new TokenEvent(instructions);
                break;
            case FALLING:
                Gravity gravity = new Gravity();
                gravityAnimation.addGravity(gravity);
                field.setupGravity(gravity);
                event = gravity;
                break;
        }

        event.process(instructions, field);

    }


    private Status stringToStatus(String string) {
        Status returnStatus = null;

        for (Status status : Status.values()) {
            if (status.name().equals(string)) {
                returnStatus = status;

            }

        }

        return returnStatus;
    }


}
