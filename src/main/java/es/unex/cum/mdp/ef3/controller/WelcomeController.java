package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.es.unex.cum.mdp.ef3.App;

public class WelcomeController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField userTextField;

    @FXML
    void createAccount(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) throws IOException {
        App.setRoot("view/userMenu");
    }
}
