package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.EquipoLiga;
import main.java.es.unex.cum.mdp.ef3.model.EstadisticaFutbolin;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.NoLigaException;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class ConsultarEstadisticaController implements Initializable {
    
    @FXML
    private TableColumn<EquipoLiga, String> enfrenEmpatadosCol;

    @FXML
    private TableColumn<EquipoLiga, String> enfrenGanadosCol;

    @FXML
    private TableColumn<EquipoLiga, String> enfrenPerdidosCol;

    @FXML
    private TableColumn<EquipoLiga, String> equipoCol;

    @FXML
    private TableColumn<EquipoLiga, String> golesAfavorCol;

    @FXML
    private TableColumn<EquipoLiga, String> golesEnContraCol;

    @FXML
    private TableColumn<EquipoLiga, ImageView> escudoCol;

    @FXML
    private TableColumn<EquipoLiga, String> idCol;

    @FXML
    private TableColumn<EquipoLiga, String> partidosEmpatadosCol;

    @FXML
    private TableColumn<EquipoLiga, String> partidosGanadosCol;

    @FXML
    private TableColumn<EquipoLiga, String> partidosJugadosCol;

    @FXML
    private TableColumn<EquipoLiga, String> partidosPerdidosCol;

    @FXML
    private TableColumn<EquipoLiga, String> puntosCol;

    @FXML
    private TableView<EquipoLiga> table;

    @FXML
    private ComboBox<String> tempComboBox;

    @FXML
    private ComboBox<String> ligaComboBox;

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
        
        //equiposObservableList = FXCollections.observableArrayList();
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
        if (ligaComboBox.getSelectionModel().isEmpty()) {
            ObservableList<String> ligasObservableList = FXCollections.observableArrayList();
            Temporada temporada = mainController.getCampeonato().getTemporada(tempComboBox.getSelectionModel().getSelectedItem());
            if (temporada != null) {
                for (Map.Entry<String, Liga> set : temporada.getLigas().entrySet()) {
                    ligasObservableList.add(set.getValue().getNombre());
                }
            }
            ligaComboBox.setItems(ligasObservableList);
        }
    }


    @FXML
    void seleccionarLiga(ActionEvent event) throws NoLigaException {
        Liga liga = mainController.getCampeonato().getTempLiga(tempComboBox.getSelectionModel().getSelectedItem(), ligaComboBox.getSelectionModel().getSelectedItem());
        table.getItems().clear();

        if (!liga.getEquiposLiga().isEmpty()) {
            equiposObservableList = FXCollections.observableArrayList(liga.getEquiposLiga());

            equipoCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(equipoLiga.getValue().getE().getNombre());
                }
            });
    
            idCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getE().getId()));
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
    
            puntosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getPuntos()));
                }
            });

            golesAfavorCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(((EstadisticaFutbolin)equipoLiga.getValue().getEst()).getGolesFavor()));
                }
            });

            golesEnContraCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(((EstadisticaFutbolin)equipoLiga.getValue().getEst()).getGolesContra()));
                }
            });
    
    
            partidosJugadosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getPartJugados()));
                }
            });
    
            partidosGanadosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getPartGanados()));
                }
            });
    
    
            partidosPerdidosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getPartPerdidos()));
                }
            });
            
            partidosEmpatadosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getPartEmpatados()));
                }
            });
    
            enfrenGanadosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getEnfrenGanados()));
                }
            });
    
    
            enfrenPerdidosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getEnfrenPerdidos()));
                }
            });
            
            enfrenEmpatadosCol.setCellValueFactory(new Callback<CellDataFeatures<EquipoLiga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<EquipoLiga, String> equipoLiga) {
                    return new SimpleStringProperty(String.valueOf(equipoLiga.getValue().getEst().getEnfrenEmpatados()));
                }
            });
    
            table.setItems(equiposObservableList);

            table.getSortOrder().add(puntosCol);
            puntosCol.setSortType(TableColumn.SortType.DESCENDING);
        }
    }

}
