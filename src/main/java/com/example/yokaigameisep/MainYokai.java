package com.example.yokaigameisep;


import com.example.yokaigameisep.components.GameComponent;
import com.example.yokaigameisep.components.PlateauComponent;
import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

        import java.io.IOException;

public class MainYokai extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainYokai.class.getResource("hello-view.fxml"));
        //=========================================






        //=======================================
        GridPane g = new GameComponent();
        Scene scene = new Scene(g );

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
