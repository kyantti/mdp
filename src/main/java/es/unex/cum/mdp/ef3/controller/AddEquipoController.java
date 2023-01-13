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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.es.unex.cum.mdp.ef3.model.Directivo;
import main.java.es.unex.cum.mdp.ef3.model.Persona;

public class AddEquipoController implements Initializable{

    private String escudo;

    private boolean[] registrable = new boolean[4];

    private List <String> ciudades = new ArrayList<>();
    
    @FXML
    private TextField ciudadTextField;

    @FXML
    private ComboBox<String> directivoComboBox;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private ToggleButton escudo1Button;

    @FXML
    private ToggleButton escudo2Button;

    @FXML
    private ToggleButton escudo3Button;

    @FXML
    private ToggleButton escudo4Button;

    @FXML
    private Button addEquipoButton;

    @FXML
    private TextField nombreTextField;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            leerCiudades();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Boca jr
        ImageView escudo1 = new ImageView(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/images/escudo1.png").toExternalForm());
        escudo1Button.setGraphic(escudo1);
        escudo1Button.setContentDisplay(ContentDisplay.TOP);
        escudo1.fitWidthProperty().bind(escudo1Button.widthProperty().divide(1.6));
        escudo1.setPreserveRatio(true);
        escudo1Button.setMaxWidth(Double.MAX_VALUE);

        //Riverplate
        ImageView escudo2 = new ImageView(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/images/escudo2.png").toExternalForm());
        escudo2Button.setGraphic(escudo2);
        escudo2Button.setContentDisplay(ContentDisplay.TOP);
        escudo2.fitWidthProperty().bind(escudo2Button.widthProperty().divide(1.6));
        escudo2.setPreserveRatio(true);
        escudo2Button.setMaxWidth(Double.MAX_VALUE);

        //Newell
        ImageView escudo3 = new ImageView(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/images/escudo3.png").toExternalForm());
        escudo3Button.setGraphic(escudo3);
        escudo3Button.setContentDisplay(ContentDisplay.TOP);
        escudo3.fitWidthProperty().bind(escudo3Button.widthProperty().divide(1.6));
        escudo3.setPreserveRatio(true);
        escudo3Button.setMaxWidth(Double.MAX_VALUE);

        //Rosario
        ImageView escudo4 = new ImageView(getClass().getResource("/main/resources/es/unex/cum/mdp/ef3/images/escudo4.png").toExternalForm());
        escudo4Button.setGraphic(escudo4);
        escudo4Button.setContentDisplay(ContentDisplay.TOP);
        escudo4.fitWidthProperty().bind(escudo3Button.widthProperty().divide(1.6));
        escudo4.setPreserveRatio(true);
        escudo4Button.setMaxWidth(Double.MAX_VALUE);
    }

    private void verificar() {
        if (registrable[0] && registrable[1] && registrable[2] && registrable[3]) {
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

    @FXML
    void verificarNombre(KeyEvent event) {
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank() && mainController.getCampeonato().getEquipo(nombreTextField.getText()) == null) {
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
    void seleccionarEscudo(ActionEvent event) {
        if (grupo.getSelectedToggle().equals(escudo1Button)) {
            escudo = "/main/resources/es/unex/cum/mdp/ef3/images/escudo1.png";
        }
        else if (grupo.getSelectedToggle().equals(escudo2Button)) {
            escudo = "/main/resources/es/unex/cum/mdp/ef3/images/escudo2.png";
        }
        else if (grupo.getSelectedToggle().equals(escudo3Button)) {
            escudo = "/main/resources/es/unex/cum/mdp/ef3/images/escudo3.png";
        }
        else if (grupo.getSelectedToggle().equals(escudo4Button)) {
            escudo = "/main/resources/es/unex/cum/mdp/ef3/images/escudo4.png";
        }
        
        if (grupo.getSelectedToggle() != null) {
            registrable[3] = true;
        } else {
            registrable[3] = false;
        }

        verificar();
    }

    @FXML
    void addEquipo(ActionEvent event) {
        String nombre = nombreTextField.getText();
        String ciudad = ciudadTextField.getText();
        int id = Integer.parseInt(directivoComboBox.getSelectionModel().getSelectedItem().split(" ID: ")[1]);

        if (mainController.getCampeonato().addEquipo(nombre, ciudad, id, escudo)) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            System.out.println(mainController.getCampeonato().getEquipos().toString());
        }
    }

}
