package com.example.yokaigameisep;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainYokai extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainYokai.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader_menu = new FXMLLoader(MainYokai.class.getResource("Menu.fxml"));
        FXMLLoader fxmlLoader_choose = new FXMLLoader(MainYokai.class.getResource("ChoosePlayer.fxml"));
        //=========================================






        //=======================================
        //GridPane g = new GameComponent();
        Scene scene = new Scene(fxmlLoader_menu.load());
        //Scene scene = new Scene(fxmlLoader_choose.load());
        //Scene scene = new Scene(g);

        scene.getStylesheets().add(getClass().getResource("non_game_style.css").toExternalForm());
        //AudioClip b = new AudioClip();


        stage.setTitle("Yokai");
        stage.setScene(scene);
        //stage.setResizable(false);
        //stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
