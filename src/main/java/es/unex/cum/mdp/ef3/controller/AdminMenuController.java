package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminMenuController {

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
    void addEquipo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addEquipo.fxml"));
        root = loader.load();

        AddEquipoController addEquipoController = loader.getController();
        addEquipoController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addLigaTemporada(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addLigaTemporada.fxml"));
        root = loader.load();

        AddLigaTemporadaController addLigaTemporadaController = loader.getController();
        addLigaTemporadaController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addPersona(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addPersona.fxml"));
        root = loader.load();

        AddPersonaController addPersonaController = loader.getController();
        addPersonaController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addTemporada(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addTemporada.fxml"));
        root = loader.load();

        AddTemporadaController addTemporadaController = loader.getController();
        addTemporadaController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addEquipoLigaTemporada(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addEquipoLigaTemporada.fxml"));
        root = loader.load();

        AddEquipoLigaTemporadaController addEquipoLigaTemporadaController = loader.getController();
        addEquipoLigaTemporadaController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addJugadorEquipoLiga(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/addJugadorEquipoLiga.fxml"));
        root = loader.load();

        AddJugadorEquipoLigaController addJugadorEquipoLigaController = loader.getController();
        addJugadorEquipoLigaController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void crearCalendario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/crearCalendario.fxml"));
        root = loader.load();

        CrearCalendarioController crearCalendarioController = loader.getController();
        crearCalendarioController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void jugar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/jugar.fxml"));
        root = loader.load();

        JugarController jugarController = loader.getController();
        jugarController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void simular(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/simular.fxml"));
        root = loader.load();

        SimularController simularController = loader.getController();
        simularController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void guardar(ActionEvent event) {
        try (ObjectOutputStream archivoObjetosSal = new ObjectOutputStream(
                new FileOutputStream("src/main/resources/es/unex/cum/mdp/ef3/data/datos.dat"))) {
            archivoObjetosSal.writeObject(mainController.getCampeonato());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/main.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void borrarCalendario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/borrar.fxml"));
        root = loader.load();

        BorrarCalendarioController borrarCalendarioController = loader.getController();
        borrarCalendarioController.setMainController(mainController);

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
