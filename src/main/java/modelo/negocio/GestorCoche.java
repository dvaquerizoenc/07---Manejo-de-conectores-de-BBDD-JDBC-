package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;

public class GestorCoche {
	private DaoCoche daoCoche = new DaoCoche();
	
	/**
	 * Método para añadir un coche pasado por parámetro
	 * @param coche
	 * @return 1 si la marca esta en blanco, 2 si el modelo esta en blanco, 3 si se ha añadido correctamente
	 */
	public int añadirCoche(Coche coche) {
		int añadido = 0;
		
		if(coche.getMarca().isBlank()) {
			añadido = 1;
		} else if(coche.getModelo().isBlank()){
			añadido = 2;
		} else {
			añadido = daoCoche.añadirCoche(coche)?3:4;
		}
		
		return añadido;
	}
	
	public boolean borrarCocheID(int id) {
		return daoCoche.borrarCocheID(id);
	}
	
	public Coche consultaCocheID(int id) {
		return daoCoche.consultaCocheID(id);
	}
	
	public boolean modificarCocheID(int id, Coche coche) {		
		return daoCoche.modificarCocheID(id, coche);
	}
	
	public List<Coche> listarCoches(){
		return daoCoche.listarCoches();
	}
}
