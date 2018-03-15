package org.academiadecodigo.haltistas.besquare.server.logic;

public enum Levels {

    LEVEL_1("resources/levels/level_1_tutorial.txt");

    private final String filePath;

    Levels(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
