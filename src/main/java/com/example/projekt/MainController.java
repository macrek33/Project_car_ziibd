package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {
    public Label getLabelStartowy() {
        return labelStartowy;
    }
    @FXML
    private Label komunikat;

    public TableView<Auto> getTabela() {
        return tabela;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private static String   username;
    @FXML
    private TableView<Auto> tabela;
    @FXML
    private TableColumn<Auto, String> nrVin;
    @FXML
    private TableColumn<Auto, String> marka;
    @FXML
    private TableColumn<Auto, String> model;
    @FXML
    private TableColumn<Auto, Integer> rokProdukcji;
    @FXML
    private TableColumn<Auto, String> silnik;
    @FXML
    private TableColumn<Auto, String> paliwo;
    @FXML
    private Button dodajWierszprzycisk;
    @FXML
    private Button usunWierszprzycisk;
    @FXML
    private Button edytujWierszprzycisk;
    @FXML
    private RadioButton tradycyjny;
    @FXML
    private RadioButton ladny;
    @FXML
    private Button wyloguj;
    @FXML
    private Button pomoc;
    @FXML
    private TextField szukaj;
    @FXML
    private ChoiceBox<ChoiceBoxEnum> wyborSzukaj;


    public void  wylogujUzytkownika(ActionEvent e) throws IOException
    {
        if(rootLogowanie==null)
        {
            rootLogowanie = loaderLogowanie.load();
            logowanie = loaderLogowanie.getController();
        }
        db.closeConnect();
        stageLogowanie = (Stage)((Node)e.getSource()).getScene().getWindow();
        scenaLogowanie = new Scene(rootLogowanie);
        stageLogowanie.setResizable(false);
        stageLogowanie.setScene(scenaLogowanie);
        stageLogowanie.show();
    }



    private Parent root;
    private Stage stage;
    private Scene scena;
    private FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("FormularzDodawania.fxml"));
    private FXMLLoader loaderLogowanie = new FXMLLoader(HelloController.class.getResource("Logowanie.fxml"));
    private Parent rootLogowanie;
    private Stage stageLogowanie;
    private Scene scenaLogowanie;

    private FormularzDodawania formularzDodawania;
    private HelloController logowanie;
    private ZapisDoPliku zapis;
    private FilteredList<Auto> filtr;
    private SortedList<Auto> listaSortujaca;

    public void initialize() throws IOException
    {
        szukaj.setPromptText("szukaj...");

        root = loader.load();
        labelStartowy.setText("Witaj "+username);
        formularzDodawania = loader.getController();
        wyborSzukaj.getItems().addAll(ChoiceBoxEnum.VIN,ChoiceBoxEnum.MARKA,ChoiceBoxEnum.MODEL,ChoiceBoxEnum.PALIWO,ChoiceBoxEnum.ROKPRODUKCJI,ChoiceBoxEnum.SILNIK);
        wyborSzukaj.setValue(ChoiceBoxEnum.VIN);


        try {
            szukaj.textProperty().addListener((obs, stara, nowa) ->
            {
                switch (wyborSzukaj.getSelectionModel().getSelectedItem()) {
                    case MARKA -> filtr.setPredicate(auto -> auto.getMarka().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case VIN -> filtr.setPredicate(auto -> auto.getNrVin().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case MODEL -> filtr.setPredicate(auto -> auto.getModel().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case SILNIK -> filtr.setPredicate(auto -> auto.getSilnik().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case PALIWO -> filtr.setPredicate(auto -> auto.getPaliwo().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case ROKPRODUKCJI -> filtr.setPredicate(auto -> Integer.toString(auto.getRokProdukcji()).toLowerCase().contains(nowa.toLowerCase().trim()));
                }

            });
        }

        catch(Exception exc)
        {
            komunikat.setText(exc.getMessage());
        }
        wyborSzukaj.setOnAction(e -> szukaj.clear());


    }

    public void pomocDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Instrukcja korzystania z programu");
        alert.setContentText("1. Aby dodać, usuwać bądź generować rapoty, skorzystaj " +
                "z przycisków poniżej.  \n" +
                "2. aby edytować komórki, kliknij na interesujący wiersz.");

        Optional<ButtonType> result = alert.showAndWait();
       // if (result.get() == ButtonType.OK){
            // ... user chose OK
     //   } else {
            // ... user chose CANCEL or closed the dialog
     //   }
    }

    public void onTyping(ActionEvent e)
    {

        try {
            szukaj.textProperty().addListener((obs, stara, nowa) ->
            {
                switch (wyborSzukaj.getSelectionModel().getSelectedItem()) {
                    case MARKA -> filtr.setPredicate(auto -> auto.getMarka().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case VIN -> filtr.setPredicate(auto -> auto.getNrVin().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case MODEL -> filtr.setPredicate(auto -> auto.getModel().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case SILNIK -> filtr.setPredicate(auto -> auto.getSilnik().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case PALIWO -> filtr.setPredicate(auto -> auto.getPaliwo().toLowerCase().contains(nowa.toLowerCase().trim()));
                    case ROKPRODUKCJI -> filtr.setPredicate(auto -> Integer.toString(auto.getRokProdukcji()).toLowerCase().contains(nowa.toLowerCase().trim()));
                }

            });
        }
        catch(Exception exc)
        {
            komunikat.setText(exc.getMessage());
        }

    }




    public void setList() {
        this.list = FXCollections.observableArrayList(db.selectAuta());
        filtr = new FilteredList(this.list, p -> true);//Pass the data to a filtered list
        listaSortujaca = new SortedList<>(filtr);
        listaSortujaca.comparatorProperty().bind(tabela.comparatorProperty());
        nrVin.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrVin"));
        marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));
        model.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));
        rokProdukcji.setCellValueFactory(new PropertyValueFactory<Auto, Integer>("rokProdukcji"));
        silnik.setCellValueFactory(new PropertyValueFactory<Auto, String>("silnik"));
        paliwo.setCellValueFactory(new PropertyValueFactory<Auto, String>("paliwo"));

        tabela.setItems(listaSortujaca);
        tabela.setEditable(true);
        model.setCellFactory(TextFieldTableCell.forTableColumn());
        marka.setCellFactory(TextFieldTableCell.forTableColumn());
        rokProdukcji.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        silnik.setCellFactory(TextFieldTableCell.forTableColumn());
        paliwo.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private ObservableList<Auto> list;



    public void setDb(DatabaseManagment db) {
        this.db = db;
    }

    @FXML
    private Label labelStartowy;

    public DatabaseManagment getDb() {
        return db;
    }

   public void createDb(String username,String password)
   {
           db = new DatabaseManagment(username, password);
   }

    private static DatabaseManagment db;

    public void dodajWiersz(ActionEvent e)
    {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scena = new Scene(root);
        stage.setScene(scena);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);

        stage.show();
    }

    public void usunWiersz(ActionEvent e)
    {
        try {
            Auto a = tabela.getSelectionModel().getSelectedItem();
            if(a!=null) {
                db.deleteRow(a);


                list.removeAll(a);

                komunikat.setText("");
            }
            else
            {
                komunikat.setText("Nie zaznaczono żadnego elementu do usunięcia!");
            }
        }
        catch(UnsupportedOperationException exc)
        {

            komunikat.setText("Wystąpił niezidentyfikowany problem z bazą danych!");
        }
    }

    public void edytujWiersz(TableColumn.CellEditEvent edditedCell)
    {
        Auto a = tabela.getSelectionModel().getSelectedItem();
        try {
            if (edditedCell.getTableColumn() == model) {
                a.setModel(edditedCell.getNewValue().toString());
            } else if (edditedCell.getTableColumn() == marka) {
                a.setMarka(edditedCell.getNewValue().toString());
            } else if (edditedCell.getTableColumn() == rokProdukcji) {
                a.setRokProdukcji((int) edditedCell.getNewValue());
            } else if (edditedCell.getTableColumn() == silnik) {
                a.setSilnik(edditedCell.getNewValue().toString());
            } else if (edditedCell.getTableColumn() == paliwo) {
                a.setPaliwo(edditedCell.getNewValue().toString());
            }
            db.updateRow(a);
            komunikat.setText("");
        }
        catch(NumberFormatException exc)
        {
            komunikat.setText(exc.getMessage());

        }
        catch(IllegalArgumentException   exc)
        {
            komunikat.setText(exc.getMessage());

        }
        catch( NullPointerException exc)
        {
            komunikat.setText("proszę podać prawidłowy rok!");

        }
        finally {
            tabela.refresh();
        }


    }

    public void zapiszDoTxt(ActionEvent e)
    {
        tabela.getItems();
        zapis = new ZapisDoPliku("raport.txt");
        List<Auto> listaAut =  tabela.getItems();
        if(listaAut instanceof ArrayList<Auto>) {
            if(tradycyjny.isSelected()) {
                zapis.zapiszAuto((ArrayList<Auto>) listaAut);
            }
            else
            {
                zapis.zapiszAutoLadnie((ArrayList<Auto>) listaAut);
            }
        }
        else {
            ArrayList<Auto> nowaLista = new ArrayList<>(listaAut);
            if(tradycyjny.isSelected()) {
                zapis.zapiszAuto(nowaLista);
            }
            else
            {
                zapis.zapiszAutoLadnie(nowaLista);
            }
        }
        komunikat.setText("zapisano w pliku raport.txt");
    }

    public Parent getRoot()
    {
        return root;
    }


}
