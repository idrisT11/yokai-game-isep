package com.example.yokaigameisep.components;

import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.SideDeck;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SideDeckComponent extends Group {

    private GridPane g;
    TipCardButtonComponent[] b_list;

    SideDeck sideDeck;

    public SideDeckComponent(SideDeck sideDeck){
        this.sideDeck = sideDeck;

        ObservableList list = this.getChildren();

        initDeck();

        list.add(g);

    }

    public void initDeck(){
        g = new GridPane();
        g.setVgap(10);
        g.setHgap(10);

        b_list = new TipCardButtonComponent[8];

        b_list[0] = new TipCardButtonComponent(null, true);
        g.add(b_list[0], 1, 1);
        for (int i = 1; i <= 7; i++) {

            sideDeck.revealCard();
            System.out.println(sideDeck.getTipPrepared().get(i - 1));
            b_list[i] = new TipCardButtonComponent(sideDeck.getTipPrepared().get(i - 1), false);

            g.add(b_list[i], 1, 1+i);

        }


    }
}
