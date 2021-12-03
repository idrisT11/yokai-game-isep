package com.example.yokaigameisep;

import java.util.HashMap;

public class Board {

    // Attributes used .......................................................
    private CaseBoard[][] mainGrid;
    private boolean canMoveCard;
    private boolean canMoveTip;
    private boolean stateEndGame;
    private boolean canTurnCard;
    private HashMap colors_Left = new HashMap();


    // Constructor ....................................................................
    public Initialize_Board() {
        int min_init_layout = (Constant.LENGTH_MAIN_GRID/2)-1;
        int max_init_layout = (Constant.LENGTH_MAIN_GRID/2)+2;
        colors_Left.put("Rouge", 4);
        colors_Left.put("Green", 4);
        colors_Left.put("Blue", 4);
        colors_Left.put("Purple", 4);

        for(int i = min_init_layout; i<max_init_layout; i++){
            for(int j = min_init_layout; j<max_init_layout; j++){







            }
        }

        return mainGrid;
    }




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
