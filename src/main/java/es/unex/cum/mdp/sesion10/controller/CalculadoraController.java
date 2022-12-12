package main.java.es.unex.cum.mdp.sesion10.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.es.unex.cum.mdp.sesion10.model.Calculator;

public class CalculadoraController implements Initializable {

	private Calculator calculator = new Calculator();
	private String operation = "";
	private String currentNumber = "";

	@FXML
    private Button botonMas;

	@FXML
	private TextField textfield;

    @FXML
    void actionClear(ActionEvent event) {
		currentNumber = "";
		textfield.clear();
		calculator.reset();
    }

    @FXML
    void actionClearEntry(ActionEvent event) {
		textfield.clear();
    }

	@FXML
	void actionChangeSign(ActionEvent event){
		Double number = Double.parseDouble(currentNumber);
		number = - number;
		textfield.setText(number.toString());
	}

    @FXML
    void actionMsg(ActionEvent event) {
		textfield.appendText("Inserte una operación");
    }

	@FXML
	void actionCalculate(ActionEvent event){
		calculator.setOperand2(Double.parseDouble(textfield.getText()));
		System.out.println(operation);
		System.out.println("B = " + calculator.getOperand2());
		textfield.clear();

		switch (operation) {
			case "+":
			textfield.appendText(calculator.sum().toString());
				break;
			case "-":
			textfield.appendText(calculator.subtraction().toString());
			    break;
			case "*":
			textfield.appendText(calculator.mutiplication().toString());
			    break;
			case "/":
			textfield.appendText(calculator.division().toString());
			    break;
			default:
				break;
		}
		
	}

	@FXML
	void actionOperation(ActionEvent event){
		if (!currentNumber.equals("")) {
			calculator.setOperand1(Double.parseDouble(textfield.getText()));
		System.out.println("A = " + calculator.getOperand1());
		}
		String text = ((Button) event.getSource()).getText();
		textfield.clear();
		operation = text;
		switch (text) {
			case "+":
			textfield.appendText("+");
				break;
			case "-":
			textfield.appendText("-");
			    break;
			case "*":
			textfield.appendText("*");
			    break;
			case "/":
			textfield.appendText("/");
                break;
			default:
				break;
		}
	}

	@FXML
	void actionNumber(ActionEvent event) {
		if (textfield.getText().contains("+") || textfield.getText().contains("*") || textfield.getText().contains("/") || textfield.getText().contains("-")) {
			textfield.clear();
		}
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
			case ".":
			textfield.appendText(".");
			    break;
			default:
				break;
		}
		currentNumber = textfield.getText();
		System.out.println(currentNumber);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
