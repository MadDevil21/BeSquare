package org.academiadecodigo.haltistas.besquare.client.event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public interface Event {

    void process(String[] data, GameField field);

}
