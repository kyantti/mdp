package main.java.es.unex.cum.mdp.ef3.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Campeonato implements Serializable{
    private Map <String, Equipo> equipos;
    private Map <Integer, Persona> federados;
    private List <Temporada> temporadas;
    private List <Usuario> usuarios;
    private List <String> claves;

    public Campeonato(){
        equipos = new HashMap<>();
        federados = new HashMap<>();
        temporadas = new ArrayList<>();
        usuarios = new ArrayList<>();
        claves = new ArrayList<>();
        try {
            leerUsuarios();
            leerClaves();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public Campeonato(Map<String, Equipo> equipos, Map<Integer, Persona> federados, List<Temporada> temporadas) {
        this.equipos = equipos;
        this.federados = federados;
        this.temporadas = temporadas;
    }

    public void leerUsuarios() throws IOException {
        File file = new File("src/main/resources/es/unex/cum/mdp/ef3/data/users.txt");

        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }

        // open a BufferedReader on the file
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // read the lines of the file
        String line;
        while ((line = reader.readLine()) != null) {
            // split the line into its parts
            String[] parts = line.split("#");

            // assign the parts to variables
            String user = parts[0];
            String password = parts[1];
            String type = parts[2];
            usuarios.add(new Usuario(user, password, type));

            // process the user, password, and type variables as needed
        }

        // close the reader
        reader.close();
    }

    public void leerClaves() throws IOException{
        File file = new File("src/main/resources/es/unex/cum/mdp/ef3/data/claves.txt");

        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }

        // open a BufferedReader on the file
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // read the lines of the file
        String line;
        while ((line = reader.readLine()) != null) {
            claves.add(line);

            // process the user, password, and type variables as needed
        }

        // close the reader
        reader.close();
        
    }

    public Map<String, Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(Map<String, Equipo> equipos) {
        this.equipos = equipos;
    }

    public Map<Integer, Persona> getFederados() {
        return federados;
    }

    public void setFederados(Map<Integer, Persona> federados) {
        this.federados = federados;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<String> getClaves() {
        return claves;
    }

    public void setClaves(List<String> claves) {
        this.claves = claves;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipos == null) ? 0 : equipos.hashCode());
        result = prime * result + ((federados == null) ? 0 : federados.hashCode());
        result = prime * result + ((temporadas == null) ? 0 : temporadas.hashCode());
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
        Campeonato other = (Campeonato) obj;
        if (equipos == null) {
            if (other.equipos != null)
                return false;
        } else if (!equipos.equals(other.equipos))
            return false;
        if (federados == null) {
            if (other.federados != null)
                return false;
        } else if (!federados.equals(other.federados))
            return false;
        if (temporadas == null) {
            if (other.temporadas != null)
                return false;
        } else if (!temporadas.equals(other.temporadas))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Campeonato [equipos=" + equipos + ", federados=" + federados + ", temporadas=" + temporadas + "]";
    }

    /**
     * Añade una nueva temporada a partir de un nombre.
     * @param nombre
     * @return {@code true} si le temporanda se ha añadido, {@code false} de lo contrario.
     */
    public boolean addTemporada(String nombre){
        if (nombre!=null) {
           for (Temporada temporada : temporadas) {
            if (temporada.getNombre().equals(nombre)) {
                return false;
            }
           }
           temporadas.add(new Temporada(nombre));
           return true;
        }
        return false;
    }

    /**
     * Obtiene una temporada a través de su nombre.
     * @param nombre
     * @return {@code Temporada}
     */
    public Temporada getTemporada(String nombre) /*throws NoTemporadaException*/ {
        for (Temporada temporada : temporadas) {
            if (temporada.getNombre().equals(nombre)) {
                return temporada;
            }
        }
        return null;

    }

    /**
     * Añade un nuevo equipo a partir de su nombre, ciudad e identificador.
     * @param nombre
     * @return {@code true} si el equipo se ha añadido, {@code false} de lo contrario.
     */
    public boolean addEquipo(String nombre, String ciudad, int id) {
        if (nombre != null && ciudad != null &&  !equipos.containsKey(nombre)){
            Persona directivo = federados.get(id);
            if (directivo != null && directivo.getClass().equals(Directivo.class)) {
                equipos.put(nombre, new EquipoBuilder().withNombre(nombre).withCiudad(ciudad).withId(id).withCargo((Directivo) directivo).build());
                return true;
            }
        }

        return false;
    }

    /**
     * Obtiene un equipo a través de su nombre.
     * @param nombre
     * @return {@code Equipo}
     */
    public Equipo getEquipo(String nombre)/*throws NoEquipoException */{
        if (nombre !=null) {
            return equipos.get(nombre);
        }
        return null;
    }

    /**
     * Añade un nueva persona a partir de su tipo, nombre, identificador, edad, valor1 y valor2. Siendo estos dos ultimos atributos propios de cierto tipo de federados.
     * @param tipo
     * @param nombre
     * @param id
     * @param edad
     * @param value1
     * @param value2
     * @return {@code true} si le persona se ha añadido, {@code false} de lo contrario.
     */
    public boolean addPersona(String tipo, String nombre, int id, int edad, String value1, String value2) /*throws IllegalArgumentException*/{
        if (tipo != null && nombre != null && !federados.containsKey(id)) {
            federados.put(id, new FactoriaPersona().build(tipo, nombre, id, edad, value1, value2));
            return true;
        }
        return false;
    }

    /**
     * Obtiene una persona a través de su identificador.
     * @param id
     * @return {@code Persona}
     */
    public Persona getPersona(int id){
        return federados.get(id);
    }

    /**
     * Añade una nueva liga a partir de un nombre a una temporada indicada por su nombre.
     * @param nomTemporada
     * @param nomLiga
     * @return {@code true} si le liga se ha añadido, {@code false} de lo contrario.
     */
    public boolean crearLigaTemporada(String nomTemp, String nomLiga){
        if (nomLiga != null && nomTemp != null && getTemporada(nomTemp) != null) {
            for (Temporada temporada : temporadas) {
                if (temporada.getNombre().equals(nomTemp) && temporada.getLigas().containsKey(nomLiga)) {
                    return false;
                }
            }
            getTemporada(nomTemp).getLigas().put(nomLiga, new Liga(nomLiga));
            return true;
        }
        return false;
    }

    /**
     * Añade un jugador a un equipo de una determinada liga y temporada.
     * @param nomTemp
     * @param nomLiga
     * @param nomEq
     * @param idJug
     * @return  {@code true} si el jugador se ha añadido, {@code false} de lo contrario.
     * @throws NoLigaException
     */
    public boolean addJugadorEquipoLigaTemporada(String nomTemp, String nomLiga, String nomEq, int idJug) {

        if (nomTemp != null && nomLiga != null && nomEq != null) {
            try {
                EquipoLiga equipoLiga = getTempLiga(nomTemp, nomLiga).getEquipoLiga(nomEq);
                if (equipoLiga!= null && !equipoLiga.getJugadores().contains(equipoLiga.getJugador(idJug))) {
                    equipoLiga.addJugadores(new Jugador(String.valueOf(idJug)));
                    return true;
                }
                return false;
            } catch (NoLigaException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    
    /** 
     * Crea un calendario para una determinada liga y temporada. Se especifica el tipo de juego para el que se crea el calendario.
     * @param nomTemp
     * @param nomLiga
     * @param tipo
     * @return  {@code true} si el calendario se ha creado, {@code false} de lo contrario. 
     * @throws NoLigaException
     */
    public boolean crearCalendario(String nomTemp, String nomLiga, String tipo){
        try {
            Liga liga = getTempLiga(nomTemp, nomLiga);
            if (nomTemp != null && nomLiga != null && tipo != null  && liga.getCalendario().isEmpty()) {
                return liga.crearCalendario(tipo);
            }
        } catch (NoLigaException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    /** 
     * Obtiene una liga para un determinada temporada a través de sus nombres.
     * @param nomTemp
     * @param nomLiga
     * @return Liga
     * @throws NoLigaException
     */
    public Liga getTempLiga(String nomTemp, String nomLiga) throws NoLigaException {
        if (nomTemp != null && nomLiga != null && getTemporada(nomTemp) != null) {
            Liga liga = getTemporada(nomTemp).getLigas().get(nomLiga);
            if (liga != null){
                return liga;
            }
        }
        throw new NoLigaException();
    }

    
    /** 
     * Añade un equipo a una liga de una determinada temporada a partir de su coeficiente.
     * @param nomTemp
     * @param nomLiga
     * @param nomEq
     * @param coef
     * @return  {@code true} si el equipo se ha añadido, {@code false} de lo contrario.
     */
    public boolean addEquipoLigaTemporada(String nomTemp, String nomLiga, String nomEq, int coef) {
        try {
            if (nomTemp != null && nomLiga != null && nomEq != null && getTempLiga(nomTemp, nomLiga) != null && equipos.get(nomEq) != null) {

                try {
                    for (EquipoLiga equipoLiga : getTempLiga(nomTemp, nomLiga).getEquiposLiga()) {
                        if (equipoLiga.getE().getNombre().equals(nomEq)) {
                            return false;
                        }
                    }
                } catch (NoLigaException e) {
                    e.printStackTrace();
                }
                getTempLiga(nomTemp, nomLiga).getEquiposLiga().add(new EquipoLiga(coef, new EquipoBuilder().withNombre(nomEq).build()));
                return true;
            }
        } catch (NoLigaException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    /** 
     * Juega un partido de una determianda jornada, liga y temporada. Se indican el numero de goles del equipo local y visitante.
     * @param nomTemp
     * @param nomLiga
     * @param jornada
     * @param partido
     * @param golLocal
     * @param golVis
     * @return  {@code true} si el partido se ha jugado, {@code false} de lo contrario.
     * @throws NoLigaException
     */
    public boolean jugar(String nomTemp, String nomLiga, int numJornada, int idPartido, int[] golLocal, int[] golVis ) {

        if (nomTemp != null && nomLiga != null && golLocal.length == Partido.getNumEnfrentamiento() && golVis.length == Partido.getNumEnfrentamiento()) {
            try {
                Liga liga = getTempLiga(nomTemp, nomLiga);
                for (Jornada jornada : liga.getCalendario()) {
                    if (jornada.getNumero() == numJornada) {
                        for (Partido partido : jornada.getPartidos()) {
                            if (partido.getId() == idPartido) {
                                partido.jugar(golLocal, golVis);
                                partido.actualizarEstadistica();
                                return true;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    
    /** 
     * Juega un partido de una determianda liga y temporada.
     * @param nomTemp
     * @param nomLiga
     * @return boolean
     * @throws NoligaException
     */
    public boolean simular(String nomTemp, String nomLiga) {
        if (nomTemp != null && nomLiga != null) {
            try {
                getTempLiga(nomTemp, nomLiga).simular();
            } catch (NoLigaException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    
    /** 
     * Muestra las estadisticas una determinada liga y temporada segun el tipo de juego.
     * @param nomTemp
     * @param nomLiga
     * @param tipo
     * @return boolean
     */
    public boolean mostrarEstadistica(String nomTemp, String nomLiga, String tipo){
        if (nomTemp != null && nomLiga != null && tipo != null) {
            try {
                Liga liga = getTempLiga(nomTemp, nomLiga);
                if (tipo.equals("Puntos")) {
                    liga.getEquiposLiga().sort(new ComparatorPuntos());
                    return true;
                }
                else if (tipo.equals("Goles")) {
                    liga.getEquiposLiga().sort(new ComparatorGoles());
                    return true;
                }
                else if (tipo.equals("Enfrentamientos")) {
                    liga.getEquiposLiga().sort(new ComparatorEnfrentamientos());
                    return true;
                }
                else if (tipo.equals("Partidos")) {
                    liga.getEquiposLiga().sort(new ComparatorPartidos());
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Usuario getUsuario(String nombre){
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public void addUsuario(String nombre, String password, String tipo) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src/main/resources/es/unex/cum/mdp/ef3/data/users.txt", true))) {
            writer.write(nombre + "#" + password + "#" + tipo);
            writer.newLine();
            usuarios.add(new Usuario(nombre, password, tipo));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
