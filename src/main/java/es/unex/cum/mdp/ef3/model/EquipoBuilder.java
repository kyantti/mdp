package main.java.es.unex.cum.mdp.ef3.model;


public class EquipoBuilder {
	private String nombre;
	private String ciudad;
	private int id;
	private Directivo cargo;
	private String escudo;

	public EquipoBuilder() {
		nombre = "";
		ciudad = "";
		cargo = null;
	}	

	
	/** Añade el campo "Nombre" al constructor del equipo.
	 * @param nombre
	 * @return EquipoBuilder
	 */
	public EquipoBuilder withNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}

	/** 
	 * Añade el campo "Ciudad" al constructor del equipo.
	 * @param ciudad
	 * @return EquipoBuilder
	 */
	public EquipoBuilder withCiudad(final String ciudad) {
		this.ciudad = ciudad;
		return this;
	}

	
	/** 
	 * Añade el campo "Cargo" al constructor del equipo.
	 * @param cargo
	 * @return EquipoBuilder
	 */
	public EquipoBuilder withCargo(final Directivo cargo) {
		this.cargo = cargo;
		return this;
	}

	
	/** 
	 * Añade el campo "Id" al constructor del equipo.
	 * @param id
	 * @return EquipoBuilder
	 */
	public EquipoBuilder withId(final int id) {
		this.id = id;
		return this;
	}

	public EquipoBuilder withEscudo(final String escudo) {
		this.escudo = escudo;
		return this;
	}

	
	/** 
	 * Construye el objeto equipo del equipo.
	 * @return Equipo
	 */
	public Equipo build() {
		return new Equipo(this.nombre, this.ciudad, this.id, this.escudo);
	}

	
	/** 
	 * @return String
	 */
	@java.lang.Override
	public String toString() {
		return "Equipo.EquipoBuilder(nombre=" + this.nombre + ", ciudad=" + this.ciudad + ", cargo="
				+ this.cargo + ")";
	}
}