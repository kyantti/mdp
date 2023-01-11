package main.java.es.unex.cum.mdp.ef3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class AddLigaTemporadaController {

    private boolean[] registrable = new boolean[2];

    @FXML
    private Button addLigaButton;

    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox<String> temporadaComboBox;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void verificar() {
        if (registrable[0] && registrable[1]) {
            addLigaButton.setDisable(false);
        } else {
            addLigaButton.setDisable(true);
        }
    }

    @FXML
    void verificarNombre(KeyEvent event) throws NoLigaException {
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank() && mainController.getCampeonato().getTempLiga(temporadaComboBox.getSelectionModel().getSelectedItem(), nombreTextField.getText()) == null) {
            registrable[0] = true;
        } else {
            registrable[0] = false;
        }
        verificar();
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
        registrable[1] = true;
        verificar();
    }

    @FXML
    void addLiga(ActionEvent event) {
        if (mainController.getCampeonato().crearLigaTemporada(temporadaComboBox.getSelectionModel().getSelectedItem(),
                nombreTextField.getText())) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
