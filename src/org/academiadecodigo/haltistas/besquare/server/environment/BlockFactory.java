package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class BlockFactory {

    public static Block createBlock(BlockType blockType, int col, int row) {

        Block block = null;

        switch (blockType) {

            case PLATFORM:

                block = new Platform(col, row);
                break;

            case EXIT:

                block = new Exit(col, row);
                break;

            case CHARACTER_1:

                block = new PlayerCharacter(col, row, 1);
                break;

            case CHARACTER_2:
                block = new PlayerCharacter(col, row, 2);
                break;

            case BACKGROUND:

                block = new Background(col, row);
                break;
        }
        
        return block;
    }
}
