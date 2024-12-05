package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public class DaoPasajero {
private Connection conn;
	
	private boolean abrirBBDD() {
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		boolean conectada = false;
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conectada = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conectada;
	}
	
	private boolean cerrarBBDD() {
		boolean cerrada = false;
		try {
			conn.close();
			cerrada = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cerrada;
	}
	
	public boolean crearPasajero(Pasajero pasajero) {
		boolean persistido = false;
		
		if(abrirBBDD()) {
			String query = "INSERT INTO `07_pasajeros` (nombre, edad, peso) VALUES (?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setString(1, pasajero.getNombre());
				ps.setInt(2, pasajero.getEdad());
				ps.setDouble(3, pasajero.getPeso());
				
				int filas = ps.executeUpdate();
				
				if(filas!=0) {
					persistido = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
		}
		
		cerrarBBDD();
		
		return persistido;
	}
	
	public boolean borrarPasajeroID(int id) {
		boolean eliminado = false;
		
		if(abrirBBDD()) {
			String query = "DELETE FROM `07_pasajeros` WHERE id=?";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, id);
				
				eliminado = ps.executeUpdate()==1?true:false;;
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
		}
		
		cerrarBBDD();
		
		return eliminado;
	}
	
	public Pasajero consultaPasajeroID(int id) {
		Pasajero pasajero = null;
		if(abrirBBDD()) {
			String query = "SELECT * FROM `07_pasajeros` WHERE id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));
					DaoCoche dC = new DaoCoche();
					Integer idCoche = rs.getInt(5);
					if(idCoche != null) {
						Coche coche = dC.consultaCocheID(idCoche);
						pasajero.setCoche(coche);
					} else {
						pasajero.setCoche(null);
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		return pasajero;
	}
	
	public List<Pasajero> listarPasajeros(){
		List<Pasajero> listaCoches = new ArrayList<Pasajero>();
		Pasajero pasajero = null;
		
		if(abrirBBDD()) {
			String query = "SELECT * FROM `07_pasajeros`";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));
					DaoCoche dC = new DaoCoche();
					Integer idCoche = rs.getInt(5);
					if(idCoche != null) {
						Coche coche = dC.consultaCocheID(idCoche);
						pasajero.setCoche(coche);
					}
					
					listaCoches.add(pasajero);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		
		return listaCoches;
	}
	
	public boolean añadirPasajeroCoche(int idP, int idC) {
		boolean añadido = false;
		
		if(abrirBBDD()) {
			String query = "UPDATE `07_pasajeros` SET coche = ? WHERE id = ?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, idC);
				ps.setInt(2, idP);
				
				int filas = ps.executeUpdate();
				
				if(filas != 0) {
					añadido = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		
		return añadido;
	}
	
	public boolean eliminarPasajeroCoche(int idP) {
		boolean eliminado = false;
		
		if(abrirBBDD()) {
			String query = "UPDATE `07_pasajeros` SET coche = NULL WHERE id = ?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, idP);
				
				int filas = ps.executeUpdate();
				
				if(filas != 0) {
					eliminado = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		
		return eliminado;
	}
	
	public List<Pasajero> listarPasajerosCoche(int idC){
		List<Pasajero> listado = new ArrayList<Pasajero>();
		Pasajero pasajero = null;

		if(abrirBBDD()) {
			String query = "SELECT * FROM `07_pasajeros` WHERE coche=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, idC);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					pasajero = new Pasajero();
					pasajero.setId(rs.getInt(1));
					pasajero.setNombre(rs.getString(2));
					pasajero.setEdad(rs.getInt(3));
					pasajero.setPeso(rs.getDouble(4));
					DaoCoche dC = new DaoCoche();
					Integer idCoche = rs.getInt(5);
					if(idCoche != null) {
						Coche coche = dC.consultaCocheID(idCoche);
						pasajero.setCoche(coche);
					}
					
					listado.add(pasajero);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		
		return listado;
	}
}
