package com.example.yokaigameisep.components;


import com.example.yokaigameisep.SideDeck;
import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.TipCard;


public class TipCardButtonComponent extends CardButtonComponent {

    final static String ID_ = "BTN_DECK";
    final static String ID_A_DOS = "BTN_DECK_A_DOS";
    final static String ID_EMPTY = "BTN_DECK_VOID";
    final static String ID_BLUE = "BTN_DECK_BLUE";
    final static String ID_RED = "BTN_DECK_RED";
    final static String ID_GREEN = "BTN_DECK_GREEN";
    final static String ID_PURPLE = "BTN_DECK_PURPLE";


    private TipCard card;


    //Ici a dos fait reference à la carte speciale de la pioche, dans ce cas on mettera card à null
    public TipCardButtonComponent(TipCard card, boolean a_dos){
        super();

        this.setPrefWidth(80);
        this.setPrefHeight(80);

        this.setMinWidth(50);
        this.setMinHeight(50);

        this.setCard(card, a_dos);

    }

    public void setCard(TipCard card, boolean a_dos){
        this.card = card;

        if (a_dos){
            this.setId(ID_A_DOS);
            return;
        }


        if (card == null || card.isPrepared())
            this.setId(ID_EMPTY);


        else
        {
            if (card.getNbColors() == 1){
                String currentID = ID_ + chooseColor(card.getColorValue1());

                this.setId(currentID);
            }


            else if(card.getNbColors() == 2){
                String currentID = ID_;
                String a1 = chooseColor(card.getColorValue1());
                String a2 = chooseColor(card.getColorValue2());

                if (a1 == "_BLUE") currentID += a1;
                else if (a2 == "_BLUE") currentID += a2;

                if (a1 == "_GREEN") currentID += a1;
                else if (a2 == "_GREEN") currentID += a2;

                if (a1 == "_PURPLE") currentID += a1;
                else if (a2 == "_PURPLE") currentID += a2;

                if (a1 == "_RED") currentID += a1;
                else if (a2 == "_RED") currentID += a2;



                this.setId(currentID);
            }


            else if(card.getNbColors() == 3){
                String currentID = ID_;
                String a1 = chooseColor(card.getColorValue1());
                String a2 = chooseColor(card.getColorValue2());
                String a3 = chooseColor(card.getColorValue3());

                if (a1 == "_BLUE") currentID += a1;
                else if (a2 == "_BLUE") currentID += a2;
                else if (a2 == "_BLUE") currentID += a2;

                if (a1 == "_GREEN") currentID += a1;
                else if (a2 == "_GREEN") currentID += a2;
                else if (a3 == "_GREEN") currentID += a3;


                if (a1 == "_PURPLE") currentID += a1;
                else if (a2 == "_PURPLE") currentID += a2;
                else if (a3 == "_PURPLE") currentID += a3;

                if (a1 == "_RED") currentID += a1;
                else if (a2 == "_RED") currentID += a2;
                else if (a3 == "_RED") currentID += a3;

                this.setId(currentID);
            }

        }
    }

    private String chooseColor(int v){
        String value = "";
        switch (v){
            case Constant.COLOR_BLUE:
                value = "_BLUE";
                break;
            case Constant.COLOR_RED:
                value = "_RED";
                break;
            case Constant.COLOR_GREEN:
                value = "_GREEN";
                break;
            case Constant.COLOR_PURPLE:
                value = "_PURPLE";
                break;
        }

        return value;
    }

    public TipCard getCard() {
        return card;
    }
}
