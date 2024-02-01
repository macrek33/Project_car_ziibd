package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;

public class HelloController {

    private Stage stage;
    private Scene scena;
    private MainController main;

    private FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("MainWindow.fxml"));
    private Image ikona =  new Image(HelloController.class.getResourceAsStream("Obrazy/auto.png"));
    @FXML
    private Button dodajWiersz;
    private Parent root;
    @FXML
    private Label status;
    @FXML
    private TextField login;
    @FXML
    private PasswordField haslo;



    @FXML
    public void logOn(ActionEvent e) throws IOException
    {

        try {

            if (root==null)
            {
                root = loader.load();
            main = loader.getController();
        }
            main.createDb(login.getText(), haslo.getText());
            main.setUsername(login.getText());
            main.getLabelStartowy().setText("Witaj "+login.getText());
            main.getDb().createSchema();

            main.setList();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scena = new Scene(root);
            stage.setScene(scena);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setFullScreen(false);
            stage.setResizable(true);
            stage.show();
        }
        catch(IllegalArgumentException exc )
        {
            status.setText("Nieprawidłowe Logowanie!");
        }








    }


}