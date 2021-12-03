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




    public PlateauComponent(Board board){

        this.board = board;

        ObservableList list = this.getChildren();

        initPlateau();

        list.add(g);

    }

    public void initPlateau(){
        g = new GridPane();
        b_list = new Button[Constant.LENGTH_MAIN_GRID][Constant.LENGTH_MAIN_GRID];

        for (int i = 0; i < Constant.LENGTH_MAIN_GRID; i++) {
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {

                b_list[i][j] = new Button("zzz");

                b_list[i][j].setUserData(board.getCase(i, j).toString());

                b_list[i][j].setPrefWidth(60);
                b_list[i][j].setPrefHeight(60);

                b_list[i][j].setMinWidth(60);
                b_list[i][j].setMinHeight(60);

                if (board.getCase(i, j) != null)
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

                b_list[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Node n = (Node) e.getSource();
                        n.setStyle("-fx-background-color: black");


                    }
                });

                g.add(b_list[i][j], i, j);
            }
        }

    }


}
