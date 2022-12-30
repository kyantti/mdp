package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import main.java.es.unex.cum.mdp.ef3.model.Equipo;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class AddEquipoLigaTemporadaController implements Initializable {

    private boolean[] registrable = new boolean[4];

    @FXML
    private Button addEquipoLigaButton;

    @FXML
    private ComboBox<Integer> coeficienteComboBox;

    @FXML
    private ComboBox<String> equipoComboBox;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private ComboBox<String> temporadaComboBox;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList <Integer> coeficientes = FXCollections.observableArrayList();
        coeficientes.addAll(1, 2, 3, 4, 5);
        coeficienteComboBox.setItems(coeficientes);
        coeficienteComboBox.setVisible(true);
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
        registrable[0] = true;
        
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
        registrable[1] = true;

        equipoComboBox.setDisable(false);
    }

    @FXML
    void mostrarEquipos(MouseEvent event) {
        ObservableList<String> equiposObservableList = FXCollections.observableArrayList();
        if (mainController.getCampeonato().getEquipos() != null) {
            for (Map.Entry<String, Equipo> set : mainController.getCampeonato().getEquipos().entrySet()) {
                equiposObservableList.add(set.getValue().getNombre());
            }
        }
        equipoComboBox.setItems(equiposObservableList);
    }
    
    @FXML
    void seleccionarEquipo(ActionEvent event) {
        registrable[2] = true;

        coeficienteComboBox.setDisable(false);
    }

    @FXML
    void seleccionarCoef(ActionEvent event) {
        registrable[3] = true;

        addEquipoLigaButton.setDisable(false);

    }

    @FXML
    void addEquipoLiga(ActionEvent event) {
        String nomTemp = temporadaComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = ligaComboBox.getSelectionModel().getSelectedItem();
        String nomEq = equipoComboBox.getSelectionModel().getSelectedItem();
        int coef = coeficienteComboBox.getSelectionModel().getSelectedItem();
        mainController.getCampeonato().addEquipoLigaTemporada(nomTemp, nomLiga, nomEq, coef);
    }

}
