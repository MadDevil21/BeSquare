package org.academiadecodigo.haltistas.besquare.server.logic.helpers;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.Block;

public class FallHelper {

    public static boolean shouldKeepFalling(PlayerCharacter character){

        return (character.isFalling() && !character.isJumping());

    }

    public static void processFall(PlayerCharacter character, Block[][] grid) {

        int destinationCol = character.getCol();
        int destinationRow = character.getRow() + 1;

        if (!grid[destinationCol][destinationRow].isColliding(character)) {
            character.setPosition(destinationCol, destinationRow);
            character.setFallen(true);

            // TODO: This commented out solution makes players fall one block at a time but only
            // when the other one moves; this is not the intended behavior but it could be
            // if we implement a timer.

            // return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

            // Recursive solution: This makes players disappear from a platform and immediately appear below


            return;
        }

        character.setFalling(false);
    }
}
