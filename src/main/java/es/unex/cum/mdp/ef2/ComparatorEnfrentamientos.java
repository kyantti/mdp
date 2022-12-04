package main.java.es.unex.cum.mdp.ef2;

import java.util.Comparator;

public class ComparatorEnfrentamientos implements Comparator <EquipoLiga> {

    @Override
    public int compare(EquipoLiga eqLocal, EquipoLiga eqVis) {
        if (eqLocal != null & eqVis != null) {
            int enfrenGanadosLocal = eqLocal.getEst().getEnfrenGanados();
            int enfrenGanadosVis = eqVis.getEst().getEnfrenGanados();

            if (enfrenGanadosLocal > enfrenGanadosVis) {
                return -1;
            }
            else if (enfrenGanadosLocal < enfrenGanadosVis) {
                return 1;
            }
            else{
                int enfrenEmpatadosLocal = eqLocal.getEst().getEnfrenEmpatados();
                int enfrenEmpatadosVis = eqVis.getEst().getEnfrenEmpatados();
                if (enfrenEmpatadosLocal > enfrenEmpatadosVis) {
                    return -2;
                }
                else if (enfrenEmpatadosLocal < enfrenEmpatadosVis) {
                    return 2;
                }
                else{
                    int enfrenPerdidosLocal = eqLocal.getEst().getEnfrenPerdidos();
                    int enfrenPerdidosVis = eqVis.getEst().getEnfrenPerdidos();

                    if (enfrenPerdidosLocal < enfrenPerdidosVis) {
                        return -3;
                    }
                    else if (enfrenPerdidosLocal > enfrenPerdidosVis) {
                        return 3;
                    }
                    else{
                        return 0;
                    }
                }
            }
            
        }
        return 0;
    }

    
}
