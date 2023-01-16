package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Directivo;
import main.java.es.unex.cum.mdp.ef3.model.Juez;
import main.java.es.unex.cum.mdp.ef3.model.Jugador;
import main.java.es.unex.cum.mdp.ef3.model.Persona;

public class ConsultarFederadosController implements Initializable {
    @FXML
    private TableColumn<Persona, Integer> antiguedadCol;

    @FXML
    private TableColumn<Persona, ImageView> aspectoCol;

    @FXML
    private TableColumn<Persona, Integer> coefCol;

    @FXML
    private TableColumn<Persona, Integer> edadCol;

    @FXML
    private TableColumn<Persona, Boolean> enLigaCol;

    @FXML
    private TableColumn<Persona, Integer> idCol;

    @FXML
    private TableColumn<Persona, String> nickCol;

    @FXML
    private TableColumn<Persona, String> nombreCol;

    @FXML
    private TableColumn<Persona, String> puestoCol;

    @FXML
    private TableView<Persona> table;

    @FXML
    private ComboBox<String> tipoComboBox;

    private MainController mainController = null;

    private ObservableList <Jugador> jugadoresObservableList;

    private ObservableList <Directivo> directivosObservableList;

    private ObservableList <Juez> juecesObservableList;

    private ObservableList <Persona> personasObservableList;

    public ObservableList<Jugador> getJugadoresObservableList() {
        return jugadoresObservableList;
    }

    public ObservableList<Directivo> getDirectivosObservableList() {
        return directivosObservableList;
    }

    public ObservableList<Juez> getJuecesObservableList() {
        return juecesObservableList;
    }


    public ObservableList<Persona> getPersonasObservableList() {
        return personasObservableList;
    }

    public TableView<Persona> getTable() {
        return table;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Combobox
        ObservableList <String> tipos = FXCollections.observableArrayList();
        tipos.addAll("Jugador", "Juez", "Directivo");
        tipoComboBox.setItems(tipos);
        tipoComboBox.getSelectionModel().selectFirst();

        //Creo los observablelist
        jugadoresObservableList = FXCollections.observableArrayList();
        directivosObservableList = FXCollections.observableArrayList();
        juecesObservableList = FXCollections.observableArrayList();
        personasObservableList = FXCollections.observableArrayList();

        //COLUMNAS
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        edadCol.setCellValueFactory(new PropertyValueFactory<>("edad"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aspectoCol.setCellValueFactory(new Callback<CellDataFeatures<Persona, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(CellDataFeatures<Persona, ImageView> persona) {
                ImageView aspecto = new ImageView(new Image(persona.getValue().getAspecto()));
                aspecto.setPreserveRatio(true);
                aspecto.setFitWidth(32);
                aspecto.setFitHeight(32);
                return new SimpleObjectProperty<>(aspecto);
            }
        });
        
    
    }

    @FXML
    void seleccionarTipo(ActionEvent event) {
        table.getItems().clear();
        switch (tipoComboBox.getValue()) {
            case "Jugador":
                personasObservableList = FXCollections.observableArrayList(jugadoresObservableList);
                
                //Jugador
                enLigaCol.setCellValueFactory(new PropertyValueFactory<>("enEquipoLiga"));
                nickCol.setCellValueFactory(new PropertyValueFactory<>("nick"));
                coefCol.setCellValueFactory(new PropertyValueFactory<>("coef"));

                enLigaCol.setVisible(true);
                nickCol.setVisible(true);
                coefCol.setVisible(true);

                //Directivo
                puestoCol.setVisible(false);

                //Juez
                antiguedadCol.setVisible(false);

                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            break;

            case "Directivo":
                personasObservableList = FXCollections.observableArrayList(directivosObservableList);
                
                //Directivo
                puestoCol.setCellValueFactory(new PropertyValueFactory<>("puesto"));

                puestoCol.setVisible(true);

                //Jugador

                enLigaCol.setVisible(false);
                nickCol.setVisible(false);
                coefCol.setVisible(false);

                //Juez
                antiguedadCol.setVisible(false);

                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            break;

            case "Juez":
                personasObservableList = FXCollections.observableArrayList(juecesObservableList);
                
                //Juez
                antiguedadCol.setCellValueFactory(new PropertyValueFactory<>("antiguedad"));

                antiguedadCol.setVisible(true);

                //Jugador

                enLigaCol.setVisible(false);
                nickCol.setVisible(false);
                coefCol.setVisible(false);

                //Directivo
                puestoCol.setVisible(false);

                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            break;

            default:
                
            break;
        }
        table.setItems(personasObservableList);
    }
}


