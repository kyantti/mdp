package test.java.es.unex.cum.mdp.ef2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.es.unex.cum.mdp.ef2.Campeonato;
import main.java.es.unex.cum.mdp.ef2.EnfrentamientoFutbolin;
import main.java.es.unex.cum.mdp.ef2.Equipo;
import main.java.es.unex.cum.mdp.ef2.EquipoLiga;
import main.java.es.unex.cum.mdp.ef2.Estadistica;
import main.java.es.unex.cum.mdp.ef2.EstadisticaFutbolin;
import main.java.es.unex.cum.mdp.ef2.Jornada;
import main.java.es.unex.cum.mdp.ef2.Liga;
import main.java.es.unex.cum.mdp.ef2.NoLigaException;
import main.java.es.unex.cum.mdp.ef2.Partido;

public class TestCampeonato {

	private String tipo = "Futbolin";
	private Campeonato lig = null;
	// Datos generales
	private int numEquipo = 40; // dado de alta en el sistema
	private int numJuez = 40; // dado de alta en el sistema
	private int numDirectivo = 40; // dado de alta en el sistema
	private int numJugadores = 400; // dado de alta en el sistema
	private int numTemp = 3; // dado de alta en el sistema
	private int inicioIndexJugadores = numJuez + numDirectivo + 20; // inicio del id de Jugadores
	private int inicioIndexJuez = numDirectivo; // inicio del id de Jugadores

	// Datos por liga
	private int numJugadoresEquipo = 10; // Número de jugadores por equipo
	private int numEquipoLiga = 6; // Número de equipos por liga. No puede ser impar

	@BeforeEach
	public void setUp() throws Exception {
		lig = new Campeonato();
		Random r = new Random();

		// añade a n directivos con nombre Directivo+i con id i (0,1,2)
		for (int i = 0; i < numDirectivo; i++) {
			lig.addPersona("Directivo", "Directivo" + String.valueOf(i), 0 + i, r.nextInt(70) + 18, "Presidente", null);
		}
		// añade a n jueces con nombre Juez+i con id inicioIndexJuez+i
		for (int i = 0; i < numJuez; i++) {
			lig.addPersona("Juez", "Juez" + String.valueOf(inicioIndexJuez + i), 50 + i, r.nextInt(70) + 18,
					String.valueOf(r.nextInt(30)), null);
		}
		// añade a n jugadores con nombre Jugador+i con id inicioIndexJugadores+i
		for (int i = 0; i < numJugadores; i++) {
			lig.addPersona("Jugador", "Jugador" + String.valueOf(i), inicioIndexJugadores + i, r.nextInt(25) + 16,
					"Jugador" + String.valueOf(i), String.valueOf(r.nextInt(10)));
		}

		// Añado n equpo con nombre Equipo+i (con id +n)
		for (int i = 0; i < numEquipo; i++) {
			lig.addEquipo("Equipo" + String.valueOf(i), "Ciudad" + String.valueOf(i), i);
		}
		// Añado tres temporadas 2020-2021, 2021-2022, 2022-2023
		for (int i = 0; i < numTemp; i++) {
			lig.addTemporada("202" + String.valueOf(i) + "-202" + String.valueOf(i + 1));
		}

	}

	@AfterEach
	public void tearDown() throws Exception {
		lig = null;
	}

	@Test
	public void testAddTemporada() {
		// Creadas ahora
		assertTrue(lig.addTemporada("2023-2024"));
		assertFalse(lig.addTemporada("2023-2024"));
		// Creadas en el setup
		assertFalse(lig.addTemporada("2022-2023"));
		assertFalse(lig.addTemporada("2021-2022"));
		assertFalse(lig.addTemporada("2020-2021"));
	}

	@Test
	public void testGetTemporada() {
		assertNull(lig.getTemporada("2023-2024"));
		assertNotNull(lig.getTemporada("2022-2023"));
		assertNotNull(lig.getTemporada("2021-2022"));
		assertNotNull(lig.getTemporada("2020-2021"));
	}

	@Test
	public void testAddEquipo() {
		assertTrue(lig.addEquipo("EquipoPrueba", "Merida", 0));
		assertFalse(lig.addEquipo("EquipoPrueba", "Merida", 0));
		// El id no es un directivo
		assertFalse(lig.addEquipo("EquipoPrueba2", "Merida2", 50));
	}

