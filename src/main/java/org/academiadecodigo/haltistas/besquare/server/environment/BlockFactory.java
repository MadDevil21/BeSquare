package org.academiadecodigo.haltistas.besquare.server.environment;

public class BlockFactory {

    public static Block createBlock(BlockType blockType, int col, int row) {

        Block block = null;

        if(blockType.equals(BlockType.EXIT)) {
            throw new IllegalArgumentException();
        }

        switch (blockType) {

            case PLATFORM:

                block = new Platform(col, row);
                break;

            case BACKGROUND:

                block = new Background(col, row);
                break;

        }

        return block;
    }

    public static Button createInteractive(int col, int row, KeyColor color){
        return new Button(col, row, color);
    }
    public static Door movingPlatform(int col, int row, KeyColor color){
        return new Door(col, row, color);
    }
    public static Exit createExit(int col, int row) {
        return new Exit(col, row);
    }
}
