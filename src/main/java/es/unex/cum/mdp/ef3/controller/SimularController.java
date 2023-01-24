package main.java.es.unex.cum.mdp.ef3.controller;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.es.unex.cum.mdp.ef3.model.Jornada;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Partido;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class SimularController {

    @FXML
    private ProgressBar barraProgreso;

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
    void simular(ActionEvent event){
        String nomTemp = tempComboBox.getSelectionModel().getSelectedItem();
        String nomLiga = ligaComboBox.getSelectionModel().getSelectedItem();
        if (mainController.getCampeonato().simular(nomTemp, nomLiga)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    double progreso = 0;
                    try {
                        List <Jornada> calendario = mainController.getCampeonato().getTempLiga(nomTemp, nomLiga).getCalendario();
                        for (int i = 0; i < calendario.size(); i++) {
                            Jornada jornada = calendario.get(i);
                            for (int j = 0; j < jornada.getPartidos().size(); j++ ) {
                                Partido partido = jornada.gePartido(j);

                                localText.setText(partido.getLocal().getE().getNombre());
                                visitanteText.setText(partido.getVisitante().getE().getNombre());
       
                                Image escudoLocalImage = new Image(partido.getLocal().getE().getEscudo());
                                escudoLocal.setImage(escudoLocalImage);
                                
                                Image escudoVisitanteImage = new Image(partido.getVisitante().getE().getEscudo());
                                escudoVisitante.setImage(escudoVisitanteImage);
       
                                puntLocal.setText(String.valueOf(partido.getPuntosLocal()));
                                puntVis.setText(String.valueOf(partido.getPuntosVisitante()));

                                progreso = progreso + (1.0 / (calendario.size() * jornada.getPartidos().size()));
                                barraProgreso.setProgress(progreso);
    
                                Thread.sleep(800);
                                
                            }
                        }
                    } catch (NoLigaException e) {
    
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
