package main.java.es.unex.cum.mdp.sesion11.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class MainController implements Initializable {

    @FXML
    private Button btAccept;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleButton btnEnglish;

    @FXML
    private ToggleButton btnSpanish;

    @FXML
    private ToggleGroup grupo1;

    @FXML
    void acceptOrCancel(ActionEvent event) {

    }

    @FXML
    void changeLenguague(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
