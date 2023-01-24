package main.java.es.unex.cum.mdp.ef3.controller;

import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Jornada;
import main.java.es.unex.cum.mdp.ef3.model.Liga;
import main.java.es.unex.cum.mdp.ef3.model.Partido;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class ConsultarLigasController {
    @FXML
    private TableColumn<Liga, String> eqVisPartidoCol;

    @FXML
    private TableColumn<Liga, ImageView> escudoEqLocalCol;

    @FXML
    private TableColumn<Liga, ImageView> escudoEqVis;

    @FXML
    private TableColumn<Liga, String> fechaJornadaCol;

    @FXML
    private TableColumn<Liga, String> idPartidoCol;

    @FXML
    private TableColumn<Liga, String> juezCol;

    @FXML
    private TableColumn<Liga, String> nombreEqLocalCol;

    @FXML
    private TableColumn<Liga, String> nombreEqVisCol;

    @FXML
    private TableColumn<Liga, String> nombreLigaCol;

    @FXML
    private TableColumn<Liga, String> numeroJornadaCol;

    @FXML
    private TableColumn<Liga, String> partidosJugadosCol11;

    @FXML
    private TableView<Liga> table;

    @FXML
    private ComboBox<String> tempComboBox;

    private ObservableList<Liga> ligasObservableList;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public TableView<Liga> getTable() {
        return table;
    }

    public ObservableList<Liga> getLigasObservableList() {
        return ligasObservableList;
    }

    @FXML
    void mostrarTemporadas(MouseEvent event) {
        if (tempComboBox.getSelectionModel().isEmpty()) {
            ObservableList<String> temporadasObservableList = FXCollections.observableArrayList();
            if (!mainController.getCampeonato().getTemporadas().isEmpty()) {
            for (Temporada temporada : mainController.getCampeonato().getTemporadas()) {
                temporadasObservableList.add(temporada.getNombre());
            }
            tempComboBox.setItems(temporadasObservableList);
        }
        }
    }

    @FXML
    void seleccionarTemp(ActionEvent event) {
        Temporada temporada = mainController.getCampeonato().getTemporada(tempComboBox.getSelectionModel().getSelectedItem());
        table.getItems().clear();

        if (!temporada.getLigas().isEmpty()) {
            ligasObservableList = FXCollections.observableArrayList(temporada.getLigas().values());

            nombreLigaCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    return new SimpleStringProperty(liga.getValue().getNombre());
                }
            });

    
            fechaJornadaCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder jornadas = new StringBuilder();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        String dateFormatted = sdf.format(jornada.getFecha());
                        jornadas.append(dateFormatted + System.lineSeparator());
                    }
                    return new SimpleStringProperty(jornadas.toString());
                }
            });
            fechaJornadaCol.setCellFactory(col -> new MultiLineCell<>());
    
            numeroJornadaCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder jornadas = new StringBuilder();
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        jornadas.append(jornada.getNumero() + System.lineSeparator());
                    }
                    return new SimpleStringProperty(jornadas.toString());
                }
            });
            numeroJornadaCol.setCellFactory(col -> new MultiLineCell<>());
            
            idPartidoCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder idPartidos = new StringBuilder();
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        for (Partido partido : jornada.getPartidos()) {
                            idPartidos.append(partido.getId() + System.lineSeparator());
                        }
                    }
                    return new SimpleStringProperty(idPartidos.toString());
                }
            });

            idPartidoCol.setCellFactory(col -> new MultiLineCell<>());

            nombreEqLocalCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder eqLocal = new StringBuilder();
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        for (Partido partido : jornada.getPartidos()) {
                            eqLocal.append(partido.getLocal().getE().getNombre() + System.lineSeparator());
                        }
                    }
                    return new SimpleStringProperty(eqLocal.toString());
                }
            });

            nombreEqLocalCol.setCellFactory(col -> new MultiLineCell<>());

            nombreEqVisCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder eqVis = new StringBuilder();
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        for (Partido partido : jornada.getPartidos()) {
                            eqVis.append(partido.getVisitante().getE().getNombre() + System.lineSeparator());
                        }
                    }
                    return new SimpleStringProperty(eqVis.toString());
                }
            });

            nombreEqVisCol.setCellFactory(col -> new MultiLineCell<>());

            juezCol.setCellValueFactory(new Callback<CellDataFeatures<Liga, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Liga, String> liga) {
                    StringBuilder eqVis = new StringBuilder();
                    for (Jornada jornada : liga.getValue().getCalendario()) {
                        for (Partido partido : jornada.getPartidos()) {
                            eqVis.append(partido.getJ().getNombre() + System.lineSeparator());
                        }
                    }
                    return new SimpleStringProperty(eqVis.toString());
                }
            });

            juezCol.setCellFactory(col -> new MultiLineCell<>());

            table.setItems(ligasObservableList);
        }
    }
}
