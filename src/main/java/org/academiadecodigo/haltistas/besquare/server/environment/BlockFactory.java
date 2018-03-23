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

    public static Interactive createInteractive(int col, int row, KeyColor color){
        return new Interactive(col, row, color);
    }
    public static MovingPlatform movingPlatform(int col, int row, KeyColor color){
        return new MovingPlatform(col, row, color);
    }
    public static Exit createExit(int col, int row) {
        return new Exit(col, row);
    }
}
