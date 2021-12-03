package com.example.yokaigameisep.components;


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
    public final int LENGTH = 12;



    public PlateauComponent(){
        ObservableList list = this.getChildren();

        initPlateau();

        list.add(g);

    }

    public void initPlateau(){
        g = new GridPane();
        b_list = new Button[LENGTH][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {

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
