package com.example.yokaigameisep.components;


import com.example.yokaigameisep.Board;
import com.example.yokaigameisep.CaseBoard;
import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.TipCard;
import com.sun.javafx.scene.control.skin.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Callback;


public class PlateauComponent extends Group {
    private GridPane g;
    BoardCardButtonComponent[][] b_list;

    //===================================
    private Board board;


    //===================================
    private int currentState;
    private boolean finishedTask;

    private boolean firstCardTurned;
    private boolean secondCardTurned;

    private boolean cardSelected;

    private boolean tipCardSelected;

    private int[] selectedCardPos;
    private TipCard selectedTipCard;

    //Callback
    public Callback<Void, Void> moveDoneCallback;
    public Callback<Void, Void> newTurnCallback;


    public PlateauComponent(Board board){

        currentState = Constant.WAITING_TO_TURN;
        firstCardTurned = false;
        secondCardTurned = false;
        cardSelected = false;
        selectedCardPos = null;
        tipCardSelected = false;
        selectedTipCard = null;

        this.board = board;

        ObservableList list = this.getChildren();

        initPlateau();

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(g);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        list.add(scroll);

    }

    public void initPlateau(){

        g = new GridPane();
        g.setVgap(10);
        g.setHgap(10);

        b_list = new BoardCardButtonComponent[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];



        for (int i = 0; i < Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {

                b_list[i][j] = new BoardCardButtonComponent(board.getCase(i, j));

                b_list[i][j].setUserData(board.getCase(5, 3).toString());


                int finalI = i;
                int finalJ = j;
                b_list[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        BoardCardButtonComponent n = (BoardCardButtonComponent) e.getSource();

                        if( currentState == Constant.WAITING_TO_TURN ){

                            //if the user have clicked on an invalid card
                            if (!board.isCardTurnable(finalI, finalJ))
                                return;

                            if (!firstCardTurned){
                                n.showCard();
                                firstCardTurned = true;
                            }
                            else if (firstCardTurned && !secondCardTurned){
                                n.showCard();
                                secondCardTurned = true;

                                finishedTask = true;
                            }
                        }
                        if (  currentState == Constant.WAITING_TO_MOVE  ) {

                            if (cardSelected && board.IsIndiceMoveGood(finalI, finalJ, selectedCardPos[0], selectedCardPos[1]))
                            {
                                moveCard(finalI, finalJ);
                                cardSelected = false;

                                //The action have been complited, we can return and change the state
                                currentState = Constant.WAITING_TO_TIP;
                                moveDoneCallback.call(null);
                                return;
                            }
                            else if (board.isCardSelectable(finalI, finalJ))
                            {
                                cardSelected = true;
                                selectedCardPos = new int[]{finalI, finalJ};

                                printPossibleMoves();
                            }
                            else
                            {
                                cardSelected = false;
                                clearPossibleMoves();
                            }
                        }

                        if( currentState == Constant.WAITING_TO_TIP ) {

                            if (tipCardSelected && board.isTipMoveGood(finalI, finalJ)){

                                board.setCardOnTop(selectedTipCard, finalI, finalJ);
                                b_list[finalI][finalJ].setCard( board.getCase(finalI, finalJ) );

                                //The turn have been complited, we can return and reload the state
                                reloadState();
                                return;
                            }

                        }

                    }
                });

                g.add(b_list[i][j], i, j);
            }
        }

    }

    public void loadMoveState(){
        finishedTask = false;
        currentState = Constant.WAITING_TO_MOVE;

        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if(board.getCase(k, l).getColor() != Constant.VOID_CASE) {
                    b_list[k][l].setADos();
                    b_list[k][l].setOpacity(1);
                }
            }
        }
    }

    private void clearPossibleMoves(){
        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if ( board.getCase(k, l).getColor() == Constant.VOID_CASE )
                {
                    b_list[k][l].setStyle("-fx-background-color: white");
                    //b_list[k][l].setOpacity(0.5);
                }

            }
        }
    }

    private void printPossibleMoves() {
        CaseBoard selectedCase = board.getCase(selectedCardPos[0], selectedCardPos[1]);

        int selectedColor = selectedCase.getColor();

        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if ( board.IsIndiceMoveGood(k, l, selectedCardPos[0], selectedCardPos[1]) )
                {
                    b_list[k][l].setStyle("-fx-background-color: #33AACC");
                    b_list[k][l].setOpacity(0.5);
                }

            }
        }

        //selectedCase.setColor(selectedColor);

    }
    private void moveCard(int finalI, int finalJ) {
        CaseBoard selectedCase = board.getCase(selectedCardPos[0], selectedCardPos[1]);

        //int selectedColor = selectedCase.getColor();

        board.makeMove(selectedCardPos[0], selectedCardPos[1], finalI, finalJ);
        //update Screen
        b_list[finalI][finalJ].setCard(selectedCase);
        b_list[selectedCardPos[0]][selectedCardPos[1]].setCard( CaseBoard.NullCard() );

        clearPossibleMoves();

        board.printBoard();

    }

    //GETTER__SETTER---------------------------------------------
    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void reloadState(){
        currentState = Constant.WAITING_TO_TURN;
        firstCardTurned = false;
        secondCardTurned = false;
        cardSelected = false;
        selectedCardPos = null;
        tipCardSelected = false;
        selectedTipCard = null;

        newTurnCallback.call(null);
    }

    public void setTipCard(TipCard card){
        if (card == null)
        {
            tipCardSelected = false;
        }
        else
        {
            tipCardSelected = true;
            selectedTipCard = card;
        }
    }

    public boolean getFinishedTask() {
        return finishedTask;
    }


}
