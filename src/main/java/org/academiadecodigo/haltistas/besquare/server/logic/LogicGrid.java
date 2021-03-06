package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;
import org.academiadecodigo.haltistas.besquare.server.logic.helpers.CollisionHelper;
import org.academiadecodigo.haltistas.besquare.server.logic.helpers.FallHelper;
import org.academiadecodigo.haltistas.besquare.server.logic.timer.ServerGravity;

import java.io.IOException;
import java.util.*;

public class LogicGrid {

    public static final int COLS = 32;
    public static final int ROWS = 20;
    private static final long FRAME_RATE = 60;

    private PlayerCharacter player1;
    private PlayerCharacter player2;

    private Block[][] grid;
    private Exit exit;
    private Map<Integer, Token> tokenMap;
    private Map<KeyColor, Button> buttonMap;
    private Map<KeyColor, Door> doorMap;
    private boolean win;
    private Timer timer;


    LogicGrid() {

        grid = new Block[COLS][ROWS];
        player1 = new PlayerCharacter(COLS, ROWS, 1);
        player2 = new PlayerCharacter(COLS, ROWS, 2);
        tokenMap = new HashMap<>();
        buttonMap = new HashMap<>();
        doorMap = new HashMap<>();
        timer = new Timer();

    }

    public void load(Levels currentLevel) throws IOException {
        resetInteractive();
        grid = LogicGridLoader.loadLevel(currentLevel, this);
        exit.setActive();
    }

    public int[] verifyAction(int playerId, Action selectedAction) {

        player1.setFallen(false);
        player2.setFallen(false);

        PlayerCharacter movingPlayer = fetchById(playerId);

        synchronized (this) {

            checkJump(movingPlayer, selectedAction);

            int destinationCol = movingPlayer.getCol() + selectedAction.getColChange();
            int destinationRow = movingPlayer.getRow() + selectedAction.getRowChange();

            Block destinationBlock = grid[destinationCol][destinationRow];

            CollisionHelper.processCollision(movingPlayer, destinationBlock, grid);

            movingPlayer.setJumping(false);

            win = CollisionHelper.checkWin(this);

            return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

        }

    }

    private void checkJump(PlayerCharacter movingPlayer, Action selectedAction) {

        if (selectedAction == Action.JUMP_LEFT || selectedAction == Action.JUMP_RIGHT) {
            movingPlayer.setJumping(true);
        }
    }

    public boolean levelWon() {
        return win;
    }

    public void addTokenToMap(Token token) {
        int size = tokenMap.size();
        tokenMap.put(size, token);

    }

    public PlayerCharacter fetchById(int id) {
        PlayerCharacter pickedCharacter;

        if (id == player1.getId()) {
            pickedCharacter = player1;

        } else {
            pickedCharacter = player2;
        }
        return pickedCharacter;

    }

    private void checkDoors(KeyColor color){
        for (KeyColor keyColor : doorMap.keySet()) {
            if (keyColor.equals(color)) {
                doorMap.get(color).open();

            }
        }
    }

    public Map<Integer, Token> getTokenMap() {
        return tokenMap;
    }

    public void removeToken(Integer index) {
        tokenMap.remove(index);

    }

    public void addInteractiveMap(Button button) {
        buttonMap.put(button.getColor(), button);

    }

    public void addMovingMap(Door platform){
        doorMap.put(platform.getColor(), platform);

    }

    public Map<KeyColor, Door> getDoorMap() {
        return doorMap;
    }

    public Map<KeyColor, Button> getButtonMap() {
        return buttonMap;
    }

    private void resetInteractive(){
        buttonMap.clear();
        doorMap.clear();

    }

    public PlayerCharacter getPlayer1() {
        return player1;
    }

    public PlayerCharacter getPlayer2() {
        return player2;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public boolean anyPlayerIsFalling() {
        return player1.hasFallen() || player2.hasFallen();
    }

    public void setupGravity(ServerGravity gravity) {
        gravity.setBlocks(grid);
        timer.scheduleAtFixedRate(gravity, 0, FRAME_RATE);
        gravity.addPlayer(player1);
        gravity.addPlayer(player2);

    }

    public Timer getTimer() {
        return timer;
    }
}
