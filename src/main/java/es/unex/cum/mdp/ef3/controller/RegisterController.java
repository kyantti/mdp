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
        App.setRoot("/main/resources/es/unex/cum/mdp/ef3/view/register");
    }

    @FXML
    void setPassword(ActionEvent event) {

    }

    @FXML
    void setSpecialCode(ActionEvent event) {

    }

    @FXML
    void setUser(ActionEvent event) {

    }

}
