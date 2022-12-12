package main.java.es.unex.cum.mdp.ef3.model;

import java.util.Random;

public class MainPartido {
	private String tipo="Petanca";
	private EquipoLiga l=null;
	private EquipoLiga v=null;
	private Partido p=null;	
	
	public static void main(String []args) {
		MainPartido m= new MainPartido();
		m.run();
	}
	public void run() {
		if (tipo.equals("Futbolin")) {
			l=new EquipoLiga(10,null,new EstadisticaFutbolin());
			v=new EquipoLiga(10,null,new EstadisticaFutbolin());
			p= new PartidoFutbolin(1,l,v);
			
		}else {
			
		}
		
		Random r= new Random();
		for (int i=0;i<10;i++) {
			Jugador j=new Jugador("Jugador"+i,4,26,"Jugador"+i,r.nextInt(10));
			l.addJugadores(j);
		}
		for (int i=10;i<20;i++) {
			Jugador j=new Jugador("Jugador"+i,4,26,"Jugador"+i,r.nextInt(10));
			v.addJugadores(j);
		}
		p.crearEnfrentamientos(l.getJugadores(), v.getJugadores());
		p.simular();
		System.out.println(p.getPuntosLocal());
		System.out.println(p.getPuntosVisitante());
		
		if (tipo.equals("Futbolin")) {
			p= new PartidoFutbolin(1,l,v);
			
		}else {
			
		}
		p.crearEnfrentamientos(l.getJugadores(), v.getJugadores());
		p.jugar(new int[]{5,6,7}, new int[] {4,7,8} );
		System.out.println(p.getPuntosLocal());
		System.out.println(p.getPuntosVisitante());
	}
}