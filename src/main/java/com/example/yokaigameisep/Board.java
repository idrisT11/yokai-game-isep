package com.example.yokaigameisep;

import java.util.HashMap;

import static com.example.yokaigameisep.Constant.*;

public class Board {

    // Attributes used .......................................................
    private CaseBoard[][] mainGrid;
    private boolean canMoveCard;
    private boolean canMoveTip;
    private boolean stateEndGame;
    private boolean canTurnCard;

    // Constructor ....................................................................
    public CaseBoard[][] Initialize_Board() {

        mainGrid = new CaseBoard[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];
        for(int i=0; i<Constant.LENGTH_MAIN_GRID;i++){
            for(int j = 0; j<Constant.LENGTH_MAIN_GRID; j++){
                mainGrid[i][j] = new CaseBoard(0,null);
                mainGrid[i][j].setCardOnTop(null);

            }
        }

        int min_init_layout = (Constant.LENGTH_MAIN_GRID/2)-2;
        int max_init_layout = (Constant.LENGTH_MAIN_GRID/2)+2;
        int Red_Left=4,Green_Left =4, Blue_Left=4, Purple_Left = 4;

        for(int i = min_init_layout; i<max_init_layout; i++){

            for(int j = min_init_layout; j<max_init_layout; j++){
                int randnumb0=0;

                do {randnumb0 = (int) (Math.random() * 4) + 1;
                }while(
                        (randnumb0 == Constant.COLOR_RED && Red_Left==0)||
                                (randnumb0==Constant.COLOR_GREEN && Green_Left==0) ||
                                (randnumb0 == Constant.COLOR_BLUE && Blue_Left==0) ||
                                (randnumb0 == Constant.COLOR_PURPLE && Purple_Left==0) );

                switch(randnumb0) {
                    case Constant.COLOR_RED:
                        mainGrid[i][j].setColor(Constant.COLOR_RED); //set case content to Red
                        Red_Left -= 1;
                        break;

                    case Constant.COLOR_GREEN:
                        mainGrid[i][j].setColor(Constant.COLOR_GREEN); //set case value
                        Green_Left -= 1;
                        break;

                    case Constant.COLOR_PURPLE:
                        mainGrid[i][j].setColor(Constant.COLOR_PURPLE); //set case value
                        Purple_Left -= 1;
                        break;

                    case Constant.COLOR_BLUE:
                        mainGrid[i][j].setColor(Constant.COLOR_BLUE); //set case value
                        Blue_Left -= 1;
                        break;
                    default:
                        // code block
                }

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
    public boolean isPlayerMoveGood(){

        return canMoveCard;
    }

    public boolean isTipMoveGood(){
        return canMoveTip;
    }

    public boolean verifyEndGame(){
        return stateEndGame;
    }

    public boolean isCardTurnable(){
        return canTurnCard;
    }

    public void makeMove(){

    }

    public CaseBoard getCase(int x, int y){

        return mainGrid[x][y];
    }

    public void printBoard(){
        for (int i = 0; i <Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {
                switch(mainGrid[i][j].getColor()) {
                    case Constant.COLOR_RED:
                        System.out.print("1");
                        break;

                    case Constant.COLOR_GREEN:
                        System.out.print("2");
                        break;

                    case Constant.COLOR_PURPLE:
                        System.out.print("3");
                        break;

                    case Constant.COLOR_BLUE:
                        System.out.print("4");
                        break;
                    default:
                        System.out.print("*");
                }
            }
            System.out.println("");
        }
    }

}
