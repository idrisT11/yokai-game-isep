package com.example.yokaigameisep.components;

import com.example.yokaigameisep.Board;
import com.example.yokaigameisep.SideDeck;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameComponent extends GridPane {

    private Group boardCmp;
    private Group sideCmp;
    private Button goBtn;

    private Board board;
    private SideDeck sideDeck;

    public GameComponent(){
        //Board
        board = new Board();
        board.Initialize_Board();

        //Side Deck
        sideDeck = new SideDeck();

        boardCmp = new PlateauComponent(board);
        goBtn = new Button("GO");
        sideCmp = new SideDeckComponent(sideDeck);

        ObservableList list = this.getChildren();
        this.add(boardCmp, 1, 1);
        this.add(goBtn, 2, 1);
        this.add(sideCmp, 3, 1);

    }
}
