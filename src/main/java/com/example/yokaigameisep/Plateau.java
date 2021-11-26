package com.example.yokaigameisep;
import java.util.ArrayList;

public class Plateau {


    private CasePlataud[][] mainGrille;
    private boolean canMoveCard;
    private boolean canMoveIndice;
    private boolean etatEndGame;
    private boolean canReturnCard;

    // Getter and Setter    ....................................................................
    public CasePlataud[][] getMainGrille() {
        return mainGrille;
    }

    public void setMainGrille(CasePlataud[][] mainGrille) {
        this.mainGrille = mainGrille;
    }

    // Methods    .............................................................................
    public isPlayerMoveGut(){

        return canMoveCard;
    }

    public isIndiceMoveGut(){
        return canMoveIndice;
    }

    public verifyEndGame(){
        return etatEndGame;
    }

    public isCardTurnable(){
        return canReturnCard;

    }



}
