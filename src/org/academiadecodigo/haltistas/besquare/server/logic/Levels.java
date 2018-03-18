package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.FilePath;

public enum Levels {

    LEVEL_1(FilePath.LEVEL_1);

    private final String filePath;

    Levels(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
