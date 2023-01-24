package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    private boolean[] registrable = { false, false, false };

    @FXML
    private Button crearCuentaButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField claveTextfield;

    @FXML
    private TextField userTextField;

    @FXML
    private ComboBox<String> tipoComboBox;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void seleccionarTipo(ActionEvent event) {
        if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Administrador")) {
            if (registrable[0] && registrable[1] && registrable[2]) {
                crearCuentaButton.setDisable(false);
            } else {
                crearCuentaButton.setDisable(true);
            }
            claveTextfield.setVisible(true);
        } else if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Normal")) {
            if (registrable[0] && registrable[1]) {
                crearCuentaButton.setDisable(false);
            } else {
                crearCuentaButton.setDisable(true);
            }
            claveTextfield.setVisible(false);
        }
    }

    @FXML
    void verificarUsuario(KeyEvent event) {
        if (mainController.getCampeonato().getUsuario(userTextField.getText()) != null) {
            crearCuentaButton.setDisable(true);
            registrable[0] = false;
        } else if (userTextField.getText().isEmpty() || userTextField.getText().isBlank()) {
            crearCuentaButton.setDisable(true);
            registrable[0] = false;
        } else {
            registrable[0] = true;
        }

        if (registrable[0] && registrable[1]) {
            crearCuentaButton.setDisable(false);
        }
    }

    @FXML
    void verificarPassword(KeyEvent event) {
        if (passwordTextField.getText().length() < 4) {
            crearCuentaButton.setDisable(true);
            registrable[1] = false;
        } else if (passwordTextField.getText().isEmpty() || passwordTextField.getText().isBlank()) {
            crearCuentaButton.setDisable(true);
            registrable[1] = false;
        } else {
            registrable[1] = true;
        }

        if (registrable[0] && registrable[1]) {
            crearCuentaButton.setDisable(false);
        }
    }

    @FXML
    void verificarClave(KeyEvent event) {
        if (!mainController.getCampeonato().getClaves().contains(claveTextfield.getText())) {
            crearCuentaButton.setDisable(true);
            registrable[2] = false;
        } else if (claveTextfield.getText().isEmpty() || claveTextfield.getText().isBlank()) {
            crearCuentaButton.setDisable(true);
            registrable[2] = false;
        } else {
            registrable[2] = true;
        }

        if (registrable[0] && registrable[1] && registrable[2]) {
            crearCuentaButton.setDisable(false);
        }
    }

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        String nombre = userTextField.getText();
        String password = passwordTextField.getText();
        String tipo = tipoComboBox.getSelectionModel().getSelectedItem();

        if (tipo.equals("Administrador")) {
            mainController.getCampeonato().addUsuario(nombre, password, "admin");
        } else if (tipo.equals("Normal")) {
            mainController.getCampeonato().addUsuario(nombre, password, "normal");
            System.out.println(mainController.getCampeonato().getUsuarios().toString());
            
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/view/main.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.addAll("Normal", "Administrador");
        tipoComboBox.setItems(tipos);
        tipoComboBox.getSelectionModel().selectFirst();
    }
}
