package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.haltistas.besquare.client.GameField;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public abstract class AbstractMenu {

    protected static final int FONT_SIZE = 22;
    protected static final int FRAME_WIDTH = 960;
    protected static final int INITIAL_Y_OPTION_POS = 330;
    protected static final int OPTIONS_MARGIN = 50;

    protected int movePointer = 0;
    protected Text[] options;
    protected Font font;
    protected Picture background;
    protected Text optionText;


    public AbstractMenu() {

        font = new Font("Arial", Font.BOLD, FONT_SIZE);
    }

    protected void createBackground() {

        background = new Picture(GameField.PADDING, GameField.PADDING, FilePath.MENU_BACKGROUND);
        background.draw();
    }

    protected Text createOption(int yPos, String option) {

        optionText = new Text(optionsXPos(), yPos, option, font);

        optionText.draw();
        optionText.setColor(Color.RED);

        return optionText;
    }

    protected int optionsXPos() {

        final int CORRECT_CENTER_POS = 50;

        return FRAME_WIDTH / 2 - CORRECT_CENTER_POS;
    }

    public void moveDown() {

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

    public void deleteBackground() {
        background.delete();
        optionText.delete();
    }
}
