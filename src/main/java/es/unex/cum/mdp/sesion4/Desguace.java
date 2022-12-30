package main.java.es.unex.cum.mdp.sesion4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;


/**
 * Desguace
 */
public class Desguace {

    private String nombre;
    private ArrayList <Vehiculo> vehiculos;
    private HashMap <String, Empleado> empleados;
    private TreeSet <Pieza> piezasTreeSet;


    public Desguace(){
        vehiculos = new ArrayList<>();
        empleados = new HashMap<>();
        piezasTreeSet = new TreeSet<>();
    }
    
    public Desguace(String nombre, int tam) {
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
        empleados = new HashMap<>();
        piezasTreeSet = new TreeSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Vehiculo getVehiculoBastidor(Integer bastidor){
        for (int i = 0; i < vehiculos.size(); i++) {
             if(vehiculos.get(i).getBastidor().equals(bastidor)){
                return vehiculos.get(i);
            }
        }
        return null;
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
        for (int i = 0; i < vehiculos.size(); i++) {
            info = info + vehiculos.get(i).toString();
        }
        return "Desguace [nombre=" + nombre + "] " ;
    }

    public boolean addVehiculo(Vehiculo v){
        Vehiculo aux=getVehiculoBastidor(v.getBastidor());
        if (aux==null){
            vehiculos.add(v);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean piezaExiste(String idPieza, Vehiculo v){
        for (int i = 0; i < v.getCont(); i++) {
            if (v.getPiezas()[i].getId().equals(idPieza)) {
                int cont = v.getPiezas()[i].getStock();
                v.getPiezas()[i].setStock(cont+getPiezaDesguace(idPieza).getStock() );
                return true;
            }
        }
        return false;
    }

    public boolean addPiezaVehiculo(String id, Integer bastidor) {
        Pieza pieza = null;
        if (bastidor == null || id == null) {
            return false;
        }
        else{
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo.getBastidor().equals(bastidor)) {
                    pieza = (getPiezaDesguace(id));
                    piezasTreeSet.remove(pieza);
                    vehiculo.addPiezaV(pieza);
                    return true;
                }
            }
        }
        return false;
    }

    public int cuantasPiezasPar(){
        int par = 0;
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getCont()>0 && vehiculos.get(i).getCont()%2==0 ) {
                par++;
            }
        }
        return par;
    }

    public String getInfoDerivada(int pos){
        if (pos>=vehiculos.size() || pos<0) {
            return null;
        }
        if (vehiculos.get(pos).getClass().equals(Coche.class)) {
            return ((Coche) vehiculos.get(pos)).getColor();
        }
        else if (vehiculos.get(pos).getClass().equals(Moto.class)) {
            return String.valueOf(((Moto) vehiculos.get(pos)).getPotencia());
        }
        else if (vehiculos.get(pos).getClass().equals(Camion.class)) {
            return String.valueOf(((Camion) vehiculos.get(pos)).getTonelage());
        }
        return null;
    }

    public Vehiculo mayorStock(){
        Collections.sort(vehiculos);
        return vehiculos.get(0);
    }

    public boolean addPiezaDesguace(Pieza pieza){
        if (pieza != null && !piezasTreeSet.contains(pieza)) {
            piezasTreeSet.add(pieza);
            return true;
        }
        return false;
    }

    public Pieza getPiezaDesguace(String id){
        for (Pieza p : piezasTreeSet) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Pieza getPiezaVehiculo(String idPieza, Integer bastidor){
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getBastidor().equals(bastidor)) {
                for (int i = 0; i < vehiculo.getPiezas().length; i++) {
                    if (vehiculo.getPiezas()[i].getId().equals(idPieza)) {
                        return vehiculo.getPiezaV(i);
                    }
                }
            }
        }
        return null;
    }

    public boolean addEmpleado(Empleado e){
        if (empleados != null && !empleados.containsKey(e.getDni())) {
            empleados.put(e.getDni(), e);
            return true;
        }
        return false;
    }

    public Empleado getEmpleado(String dni){
        if (dni != null && !empleados.isEmpty()) {
            return empleados.get(dni);
        }
        return null;
    }

    public float getMediaSueldo(){
        float sueldoTotal = 0f;

        Iterator <String> it = empleados.keySet().iterator();

        while (it.hasNext()) {
            String clave = it.next();
            Empleado empleado = empleados.get(clave);
            sueldoTotal = sueldoTotal + empleado.getSueldo();
        }

        return sueldoTotal/(float) empleados.size();
    }

}