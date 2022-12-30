package main.java.es.unex.cum.mdp.sesion2;


/**
 * Desguace
 */
public class Desguace {

    private String nombre;
    private Vehiculo [] vehiculos;
    private int cont;

    public Desguace(){
        vehiculos = new Vehiculo[5];
    }
    
    public Desguace(String nombre, int tam) {
        this.nombre = nombre;
        this.vehiculos = new Vehiculo[tam];
        cont = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }
    
    public Vehiculo getVehiculoBastidor(Integer bastidor){
        for (int i = 0; i < cont; i++) {
             if(vehiculos[i].getBastidor().equals(bastidor)){
                return vehiculos[i];
            }
        }
        return null;
    }
    

    public void setVehiculos(Vehiculo[] vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Desguace other = (Desguace) obj;
        if (cont != other.cont)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (vehiculos == null) {
            if (other.vehiculos != null)
                return false;
        } else if (!vehiculos.equals(other.vehiculos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String info = "";
        for (int i = 0; i < cont; i++) {
            info = info + vehiculos[i].toString();
        }
        return "Desguace [nombre=" + nombre + "] " ;
    }

    public boolean addVehiculo(Vehiculo v){
        Vehiculo aux=getVehiculoBastidor(v.getBastidor());
        if (aux==null && cont<vehiculos.length){
            vehiculos[cont] = v;
            cont++;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean piezaExiste(Pieza p, Vehiculo v){
        for (int i = 0; i < v.getCont(); i++) {
            if (v.getPiezas()[i].getId().equals(p.getId())) {
                v.getPiezas()[i].setStock(v.getPiezas()[i].getStock() + p.getStock() );
                return true;
            }
        }
        return false;
    }

    public boolean addPiezaVehiculo(Pieza p, Integer bastidor){
        if(bastidor==null || p==null){
            return false;
        }
        else{
            for (int i = 0; i < cont; i++) {
            if (vehiculos[i].getBastidor().equals(bastidor) && piezaExiste(p, vehiculos[i])) {
                return true;
            }
            else if (vehiculos[i].getBastidor().equals(bastidor)) {
                return vehiculos[i].addPiezaV(p);
                
            }
        }
        return false;
        }
    }

    public int cuantasPiezasPar(){
        int par = 0;
        for (int i = 0; i < cont; i++) {
            if (vehiculos[i].getCont()>0 && vehiculos[i].getCont()%2==0 ) {
                par++;
            }
        }
        return par;
    }

    public String getInfoDerivada(int pos){
        if (pos>=cont || pos<0) {
            return null;
        }
        if (vehiculos[pos].getClass().equals(Coche.class)) {
            return ((Coche) vehiculos[pos]).getColor();
        }
        else if (vehiculos[pos].getClass().equals(Moto.class)) {
            return String.valueOf(((Moto) vehiculos[pos]).getPotencia());
        }
        else if (vehiculos[pos].getClass().equals(Camion.class)) {
            return String.valueOf(((Camion) vehiculos[pos]).getTonelage());
        }
        return null;
    }

}