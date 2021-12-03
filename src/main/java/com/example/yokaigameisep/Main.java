package com.example.yokaigameisep;


import com.example.yokaigameisep.components.PlateauComponent;
import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        //Group root = fxmlLoader.load();


        Group g = new PlateauComponent();
        //root.getChildren().add(g);

        Scene scene = new Scene(g, 1000, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
