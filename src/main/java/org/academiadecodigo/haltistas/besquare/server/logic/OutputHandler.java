package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.Status;
import org.academiadecodigo.haltistas.besquare.server.environment.KeyColor;

import javax.swing.plaf.synth.ColorType;

public class OutputHandler {

    /**
     * This Utility class will build a String in the correct protocol standard for the game, for the server to
     * send to the clients, after updating their positions.
     *
     * @param status            Current status;
     * @param level             Current level;
     * @param playerCoordinates Player coordinates, in the following order: player 1 X, player 1 Y, player 2 X, player 2 Y;
     * @return A string formatted per our protocol standard.
     */

    public static String buildPacket(Status status, Levels level, int[] playerCoordinates) {

        StringBuilder sb = new StringBuilder();

        sb.append(status.name());
        sb.append(' ');

        sb.append(level.name());
        sb.append(' ');

        for (int i = 0; i < playerCoordinates.length; i++) {
            sb.append(playerCoordinates[i]);

            // check for the last number so an extra space is not sent at the end
            if (!(i == playerCoordinates.length - 1)) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    // sends 1 to create and 0 to eat
    public static String tokenPacketBuilder(int action, int col, int row) {
        StringBuilder sb = new StringBuilder();

        sb.append(Status.TOKEN.name() + ' ');
        sb.append(action);
        sb.append(' ');
        sb.append(col);
        sb.append(' ');
        sb.append(row);
        sb.append(' ');
        sb.append("0 0");

        return sb.toString();

    }

    public static String tokenPacketBuilder(int action, int tokenIndex) {

        StringBuilder sb = new StringBuilder();

        sb.append(Status.TOKEN.name() + ' ');
        sb.append(action);
        sb.append(' ');
        sb.append(tokenIndex);
        sb.append(' ');
        sb.append("0 0 0");

        return sb.toString();

    }

    /**
     * Creates a string to send to the clients so they can interpret and draw buttons and blocking doors
     * @param color 0 for red, 1 for green
     * @param option 0 for a button, 1 for a door
     * @param action 0 to create, 1 to activate
     * @param col   Column in which the element will be created
     * @param row   Row in which the element will be created
     * @return  Formatted protocol string
     */
    public static String buildInteractivePacket(int color, int option, int action, int col, int row){
        StringBuilder sb = new StringBuilder();

        sb.append(Status.INTERACTIVE.name());
        sb.append(' ');
        sb.append(color);
        sb.append(' ');
        sb.append(option);
        sb.append(' ');
        sb.append(action);
        sb.append(' ');
        sb.append(col);
        sb.append(' ');
        sb.append(row);

        return sb.toString();

    }
}