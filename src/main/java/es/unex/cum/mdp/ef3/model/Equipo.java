package main.java.es.unex.cum.mdp.ef3.model;

import java.io.Serializable;

public class Equipo implements Serializable {
    protected String nombre;
    protected String ciudad;
    protected int id;
    protected Directivo cargo;
    protected boolean enLiga;

    public Equipo(String nombre){
        this.nombre = nombre;
        ciudad = "";
        cargo = new Directivo("");
    }

    public Equipo(String nombre, String ciudad, int id) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public Directivo getCargo() {
        return cargo;
    }
    public void setCargo(Directivo cargo) {
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnLiga() {
        return enLiga;
    }

    public void setEnLiga(boolean enLiga) {
        this.enLiga = enLiga;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
        result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (ciudad == null) {
            if (other.ciudad != null)
                return false;
        } else if (!ciudad.equals(other.ciudad))
            return false;
        if (cargo == null) {
            if (other.cargo != null)
                return false;
        } else if (!cargo.equals(other.cargo))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", ciudad=" + ciudad + ", cargo=" + cargo + "]";
    }


    
}
