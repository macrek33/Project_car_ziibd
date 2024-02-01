package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FormularzEdytowania {
    @FXML
    private TextField nrVin;
    @FXML
    private TextField marka;
    @FXML
    private TextField model;
    @FXML
    private TextField rokProdukcji;
    @FXML
    private TextField silnik;
    @FXML
    private TextField paliwo;
    @FXML
    private Button powrot;
    @FXML
    private Button dodaj;


    @FXML
    private Label nrVinBlad;
    @FXML
    private Label markaBlad;
    @FXML
    private Label modelBlad;
    @FXML
    private Label rokProdukcjiBlad;
    @FXML
    private Label silnikBlad;
    @FXML
    private Label komunikat;

    public Label getNrVinBlad() {
        return nrVinBlad;
    }

    public void setNrVinBlad(Label nrVinBlad) {
        this.nrVinBlad = nrVinBlad;
    }

    public Label getMarkaBlad() {
        return markaBlad;
    }

    public void setMarkaBlad(Label markaBlad) {
        this.markaBlad = markaBlad;
    }

    public Label getModelBlad() {
        return modelBlad;
    }

    public void setModelBlad(Label modelBlad) {
        this.modelBlad = modelBlad;
    }

    public Label getRokProdukcjiBlad() {
        return rokProdukcjiBlad;
    }

    public void setRokProdukcjiBlad(Label rokProdukcjiBlad) {
        this.rokProdukcjiBlad = rokProdukcjiBlad;
    }

    public Label getSilnikBlad() {
        return silnikBlad;
    }

    public void setSilnikBlad(Label silnikBlad) {
        this.silnikBlad = silnikBlad;
    }

    public Label getPaliwoBlad() {
        return paliwoBlad;
    }

    public void setPaliwoBlad(Label paliwoBlad) {
        this.paliwoBlad = paliwoBlad;
    }

    @FXML
    private Label paliwoBlad;

    private Parent root;
    private Stage stage;
    private Scene scena;
    private FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("MainWindow.fxml"));
    private MainController main;

    public void setController(MainController main) {
        this.main = main;
    }

    public void powrotDoProgramu(ActionEvent e) throws IOException
    {

        System.out.println(main);
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        if(root==null) {
            root = loader.load();
            main = loader.getController();
        }

        scena = new Scene(root);

        main.setList();
        stage.setScene(scena);
        stage.show();

    }

}
