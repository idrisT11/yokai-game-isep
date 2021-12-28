package com.example.yokaigameisep.components;


import com.example.yokaigameisep.CaseBoard;
import com.example.yokaigameisep.Constant;


public class BoardCardButtonComponent extends CardButtonComponent {

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
        a_dos = true;
        this.setId(ID_A_DOS);
    }
}
