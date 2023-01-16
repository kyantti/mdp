package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.Jornada;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Partido;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class JugarController implements Initializable {

    private int [] golLocal = new int[3];
    private int [] golVis = new int [3];

    private boolean[] jugable = new boolean[6];

    private MainController mainController = null;

    @FXML
    private ComboBox<Integer> jornadaComboBox;

    @FXML
    private ImageView escudoLocal;

    @FXML
    private ImageView escudoVisitante;

    @FXML
    private ComboBox<Integer> enf1LocalCmbBox;

    @FXML
    private ComboBox<Integer> enf1VisCmbBox;

    @FXML
    private ComboBox<Integer> enf2LocalCmbBox;

    @FXML
    private ComboBox<Integer> enf2VisCmbBox;

    @FXML
    private ComboBox<Integer> enf3LocalCmbBox;

    @FXML
    private ComboBox<Integer> enf3VisCmbBox;

    @FXML
    private Button jugarButton;

    @FXML
    private ComboBox<String> ligaComboBox;

    @FXML
    private Text localText;
    @FXML
    private Text localText2;

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

    @FXML
    private Text visitanteText2;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 31; i++) {
            enf1LocalCmbBox.getItems().add(i);
            enf2LocalCmbBox.getItems().add(i);
            enf3LocalCmbBox.getItems().add(i);
            enf1VisCmbBox.getItems().add(i);
            enf2VisCmbBox.getItems().add(i);
            enf3VisCmbBox.getItems().add(i);

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

        Image escudoLocalImage = new Image(partido.getLocal().getE().getEscudo());
        escudoLocal.setImage(escudoLocalImage);
        
        Image escudoVisitanteImage = new Image(partido.getVisitante().getE().getEscudo());
        escudoVisitante.setImage(escudoVisitanteImage);

        localText2.setText(partido.getLocal().getE().getNombre());
        visitanteText2.setText(partido.getVisitante().getE().getNombre());

        enf1LocalCmbBox.setDisable(false);
    }

    @FXML
    void seleccionarGoles1enfLocal(ActionEvent event) {
        golLocal[0] = enf1LocalCmbBox.getSelectionModel().getSelectedItem();
        enf2LocalCmbBox.setDisable(false);
    }

    @FXML
    void seleccionarGoles2enfLocal(ActionEvent event) {
        golLocal[1] = enf2LocalCmbBox.getSelectionModel().getSelectedItem();
        enf3LocalCmbBox.setDisable(false);
    }

    @FXML
    void seleccionarGoles3enfLocal(ActionEvent event) {
        golLocal[2] = enf3LocalCmbBox.getSelectionModel().getSelectedItem();
        puntLocal.setText(String.valueOf(Arrays.stream(golLocal).sum()));
        enf1VisCmbBox.setDisable(false);
    }

    @FXML
    void seleccionarGoles1enfVis(ActionEvent event) {
        golVis[0] = enf1VisCmbBox.getSelectionModel().getSelectedItem();
        enf2VisCmbBox.setDisable(false);
    }

    
    @FXML
    void seleccionarGoles2enfVis(ActionEvent event) {
        golVis[1] = enf2VisCmbBox.getSelectionModel().getSelectedItem();
        enf3VisCmbBox.setDisable(false);
    }


    @FXML
    void seleccionarGoles3enfVis(ActionEvent event) {
        golVis[2] = enf3VisCmbBox.getSelectionModel().getSelectedItem();
        puntVis.setText(String.valueOf(Arrays.stream(golVis).sum()));
        jugarButton.setDisable(false);
    }

    @FXML
    void jugar(ActionEvent event) {
        String nomTemp = tempComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = ligaComboBox.getSelectionModel().getSelectedItem();
        int numJornada = jornadaComboBox.getSelectionModel().getSelectedItem();
        int idPartido = partidoComboBox.getSelectionModel().getSelectedItem();
        
        if (mainController.getCampeonato().jugar(nomTemp, nomLiga, numJornada, idPartido, golLocal, golVis)) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
