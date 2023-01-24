package main.java.es.unex.cum.mdp.ef3.controller;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class BorrarCalendarioController {
    @FXML
    private Button borrarButton;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private ComboBox<String> temporadaComboBox;
    
    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void mostrarTemporadas(MouseEvent event) {
        ObservableList<String> temporadasObservableList = FXCollections.observableArrayList();
        if (!mainController.getCampeonato().getTemporadas().isEmpty()) {
            for (Temporada temporada : mainController.getCampeonato().getTemporadas()) {
                temporadasObservableList.add(temporada.getNombre());
            }
            temporadaComboBox.setItems(temporadasObservableList);
        }
    }

    
    @FXML
    void seleccionarTemporada(ActionEvent event) {
        ligaComboBox.setDisable(false);
    }

    @FXML
    void mostrarLigas(MouseEvent event) {
        ObservableList<String> ligasObservableList = FXCollections.observableArrayList();
        Temporada temporada = mainController.getCampeonato().getTemporada(temporadaComboBox.getSelectionModel().getSelectedItem());
        if (temporada != null) {
            for (Map.Entry<String, Liga> set : temporada.getLigas().entrySet()) {
                ligasObservableList.add(set.getValue().getNombre());
            }
        }
        ligaComboBox.setItems(ligasObservableList);
    }

    
    @FXML
    void seleccionarLiga(ActionEvent event) {
        borrarButton.setDisable(false);
    }

    @FXML
    void borrarCalendario(ActionEvent event) throws NoLigaException {
        String nomTemp = temporadaComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = ligaComboBox.getSelectionModel().getSelectedItem();
        Liga liga = mainController.getCampeonato().getTempLiga(nomTemp, nomLiga);
        
        liga.getCalendario().clear();

        if (liga.getCalendario().isEmpty()) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
