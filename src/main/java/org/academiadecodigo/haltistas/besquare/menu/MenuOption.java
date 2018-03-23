package org.academiadecodigo.haltistas.besquare.menu;

public enum MenuOption {

    HOST_SERVER ("HOST SERVER"),
    JOIN_GAME ("JOIN GAME"),
    QUIT ("QUIT");

    private String option;

    MenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
