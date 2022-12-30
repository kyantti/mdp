package main.java.es.unex.cum.mdp.sesion7;

public class Coche extends Vehiculo {

    private String color;

    public Coche(String marca,String modelo, Persona propietario, Integer bastidor, String color){
        super(marca, modelo, propietario, bastidor);
        this.color = color;
    }

    
    
    @Override
    public String toString() {
        return "Coche [marca="+ super.getMarca()+ "modelo=" + super.getModelo() + "bastidor=" + super.getBastidor()+ "]";
    }



    public String getColor() {
        return color;
    }



    public void setColor(String color) {
        this.color = color;
    }

    
}