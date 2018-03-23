package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class MainMenu extends AbstractMenu {

    private ConnectMenu connectMenu;

    public void init() {

        options = new Text[MenuOption.values().length];

        createBackground();
        createOptions();

        options[movePointer].setColor(Color.WHITE);
    }

    private void createOptions() {

        int yPos = INITIAL_Y_OPTION_POS;

        for (MenuOption option : MenuOption.values()) {

            yPos += OPTIONS_MARGIN;

            options[option.ordinal()] = createOption(yPos, option.getOption());

        }
    }

    public void actionSelection() {

        if (movePointer == MenuOption.QUIT.ordinal()) {

           System.exit(0);
        }

        connectMenu.init();
    }

    @Override
    public void moveDown() {

        if (movePointer == MenuOption.values().length - 1) {
            return;
        }

        super.moveDown();
    }

    public void setConnectMenu(ConnectMenu connectMenu) {
        this.connectMenu = connectMenu;
    }
}
