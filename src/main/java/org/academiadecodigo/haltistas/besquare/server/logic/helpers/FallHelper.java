package org.academiadecodigo.haltistas.besquare.server.logic.helpers;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.Block;

public class FallHelper {

    public static void processFall(PlayerCharacter character, Block[][] grid) {

        int destinationCol = character.getCol();
        int destinationRow = character.getRow() + 1;

        if (!grid[destinationCol][destinationRow].isColliding(character)) {
            character.setPosition(destinationCol, destinationRow);
            character.setFallen(true);

            return;
        }

        character.setFalling(false);
    }
}
