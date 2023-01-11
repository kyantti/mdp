package main.java.es.unex.cum.mdp.ef3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank() && mainController.getCampeonato().getTemporada(nombreTextField.getText()) == null) {
            addTemporadaButton.setDisable(false);
        }
        else{
            addTemporadaButton.setDisable(true);
        }
        System.out.println(mainController.getCampeonato().getTemporadas().toString());
    }

    @FXML
    void addTemporada(ActionEvent event) {
        if (mainController.getCampeonato().addTemporada(nombreTextField.getText())) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

}
