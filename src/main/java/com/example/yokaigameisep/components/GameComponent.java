package com.example.yokaigameisep.components;

import com.example.yokaigameisep.*;
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


    public int get_score(){
        int score = 0;
        if(isGameWon()){
            score = calc_score();
        }
        return score;
    }

    public int calc_score(){
        int score = 0;


        for (int i = 0; i <Constant.LENGTH_MAIN_GRID; i++) {    // Color Purple
            for (int j = 0; j < Constant.LENGTH_MAIN_GRID; j++) {
                if(board.getCase(i,j).getColor() != Constant.VOID_CASE && board.getCase(i,j).getCardOnTop() != null){
                    if(board.isTipEqualCard(i,j)){
                        score += 1;
                    } else {
                        score -= 1;
                    }
                }
            }
        }

        if(sideDeck.isEmpty()){ // si il reste des cartes dans la pioche
            for(int i=0; i< sideDeck.getDrawFile().size()-1; i++){
                score += 5;
            }
        }

        if(!sideDeck.isEmpty()){
            for(int i=0; i<sideDeck.getTipPrepared().size()-1; i++){
                score += 2;
            }
        }
        return score;
    }

    public boolean isGameFinished(){
        boolean isFinished = false;
        if(sideDeck.isEmpty() || playerpressedbutton){
            isFinished = true;
        }

        return isFinished;
    }

    public boolean isGameWon(){
        boolean win_game = false;
        if(board.IsColorInOneBlock()){
            win_game= true;
        }
        return win_game;
    }

}
