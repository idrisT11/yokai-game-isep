package com.example.yokaigameisep;

public class Board {


    private CaseBoard[][] mainGrid;
    private boolean canMoveCard;
    private boolean canMoveTip;
    private boolean stateEndGame;
    private boolean canTurnCard;



    // Getter and Setter    ....................................................................
    public CaseBoard[][] getMainGriD() {
        return mainGrille;
    }

    public void setMainGrille(CaseBoard[][] mainGrille) {
        this.mainGrille = mainGrille;
    }

    // Methods    .............................................................................
    public isPlayerMoveGood(){

        return canMoveCard;
    }

    public isIndiceMoveGood(){
        return canMoveIndice;
    }

    public verifyEndGame(){
        return etatEndGame;
    }

    public isCardTurnable(){
        return canReturnCard;

    }

    public makeMove(){}


}
