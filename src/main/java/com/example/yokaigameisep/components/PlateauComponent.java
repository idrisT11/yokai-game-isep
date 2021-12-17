package com.example.yokaigameisep.components;


import com.example.yokaigameisep.Board;
import com.example.yokaigameisep.Constant;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;


public class PlateauComponent extends Group {
    private GridPane g;
    Button[][] b_list;

    //===================================
    private Board board;


    //===================================
    private boolean returnCart;
    private boolean Cart;
    private boolean chooseCart;




    public PlateauComponent(Board board){

        this.board = board;

        ObservableList list = this.getChildren();

        initPlateau();

        list.add(g);

    }

    public void initPlateau(){
        g = new GridPane();
        g.setVgap(10);
        g.setHgap(10);

        b_list = new Button[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];

        for (int i = 0; i < Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {

                b_list[i][j] = new Button("");

                b_list[i][j].setUserData(board.getCase(5, 3).toString());

                b_list[i][j].setPrefWidth(60);
                b_list[i][j].setPrefHeight(60);

                b_list[i][j].setMinWidth(60);
                b_list[i][j].setMinHeight(60);

                if (board.getCase(i, j) == null)
                    b_list[i][j].setStyle("-fx-background-color: white");
                else{
                    if (board.getCase(i, j).getColor() == Constant.COLOR_BLUE){
                        b_list[i][j].setStyle("-fx-background-color: blue");
                    }
                    else if (board.getCase(i, j).getColor() == Constant.COLOR_RED){
                        b_list[i][j].setStyle("-fx-background-color: red");
                    }
                    else if (board.getCase(i, j).getColor() == Constant.COLOR_PURPLE){
                        b_list[i][j].setStyle("-fx-background-color: purple");
                    }
                    else if (board.getCase(i, j).getColor() == Constant.COLOR_GREEN){
                        b_list[i][j].setStyle("-fx-background-color: green");
                    }
                }

                int finalI = i;
                int finalJ = j;
                b_list[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Node n = (Node) e.getSource();
                        System.out.println(board.isPlayerMoveGood(finalI, finalJ));
                        n.setStyle("fx-background-image: url(\"cartes_individuelles/carte_bleue.pdf\")");
                        for (int k = 0; k < Constant.LENGTH_MAIN_GRID; k++) {
                            for (int l = 0; l < Constant.LENGTH_MAIN_GRID; l++) {
                                if ( Board.IsIndiceMoveGood(board, k, l) )
                                {
                                    b_list[k][l].setStyle("-fx-background-color: #33AACC");
                                    b_list[k][l].setOpacity(0.5);
                                }

                            }
                        }

                    }
                });

                g.add(b_list[i][j], i, j);
            }
        }

    }


}
