package main.java.es.unex.cum.mdp.ef3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddTemporadaController {
    @FXML
    private Button addTemporadaButton;

    @FXML
    private TextField nombreTextField;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void verificarNombre(KeyEvent event) {
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank()) {
            addTemporadaButton.setDisable(false);
        }
        else{
            addTemporadaButton.setDisable(true);
        }
    }

    @FXML
    void addTemporada(ActionEvent event) {
        mainController.getCampeonato().addTemporada(nombreTextField.getText());
    }

}
