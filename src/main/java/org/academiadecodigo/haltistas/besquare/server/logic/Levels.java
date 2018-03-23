package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.FilePath;

public enum Levels {

    LEVEL_1(FilePath.LEVEL_1_PNG, FilePath.LEVEL_1_TXT),
    LEVEL_2(FilePath.LEVEL_2_PNG, FilePath.LEVEL_2_TXT),
    LEVEL_3(FilePath.LEVEL_3_PNG, FilePath.LEVEL_3_TXT),
    LEVEL_4(FilePath.LEVEL_4_PNG, FilePath.LEVEL_4_TXT),
    LEVEL_5(FilePath.LEVEL_5_PNG, FilePath.LEVEL_5_TXT),
    LEVEL_6(FilePath.LEVEL_6_PNG, FilePath.LEVEL_6_TXT),
    LEVEL_7(FilePath.LEVEL_7_PNG, FilePath.LEVEL_7_TXT);

    private final String background;
    private final String matrix;

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
