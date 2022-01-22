package com.example.yokaigameisep.control;

import com.example.yokaigameisep.components.GameComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChoosePlayer {


    @FXML
    private ComboBox<String> input_nb_joueur;

    @FXML
    private TextField input_1;

    @FXML
    private TextField input_2;

    @FXML
    private TextField input_3;

    @FXML
    private TextField input_4;


    @FXML
    private void comboAction(ActionEvent event) {
        switch ( input_nb_joueur.getValue() ){
            case "2":
                input_3.setDisable(true);
                input_4.setDisable(true);
                break;
            case "3":
                input_3.setDisable(false);
                input_4.setDisable(true);
                break;
            case "4":
                input_3.setDisable(false);
                input_4.setDisable(false);
                break;
        }
    }


    @FXML
    private void launchGame(ActionEvent event) {

        ArrayList playerName = new ArrayList<>();
        playerName.add(input_1.getText());
        playerName.add(input_2.getText());
        if (!input_3.isDisabled())
        {
            playerName.add(input_3.getText());
            if (!input_4.isDisabled())
                playerName.add(input_4.getText());

        }

        System.out.println(playerName.toString());

        GridPane g = new GameComponent(playerName);

        Scene scene = new Scene(g, 1200, 920);
        scene.getStylesheets().add(com.example.yokaigameisep.MainYokai.class.getResource("style.css").toExternalForm());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.setMaximized(true);
        stage.show();
    }
}
