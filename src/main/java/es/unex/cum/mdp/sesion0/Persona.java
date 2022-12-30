/**
 * Implementacion de la clase Persona
 * 
 * @author Luis Arevalo
 *
 */

package main.java.es.unex.cum.mdp.sesion0;

/**
 * Clase que representa a una persona
 *
 */
public class Persona {
	private String nombre;
	private String dni;
	private int edad;

	 /**
	 *  Constructor por defecto
	 */
	public Persona() {
		nombre = new String();
		dni    = "";
		edad   = 0;
	}


	/**
	 * Constructor parametrizado
	 * @param _nombre Nombre de la persona
	 * @param _dni DNI de la persona
	 * @param _edad Edad de la persona
	 */
	public Persona(String _nombre, String _dni, int _edad) {
		this.nombre = _nombre;
		this.dni = _dni;
		this.edad = _edad;
	}
	
	/**
	 *  Constructor de copia
	 *  
	 *  @param p Persona
	 */
	public Persona(Persona p) {
		nombre = p.nombre;
		dni    = p.dni;
		edad   = p.edad;
	}


	/**
	 * @return El nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre El nombre de la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return DNI de la persona
	 */
	public String getDni() {
		return dni;
	}


	/**
	 * @param dni DNI de la persona
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}


	/**
	 * @return Edad de la persona
	 */
	public int getEdad() {
		return edad;
	}


	/**
	 * @param edad Edad de la persona
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}


	/** 
	 * toString
	 */
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", dni=" + dni + ", edad=" + edad
				+ "]";
	}

	
	/** 
	 * Compara dos personas por DNI
	 */
	@Override
	public boolean equals(Object o) {
		Persona p = (Persona) o;
        /* MAL, pues dni es un String y estariamos comparando sus referencias
        * if (dni==p.dni) return true;
        * else return false;
        */
        /* BIEN */
        return dni.equals(p.dni); 
	}

}
