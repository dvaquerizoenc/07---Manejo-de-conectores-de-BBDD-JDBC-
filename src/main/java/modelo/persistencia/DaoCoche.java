package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;

public class DaoCoche {
	
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
	
	public boolean añadirCoche(Coche coche) {
		boolean persistido = false;
		
		if(abrirBBDD()) {
			String query = "INSERT INTO `07_coches` (marca, modelo, año, km) VALUES (?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setString(1, coche.getMarca());
				ps.setString(2, coche.getModelo());
				ps.setInt(3, coche.getAño());
				ps.setInt(4, coche.getKm());
				
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
	
	public boolean borrarCocheID(int id) {
		boolean eliminado = false;
		
		if(abrirBBDD()) {
			String query = "DELETE FROM `07_coches` WHERE id=?";
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
	
	public Coche consultaCocheID(int id) {
		Coche coche = null;
		if(abrirBBDD()) {
			String query = "SELECT * FROM `07_coches` WHERE id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMarca(rs.getString(2));
					coche.setModelo(rs.getString(3));
					coche.setAño(rs.getInt(4));
					coche.setKm(rs.getInt(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		return coche;
	}
	
	public boolean modificarCocheID(int id, Coche coche) {
		boolean modificado = false;
		
		if(abrirBBDD()) {
			String query = "UPDATE `07_coches` SET marca=?, modelo=?, año=?, km=? WHERE id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ps.setString(1, coche.getMarca());
				ps.setString(2, coche.getModelo());
				ps.setInt(3, coche.getAño());
				ps.setInt(4, coche.getKm());
				ps.setInt(5, coche.getId());

				int filas = ps.executeUpdate();
				if(filas==1) {
					modificado = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
		}
		
		cerrarBBDD();
		
		return modificado;
	}
	
	public List<Coche> listarCoches(){
		List<Coche> listaCoches = new ArrayList<Coche>();
		Coche coche = null;
		
		if(abrirBBDD()) {
			String query = "SELECT * FROM `07_coches`";
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					coche = new Coche();
					
					coche.setId(rs.getInt(1));
					coche.setMarca(rs.getString(2));
					coche.setModelo(rs.getString(3));
					coche.setAño(rs.getInt(4));
					coche.setKm(rs.getInt(5));
					
					listaCoches.add(coche);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cerrarBBDD();
			}
			
		}
		
		cerrarBBDD();
		
		return listaCoches;
	}
	
}