	@Test
	public void testGetEquipo() {
		assertNull(lig.getEquipo("EquipoPrueba"));
		lig.addEquipo("EquipoPrueba", "Merida", 0);
		assertNotNull(lig.getEquipo("EquipoPrueba"));

		// compruebo los n equipos añadidos en el setup
		for (int i = 0; i < numEquipo; i++) {
			Equipo e = lig.getEquipo("Equipo" + String.valueOf(i));
			if (e == null)
				System.out.println(i);
			assertNotNull(e);
		}

	}

	@Test
	public void testAddPersona() {
		assertTrue(lig.addPersona("Directivo", "Directivo41", 41, 35, "Presidente", null));
		assertFalse(lig.addPersona("Directivo", "Directivo41", 41, 35, "Presidente", null));
		assertTrue(lig.addPersona("Juez", "Juez91", 91, 50, "10", null));
		assertFalse(lig.addPersona("Juez", "Juez91", 91, 50, "10", null));
		assertTrue(lig.addPersona("Jugador", "Jugador100001", 100001, 25, "Pepe", "2"));
		assertFalse(lig.addPersona("Juez", "Juez91", 91, 50, "10", null));

	}

	@Test
	public void testGetPersona() {
		for (int i = 0; i < numDirectivo; i++) {
			assertNotNull(lig.getPersona(i));
			assertNotNull(lig.getPersona(50 + i));
		}
		// añade a 200 jugadores con nombre Jugador+i con id 100+i
		for (int i = 0; i < numJugadores; i++) {
			assertNotNull(lig.getPersona(100 + i));
		}
	}

	@Test
	public void testCrearLigaTemporada() {
		assertTrue(lig.crearLigaTemporada("2022-2023", "Primera"));
		assertFalse(lig.crearLigaTemporada("2010-2023", "Primera"));
		assertFalse(lig.crearLigaTemporada("2022-2023", "Primera"));
	}

	@Test
	public void testGetLigaTemporada() {
		assertTrue(lig.crearLigaTemporada("2022-2023", "Primera"));
		try {
			assertNotNull(lig.getTempLiga("2022-2023", "Primera"));
		} catch (NoLigaException e) {
			fail();
		}
		try {
			lig.getTempLiga("2010-2023", "Primera");
			fail();
		} catch (NoLigaException e) {

		}
		try {
			lig.getTempLiga("2022-2023", "Octava");
			fail();
		} catch (NoLigaException e) {

		}

	}

