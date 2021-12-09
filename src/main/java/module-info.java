module com.example.yokaigameisep {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.kordamp.ikonli.javafx;
    // requires org.kordamp.bootstrapfx.core;

    opens com.example.yokaigameisep to javafx.fxml;
    exports com.example.yokaigameisep;
}