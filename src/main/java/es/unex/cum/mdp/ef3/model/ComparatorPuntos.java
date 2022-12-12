package main.java.es.unex.cum.mdp.ef3.model;
import java.util.Comparator;

public class ComparatorPuntos implements Comparator <EquipoLiga> {

    @Override
    public int compare(EquipoLiga eqLocal, EquipoLiga eqVis) {
        if (eqLocal != null && eqVis != null) {
            int puntosLocal = eqLocal.getEst().getPuntos();
            int puntosVis = eqVis.getEst().getPuntos();
            
            if (puntosLocal > puntosVis) {
                return -1;
            }
            else if (puntosLocal < puntosVis) {
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
}
