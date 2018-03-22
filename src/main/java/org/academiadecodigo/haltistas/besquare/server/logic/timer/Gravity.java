package org.academiadecodigo.haltistas.besquare.server.logic.timer;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.logic.Game;
import org.academiadecodigo.haltistas.besquare.server.logic.helpers.FallHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

public class Gravity extends TimerTask {

    private final List<PlayerCharacter> players;
    private Block[][] blocks;
    private boolean acting;
    private Game game;

    public Gravity() {
        acting = true;
        players = new ArrayList<>(2);
    }

    public void addPlayer(PlayerCharacter playerCharacter) {

        if (players.size() >= 2) {
            return;
        }

        players.add(playerCharacter);
    }

    @Override
    public void run() {

        synchronized (this) {
            if (!acting) {
                this.cancel();
            }
        }

        if (blocks == null) {
            return;
        }

        int[] toReturn = new int[4];

        int coordinate = 0;
        for (int playerNumber = 0; playerNumber < players.size(); playerNumber++) {
            PlayerCharacter player = players.get(playerNumber);
            FallHelper.processFall(player, blocks);
            toReturn[coordinate] = player.getCol();
            toReturn[coordinate+ 1] = player.getRow();
            coordinate += 2;
        }

        game.processThisShit(toReturn);

    }

    public synchronized void hovering() {
        this.acting = false;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }
}
