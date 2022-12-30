package main.java.es.unex.cum.mdp.ef3.controller;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import main.java.es.unex.cum.mdp.ef3.model.Equipo;
import main.java.es.unex.cum.mdp.ef3.model.EquipoLiga;
import main.java.es.unex.cum.mdp.ef3.model.Jugador;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Persona;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class AddJugadorEquipoLigaController {

    private boolean[] registrable = new boolean[4];

    @FXML
    private ComboBox<String> temporadaComboBox;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private ComboBox<String> equipoComboBox;

    @FXML
    private ComboBox<String> jugadorComboBox;

    @FXML
    private Button addJugadorButton;

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
        registrable[0] = true;

        ligaComboBox.setDisable(false);
    }

    @FXML
    void mostrarLigas(MouseEvent event) {
        ObservableList<String> ligasObservableList = FXCollections.observableArrayList();
        Temporada temporada = mainController.getCampeonato()
                .getTemporada(temporadaComboBox.getSelectionModel().getSelectedItem());
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
    void mostrarEquipos(MouseEvent event) throws NoLigaException {
        ObservableList<String> equiposObservableList = FXCollections.observableArrayList();
        Liga liga = mainController.getCampeonato().getTempLiga(temporadaComboBox.getSelectionModel().getSelectedItem(), ligaComboBox.getSelectionModel().getSelectedItem());
        if (liga.getEquiposLiga() != null) {
            for (EquipoLiga equipoLiga : liga.getEquiposLiga()) {
                equiposObservableList.add(equipoLiga.getE().getNombre());
            }
        }
        equipoComboBox.setItems(equiposObservableList);
    }

    @FXML
    void seleccionarEquipo(ActionEvent event) {
        registrable[2] = true;

        jugadorComboBox.setDisable(false);
    }

    @FXML
    void mostrarJugadores(MouseEvent event) {
        int id = 0;
        String nombre = "";

        ObservableList<String> jugadoresObservableList = FXCollections.observableArrayList();
        if (mainController.getCampeonato().getFederados() != null) {
            for (Map.Entry<Integer, Persona> set : mainController.getCampeonato().getFederados().entrySet()) {
                if (set.getValue().getClass().equals(Jugador.class)) {
                    nombre = set.getValue().getNombre();
                    id = set.getValue().getId();
                    jugadoresObservableList.add("Nombre: " + nombre + " ID: " + id);
                }
            }
        }
        jugadorComboBox.setItems(jugadoresObservableList);
    }

    @FXML
    void seleccionarJugador(ActionEvent event) {
        registrable[3] = true;

        addJugadorButton.setDisable(false);
    }

    @FXML
    void addJugadorEquipoLigatemporada(ActionEvent event) {
        String temporada = temporadaComboBox.getSelectionModel().getSelectedItem();
        String liga = ligaComboBox.getSelectionModel().getSelectedItem();
        String equipo = equipoComboBox.getSelectionModel().getSelectedItem();
        int id = Integer.parseInt(jugadorComboBox.getSelectionModel().getSelectedItem().split(" ID: ")[1]);
        mainController.getCampeonato().addJugadorEquipoLigaTemporada(temporada, liga, equipo, id);
    }
}
