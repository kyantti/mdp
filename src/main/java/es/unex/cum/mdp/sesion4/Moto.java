package main.java.es.unex.cum.mdp.sesion4;



/**
 * Moto
 */
public class Moto extends Vehiculo{

    private int potencia;

    public Moto(String marca,String modelo, Persona propietario, Integer bastidor, int potencia){
        super(marca, modelo, propietario, bastidor);
        this.potencia = potencia;
    }

    

    @Override
    public String toString() {
        return "Moto [marca="+ super.getMarca()+ "modelo=" + super.getModelo() + "bastidor=" + super.getBastidor()+ "]";
    }



    public int getPotencia() {
        return potencia;
    }



    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    
}