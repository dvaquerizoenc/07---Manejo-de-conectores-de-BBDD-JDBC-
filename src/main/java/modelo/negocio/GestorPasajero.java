package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajero;

public class GestorPasajero {
	private DaoPasajero daoPasajero = new DaoPasajero();
	
	public boolean crearPasajero(Pasajero pasajero) {
		return daoPasajero.crearPasajero(pasajero);
	}
	
	public boolean borrarPasajeroID(int id) {
		return daoPasajero.borrarPasajeroID(id);
	}
	
	public Pasajero consultaPasajeroID(int id) {
		return daoPasajero.consultaPasajeroID(id);
	}
	
	public List<Pasajero> listarPasajeros() {
		return daoPasajero.listarPasajeros();
	}
	
	public boolean añadirPasajeroCoche(int idP, int idC) {
		return daoPasajero.añadirPasajeroCoche(idP, idC);
	}
	
	public boolean eliminarPasajeroCoche(int idP) {
		return daoPasajero.eliminarPasajeroCoche(idP);
	}
	
	public List<Pasajero> listarPasajerosCoche(int idC) {
		return daoPasajero.listarPasajerosCoche(idC);
	}
}
