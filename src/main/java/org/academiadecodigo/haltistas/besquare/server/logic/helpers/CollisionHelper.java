package org.academiadecodigo.haltistas.besquare.server.logic.helpers;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;
import org.academiadecodigo.haltistas.besquare.server.logic.LogicGrid;

import java.util.Map;

public class CollisionHelper {


    public static void processCollision(PlayerCharacter movingPlayer, Block destinationBlock, Block[][] grid) {

        if (destinationBlock.isColliding(movingPlayer)) {

            return;
        }
            movingPlayer.setPosition(destinationBlock.getCol(), destinationBlock.getRow());
    }

    public static boolean platformUnder(PlayerCharacter movingPlayer, Block[][] grid){

        int playerCol = movingPlayer.getCol();
        int playerRow = movingPlayer.getRow();

        Block underPlayer = grid[playerCol][playerRow+1];

        return underPlayer.isColliding(movingPlayer);

    }

    public static int tokenCollisions(int playerID, LogicGrid grid) {

        int eatenTokenIndex = -1;
        Map<Integer, Token> tokenMap = grid.getTokenMap();
        PlayerCharacter checkedCharacter = grid.fetchById(playerID);

        for (Integer index : tokenMap.keySet()) {

            if (tokenMap.get(index) == null) {
                continue;
            }

            Token checkedToken = tokenMap.get(index);

            //TODO extract this nested if to another method

            if (checkedToken.isColliding(checkedCharacter)) {
                eatenTokenIndex = index;

                System.out.println("Om nom nom token # " + eatenTokenIndex);
                grid.removeToken(eatenTokenIndex);

                return eatenTokenIndex;

            }

        }

        return eatenTokenIndex;

    }

    public static KeyColor activatorCollisions(int playerID, LogicGrid grid){

        KeyColor returnColor = null;
        Map<KeyColor, Interactive> interactiveMap = grid.getInteractiveMap();

        PlayerCharacter checkedCharacter = grid.fetchById(playerID);

        for (KeyColor color : interactiveMap.keySet()) {

            if (interactiveMap.get(color) == null) {
                continue;
            }

            Interactive button = grid.getInteractiveMap().get(color);

            //TODO extract this nested if to another method

            if (button.isColliding(checkedCharacter)) {
                returnColor = color;

                return returnColor;

            }

        }

        return returnColor;
    }

    public static boolean checkWin(LogicGrid grid){

        Exit exit = grid.getExit();
        PlayerCharacter player1 = grid.getPlayer1();
        PlayerCharacter player2 = grid.getPlayer2();

        return (exit.isColliding(player1, player2) &&
                grid.getTokenMap().isEmpty());

    }
}
