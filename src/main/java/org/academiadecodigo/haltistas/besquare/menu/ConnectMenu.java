package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.Launcher;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectMenu extends AbstractMenu {

    public static final int MAX_DIGITS_IP = 15;
    public static final int MAX_DIGITS_PORT = 5;

    private String ip;
    private String port;
    private int index;
    private int movePointer;
    private Text ipText;
    private Text portText;

    private Launcher launcher;

    public void init(int movePointer) {

        Launcher.gameState = GameState.CONNECT_MENU;
        this.movePointer = movePointer;
        ip = "";
        port = "";
        createBackground();
        createConnectOptions();
        index = 0;
    }

    private void requestIP() {

        createOption(200, "IP: ");
        ipText = new Text(optionsXPos() + 50, 200, ip, font);
        ipText.draw();
    }

    private void requestPort() {

        createOption(250, "PORT: ");
        portText = new Text(optionsXPos() + 100, 250, port, font);
        portText.draw();
    }

    protected void createConnectOptions() {

        requestIP();
        if (movePointer == 0) {
            fillIp();
        }
        requestPort();
    }

    public void insertIP(char c) {

        if (validIP(ip)) {

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

    private boolean validIP(String ip) {

        System.out.println("IP " + ip);

        Pattern pattern = Pattern.compile("^(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.(\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))$");
        Matcher matcher = pattern.matcher(ip);

        System.out.println("MATCHES " + matcher.matches());

        return matcher.matches();
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
        launcher.hostServer(Integer.parseInt(port));
        deleteAssets();
    }

    public void joinGame() {

        launcher.joinGame(ip, Integer.parseInt(port));
        deleteAssets();
    }

    public int getMovePointer() {
        return movePointer;
    }

    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    private void deleteAssets() {

        deleteBackground();
        ipText.delete();
        portText.delete();
    }

    public void ipErase() {
        if (ip.equals("")){
            return;
        }
        ip = ip.substring(0, ip.length() -1);
        ipText.setText(ip);
    }

    public void portErase() {
        if (port.equals("")){
            return;
        }
        port = port.substring(0, port.length() -1);
        portText.setText(port);
    }


    public void fillIp() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            ipText.setText(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public boolean hasIp() {
        return validIP(ip);
    }
}
