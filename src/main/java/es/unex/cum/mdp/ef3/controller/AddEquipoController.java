package main.java.es.unex.cum.mdp.ef3.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.java.es.unex.cum.mdp.ef3.model.Directivo;
import main.java.es.unex.cum.mdp.ef3.model.Persona;

public class AddEquipoController implements Initializable{

    private boolean[] registrable = new boolean[3];

    private List <String> ciudades = new ArrayList<>();
    
    @FXML
    private TextField ciudadTextField;

    @FXML
    private ComboBox<String> directivoComboBox;

    @FXML
    private Button addEquipoButton;

    @FXML
    private TextField nombreTextField;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void verificar() {
        if (registrable[0] && registrable[1] && registrable[2]) {
            addEquipoButton.setDisable(false);
        }
        else{
            addEquipoButton.setDisable(true);
        }
    }

    public void leerCiudades() throws IOException{
        File file = new File("src/main/resources/es/unex/cum/mdp/ef3/data/ciudades.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            ciudades.add(line);
        }
        reader.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            leerCiudades();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    void verificarNombre(KeyEvent event) {
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank()) {
            registrable[0] = true;
        } else {
            registrable[0] = false;
        }
        
        verificar();
    }


    @FXML
    void verificarCiudad(KeyEvent event) {
        if ( !ciudadTextField.getText().isEmpty() && !ciudadTextField.getText().isBlank() && ciudades.contains(ciudadTextField.getText())) {
            registrable[1] = true;
        } else {
            registrable[1] = false;
        }

        verificar();
    }

    @FXML
    void mostrarDirectivos(MouseEvent event) {
        int id = 0;
        String nombre = "";

        ObservableList <String> directivosObservableList = FXCollections.observableArrayList();
        if (!mainController.getCampeonato().getFederados().isEmpty()) {
            for (Map.Entry<Integer, Persona> set : mainController.getCampeonato().getFederados().entrySet()){
                if (set.getValue().getClass().equals(Directivo.class)) {
                    nombre = set.getValue().getNombre();
                    id = set.getValue().getId();
                    directivosObservableList.add("Nombre: " + nombre + " ID: " + id);
                }
            }
            directivoComboBox.setItems(directivosObservableList);
        }
    }

    @FXML
    void seleccionarDirectivo(ActionEvent event) {
        registrable[2] = true;

        verificar();
    }

    @FXML
    void addEquipo(ActionEvent event) {
        String nombre = nombreTextField.getText();
        String ciudad = ciudadTextField.getText();
        int id = Integer.parseInt(directivoComboBox.getSelectionModel().getSelectedItem().split(" ID: ")[1]);

        mainController.getCampeonato().addEquipo(nombre, ciudad, id);
    }

}
