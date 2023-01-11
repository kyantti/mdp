package main.java.es.unex.cum.mdp.ef3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Liga implements Serializable {
    private String nombre;
    private List <Jornada> calendario;
    private List <EquipoLiga> equiposLiga;

    public Liga(String nombre){
        this.nombre = nombre;
        calendario = new ArrayList<>();
        equiposLiga = new LinkedList<>();
    }

    public Liga(String nombre, List<Jornada> calendario, List<EquipoLiga> equiposLiga) {
        this.nombre = nombre;
        this.calendario = calendario;
        this.equiposLiga = equiposLiga;
    }

    
    /** 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return List<Jornada>
     */
    public List<Jornada> getCalendario() {
        return calendario;
    }

    
    /** 
     * @param calendario
     */
    public void setCalendario(List<Jornada> calendario) {
        this.calendario = calendario;
    }

    
    /** 
     * @return List<EquipoLiga>
     */
    public List<EquipoLiga> getEquiposLiga() {
        return equiposLiga;
    }

    
    /** 
     * @param equiposLiga
     */
    public void setEquiposLiga(List<EquipoLiga> equiposLiga) {
        this.equiposLiga = equiposLiga;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((calendario == null) ? 0 : calendario.hashCode());
        result = prime * result + ((equiposLiga == null) ? 0 : equiposLiga.hashCode());
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Liga other = (Liga) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (calendario == null) {
            if (other.calendario != null)
                return false;
        } else if (!calendario.equals(other.calendario))
            return false;
        if (equiposLiga == null) {
            if (other.equiposLiga != null)
                return false;
        } else if (!equiposLiga.equals(other.equiposLiga))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Liga [nombre=" + nombre + ", calendario=" + calendario + ", equiposLiga=" + equiposLiga + "]";
    }

    
    /** 
     * Añade una jornada al calendario.
     * @param jornada
     * @return boolean
     */
    public boolean addJornada(Jornada jornada){
        if (jornada != null && !calendario.contains(jornada)) {
            calendario.add(jornada);
            return true;
        }
        return false;
    }

    
    /** 
     * Obtiene una jornada del calendario a través de su numero.
     * @param numero
     * @return Jornada
     */
    public Jornada getJornada(int numero){
        if (!calendario.isEmpty()) {
            return calendario.get(numero);
        }
        return null;
        
    }
    
    /** 
     * Crea una lista de jornadas segun el tipo de juego.
     * @param tipo
     * @return boolean
     */
    public boolean crearCalendario(String tipo) {
        int numEquipos = equiposLiga.size();
        int numDias = (numEquipos - 1); // Dias que tendrá el torneo

        // TODO: Compruebo que hay equipos
        if (equiposLiga.size() > 1) {
            // TODO: Compruebo que para equipo hay al menos tantos jugadores como Partido.getNumEnfrentamiento()*2
            for (EquipoLiga equipoLiga : equiposLiga) {
                if (equipoLiga.getJugadores().size() < Partido.getNumEnfrentamiento() * 2) {
                    return false;
                }
            }
            // Lista para copiar la lista de jugadores
            List<EquipoLiga> equipos = new ArrayList<EquipoLiga>();

            equipos.addAll(equiposLiga); // Añado todos los equipos al temporal
            equipos.remove(equipos.get(0)); // Elimino el primer elemento

            for (int dia = 0; dia < numDias; dia++) {
                // Primer partido de la jornada
                System.out.println("Jornada: " + dia);
                int equipoIdx = dia % equipos.size();
                System.out.println(equipos.get(equipoIdx) + " vs " + equiposLiga.get(0));
                // TODO: crear jornada
                Jornada jornada = new Jornada(dia);
                // TODO: crear primer partidos
                Partido partido = new PartidoFutbolin(jornada.getPartidos().size());

                partido.setLocal(equipos.get(equipoIdx));
                partido.setVisitante(equiposLiga.get(0));

                // TODO: fijar la estadistica en base al tipo
                if (tipo.equals("Futbolin")) {
                    partido.getLocal().setEstadistica(new EstadisticaFutbolin());
                    partido.getVisitante().setEstadistica(new EstadisticaFutbolin());
                }

                // TODO: Asignar Juez. NO HACERLO

                // TODO: crear enfrentamiento para cada partido llamando al método de Enfrentamiento
                
                partido.crearEnfrentamientos(partido.getLocal().getJugadores(), partido.getVisitante().getJugadores());

                jornada.addPartido(partido);
                

                // El resto de partidos de la jornada
                for (int idx = 1; idx < numEquipos / 2; idx++) {
                    int firstequipo = (dia + idx) % equipos.size();
                    int secondequipo = (dia + equipos.size() - idx) % equipos.size();
                    System.out.println(equipos.get(firstequipo) + " vs " + equipos.get(secondequipo));
                    // TODO: crear cada partido
                    Partido partido2 = new PartidoFutbolin(jornada.getPartidos().size());

                    partido2.setLocal(equipos.get(firstequipo));
                    partido2.setVisitante(equipos.get(secondequipo));

                    // TODO: fijar la estadistica en base al tipo
                    if (tipo.equals("Futbolin")) {
                        partido2.getLocal().setEstadistica(new EstadisticaFutbolin());
                        partido2.getVisitante().setEstadistica(new EstadisticaFutbolin());
                    }
                    
                    // TODO: Asignar Juez. NO HACERLO

                    // TODO: crear enfrentamiento para cada partido llamando al método de Enfrentamiento
                    
                    partido2.crearEnfrentamientos(partido2.getLocal().getJugadores(), partido2.getVisitante().getJugadores());
                    jornada.addPartido(partido2);
                    
                }
                calendario.add(jornada);
            }
            return true;
        }
        return false;
    }
    
    /** 
     * Obtiene un equipo a partir de su nombre.
     * @param nombre
     * @return EquipoLiga
     */
    public EquipoLiga getEquipoLiga(String nombre){
        if (nombre != null) {
            for (EquipoLiga equipoLiga : equiposLiga) {
                if (equipoLiga.getE().getNombre().equals(nombre)) {
                    return equipoLiga;
                }
            }
        }
        return null;
    }
    
    /**
     * Simula todas las jornadas del calendario.
     */
    public void simular(){
        for (Jornada jornada : calendario) {
            jornada.simular();
        }
    }

    
    /** 
     * Obtiene un partido de un jornada especifica a partir de sus numeros.
     * @param numJornada
     * @param numPartido
     * @return Partido
     */
    private Partido getPartidoJornada(int numJornada, int numPartido) {
        for (Jornada jornada : calendario) {
            if (jornada.getNumero() == numJornada) {
                for (Partido partido : jornada.getPartidos()) {
                    if (partido.getId() == numPartido) {
                        return partido;
                    }
                }
            }
        }
        return null;
    }

    
    /** 
     * Juega un partido de una jornada especifica. Se indican el numero de jornada del partido a jugar y el numero de partido, ademas del resultado del partido.
     * @param numJornada
     * @param numPartido
     * @param gollocal
     * @param golVis
     */
    public void jugar(int numJornada, int numPartido, int[] gollocal, int[] golVis){
        Partido partido = getPartidoJornada(numJornada, numPartido);
        if (gollocal != null && golVis != null && partido != null) {
            partido.jugar(gollocal, golVis);
        }
    }

    /**
     * Muestra las estadisticas de todos los equipos de la jornada.
     */
    public void mostrarEstadistica(){
        for (EquipoLiga equipoLiga : equiposLiga) {
            System.out.println(equipoLiga.getEst().toString());
        }
    }
}
