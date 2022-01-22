package com.example.yokaigameisep.control;

import com.example.yokaigameisep.components.GameComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    public void switchToOptions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(     com.example.yokaigameisep.MainYokai.class.getResource("ChoosePlayer.fxml")      );

        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(com.example.yokaigameisep.MainYokai.class.getResource("non_game_style.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void switchToLoadGame(ActionEvent event) throws IOException {

       /* GridPane g = new GameComponent();

        Scene scene = new Scene(g);
        scene.getStylesheets().add(com.example.yokaigameisep.MainYokai.class.getResource("style.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();*/

    }



}
