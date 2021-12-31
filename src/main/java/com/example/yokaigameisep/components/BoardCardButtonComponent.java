package com.example.yokaigameisep.components;


import com.example.yokaigameisep.CaseBoard;
import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.TipCard;


public class BoardCardButtonComponent extends CardButtonComponent {

    final static String ID_ = "BTN_DECK";

    final static String ID_A_DOS = "BTN_BOARD_A_DOS";
    final static String ID_EMPTY = "BTN_BOARD_VOID";
    final static String ID_BLUE = "BTN_BOARD_BLUE";
    final static String ID_RED = "BTN_BOARD_RED";
    final static String ID_GREEN = "BTN_BOARD_GREEN";
    final static String ID_PURPLE = "BTN_BOARD_PURPLE";


    private CaseBoard card;
    private boolean a_dos;  //If the

    public BoardCardButtonComponent(CaseBoard card){
        super();

        this.setPrefWidth(50);
        this.setPrefHeight(50);

        this.setMinWidth(50);
        this.setMinHeight(50);

        this.setCard(card);

    }

    public void setCard(CaseBoard card){
        this.card = card;

        if (card.getColor() == Constant.VOID_CASE){
            this.setId(ID_EMPTY);
        }
        else if (card.getCardOnTop() != null){
            this.generateIdCardOnTop(card.getCardOnTop());
        }
        else{
            this.setId(ID_A_DOS);
        }
    }

    public void showCard(){
        a_dos = false;

        switch (card.getColor()){
            case Constant.COLOR_BLUE:
                this.setId(ID_BLUE);
                break;
            case Constant.COLOR_RED:
                this.setId(ID_RED);
                break;
            case Constant.COLOR_GREEN:
                this.setId(ID_GREEN);
                break;
            case Constant.COLOR_PURPLE:
                this.setId(ID_PURPLE);
                break;

            default:
                System.err.println("Error, trying to turn an empty card.");
        }
    }

    public void setADos(){
        //if the case has a card on top of it, we do not return it
        if (card.getCardOnTop() != null)
            return;

        a_dos = true;
        this.setId(ID_A_DOS);
    }

    private void generateIdCardOnTop(TipCard card){

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
}
