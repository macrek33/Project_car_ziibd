package com.example.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Logowanie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Komis Samochodowy");
        stage.getIcons().add(new Image(HelloController.class.getResourceAsStream("Obrazy/auto.png")));

        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}