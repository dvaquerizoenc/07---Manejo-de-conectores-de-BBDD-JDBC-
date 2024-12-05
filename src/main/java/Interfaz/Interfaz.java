package Interfaz;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

public class Interfaz {
	GestorCoche gc;
	GestorPasajero gp;
	public void mostrarInterfaz() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("########################");
		System.out.println("## Gestor BBDD Coches ##");
		System.out.println("########################");

		System.out.println("1. Añadir nuevo coche");
		System.out.println("2. Borrar coche por id");
		System.out.println("3. Consulta coche por id");
		System.out.println("4. Modificar coche por id");
		System.out.println("5. Listado de coches");
		System.out.println("6. Gestión de pasajeros");
		System.out.println("0. Salir");
		
		try {
			int respuesta = Integer.parseInt(sc.nextLine());
			
			switch (respuesta) {
			case 1:
				nuevoCoche();
				break;

			case 2:
				eliminarCoche();
				break;
			
			case 3:
				consultarCoche();
				break;

			case 4:
				modificarCoche();
				break;
				
			case 5:
				listarCoches();
				break;

			case 6:
				menuPasajeros();
				break;
				
			case 0:
				return;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void nuevoCoche() {
		Scanner sc = new Scanner(System.in);
		Coche coche = new Coche();
		gc = new GestorCoche();
		int añadido = 0;
		
		try {
			System.out.println("--> ¿Que marca tiene el coche?");
			coche.setMarca(sc.nextLine());
			System.out.println("--> ¿Que modelo tiene el coche?");
			coche.setModelo(sc.nextLine());
			System.out.println("--> ¿De que año es el coche?");
			coche.setAño(Integer.parseInt(sc.nextLine()));
			System.out.println("--> ¿Cuantos kilometros tiene el coche?");
			coche.setKm(Integer.parseInt(sc.nextLine()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		añadido = gc.añadirCoche(coche);
		
		switch(añadido) {
			case 1:
				System.out.println("La marca esta en blanco");
				break;
			case 2:
				System.out.println("El modelo esta en blanco");
				break;
			case 3: 
				System.out.println("--> Coche agregado correctamente");
				break;
		}
		
	}
	
	void eliminarCoche() {
		Scanner sc = new Scanner(System.in);
		gc = new GestorCoche();
		int id = 0;
		
		System.out.println("--> ¿Que id tiene el coche que quiere eliminar?");
		try {
			id = Integer.parseInt(sc.nextLine());
			
			if(gc.borrarCocheID(id)) {
				System.out.println("--> Coche eliminado correctamente");
			} else {
				System.out.println("--> No ha sido posible eliminar el coche");
			}
		} catch(NumberFormatException e) {
			System.out.println("ERROR: El valor introducido no es un número");
			eliminarCoche();
		}
	}
	
	void consultarCoche() {
		Scanner sc = new Scanner(System.in);
		gc = new GestorCoche();
		int id = 0;
		Coche coche = new Coche();
		
		System.out.println("--> ¿Que id tiene el coche que quiere consultar?");
		try {
			id = Integer.parseInt(sc.nextLine());
			
			coche = gc.consultaCocheID(id);
			
			System.out.println("# ID: "+coche.getId());
			System.out.println("# Marca: "+coche.getMarca());
			System.out.println("# Modelo: "+coche.getModelo());
			System.out.println("# Año: "+coche.getAño());
			System.out.println("# Km: "+coche.getKm());

		} catch(NumberFormatException e) {
			System.out.println("ERROR: El valor introducido no es un número");
			consultarCoche();
		} catch(Exception e) {
			System.out.println("ERROR: Se ha producido un error inesperado");
			e.printStackTrace();
		}
	}
	
	void modificarCoche() {
		Scanner sc = new Scanner(System.in);
		gc = new GestorCoche();
		int id = 0;
		Coche coche = new Coche();
		
		System.out.println("--> ¿Que id tiene el coche que quiere modificar?");
		try {
			id = Integer.parseInt(sc.nextLine());
			coche.setId(id);
			System.out.println("--> ¿Cual es la nueva marca?");
			coche.setMarca(sc.nextLine());
			System.out.println("--> ¿Cual es el nuevo modelo?");
			coche.setModelo(sc.nextLine());
			System.out.println("--> ¿De que año es el coche?");
			coche.setAño(Integer.parseInt(sc.nextLine()));
			System.out.println("--> ¿Cuantos kilometros tiene el coche?");
			coche.setKm(Integer.parseInt(sc.nextLine()));
			
			if(gc.modificarCocheID(id, coche)) {
				System.out.println("--> Coche modificado correctamente");
			}
		} catch(NumberFormatException e) {
			System.out.println("ERROR: El valor introducido no es un número");
			modificarCoche();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void listarCoches() {
		gc = new GestorCoche();
		
		List<Coche> listaCoches = gc.listarCoches();
		
		for(Coche c : listaCoches) {
			System.out.println("# ID: "+c.getId());
			System.out.println("# Marca: "+c.getMarca());
			System.out.println("# Modelo: "+c.getModelo());
			System.out.println("# Año: "+c.getAño());
			System.out.println("# Km: "+c.getKm());
			System.out.println();
		}
	}
	
	void menuPasajeros() {
		Scanner sc = new Scanner(System.in);
		int respuesta = 0;
		
		System.out.println("## MENÚ PASAJEROS ##");
		System.out.println("1. Crear un nuevo pasajero");
		System.out.println("2. Borrar pasajero por id");
		System.out.println("3. Consultar pasajero por id");
		System.out.println("4. Listar todos los pasajeros");
		System.out.println("5. Añadir pasajero a coche");
		System.out.println("6. Eliminar pasajero de coche");
		System.out.println("7. Listar todos los pasajeros de un coche");
		System.out.println("0. Volver");
		
		try {
			respuesta = Integer.parseInt(sc.nextLine());
			
			switch(respuesta) {
				case 1:
					crearPasajero();
					break;
					
				case 2: 
					borrarPasajero();
					break;
					
				case 3:
					consultarPasajero();
					break;
					
				case 4:
					listarPasajeros();
					break;
					
				case 5:
					añadirPasajeroCoche();
					break;
					
				case 6:
					eliminarPasajeroCoche();
					break;
					
				case 7:
					listarPasajerosCoche();
					break;
			}
		} catch (NumberFormatException e) {
			System.out.println("ERROR: El valor introducido no es un número");
			menuPasajeros();
		}
	}

	void crearPasajero() {
		Scanner sc = new Scanner(System.in);
		Pasajero pasajero = new Pasajero();
		gp = new GestorPasajero();
		
		System.out.println("--> ¿Que nombre tiene el nuevo pasajero?");
		pasajero.setNombre(sc.nextLine());
		System.out.println("--> ¿Que edad tiene el nuevo pasajero?");
		pasajero.setEdad(Integer.parseInt(sc.nextLine()));
		System.out.println("--> ¿Que peso tiene el nuevo pasajero?");
		pasajero.setPeso(Double.parseDouble(sc.nextLine()));
		
		if(gp.crearPasajero(pasajero)) {
			System.out.println("--> Pasajero creado correctamente");
		} else {
			System.out.println("ERROR: No ha sido posible crear el pasajero");
		}
	}
	
	void borrarPasajero() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		gp = new GestorPasajero();
		
		System.out.println("--> ¿Que id tiene el pasajero que quieres eliminar?");
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("--> El valor introducido no es un número");
			borrarPasajero();
		} catch (Exception e) {
			System.out.println("ERROR: Se ha producido un error inesperado");
			e.printStackTrace();
		}
		
		gp.borrarPasajeroID(id);
	}
	
	void consultarPasajero() {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		gp = new GestorPasajero();
		
		System.out.println("--> ¿Que id tiene el pasajero que quieres consultar?");
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("--> El valor introducido no es un número");
			borrarPasajero();
		} catch (Exception e) {
			System.out.println("ERROR: Se ha producido un error inesperado");
			e.printStackTrace();
		}
		
		Pasajero p = gp.consultaPasajeroID(id);
		System.out.println("# ID: "+p.getId());
		System.out.println("# Nombre: "+p.getNombre());
		System.out.println("# Edad: "+p.getEdad());
		System.out.println("# Peso: "+p.getPeso());
	}
	
	void listarPasajeros() {
		gp = new GestorPasajero();
		
		List<Pasajero> listaPasajeros = gp.listarPasajeros();
		
		for(Pasajero p : listaPasajeros) {
			System.out.println("# ID: "+p.getId());
			System.out.println("# Nombre: "+p.getNombre());
			System.out.println("# Edad: "+p.getEdad());
			System.out.println("# Peso: "+p.getPeso());
			Coche coche = p.getCoche();
			if(coche != null) {
				System.out.println("# Coche");
				System.out.println("# ID: "+coche.getId());
				System.out.println("# Marca: "+coche.getMarca());
				System.out.println("# Modelo: "+coche.getModelo());
				System.out.println("# Año: "+coche.getAño());
				System.out.println("# Km: "+coche.getKm());
			} else {
				System.out.println("# Coche: No tiene coche");
			}
			System.out.println();
		}
	}
	
	void añadirPasajeroCoche() {
		Scanner sc = new Scanner(System.in);
		int idPasajero=0, idCoche=0;
		gp = new GestorPasajero();
		boolean añadido = false;
		
		System.out.println("--> ¿Que id tiene el pasajero que quieres añadir a un coche?");
		try {
			idPasajero = Integer.parseInt(sc.nextLine());
			listarCoches();
			System.out.println("--> ¿A que coche quieres agregarlo?");
			idCoche = Integer.parseInt(sc.nextLine());
			
			añadido = gp.añadirPasajeroCoche(idPasajero, idCoche);
			if(añadido) {
				System.out.println("--> Se ha agregado correctamente al pasajero al coche con id "+idCoche);
			} else {
				System.out.println("ERROR: No se ha podido agregar el pasajero al coche");
			}
		} catch (NumberFormatException e) {
			System.out.println("ERROR: el dato introducido no es un número");
			añadirPasajeroCoche();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void eliminarPasajeroCoche() {
		Scanner sc = new Scanner(System.in);
		int idP = 0;
		gp = new GestorPasajero();
		boolean eliminado = false;
		
		System.out.println("--> ¿Que id tiene el pasajero al que le quieres eliminar el coche?");
		try {
			idP = Integer.parseInt(sc.nextLine());
			
			eliminado = gp.eliminarPasajeroCoche(idP);
			if(eliminado) {
				System.out.println("--> Se ha eliminado correctamente el coche al pasajero");
			} else {
				System.out.println("ERROR: No se ha podido eliminar el coche al pasajero");
			}
		} catch (NumberFormatException e) {
			System.out.println("ERROR: el dato introducido no es un número");
			eliminarCoche();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void listarPasajerosCoche() {
		Scanner sc = new Scanner(System.in);
		int idC = 0;
		gp = new GestorPasajero();
		
		System.out.println("--> ¿Que id tiene el coche del que quieres ver los pasajeros?");
		try {
			idC = Integer.parseInt(sc.nextLine());
			
			List<Pasajero> listado = gp.listarPasajerosCoche(idC);
			
			System.out.println("--> El coche introducido tiene ["+listado.size()+"] pasajeros");
			
			if(listado.size()>0) {
				for(Pasajero p:listado) {
					System.out.println("# ID: "+p.getId());
					System.out.println("# Nombre: "+p.getNombre());
					System.out.println("# Edad: "+p.getEdad());
					System.out.println("# Peso: "+p.getPeso());
				}
			}
			
		} catch(NumberFormatException e) {
			System.out.println("ERROR: el dato introducido no es un número");
			listarPasajerosCoche();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
