package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.FilePath;

public enum Levels {

    LEVEL_1(FilePath.LEVEL_1_PNG, FilePath.LEVEL_1_TXT),
    LEVEL_2(FilePath.LEVEL_2_PNG, FilePath.LEVEL_2_TXT);

    private final String background;
    private  final String matrix;

    Levels(String background, String matrix) {
        this.background = background;
        this.matrix = matrix;
    }

    public String getBackground() {
        return background;
    }

    public String getMatrix() {
        return matrix;
    }
}
