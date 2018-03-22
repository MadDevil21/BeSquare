package org.academiadecodigo.haltistas.besquare.client.Event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public interface Event {

    void process(String[] data, GameField field);

}
