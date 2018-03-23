package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Menu {

    private static final int PADDING = 10;
    private static final int FRAME_WIDTH = 960;
    private static final int INITIAL_Y_OPTION_POS = 330;
    private static final int OPTIONS_MARGIN = 50;
    private static final int FONT_SIZE = 22;

    private Font font;
    private String message;
    private int movePointer = 0;
    private Text selectedOption;
    private Text[] options;

    public void init() {

        options = new Text[MenuOption.values().length];
        font = new Font("Arial", Font.BOLD, FONT_SIZE);
        createBackground();
        createOptions();

        options[movePointer].setColor(Color.WHITE);
    }

    private void createBackground() {

        Picture background = new Picture(PADDING, PADDING, FilePath.MENU_BACKGROUND);
        background.draw();
    }

    private void createOptions() {

        int yPos = INITIAL_Y_OPTION_POS;

        for (MenuOption option : MenuOption.values()) {

            yPos += OPTIONS_MARGIN;

            options[option.ordinal()] = createOption(yPos, option.getOption());

        }
    }

    private Text createOption(int yPos, String option) {

        message = option;

        Text optionText = new Text(optionsXPos(), yPos, message, font);

        optionText.draw();
        optionText.setColor(Color.RED);

        return optionText;
    }

    public void moveDown() {

        if (movePointer == MenuOption.values().length - 1) {
            return;
        }

        options[movePointer].setColor(Color.RED);
        movePointer++;
        options[movePointer].setColor(Color.WHITE);
    }

    public void moveUp() {

        if (movePointer == 0) {
            return;
        }

        options[movePointer].setColor(Color.RED);
        movePointer--;
        options[movePointer].setColor(Color.WHITE);
    }

    public void actionSelection() {

        switch (movePointer) {

            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private int optionsXPos() {

        final int CORRECT_CENTER_POS = 50;

        return FRAME_WIDTH / 2 - CORRECT_CENTER_POS;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
