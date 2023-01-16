package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Equipo;
import main.java.es.unex.cum.mdp.ef3.model.EquipoLiga;

public class ConsultarEstadisticaController implements Initializable {
    @FXML
    private TableColumn<EquipoLiga, Integer> enfrenEmpatadosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> enfrenGanadosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> enfrenPerdidosCol;

    @FXML
    private TableColumn<EquipoLiga, String> equipoCol;

    @FXML
    private TableColumn<EquipoLiga, ImageView> escudoCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> idCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> partidosEmpatadosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> partidosGanadosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> partidosJugadosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> partidosPerdidosCol;

    @FXML
    private TableColumn<EquipoLiga, Integer> puntosCol;

    @FXML
    private TableView<EquipoLiga> table;

    private ObservableList <EquipoLiga> equiposObservableList;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public TableView<EquipoLiga> getTable() {
        return table;
    }

    public ObservableList<EquipoLiga> getEquiposObservableList() {
        return equiposObservableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        equiposObservableList = FXCollections.observableArrayList();

        equipoCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                return new SimpleStringProperty(equipoLiga.getValue().getE().getNombre());
            }
        });

        idCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<EquipoLiga, Integer> equipoLiga) {
                return new SimpleIntegerProperty(equipoLiga.getValue().getE().getId());
            }
        });

        escudoCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(CellDataFeatures<EquipoLiga, ImageView> equipoLiga) {
                ImageView escudo = new ImageView(new Image(equipoLiga.getValue().getE().getEscudo()));
                escudo.setPreserveRatio(true);
                escudo.setFitWidth(32);
                escudo.setFitHeight(32);
                return new SimpleObjectProperty<>(escudo);
            }
        });
        

        
    }
}
