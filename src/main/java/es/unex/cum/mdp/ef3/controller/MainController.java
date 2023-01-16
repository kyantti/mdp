package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.Temporada;
import main.java.es.unex.cum.mdp.ef3.model.Campeonato;
import main.java.es.unex.cum.mdp.ef3.model.Usuario;

public class MainController implements Initializable {

    private Campeonato campeonato;
    private Usuario usuario;

    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private PasswordField passwordTexField;

    @FXML
    private TextField userTextField;

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    private void verificar() {
        usuario = campeonato.getUsuario(userTextField.getText());
        //campeonato.getUsuarios().clear();
        System.out.println(campeonato.getUsuarios().toString());
        if (usuario != null && usuario.getPassword().equals(passwordTexField.getText())) {
            iniciarSesionButton.setDisable(false);
        } else {
            iniciarSesionButton.setDisable(true);
        }

        //System.out.println(campeonato.getUsuarios().toString());
    }

    @FXML
    void verificarUsuario(KeyEvent event) {
        verificar();
    }

    @FXML
    void verificarPassword(KeyEvent event) {
        verificar();
    }

    @FXML
    void createAccount(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/register.fxml"));	
		root = loader.load();	
		
		RegisterController registerController = loader.getController();
		registerController.setMainController(this);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void login(ActionEvent event) throws IOException {
        
        if (usuario.getTipo().equals("admin")) {
            usuario.setLogueado(true);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/adminMenu.fxml"));
            root = loader.load();

            AdminMenuController adminMenuController = loader.getController();
            adminMenuController.setMainController(this);

            adminMenuController.getBienvenidoLabel().setText("Bienvenido " + usuario.getNombre());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (usuario.getTipo().equals("normal")) {
            usuario.setLogueado(true);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/usuarioNormalMenu.fxml"));
            root = loader.load();

            NormalMenuController normalMenuController = loader.getController();
            normalMenuController.setMainController(this);

            normalMenuController.getBienvenidoLabel().setText("Bienvenido " + usuario.getNombre());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        /*
         * else if (usuario.getPassword().equals(passwordTexField.getText()) &&
         * usuario.getTipo().equals("normal")) {
         * FXMLLoader loader = new FXMLLoader(getClass().getResource(
         * "/main/resources/es/unex/cum/mdp/ef3/view/normalMenu.fxml"));
         * root = loader.load();
         * 
         * 
         * //
         * 
         * stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         * scene = new Scene(root);
         * stage.setScene(scene);
         * stage.show();
         * }
         */
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try (ObjectInputStream archivoObjetosEnt = new ObjectInputStream( new FileInputStream("src/main/resources/es/unex/cum/mdp/ef3/data/datos.dat"))) {
            campeonato = (Campeonato) archivoObjetosEnt.readObject();
            System.out.println("OK");
        }
        catch (ClassNotFoundException | IOException e) {
            campeonato = new Campeonato();
        }  
    }
}
