package main.java.es.unex.cum.mdp.sesion7;

public class Camion extends Vehiculo{
    
    private int tonelage;

    public Camion(String marca,String modelo, Persona propietario, Integer bastidor, int tonelage){
        super(marca, modelo, propietario, bastidor);
        this.tonelage = tonelage;
    }


    @Override
    public String toString() {
        return "Camion [marca="+ super.getMarca()+ "modelo=" + super.getModelo() + "bastidor=" + super.getBastidor()+ "]";
    }



    public int getTonelage() {
        return tonelage;
    }



    public void setTonelage(int tonelage) {
        this.tonelage = tonelage;
    }

    
}