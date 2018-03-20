package org.academiadecodigo.haltistas.besquare.menu;
import org.academiadecodigo.haltistas.besquare.menu.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MenuView {

    public static final int X_PADDING = 10;
    public static final int Y_PADDING = 10;

    private Rectangle canvas;
    private Picture logo;
    private MenuInput menuInput;

    public MenuView(int width, int height) {
        org.academiadecodigo.haltistas.besquare.menu.MenuInput menuInput = new MenuInput();
        canvas = new Rectangle(X_PADDING, Y_PADDING, width, height);
        this.menuInput = menuInput;
        canvas.setColor(Color.BLACK);
        canvas.fill();
    }

    public void setCanvas() {

        
        setLogo("org/academiadecodigo/haltistas/besquare/menu/images/B2scaled.png");
        setLogin();



/*

          Text ipText = new Text(2 * X_PADDING, loginText.getY() + 2 * Y_PADDING,
                "IP: " + menuInput.getInterfaceLogic().getIP());
        ipText.setColor(Color.LIGHT_GRAY);
        ipText.grow(20, 10);
        ipText.translate(loginBackground.getX() + 2 * X_PADDING, 20);
        ipText.draw();

        Text portText = new Text(2 * X_PADDING, ipText.getY() + 2 * Y_PADDING,
                "Port Nr. : " + menuInput.getInterfaceLogic().getPort());
        portText.setColor(Color.LIGHT_GRAY);
        portText.grow(20, 10);

        portText.translate(loginBackground.getX() + 2 * X_PADDING, 40);
        portText.draw();


        while (!menuInput.isFinished()) {
            ipText.setText("IP: " + menuInput.getInterfaceLogic().getIP());
            ipText.draw();
            portText.setText("Port Nr. " + menuInput.getInterfaceLogic().getPort());
            portText.draw();
        }
        portText.setText("Port Nr. " + menuInput.getInterfaceLogic().getPort());
        portText.draw();

        System.out.println("EXIT");
**/
    }

    private void setLogo(String src) {
        logo = new Picture(X_PADDING, 3 * Y_PADDING, src);
        logo.translate(.5 * (canvas.getWidth() - logo.getWidth()), 0);
        logo.draw();
    }

    private void setLogin() {

        Rectangle loginBackground = new Rectangle(logo.getX(), logo.getY() + logo.getHeight() + 2 * Y_PADDING,
                logo.getWidth(), logo.getHeight());
        loginBackground.setColor(Color.LIGHT_GRAY);
        loginBackground.draw();

        Text loginText = new Text(logo.getX(), loginBackground.getY(), "Login:");
        loginText.translate(.5 * (loginBackground.getWidth() - loginText.getWidth()), -Y_PADDING);
        loginText.setColor(Color.RED);

        Rectangle loginTextBack = new Rectangle(loginText.getX(), loginText.getY(), loginText.getWidth(),
                loginText.getHeight());
        loginTextBack.grow(5, 0);

        loginTextBack.fill();
        loginBackground.draw();
        loginText.draw();

        setInterface(loginBackground.getY() + 2 * Y_PADDING);

    }

    private void setInterface(int y) {
        Text ipText = new Text(X_PADDING, y,
                "IP: " + menuInput.getMenuController().getIP());
        System.out.println(ipText.getWidth());
        ipText.setColor(Color.LIGHT_GRAY);
        ipText.grow(25, 8);
        System.out.println(ipText.getWidth());
        ipText.translate(-ipText.getX() + logo.getX(), 0);
        ipText.translate(25, 2 * Y_PADDING);
        ipText.draw();


        Text portText = new Text(ipText.getX(), ipText.getY() + ipText.getHeight(),
                "Port Nr. : " + menuInput.getMenuController().getPort());
        portText.setColor(Color.LIGHT_GRAY);
        portText.grow(25, 8);
        portText.translate(ipText.getX() - portText.getX(), 2 * Y_PADDING);
        portText.draw();
        org.academiadecodigo.haltistas.besquare.menu.MenuController menuController = menuInput.getMenuController();
        while (!menuInput.isFinished()) {
            if (menuInput.isSetPort()) {
                portText.setColor(Color.RED);
                ipText.setColor(Color.LIGHT_GRAY);
            } else {
                ipText.setColor(Color.RED);
                portText.setColor(Color.LIGHT_GRAY);
            }
            ipText.setText("IP: " + menuInput.getMenuController().getIP());
            ipText.draw();
            portText.setText("Port Nr. " + menuInput.getMenuController().getPort());
            portText.draw();
        }


    }


}




