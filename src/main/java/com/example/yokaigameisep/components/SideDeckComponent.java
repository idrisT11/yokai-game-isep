package com.example.yokaigameisep.components;

import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.SideDeck;
import com.example.yokaigameisep.TipCard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javafx.util.Callback;
import java.util.ArrayList;
import java.util.List;

public class SideDeckComponent extends Group {

    private GridPane g;

    TipCardButtonComponent b_deck;
    TipCardButtonComponent[] b_list;

    SideDeck sideDeck;
    ArrayList<Boolean> cardOnTable;

    private boolean canInteract;
    private int numSelectedTipCard;
    private TipCard selectedTipCard;

    //Callbacks

    public Callback<TipCard, Void> selectTipCardCallback;


    public SideDeckComponent(SideDeck sideDeck){
        this.sideDeck = sideDeck;

        ObservableList list = this.getChildren();

        initDeck();

        list.add(g);

    }

    public void initDeck(){

        //There are no card on table at the begining
        cardOnTable = new ArrayList<Boolean>(List.of(false, false, false, false, false, false, false));
        canInteract = false;
        numSelectedTipCard = -1;

        g = new GridPane();
        g.setId("sideDeckCTN");
        g.setVgap(10);
        g.setHgap(10);


        b_list = new TipCardButtonComponent[7];
        b_deck = new TipCardButtonComponent(null, true);

        g.add(b_deck, 1, 1);

        for (int i = 0; i < 7; i++) {

            //sideDeck.revealCard();

            b_list[i] = new TipCardButtonComponent(null, false);

            g.add(b_list[i], 1, 2+i);

        }

        //
        b_deck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TipCardButtonComponent n = (TipCardButtonComponent) e.getSource();

                int emptySlot = cardOnTable.indexOf(false);

                if ( emptySlot != -1 && !sideDeck.isEmpty() && canInteract)
                {
                    sideDeck.revealCard();
                    b_list[emptySlot].setCard(sideDeck.getTipPrepared().get(emptySlot), false);

                    cardOnTable.set(emptySlot, true);

                    canInteract = false;

                    selectTipCardCallback.call(null);
                }

                if (sideDeck.isEmpty())
                {
                    n.setStyle("-fx-opacity : 0.5");
                }
            }
        });

        for (int i = 0; i < 7; i++ )
        {
            TipCardButtonComponent tipCardButtonComponent = b_list[i];

            int finalI = i;
            tipCardButtonComponent.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    TipCardButtonComponent n = (TipCardButtonComponent) e.getSource();


                    if ( n.getCard() == null || !canInteract)
                        return;

                    //if (numSelectedTipCard == -1)
                    //{
                        n.setStyle("-fx-border-color: blue");
                        numSelectedTipCard = finalI;
                        selectedTipCard = n.getCard();
                    selectTipCardCallback.call(selectedTipCard);

                    //}
                    //else
                    {
                    }

                }
            });
        }


    }

    public void reloadState(){

        //We remove the selected card from the side Deck (if we've moved one)
        if (numSelectedTipCard != -1){
            sideDeck.getTipPrepared().set(numSelectedTipCard, null);
            cardOnTable.set(numSelectedTipCard, false);
            b_list[numSelectedTipCard].setCard(null, false);
        }
        numSelectedTipCard = -1;
        selectedTipCard = null;
        canInteract = false;
    }

    public void setInteractAcces(boolean b){
        canInteract = b;
    }
}
