package com.example.yokaigameisep.components;

import com.example.yokaigameisep.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.util.Callback;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameComponent extends GridPane {

    private PlateauComponent boardCmp;
    private SideDeckComponent sideCmp;
    private Button goBtn;
    private Button FinishGameBtn;

    private Label indication;
    private Label playerTurnLabel;
    private Label title;

    private Board board;
    private SideDeck sideDeck;


    private ArrayList<String> playerList;


    public GameComponent(ArrayList<String> playerName){

        this.setId("gameScreen");

        playerList = new ArrayList<>(playerName);

        //Board
        board = new Board();
        board.Initialize_Board();

        //Side Deck
        sideDeck = new SideDeck();


        //Interface
        boardCmp = new PlateauComponent(board);
        goBtn = new Button("Continuer");
        FinishGameBtn = new Button("Terminer la partie");
        indication = new Label("Retourner deux Cartes");
        playerTurnLabel = new Label(playerList.get(0));
        sideCmp = new SideDeckComponent(sideDeck);
        title = new Label("Yokai Game");

        styliseInterface();


        goBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Button n = (Button) e.getSource();


                if (boardCmp.getCurrentState() == Constant.WAITING_TO_TURN && boardCmp.getFinishedTask() ){
                    indication.setText("Deplacer une carte");
                    boardCmp.loadMoveState();
                }
            }
        });

        FinishGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });


        boardCmp.newTurnCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void unused) {
                indication.setText("Retourner deux Cartes");
                playerList.add(playerList.remove(0));
                playerTurnLabel.setText(playerList.get(0));



                sideCmp.reloadState();

                //WriteObjectToFile(board);

                return null;
            }
        };

        boardCmp.moveDoneCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void unused) {
                indication.setText("Retourner/déplacer une TipCard");
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
        this.add(title, 1, 1, 1, 1);
        this.add(indication, 2, 1, 1,1);
        this.add(playerTurnLabel, 4, 1);

        this.add(boardCmp, 2, 3);
        this.add(sideCmp, 3, 3);

        this.add(goBtn, 4, 4);
        this.add(FinishGameBtn, 2, 4);

    }

    private void styliseInterface(){


        title.setId("gameTitle");
        indication.setId("gameindication");
        playerTurnLabel.setId("gameplayerTurn");

        this.setHalignment(sideCmp, HPos.RIGHT);

        this.setVgap(5);
    }

    public void WriteObjectToFile(Object serObj) {

        try {

            File yourFile = new File("yokai.sav");
            yourFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream fileOut = new FileOutputStream("yokai.sav");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            System.out.println("llll");

            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object ReadObjectFromFile(String filepath) {

        try {


            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
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

        if(sideDeck.isEmpty()){ // s'il reste des cartes dans la pioche
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
        if((sideDeck.isEmpty() & sideDeck.getTipPrepared()==null)){
            // may be 0 instead of null gotta check
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
