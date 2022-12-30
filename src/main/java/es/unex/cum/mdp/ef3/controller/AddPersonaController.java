package main.java.es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


public class AddPersonaController implements Initializable {

    private boolean[] jugadorRegistrable = new boolean[4];
    private boolean[] juezRegistrable = new boolean[3];
    private boolean[] directivoRegistrable = new boolean[3];

    private int edad;
    private int antiguedad;

    @FXML
    private Button addPersonaButton;

    @FXML
    private DatePicker antiguedadDatePicker;

    @FXML
    private ComboBox<Integer> coeficienteComboBox;

    @FXML
    private DatePicker edadDatePicker;

    @FXML
    private TextField nickTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox<String> puestoComboBox;

    @FXML
    private ComboBox<String> tipoComboBox;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private ToggleButton blancoButton;

    @FXML
    private ToggleButton medioButton;

    @FXML
    private ToggleButton morenoButton;

    @FXML
    private ToggleButton negroButton;

    private MainController mainController = null;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void verificar(){
        if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Jugador")) {
            if (jugadorRegistrable[0] && jugadorRegistrable[1] && jugadorRegistrable[2] && jugadorRegistrable[3]) {
                addPersonaButton.setDisable(false);
            } else {
                addPersonaButton.setDisable(true);
            }
        } else if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Juez")){
            System.out.println("Juez");
            for (int i = 0; i < juezRegistrable.length; i++) {
                System.out.println("Atributo " + i + ": " + juezRegistrable[i]);
            }
            if (juezRegistrable[0] && juezRegistrable[1] && juezRegistrable[2]) {
                addPersonaButton.setDisable(false);

                System.out.println("Boton activado");
            } else {
                addPersonaButton.setDisable(true);
            }
        } else if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Directivo")){
            if (directivoRegistrable[0] && directivoRegistrable[1] && directivoRegistrable[2]) {
                addPersonaButton.setDisable(false);
            } else {
                addPersonaButton.setDisable(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList <String> tipos = FXCollections.observableArrayList();
        tipos.addAll("Jugador", "Juez", "Directivo");
        tipoComboBox.setItems(tipos);
        tipoComboBox.getSelectionModel().selectFirst();

        ObservableList <String> puestos = FXCollections.observableArrayList();
        puestos.addAll("Puesto 1", "Puesto 2", "Puesto 3", "Puesto 4");
        puestoComboBox.setItems(puestos);

        ObservableList <Integer> coeficientes = FXCollections.observableArrayList();
        coeficientes.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        coeficienteComboBox.setItems(coeficientes);
        coeficienteComboBox.setVisible(true);

        //ImageView imageview = new ImageView(getClass().getResource("src/main/resources/es/unex/cum/mdp/ef3/images/chicoBlanco.png").toExternalForm());
        //blancoButton.setGraphic(imageview);
        
    }

    @FXML
    void seleccionarTipo(ActionEvent event) {
        int i = 0;
        if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Jugador")) {
            // Jugador
            nickTextField.clear();
            nickTextField.setVisible(true);
            coeficienteComboBox.setValue(null);
            coeficienteComboBox.setVisible(true);
            for (i = 0; i < jugadorRegistrable.length; i++) {
                jugadorRegistrable[i] = false;
            }
            // Juez
            antiguedadDatePicker.setVisible(false);
            // Directivo
            puestoComboBox.setVisible(false);

            verificar();

        } else if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Juez")) {
            // Jugador
            nickTextField.setVisible(false);
            coeficienteComboBox.setVisible(false);
            // Juez
            
            antiguedadDatePicker.setValue(null);
            antiguedadDatePicker.setVisible(true);
            
            for (i = 0; i < juezRegistrable.length; i++) {
                juezRegistrable[i] = false;
            }
            // Directivo
            puestoComboBox.setVisible(false);

            verificar();

        } else if (tipoComboBox.getSelectionModel().getSelectedItem().equals("Directivo")) {
            // Jugador
            nickTextField.setVisible(false);
            coeficienteComboBox.setVisible(false);
            // Juez
            antiguedadDatePicker.setValue(null);
            antiguedadDatePicker.setVisible(true);
            // Directivo
            puestoComboBox.setValue(null);
            puestoComboBox.setVisible(true);
            for (i = 0; i < directivoRegistrable.length; i++) {
                directivoRegistrable[i] = false;
            }

            verificar();
        }
        // Todos
        nombreTextField.clear();
        nombreTextField.setVisible(true);

        System.out.println("HOLA");
        edadDatePicker.setValue(null);
        edadDatePicker.setVisible(true);
        System.out.println("ADIOS");
    }

    @FXML
    void verficarNombre(KeyEvent event) {
        if (!nombreTextField.getText().isEmpty() && !nombreTextField.getText().isBlank()) {
            jugadorRegistrable[0] = true;
            juezRegistrable[0] = true;
            directivoRegistrable[0] = true;

            System.out.println("Nombre correcto");
        } else {
            jugadorRegistrable[0] = false;
            juezRegistrable[0] = false;
            directivoRegistrable[0] = false;
        }

        verificar();
    }

    @FXML
    void seleccionarEdad(ActionEvent event) {
        edad = Year.now().getValue() - edadDatePicker.getValue().getYear();
        if (edad >= 14 && edad <= 90) {
            jugadorRegistrable[1] = true;
            juezRegistrable[1] = true;
            directivoRegistrable[1] = true;

            System.out.println("Edad = " + edad);
        } else {
            jugadorRegistrable[1] = false;
            juezRegistrable[1] = false;
            directivoRegistrable[1] = false;
        }

        verificar();
    }

    @FXML
    void seleccionarAntiguedad(ActionEvent event) {
        antiguedad = Year.now().getValue() - antiguedadDatePicker.getValue().getYear();
        if (antiguedad >= 0  && antiguedad <= 50) {
            juezRegistrable[2] = true;
            System.out.println("Antiguedad = " + antiguedad);
        } else {
            juezRegistrable[2] = false;
        }

        verificar();

        System.out.println("Verificado");
    }

    @FXML
    void verificarNick(KeyEvent event) {
        if (!nickTextField.getText().isEmpty() && !nickTextField.getText().isBlank()) {
            jugadorRegistrable[2] = true;
        } else {
            jugadorRegistrable[2] = false;
        }

        verificar();
    }

    @FXML
    void seleccionarCoef(ActionEvent event) {
        jugadorRegistrable[3] = true;

        verificar();
    }

    @FXML
    void seleccionarPuesto(ActionEvent event) {
        directivoRegistrable[2] = true;

        verificar();
    }

    @FXML
    void seleccionarAspecto(ActionEvent event) {

    }

    @FXML
    void addPersona(ActionEvent event) {
        String tipo = tipoComboBox.getSelectionModel().getSelectedItem();
        String nombre = nombreTextField.getText();
        int id = mainController.getCampeonato().getFederados().size();

        if (tipo.equals("Jugador")) {
            String nick = nickTextField.getText();
            int coeficiente = coeficienteComboBox.getSelectionModel().getSelectedItem();
            mainController.getCampeonato().addPersona(tipo, nombre, id, edad, nick , String.valueOf(coeficiente));
        } else if (tipo.equals("Juez")) {
            mainController.getCampeonato().addPersona(tipo, nombre, id, edad, String.valueOf(antiguedad), null);
        } else if (tipo.equals("Directivo")) {
            String puesto = puestoComboBox.getSelectionModel().getSelectedItem();
            mainController.getCampeonato().addPersona(tipo, nombre, id, edad, puesto, null);
        }

        System.out.println("El id es " + id);
        System.out.println(mainController.getCampeonato().getFederados().toString());

    }
    
}
