package main.java.es.unex.cum.mdp.sesion11;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) {
        try {
            Locale l = new Locale("es", "ES");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("/main/resources/es/unex/cum/mdp/sesion11/i18/misMensajes", l);
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/main/resources/es/unex/cum/mdp/sesion11/view/Main.fxml"), resourceBundle);
            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
