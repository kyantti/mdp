package main.java.es.unex.cum.mdp.ef3.controller;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class SimularController {
    @FXML
    private ImageView escudoLocal;

    @FXML
    private ImageView escudoVisitante;

    @FXML
    private Button simularButton;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private Text localText;

    @FXML
    private Text puntLocal;

    @FXML
    private Text puntVis;

    @FXML
    private ComboBox<String> tempComboBox;

    @FXML
    private Text visitanteText;

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
            tempComboBox.setItems(temporadasObservableList);
        }
    }
    
    @FXML
    void seleccionarTemp(ActionEvent event) {
        ligaComboBox.setDisable(false);
    }

    @FXML
    void mostrarLigas(MouseEvent event) {
        ObservableList<String> ligasObservableList = FXCollections.observableArrayList();
        Temporada temporada = mainController.getCampeonato().getTemporada(tempComboBox.getSelectionModel().getSelectedItem());
        if (temporada != null) {
            for (Map.Entry<String, Liga> set : temporada.getLigas().entrySet()) {
                ligasObservableList.add(set.getValue().getNombre());
            }
        }
        ligaComboBox.setItems(ligasObservableList);
    }

    @FXML
    void seleccionarLiga(ActionEvent event) {
        simularButton.setDisable(false);
    }

    @FXML
    void simular(ActionEvent event) {
        String nomTemp = tempComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = tempComboBox.getSelectionModel().getSelectedItem();

        if (mainController.getCampeonato().simular(nomTemp, nomLiga)) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        
    }
}
