package main.java.es.unex.cum.mdp.ef3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.controller.MainController;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/main.fxml"));
            // Recuperamos el controller
            Parent root = loader.load();
            MainController controller = (MainController) loader.getController();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            // Cuando se cierre se llama al método shutdown de controller
            // primaryStage.setOnHidden(e -> { //Capturar al pulsar Exit
            // controller.shutdown();
            // Platform.exit();
            // });
            primaryStage.show();
            }
            catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}