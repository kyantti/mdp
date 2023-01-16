package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class ConsultarTemporadasController implements Initializable {
    @FXML
    private TableColumn<Temporada, String> ligasCol;

    @FXML
    private TableView<Temporada> table;

    @FXML
    private TableColumn<Temporada, String> tempCol;

    private ObservableList <Temporada> temporadasObservableList;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public TableView<Temporada> getTable() {
        return table;
    }

    public ObservableList<Temporada> getTemporadasObservableList() {
        return temporadasObservableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        temporadasObservableList = FXCollections.observableArrayList();

        tempCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ligasCol.setCellValueFactory(new Callback<CellDataFeatures<Temporada, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Temporada, String> temporada) {
                return new SimpleObjectProperty<>(temporada.getValue().getNombresLigas());
            }
        });
    } 
}
