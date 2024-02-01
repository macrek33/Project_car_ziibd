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
import java.util.Collection;

public class FormularzDodawania {

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




    @FXML
    private Label paliwoBlad;




    public void setRoot(Parent root) {
        this.root = root;
    }


    public void setController(MainController main) {
        this.main = main;
    }

    private Parent root;
    private Stage stage;
    private Scene scena;
    private FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("MainWindow.fxml"));
    private MainController main;



    public void dodajWiersz()throws IOException
    {

            boolean sprawdz = true;
            nrVinBlad.setText("");
            modelBlad.setText("");
            markaBlad.setText("");
            rokProdukcjiBlad.setText("");
            silnikBlad.setText("");
            paliwoBlad.setText("");
            Auto auto = new Auto();
            try {
                auto.setNrVin(nrVin.getText());
            } catch (IllegalArgumentException exc) {
                nrVinBlad.setText(exc.getMessage());
                sprawdz = false;
            } catch (NullPointerException exc) {
                nrVinBlad.setText(exc.getMessage());
                sprawdz = false;
            }
            try {
                auto.setMarka(marka.getText());
            } catch (IllegalArgumentException exc) {
                markaBlad.setText(exc.getMessage());
                sprawdz = false;
            } catch (NullPointerException exc) {
                markaBlad.setText("Proszę podać prawidłowe dane!");
                sprawdz = false;
            }
            try {
                auto.setModel(model.getText());
            } catch (IllegalArgumentException exc) {
                modelBlad.setText(exc.getMessage());
                sprawdz = false;
            } catch (NullPointerException exc) {
                modelBlad.setText("Proszę podać prawidłowe dane!");
                sprawdz = false;
            }
            try {
                auto.setRokProdukcji(Integer.parseInt(rokProdukcji.getText()));
            } catch (IllegalArgumentException exc) {
                rokProdukcjiBlad.setText("proszę podać prawidłowy rok!");
                sprawdz = false;
            } catch (NullPointerException exc) {
                rokProdukcjiBlad.setText("Proszę podać prawidłowe dane!");
                sprawdz = false;
            }
            catch(Exception e )
            {
                rokProdukcjiBlad.setText("proszę podać prawidłowy rok!");
            }
            try {
                auto.setSilnik(silnik.getText());
            } catch (IllegalArgumentException exc) {
                silnikBlad.setText(exc.getMessage());
                sprawdz = false;
            } catch (NullPointerException exc) {
                silnikBlad.setText("Proszę podać prawidłowe dane!");
                sprawdz = false;
            }
            try {
                auto.setPaliwo(paliwo.getText());
            } catch (IllegalArgumentException exc) {
                paliwoBlad.setText(exc.getMessage());
                sprawdz = false;
            } catch (NullPointerException exc) {
                paliwoBlad.setText(exc.getMessage());
                sprawdz = false;
            }

            if(sprawdz)
            {
                if(root==null) {
                    root = loader.load();
                    main = loader.getController();
                }
                try {
                    main.getDb().addRow(auto);

                    komunikat.setText("Dodanie powiodło się");
                }
                catch(UnsupportedOperationException exc)
                {
                    komunikat.setText(exc.getMessage());
                }
            }
            else
            {
                komunikat.setText("Popraw błędy!");
            }





    }
    public void powrotDoProgramu(ActionEvent e) throws IOException
    {


        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        if(root==null) {
            root = loader.load();
            main = loader.getController();

        }

        scena = new Scene(root);

        main.setList();
        stage.setScene(scena);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(true);
        stage.show();

    }


}
