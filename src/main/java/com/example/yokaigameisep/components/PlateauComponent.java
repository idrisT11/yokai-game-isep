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

    private Board board;




    public PlateauComponent(){
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

                b_list[i][j].setPrefWidth(60);
                b_list[i][j].setPrefHeight(60);

                b_list[i][j].setMinWidth(60);
                b_list[i][j].setMinHeight(60);

                b_list[i][j].setStyle("-fx-background-color: white");

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
