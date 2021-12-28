package com.example.yokaigameisep.components;


import com.example.yokaigameisep.Board;
import com.example.yokaigameisep.CaseBoard;
import com.example.yokaigameisep.Constant;
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


public class PlateauComponent extends Group {
    private GridPane g;
    BoardCardButtonComponent[][] b_list;

    //===================================
    private Board board;


    //===================================
    private int currentState;

    private boolean firstCardTurned;
    private boolean secondCardTurned;

    private boolean cardSelected;
    private boolean Card;
    private boolean chooseCard;


    private int[] selectedCardPos;




    public PlateauComponent(Board board){

        currentState = Constant.WAITING_TO_TURN;
        firstCardTurned = false;
        secondCardTurned = false;
        cardSelected = false;
        selectedCardPos = null;

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
                            }
                        }
                        if (  currentState == Constant.WAITING_TO_MOVE  ) {
                            if (cardSelected) {
                                moveCard(finalI, finalJ);
                                //selectedCardPos = null;
                                cardSelected = false;
                            } else {
                                cardSelected = true;
                                selectedCardPos = new int[]{finalI, finalJ};

                                printPossibleMoves();
                            }
                        }

                    }
                });

                g.add(b_list[i][j], i, j);
            }
        }

    }
    private void printPossibleMoves() {
        CaseBoard selectedCase = board.getCase(selectedCardPos[0], selectedCardPos[1]);

        int selectedColor = selectedCase.getColor();
        selectedCase.setColor(Constant.VOID_CASE);

        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if ( board.IsIndiceMoveGood(board, k, l) )
                {
                    b_list[k][l].setStyle("-fx-background-color: #33AACC");
                    b_list[k][l].setOpacity(0.5);
                }

            }
        }

        selectedCase.setColor(selectedColor);

    }
    private void moveCard(int finalI, int finalJ) {
        CaseBoard selectedCase = board.getCase(selectedCardPos[0], selectedCardPos[1]);

        int selectedColor = selectedCase.getColor();

        board.makeMove(selectedCardPos[0], selectedCardPos[1], finalI, finalJ);
        //update Screen
        b_list[finalI][finalJ].setCard(selectedCase);
        b_list[selectedCardPos[0]][selectedCardPos[1]].setCard( CaseBoard.NullCard() );

        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if ( board.getCase(k, l).getColor() == Constant.VOID_CASE )
                {
                    b_list[k][l].setStyle("-fx-background-color: white");
                    //b_list[k][l].setOpacity(0.5);
                }

            }
        }

        board.printBoard();

    }
    private void handleClick(BoardCardButtonComponent button, int finalI, int finalJ){

        System.out.println(board.isPlayerMoveGood(finalI, finalJ));
        button.showCard();

        CaseBoard selectedCase = board.getCase(finalI, finalJ);
        int selectedColor = selectedCase.getColor();
        selectedCase.setColor(Constant.VOID_CASE);

        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                if ( board.IsIndiceMoveGood(board, k, l) )
                {
                    b_list[k][l].setStyle("-fx-background-color: #33AACC");
                    //b_list[k][l].setOpacity(0.5);
                }

            }
        }
        selectedCase.setColor(selectedColor);

    }


}
