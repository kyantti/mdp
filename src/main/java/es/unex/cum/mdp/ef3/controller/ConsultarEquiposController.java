package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Equipo;

public class ConsultarEquiposController implements Initializable {

    @FXML
    private TableView<Equipo> table;

    @FXML
    private TableColumn<Equipo, String> cargoCol;

    @FXML
    private TableColumn<Equipo, String> ciudadCol;

    @FXML
    private TableColumn<Equipo, Boolean> enLigaCol;

    @FXML
    private TableColumn<Equipo, ImageView> escudoCol;

    @FXML
    private TableColumn<Equipo, Integer> idCol;

    @FXML
    private TableColumn<Equipo, String> nombreCol;

    private ObservableList <Equipo> equiposObservableList;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public TableView<Equipo> getTable() {
        return table;
    }

    public ObservableList<Equipo> getEquiposObservableList() {
        return equiposObservableList;
    }

    public TableColumn<Equipo, String> getCargoCol() {
        return cargoCol;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setFixedCellSize(64);
        // Creo el observablelist
        equiposObservableList = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cargoCol.setCellValueFactory(new Callback<CellDataFeatures<Equipo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Equipo, String> equipo) {
                return new SimpleStringProperty(equipo.getValue().getCargo().getNombre());
            }
        });
        ciudadCol.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        escudoCol.setCellValueFactory(new Callback<CellDataFeatures<Equipo, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(CellDataFeatures<Equipo, ImageView> equipo) {
                ImageView escudo = new ImageView(new Image(equipo.getValue().getEscudo()));
                escudo.setPreserveRatio(true);
                escudo.setFitWidth(32);
                escudo.setFitHeight(32);
                return new SimpleObjectProperty<>(escudo);
            }
        });
        
        enLigaCol.setCellValueFactory(new PropertyValueFactory<>("enLiga"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    }
    

}