	@Test
	public void testAddEquipoLigaTemporada() {
		lig.crearLigaTemporada("2022-2023", "Primera");
		assertTrue(lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo0", 2));
		assertFalse(lig.addEquipoLigaTemporada("2021-2023", "Primera", "Equipo0", 2));
		assertFalse(lig.addEquipoLigaTemporada("2022-2023", "Segunda", "Equipo0", 2));
		assertFalse(lig.addEquipoLigaTemporada("2022-2023", "Primera", "EquipoA", 2));

	}

	@Test
	public void testAddJugadorEquipoLigaTemporada() {
		lig.crearLigaTemporada("2022-2023", "Primera");
		lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo0", 2);
		assertTrue(lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo0", inicioIndexJugadores)); // Jugador
																												// 400
		assertFalse(lig.addJugadorEquipoLigaTemporada("2021-2023", "Primera", "Equipo0", inicioIndexJugadores)); // Jugador
																													// 400
		assertFalse(lig.addJugadorEquipoLigaTemporada("2022-2023", "Segunda", "Equipo0", inicioIndexJugadores)); // Jugador
																													// 400
		assertFalse(lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "EquipoA", inicioIndexJugadores)); // Jugador
																													// 400
		assertFalse(lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "EquipoA", 0)); // Directivo
		assertFalse(lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "EquipoA", 50)); // Juez
	}

	@Test
	public void testCrearCalendario() {
		lig.crearLigaTemporada("2022-2023", "Primera");
		// Sin equipo
		assertFalse(lig.crearCalendario("2022-2023", "Primera", tipo));
		// Con un número impar de equipos
		lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(0), 0);
		// Añado los n equipos a la liga
		for (int i = 1; i < numEquipoLiga; i++) {
			lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i), i);
		}

		// Sin jugadores añadidos
		assertFalse(lig.crearCalendario("2022-2023", "Primera", tipo));
		// Añado n-1 jugadores al primero equipo para que falle
		for (int j = 0; j < numJugadoresEquipo - 1; j++) {
			lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(0),
					inicioIndexJugadores + j + (0 * 5));
			assertFalse(lig.crearCalendario("2022-2023", "Primera", tipo));
		}
		// Añado el que falta
		lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(0),
				inicioIndexJugadores + numJugadoresEquipo - 1 + (0 * 5));

		// Añado n jugadores a cada equipo de la liga
		for (int i = 1; i < numEquipoLiga; i++) {
			for (int j = 0; j < numJugadoresEquipo; j++) {
				lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i),
						inicioIndexJugadores + j + (i * 5));
			}
		}

		assertTrue(lig.crearCalendario("2022-2023", "Primera", tipo));

		// Comprobaciones al calendario creado
		try {
			Liga l = lig.getTempLiga("2022-2023", "Primera");
			// Compruebo que hay tantas jornadas como el número de equipos -1
			assertTrue(l.getCalendario().size() == (numEquipoLiga - 1));
			// por cada jornada
			for (int i = 0; i < l.getCalendario().size(); i++) {
				Jornada jor = (Jornada) l.getCalendario().get(i);
				// Los atributos de jornadas están bien
				assertNotNull(jor.getFecha());
				assertTrue(jor.getNumero() == i);
				assertTrue(jor.getPartidos().size() == (numEquipoLiga / 2));
				for (int j = 0; j < jor.getPartidos().size(); j++) {
					// Los atributos de Partido están bien
					Partido p = (Partido) jor.getPartidos().get(j);
					assertNotNull(p.getLocal());
					assertNotNull(p.getVisitante());
					assertTrue(p.getPuntosLocal() == 0);
					assertTrue(p.getPuntosVisitante() == 0);
					assertTrue(p.getId() == j);
					// El número de enfrentamiento es el correcto
					assertTrue(p.getEs().size() == Partido.getNumEnfrentamiento());
				}
			}
		} catch (NoLigaException e) {
			fail();
		}
	}

	@Test
	public void testSimular() {
		lig.crearLigaTemporada("2022-2023", "Primera");
		// Añado los n equipos a la liga
		for (int i = 0; i < numEquipoLiga; i++) {
			lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i), i);
		}

		// Añado n jugadores a cada equipo de la liga
		for (int i = 0; i < numEquipoLiga; i++) {
			for (int j = 0; j < numJugadoresEquipo; j++) {
				lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i),
						inicioIndexJugadores + j + (i * 5));
			}
		}

		lig.crearCalendario("2022-2023", "Primera", tipo);

		assertTrue(lig.simular("2022-2023", "Primera"));

		// Comprobaciones tras simular
		try {
			Liga l = lig.getTempLiga("2022-2023", "Primera");

			Iterator it = l.getEquiposLiga().iterator();
			while (it.hasNext()) {
				EquipoLiga e = (EquipoLiga) it.next();
				Estadistica es = e.getEst();
				assertTrue(es.getPartJugados() == (numEquipoLiga - 1));
				assertEquals(es.getPartEmpatados() + es.getPartPerdidos() + es.getPartGanados(), es.getPartJugados());
				assertEquals(es.getPartJugados() * Partido.getNumEnfrentamiento(), es.getEnfrenEmpatados() + es.getEnfrenGanados() + es.getEnfrenPerdidos());
				int puntos = 0;
				if (tipo.equals("Futbolin")) {
					puntos = es.getPartEmpatados() + 3 * es.getPartGanados();
					EstadisticaFutbolin esf = (EstadisticaFutbolin) es;
					assertFalse(esf.getGolesFavor() == 0);
					assertFalse(esf.getGolesContra() == 0);

				} else {
					puntos = -3 * es.getPartPerdidos() + 2 * es.getPartEmpatados() + (es.getPartGanados() * 5);
				}
				assertEquals(puntos, es.getPuntos());
			}

			// por cada jornada
			for (int i = 0; i < l.getCalendario().size(); i++) {
				Jornada jor = (Jornada) l.getCalendario().get(i);
				// Por cada Partido
				for (int j = 0; j < jor.getPartidos().size(); j++) {
					// Los atributos de Partido están bien
					Partido p = (Partido) jor.getPartidos().get(j);
					assertTrue(p.getPuntosLocal() != 0 || p.getPuntosVisitante() != 0);
					for (int k = 0; k < p.getEs().size(); k++) {
						// Los atributos de Partido están bien
						if (tipo.equals("Futbolin")) {
							EnfrentamientoFutbolin enf = (EnfrentamientoFutbolin) p.getEs().get(k);
							assertTrue(enf.getLocal() != 0 && enf.getVisitante() != 0);
						} /*else {
							EnfrentamientoPetanca enf = (EnfrentamientoPetanca) p.getEs().get(k);
							assertTrue(enf.getLocal() != 0 && enf.getVisitante() != 0);
						}*/

					}
				}
			}
		} catch (NoLigaException e) {
			fail();
		}

	}

	@Test
	public void testJugar() {

		lig.crearLigaTemporada("2022-2023", "Primera");
		// Añado los n equipos a la liga
		numEquipoLiga = 4;
		for (int i = 0; i < numEquipoLiga; i++) {
			lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i), i);
		}

		// Añado n jugadores a cada equipo de la liga
		for (int i = 0; i < numEquipoLiga; i++) {
			for (int j = 0; j < numJugadoresEquipo; j++) {
				lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i),
						inicioIndexJugadores + j + (i * 5));
			}
		}

		lig.crearCalendario("2022-2023", "Primera", tipo);
		/*
		 * Jornada: 0 
		 * Equipo1 vs Equipo0 
		 * Equipo2 vs Equipo3 
		 * Jornada: 1 
		 * Equipo2 vs Equipo0 
		 * Equipo3 vs Equipo1 
		 * Jornada: 2 
		 * Equipo3 vs Equipo0 
		 * Equipo1 vs Equipo2
		 */
		// jugar(String nomTemp, String nomLiga, int jornada, int partido, int []golLocal, int []golVis)
		//Son 0-2 jornada
		
		/////////////////////////////////
		//Casos de fallo de jugar la liga
		/////////////////////////////////
		
		//Jornada no correcta
		assertFalse(lig.jugar("2022-2023", "Primera", 5, 0, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 }));
		assertFalse(lig.jugar("2022-2023", "Primera", -2, 0, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 }));
		
		//Partido no correcto
		assertFalse(lig.jugar("2022-2023", "Primera", 0, 3, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 }));
		assertFalse(lig.jugar("2022-2023", "Primera", 0, -13, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 }));
		
		//Son 3 enfrentamientos. No puede haber menos
		assertFalse(lig.jugar("2022-2023", "Primera", 0, 0, new int[] { 5, 5 }, new int[] { 3, 13,3 }));
		assertFalse(lig.jugar("2022-2023", "Primera", 0, 0, new int[] { 5, 5,5 }, new int[] { 3, 13 }));
		
		
		/////////////////////////////////
		//Casos correctos de jugar la liga
		/////////////////////////////////

		assertTrue(lig.jugar("2022-2023", "Primera", 0, 0, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 })); // Gana local e1 y
																								// pierde e0
		assertTrue(lig.jugar("2022-2023", "Primera", 0, 1, new int[] { 10, 15, 20 }, new int[] { 4, 8, 12 })); // Gana local e2 y
																									// pierde e3

		assertTrue(lig.jugar("2022-2023", "Primera", 1, 0, new int[] { 5, 10, 15 }, new int[] { 5, 10, 15 })); // Empate e2 y e0
		assertTrue(lig.jugar("2022-2023", "Primera", 1, 1, new int[] { 4, 8, 12 }, new int[] { 5, 8, 13 })); // Gana visitante e1 y
																									// pierde e3

		assertTrue(lig.jugar("2022-2023", "Primera", 2, 0, new int[] { 6, 8, 10 }, new int[] { 6, 8, 8 })); // Gana local e3 y
																								// pierde e0
		assertTrue(lig.jugar("2022-2023", "Primera", 2, 1, new int[] { 7, 10, 13 }, new int[] { 6, 9, 12 })); // Gana local e1 y
																									// pierde e2

		// Comprobaciones tras jugar
		try {
			Liga l = lig.getTempLiga("2022-2023", "Primera");

			// Equipo 0. PG 0 PP 1 PE 2. EG 1 EP 3 EE 5. Puntos. 1/-4 GolesFavor: 71
			// GolesContra: 69
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getPartGanados(), 0);
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getPartPerdidos(), 2);
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getPartEmpatados(), 1);
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getEnfrenGanados(), 1);
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getEnfrenPerdidos(), 3);
			assertEquals(l.getEquipoLiga("Equipo0").getEst().getEnfrenEmpatados(), 5);
			if (tipo.equals("Futbolin")) {
				assertEquals(l.getEquipoLiga("Equipo0").getEst().getPuntos(), 1);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo0").getEst())).getGolesFavor(), 71);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo0").getEst())).getGolesContra(), 69);
			} else {
				assertEquals(l.getEquipoLiga("Equipo0").getEst().getPuntos(), -4);
			}

			// Equipo 1: PG 3 PP 0 PE 0. EG 7 EP 1 EE 1. Puntos. 9/34 GolesFavor: 71
			// GolesContra: 70
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getPartGanados(), 3);
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getPartPerdidos(), 0);
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getPartEmpatados(), 0);
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getEnfrenGanados(), 7);
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getEnfrenPerdidos(), 1);
			assertEquals(l.getEquipoLiga("Equipo1").getEst().getEnfrenEmpatados(), 1);
			if (tipo.equals("Futbolin")) {
				assertEquals(l.getEquipoLiga("Equipo1").getEst().getPuntos(), 9);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo1").getEst())).getGolesFavor(), 71);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo1").getEst())).getGolesContra(), 70);
			} else {
				assertEquals(l.getEquipoLiga("Equipo1").getEst().getPuntos(), 15);
			}

			// Equipo 2: PG 1 PP 1 PE 1. EG 3 EP 3 EE 3. Puntos. 4/4 GolesFavor: 104
			// GolesContra: 84
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getPartGanados(), 1);
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getPartPerdidos(), 1);
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getPartEmpatados(), 1);
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getEnfrenGanados(), 3);
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getEnfrenPerdidos(), 3);
			assertEquals(l.getEquipoLiga("Equipo2").getEst().getEnfrenEmpatados(), 3);
			if (tipo.equals("Futbolin")) {
				assertEquals(l.getEquipoLiga("Equipo2").getEst().getPuntos(), 4);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo2").getEst())).getGolesFavor(), 102);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo2").getEst())).getGolesContra(), 84);
			} else {
				assertEquals(l.getEquipoLiga("Equipo2").getEst().getPuntos(), 4);
			}

			// Equipo 3: PG 1 PP 2 PE 0. EG 1 EP 5 EE 3. Puntos. 3/-1 GolesFavor: 72
			// GolesContra: 93
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getPartGanados(), 1);
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getPartPerdidos(), 2);
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getPartEmpatados(), 0);
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getEnfrenGanados(), 1);
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getEnfrenPerdidos(), 5);
			assertEquals(l.getEquipoLiga("Equipo3").getEst().getEnfrenEmpatados(), 3);
			if (tipo.equals("Futbolin")) {
				assertEquals(l.getEquipoLiga("Equipo3").getEst().getPuntos(), 3);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo3").getEst())).getGolesFavor(), 72);
				assertEquals(((EstadisticaFutbolin) (l.getEquipoLiga("Equipo3").getEst())).getGolesContra(), 93);
			} else {
				assertEquals(l.getEquipoLiga("Equipo3").getEst().getPuntos(), -1);
			}
		} catch (NoLigaException e) {
			fail();
		}

	}

	
	 @Test 
	 public void testMostrarEstadistica() {
			lig.crearLigaTemporada("2022-2023", "Primera");
			// Añado los n equipos a la liga
			numEquipoLiga = 4;
			for (int i = 0; i < numEquipoLiga; i++) {
				lig.addEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i), i);
			}

			// Añado n jugadores a cada equipo de la liga
			for (int i = 0; i < numEquipoLiga; i++) {
				for (int j = 0; j < numJugadoresEquipo; j++) {
					lig.addJugadorEquipoLigaTemporada("2022-2023", "Primera", "Equipo" + String.valueOf(i),
							inicioIndexJugadores + j + (i * 5));
				}
			}

			lig.crearCalendario("2022-2023", "Primera", tipo);
			/*
			 * Jornada: 0 
			 * Equipo1 vs Equipo0 
			 * Equipo2 vs Equipo3 
			 * Jornada: 1 
			 * Equipo2 vs Equipo0 
			 * Equipo3 vs Equipo1 
			 * Jornada: 2 
			 * Equipo3 vs Equipo0 
			 * Equipo1 vs Equipo2
			 */
			// jugar(String nomTemp, String nomLiga, int jornada, int partido, int
			// []golLocal, int []golVis)
			lig.jugar("2022-2023", "Primera", 0, 0, new int[] { 5, 5, 5 }, new int[] { 3, 13, 3 }); // Gana local e1 y
																									// pierde e0
			lig.jugar("2022-2023", "Primera", 0, 1, new int[] { 10, 15, 20 }, new int[] { 4, 8, 12 }); // Gana local e2 y
																										// pierde e3

			lig.jugar("2022-2023", "Primera", 1, 0, new int[] { 5, 10, 15 }, new int[] { 5, 10, 15 }); // Empate e2 y e0
			lig.jugar("2022-2023", "Primera", 1, 1, new int[] { 4, 8, 12 }, new int[] { 5, 8, 13 }); // Gana visitante e1 y
																										// pierde e3

			lig.jugar("2022-2023", "Primera", 2, 0, new int[] { 6, 8, 10 }, new int[] { 6, 8, 8 }); // Gana local e3 y
																									// pierde e0
			lig.jugar("2022-2023", "Primera", 2, 1, new int[] { 7, 10, 13 }, new int[] { 6, 9, 12 }); // Gana local e1 y
																										// pierde e2

			// Comprobaciones tras simular
			try {
				Liga l = lig.getTempLiga("2022-2023", "Primera");

				// Equipo 0. PG 0 PP 1 PE 2. EG 1 EP 3 EE 5. Puntos. 1/-4 GolesFavor: 71
				// GolesContra: 69
				// Equipo 1: PG 3 PP 0 PE 0. EG 7 EP 1 EE 1. Puntos. 9/34 GolesFavor: 71
				// GolesContra: 70
				// Equipo 2: PG 1 PP 1 PE 1. EG 3 EP 3 EE 3. Puntos. 4/14 GolesFavor: 104
				// GolesContra: 84
				// Equipo 3: PG 1 PP 2 PE 0. EG 1 EP 5 EE 3. Puntos. 3/-1 GolesFavor: 72
				// GolesContra: 93
				
				//Clasificación por puntos
				lig.mostrarEstadistica("2022-2023", "Primera", "Puntos");
				assertEquals(l.getEquiposLiga().get(0).getE().getNombre(),"Equipo1");
				assertEquals(l.getEquiposLiga().get(1).getE().getNombre(),"Equipo2");
				assertEquals(l.getEquiposLiga().get(2).getE().getNombre(),"Equipo3");
				assertEquals(l.getEquiposLiga().get(3).getE().getNombre(),"Equipo0");
				
				//Clasificación por Goles
				if (tipo.equals("Futbolin")) {
					lig.mostrarEstadistica("2022-2023","Primera", "Goles");	
					assertEquals(l.getEquiposLiga().get(0).getE().getNombre(),"Equipo2");
					assertEquals(l.getEquiposLiga().get(1).getE().getNombre(),"Equipo0");
					assertEquals(l.getEquiposLiga().get(2).getE().getNombre(),"Equipo1");
					assertEquals(l.getEquiposLiga().get(3).getE().getNombre(),"Equipo3");
				}
				
				//Clasificación por Partidos
				lig.mostrarEstadistica("2022-2023", "Primera", "Partidos");
				assertEquals(l.getEquiposLiga().get(0).getE().getNombre(),"Equipo1");
				assertEquals(l.getEquiposLiga().get(1).getE().getNombre(),"Equipo2");
				assertEquals(l.getEquiposLiga().get(2).getE().getNombre(),"Equipo3");
				assertEquals(l.getEquiposLiga().get(3).getE().getNombre(),"Equipo0");
				
				//Clasificación por Enfrentamientos
				lig.mostrarEstadistica("2022-2023", "Primera", "Enfrentamientos");
				assertEquals(l.getEquiposLiga().get(0).getE().getNombre(),"Equipo1");
				assertEquals(l.getEquiposLiga().get(1).getE().getNombre(),"Equipo2");
				assertEquals(l.getEquiposLiga().get(2).getE().getNombre(),"Equipo0");
				assertEquals(l.getEquiposLiga().get(3).getE().getNombre(),"Equipo3");
			
			} catch (NoLigaException e) {
				fail();
			}
	 }
	 
}