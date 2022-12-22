package main.java.es.unex.cum.mdp.sesion10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainCalculadora extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("src/main/resources/es/unex/cum/mdp/sesion10/view/ej7Calculadora.fxml")); // Ponemos el fichero bfxml creado
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("src/main/resources/es/unex/cum/mdp/sesion10/view/ej7Estilos.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
