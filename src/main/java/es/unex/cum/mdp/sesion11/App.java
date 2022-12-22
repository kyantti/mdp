package main.java.es.unex.cum.mdp.sesion11;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    private static Scene scene;
    private static ResourceBundle resourceBundle;

    @Override
    public void start(Stage stage) throws IOException {
        Locale l=new Locale("es","ES");
        resourceBundle = ResourceBundle.getBundle("/main/misMensajes",l);
        scene = new Scene(loadFXML("/main/resources/es/unex/cum/mdp/sesion11/view/Main"), 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resourceBundle);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
