package main.java.es.unex.cum.mdp.sesion10;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculadoraController implements Initializable {

	@FXML
	private TextField textfield;

	private Float operando1;

    private Float operando2;

	private Float resultado;

    private String operacion;

	@FXML
	void ActionCambiarSigno(ActionEvent event) {
		operando1 = -operando1;
		operando2 = -operando2;
	}

	@FXML
	void ActionClear(ActionEvent event) {

	}

	@FXML
	void ActionClearEntry(ActionEvent event) {
		textfield.clear();
	}

	@FXML
	void ActionMsg(ActionEvent event) {
		textfield.appendText("Inserte una operación");
	}

	@FXML
	void actionDividir(ActionEvent event) {
		seleccionarOperacion("/");
	}

	@FXML
	void actionMas(ActionEvent event) {
		seleccionarOperacion("+");
	}

	@FXML
	void actionMenos(ActionEvent event) {
		seleccionarOperacion("-");
	}

	@FXML
	void actionMultiplicar(ActionEvent event) {
		seleccionarOperacion("*");
	}

	@FXML
	void actionPunto(ActionEvent event) {
		textfield.appendText(".");
	}

	@FXML
	void actionIgual(ActionEvent event) {
		operando1 = Float.parseFloat(textfield.getText().split("\\" + operacion)[0]);
		operando2 = Float.parseFloat(textfield.getText().split("\\" + operacion)[1]);

		textfield.clear();

		switch (operacion) {
			case "+":

				resultado = operando1 + operando2;
				textfield.setText(resultado.toString());

				break;
			case "-":
				resultado = operando1 - operando2;
				textfield.setText(resultado.toString());
				break;
			case "*":
				resultado = operando1 * operando2;
				textfield.setText(resultado.toString());
				break;
			case "/":
				resultado = operando1 / operando2;
				textfield.setText(resultado.toString());
				break;

			default:
				break;
		}

	}

	public void seleccionarOperacion(String operacion){
		this.operacion = operacion;
		textfield.appendText(operacion);
	}

	@FXML
	void botonNumerico(ActionEvent event) {
		// En base al valor del text
		String text = ((Button) event.getSource()).getText();

		switch (text) {
			case "0":
			textfield.appendText("0");
				break;
			case "1":
			textfield.appendText("1");
			    break;
			case "2":
			textfield.appendText("2");
			    break;
			case "3":
			textfield.appendText("3");
                break;
			case "4":
			textfield.appendText("4");
				break;
			case "5":
			textfield.appendText("5");
			    break;
			case "6":
			textfield.appendText("6");
			    break;
			case "7":
			textfield.appendText("7");
                break;
			case "8":
			textfield.appendText("8");
				break;
			case "9":
			textfield.appendText("9");
			    break;
			default:
				break;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
