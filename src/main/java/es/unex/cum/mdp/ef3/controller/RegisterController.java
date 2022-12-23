package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.java.es.unex.cum.mdp.ef3.App;

public class RegisterController {
    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField specialCodeTextfield;

    @FXML
    private TextField userTextField;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        String nombre = userTextField.getText();
        String password = passwordTextField.getText();
        String clave = specialCodeTextfield.getText();
        if (!nombre.isBlank() && !password.isBlank()) {
            App.getCampeonato().crearCuenta(nombre, password, clave);
            App.setRoot("/main/resources/es/unex/cum/mdp/ef3/view/main");
        }
    }
}
