package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.IOException;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.java.es.unex.cum.mdp.ef3.model.Directivo;
import main.java.es.unex.cum.mdp.ef3.model.Equipo;
import main.java.es.unex.cum.mdp.ef3.model.EquipoLiga;
import main.java.es.unex.cum.mdp.ef3.model.Juez;
import main.java.es.unex.cum.mdp.ef3.model.Jugador;
import main.java.es.unex.cum.mdp.ef3.model.Persona;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;

public class NormalMenuController {

    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Label bienvenidoLabel;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Label getBienvenidoLabel() {
        return bienvenidoLabel;
    }

    @FXML
    void consultarEquipos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/consultarEquipos.fxml"));	
		root = loader.load();
        
        ConsultarEquiposController consultarEquiposController = loader.getController();
		consultarEquiposController.setMainController(mainController);

        for (Map.Entry<String, Equipo> set : mainController.getCampeonato().getEquipos().entrySet()) {
            consultarEquiposController.getEquiposObservableList().add(set.getValue());
        }
        consultarEquiposController.getTable().setItems(consultarEquiposController.getEquiposObservableList());

        System.out.println(mainController.getCampeonato().getEquipos().toString());
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
stage.setResizable(false);
		stage.show();

        
    }

    @FXML
    void consultarEstadisticas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/consultarEstadistica.fxml"));	
		root = loader.load();
        
        ConsultarEstadisticaController consultarEstadisticaController = loader.getController();
		consultarEstadisticaController.setMainController(mainController);
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
stage.setResizable(false);
		stage.show();
    }

    @FXML
    void consultarFederados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/consultarFederados.fxml"));	
		root = loader.load();
        
        ConsultarFederadosController consultarFederadosController = loader.getController();
		consultarFederadosController.setMainController(mainController);

        for (Map.Entry<Integer, Persona> set : mainController.getCampeonato().getFederados().entrySet()) {
            //consultarFederadosController.getPersonasObservableList().add(set.getValue());

            if (set.getValue().getClass().equals(Jugador.class)) {
                consultarFederadosController.getJugadoresObservableList().add((Jugador) set.getValue());
            }
            else if (set.getValue().getClass().equals(Directivo.class)) {
                consultarFederadosController.getDirectivosObservableList().add((Directivo) set.getValue());
            }
            else if (set.getValue().getClass().equals(Juez.class)) {
                consultarFederadosController.getJuecesObservableList().add((Juez) set.getValue());
            }
        }
        consultarFederadosController.getTable().setItems(consultarFederadosController.getPersonasObservableList());
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
stage.setResizable(false);
		stage.show();
    }

    @FXML
    void consultarLigas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/consultarLigas.fxml"));	
		root = loader.load();
        
        ConsultarLigasController consultarLigasController = loader.getController();
		consultarLigasController.setMainController(mainController);
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
stage.setResizable(false);
		stage.show();
    }

    @FXML
    void consultarTemporadas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/consultarTemporadas.fxml"));	
		root = loader.load();
        
        ConsultarTemporadasController consultarTemporadasController = loader.getController();
		consultarTemporadasController.setMainController(mainController);

        for (Temporada temporada: mainController.getCampeonato().getTemporadas()) {
            consultarTemporadasController.getTemporadasObservableList().add(temporada);
        }
        consultarTemporadasController.getTable().setItems(consultarTemporadasController.getTemporadasObservableList());
		
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
stage.setResizable(false);
		stage.show();
    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/main.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
stage.setResizable(false);
        stage.show();
    }
}
