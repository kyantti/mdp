package main.java.es.unex.cum.mdp.sesion7;

import java.io.IOException;


public class MainSingleton {
	private Desguace d = new Desguace();

	// No hace falta. Usaremos con singleton en cada método
	// private Teclado t = new Teclado();
	public static void main(String[] args) throws ExceptionPiezaNoEncontrada, ExceptionBastidorNoEncontrado {
		MainSingleton m = new MainSingleton();
		m.ejecutar();
	}

	private int menu() throws IOException {
		STeclado st = STeclado.getInstance();
		return st.Menu(new String[] { "1. Añadir Pieza", "2. Añadir Vehiculo", "3. Añadir Pieza Vehiculo",
				"4. Mostrar Piezas/Vehiculo", "5. Salir" }, 1, 5);

	}

	private boolean addPieza() throws IOException, ExceptionPiezaNoEncontrada {
		STeclado st = STeclado.getInstance();
		String id = st.literalConString("Dame el id");

		Pieza p2 = d.getPiezaDesguace(id);
		if (p2 == null) {
			String nombre = st.literalConString("Dame elnombre");
			int stock = st.literalConEntero("Dame el número");
			Pieza paux = new Pieza(id, nombre, stock);
			d.addPiezaDesguace(paux);
			return true;
		}
		return false;
	}

	private boolean addVehiculo() throws IOException, ExceptionBastidorNoEncontrado {
		STeclado st = STeclado.getInstance();
		Integer bastidor = st.literalConEntero("Dame el bástidor");
		String marca = st.literalConString("Dame el marca");
		String modelo = st.literalConString("Dame el modelo");
		int tipo = st.literalConEntero("1. Coche 2. Moto 3. Camion");
		Vehiculo v = null;
		if (tipo == 1) {
			String color = st.literalConString("Dame el color");
			v = new Coche(marca, modelo, new Persona(), bastidor, color);
		}
		return d.addVehiculo(v);
	}

	private boolean addPiezaVehiculo() throws IOException {
		STeclado st = STeclado.getInstance();
		String id2 = st.literalConString("Dame el id");
		Integer b2 = st.literalConEntero("Dame el bástidor");
		return d.addPiezaVehiculo(id2, b2);
	}

	public void ejecutar() throws ExceptionPiezaNoEncontrada, ExceptionBastidorNoEncontrado {
		int opcion;
		do {
			try {
				opcion = menu();
				switch (opcion) {
				case 1:
					if (addPieza())
						System.out.println("Se ha añadido");
					else
						System.out.println("No se ha añadido");
					break;
				case 2:
					if (addVehiculo())
						System.out.println("Se ha añadido");
					else
						System.out.println("No se ha añadido");
					break;

				case 3:
					if (addPiezaVehiculo())
						System.out.println("Se ha añadido");
					else
						System.out.println("No se ha añadido");
					break;
				case 4:
					System.out.println(d.getCatalogo().toString());
					System.out.println(d.getVehiculos().toString());
					break;
				}
			} catch (IOException e) {
				opcion = 0;
				System.out.println("Excepción");
			}
		} while (opcion != 5);
	}

}
