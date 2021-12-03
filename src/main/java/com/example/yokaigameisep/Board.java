package com.example.yokaigameisep;

public class Board {


    private CaseBoard[][] mainGrid;
    private boolean canMoveCard;
    private boolean canMoveTip;
    private boolean stateEndGame;
    private boolean canTurnCard;



    // Getter and Setter    ....................................................................
    public CaseBoard[][] getMainGrid() {
        return mainGrid;
    }

    public void setMainGrid(CaseBoard[][] mainGrid) {
        this.mainGrid = mainGrid;
    }

    // Methods    .............................................................................
    public isPlayerMoveGood(){

        return canMoveCard;
    }

    public isTipMoveGood(){
        return canMoveTip;
    }

    public verifyEndGame(){
        return stateEndGame;
    }

    public isCardTurnable(){
        return canTurnCard;

    }

    public makeMove(){}


}
