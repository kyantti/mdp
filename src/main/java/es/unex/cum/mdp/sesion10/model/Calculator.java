package main.java.es.unex.cum.mdp.sesion10.model;

public class Calculator {
	private Double operand1;
    private Double operand2;
    private Double result;

    public Calculator(){
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
    }

    public Double getOperand1() {
        return operand1;
    }

    public void setOperand1(double d) {
        this.operand1 = d;
    }

    public Double getOperand2() {
        return operand2;
    }

    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double sum(){
        return operand1 + operand2;
    }

    public Double subtraction(){
        return operand1 - operand2;
    }

    public Double mutiplication(){
        return operand1 * operand2;
    }

    public Double division(){
        return operand1 / operand2;
    }

    public void reset(){
        operand1 = 0.0;
        operand2 = 0.0;
        result = 0.0;
    }


}
