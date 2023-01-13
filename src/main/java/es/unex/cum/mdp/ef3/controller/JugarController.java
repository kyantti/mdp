package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.es.unex.cum.mdp.ef3.model.Jornada;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Partido;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class JugarController implements Initializable {

    private boolean[] jugable = new boolean[6];

    private MainController mainController = null;

    @FXML
    private ComboBox<Integer> jornadaComboBox;

    @FXML
    private ImageView escudoLocal;

    @FXML
    private ImageView escudoVisitante;

    @FXML
    private ComboBox<Integer> golLocalComboBox;

    @FXML
    private ComboBox<Integer> golVisComboBox;

    @FXML
    private Button jugarButton;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private Text localText;

    @FXML
    private ComboBox<Integer> partidoComboBox;

    @FXML
    private Text puntLocal;

    @FXML
    private Text puntVis;

    @FXML
    private ComboBox<String> tempComboBox;

    @FXML
    private Text visitanteText;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 31; i++) {
            golLocalComboBox.getItems().add(i);
            golVisComboBox.getItems().add(i);
        }
        puntLocal.setText("X");
        puntVis.setText("X");
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
        jornadaComboBox.setDisable(false);
    }

    @FXML
    void mostrarJornadas(MouseEvent event) throws NoLigaException {
        ObservableList<Integer> jornadasObservableList = FXCollections.observableArrayList();
        Liga liga = mainController.getCampeonato().getTempLiga(tempComboBox.getSelectionModel().getSelectedItem(), ligaComboBox.getSelectionModel().getSelectedItem());
        if (liga != null) {
            for (Jornada jornada : liga.getCalendario()) {
                System.out.println(jornada.toString());
                jornadasObservableList.add(jornada.getNumero());
            }
        }
        jornadaComboBox.setItems(jornadasObservableList);
    }

    @FXML
    void seleccionarJornada(ActionEvent event) {
        partidoComboBox.setDisable(false);
    }

    @FXML
    void mostrarPartidos(MouseEvent event) throws NoLigaException {
        ObservableList<Integer> partidosObservableList = FXCollections.observableArrayList();
        Jornada jornada = mainController.getCampeonato().getTempLiga(tempComboBox.getSelectionModel().getSelectedItem(), ligaComboBox.getSelectionModel().getSelectedItem()).getJornada(jornadaComboBox.getSelectionModel().getSelectedItem());
        if (jornada != null) {
            for (Partido partido : jornada.getPartidos()) {
                partidosObservableList.add(partido.getId());
            }
        }
        partidoComboBox.setItems(partidosObservableList);
    }

    @FXML
    void seleccionarPartido(ActionEvent event) throws NoLigaException, FileNotFoundException {
        Partido partido = mainController.getCampeonato().getTempLiga(tempComboBox.getSelectionModel().getSelectedItem(), ligaComboBox.getSelectionModel().getSelectedItem()).getJornada(jornadaComboBox.getSelectionModel().getSelectedItem()).gePartido(partidoComboBox.getSelectionModel().getSelectedItem());
        localText.setText(partido.getLocal().getE().getNombre());
        visitanteText.setText(partido.getVisitante().getE().getNombre());

        escudoLocal.setImage(new Image(new FileInputStream(partido.getLocal().getE().getEscudo())));
        escudoVisitante.setImage(new Image(new FileInputStream(partido.getVisitante().getE().getEscudo())));

        golLocalComboBox.setDisable(false);

    }

    @FXML
    void seleccionarGolesLocal(ActionEvent event) {
        golVisComboBox.setDisable(false);
    }

    @FXML
    void seleccionarGolesVis(ActionEvent event) {
        jugarButton.setDisable(false);
    }


    @FXML
    void jugar(ActionEvent event) {
        String nomTemp = tempComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = ligaComboBox.getSelectionModel().getSelectedItem();
        int numJornada = jornadaComboBox.getSelectionModel().getSelectedItem();
        int idPartido = partidoComboBox.getSelectionModel().getSelectedItem();
        int golLocal = golLocalComboBox.getSelectionModel().getSelectedItem();
        int golVis = golVisComboBox.getSelectionModel().getSelectedItem();

        if (mainController.getCampeonato().jugar(nomTemp, nomLiga, numJornada, idPartido, golLocal, golVis)) {
            
        }
    }
}
