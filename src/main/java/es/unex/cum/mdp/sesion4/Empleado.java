package main.java.es.unex.cum.mdp.sesion4;

import java.util.Date;

public class Empleado extends Persona {

    private float sueldo;
    private Date fecha;

    public Empleado(String nombre, String dni, int edad, float sueldo, Date fecha) {
        super(nombre, dni, edad);
        this.sueldo = sueldo;
        this.fecha = fecha;
    }

    public Empleado(Persona p) {
        super(p);
    }

    public Empleado(String nombre, String dni, int edad) {
        super(nombre, dni, edad);
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
