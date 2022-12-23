package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.es.unex.cum.mdp.ef3.App;

public class MainController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField userTextField;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        App.setRoot("/main/resources/es/unex/cum/mdp/ef3/view/register");
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        App.setRoot("/main/resources/es/unex/cum/mdp/ef3/view/userMenu");
    }
}
