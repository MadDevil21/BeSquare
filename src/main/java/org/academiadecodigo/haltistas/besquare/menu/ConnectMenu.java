package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.Initializer;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class ConnectMenu extends AbstractMenu {

    public static final int MAX_DIGITS_IP = 15;
    public static final int MAX_DIGITS_PORT = 5;

    private String ip;
    private String port;
    private int index;
    private int movePointer;
    private Text ipText;
    private Text portText;
    private boolean ipAddress;

    private Initializer initializer;

    public void init(int movePointer) {

        Initializer.gameState = GameState.CONNECT_MENU;
        this.movePointer = movePointer;
        ip = "";
        port = "";
        createBackground();
        createConnectOptions();
        index = 0;
        ipAddress = false;
    }

    private void requestIP() {

        createOption(200, "IP: ");
        ipText = new Text(optionsXPos() + 50, 200, ip, font);
        ipText.draw();
    }

    private void requestPort() {

        createOption(250, "PORT: ");
        portText = new Text(optionsXPos() + 50, 250, port, font);
        portText.draw();
    }

    private void createConnectOptions() {

        requestIP();
        requestPort();
    }

    public void insertIP(char c) {

        if (ip.length() == MAX_DIGITS_IP) {
            return;
        }

        if (index == 3) {

            ip += ".";
            index = 0;
        }

        index++;

        ip += c;
        ipText.setColor(Color.WHITE);
        ipText.setText(ip);
    }

    public void insertPort(char c) {

        if (port.length() == MAX_DIGITS_PORT) {
            return;
        }

        port += c;
        portText.setText(port);
        portText.setColor(Color.WHITE);
    }

    public void hostServer() {

        ipAddress = true;
        initializer.hostServer(20021);
    }

    public void joinGame() {
        initializer.joinGame("192.168.1.29", 20021);
    }

    public void hostGame() {
        initializer.hostServer(20021);
        initializer.joinGame("192.168.1.29", 20021);
    }

    public int getMovePointer() {
        return movePointer;
    }

    public void setInitializer(Initializer initializer) {
        this.initializer = initializer;
    }

    public boolean isIpAddress() {
        return ipAddress;
    }

    public boolean getIp() {
        return !ip.equals("");
    }
}
