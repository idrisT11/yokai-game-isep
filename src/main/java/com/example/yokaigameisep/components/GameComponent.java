package com.example.yokaigameisep.components;

import com.example.yokaigameisep.Board;
import com.example.yokaigameisep.Constant;
import com.example.yokaigameisep.SideDeck;
import com.example.yokaigameisep.TipCard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javafx.util.Callback;



public class GameComponent extends GridPane {

    private PlateauComponent boardCmp;
    private SideDeckComponent sideCmp;
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

        goBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Button n = (Button) e.getSource();

                n.setStyle("-fx-background-color: #AAA");

                if (boardCmp.getCurrentState() == Constant.WAITING_TO_TURN && boardCmp.getFinishedTask() ){
                    boardCmp.loadMoveState();
                }
            }
        });

        boardCmp.newTurnCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void unused) {

                sideCmp.reloadState();

                return null;
            }
        };

        boardCmp.moveDoneCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void unused) {

                sideCmp.setInteractAcces(true);

                return null;
            }
        };

        sideCmp.selectTipCardCallback = new Callback<TipCard, Void>() {
            @Override
            public Void call(TipCard tipCard) {

                //If tipCard is at null, that means that the player had only reveal a card
                //Thus we can pass to the next turn
                if (tipCard == null)
                    boardCmp.reloadState();
                else
                    boardCmp.setTipCard(tipCard);

                return null;
            }

        };

        ObservableList list = this.getChildren();
        this.add(boardCmp, 1, 1);
        this.add(goBtn, 2, 1);
        this.add(sideCmp, 3, 1);

    }


    public void selectTipCard(){
        System.out.println("hallo");

    }
}
