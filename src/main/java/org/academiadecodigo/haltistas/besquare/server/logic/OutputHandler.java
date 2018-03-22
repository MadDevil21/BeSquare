package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.Status;

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
}