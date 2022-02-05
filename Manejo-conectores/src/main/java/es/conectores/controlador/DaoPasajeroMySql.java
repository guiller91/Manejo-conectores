package es.conectores.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.conectores.controlador.interfaz.DaoPasajero;
import es.conectores.modelo.Pasajero;




public class DaoPasajeroMySql implements DaoPasajero{

	private Connection conexion;
	
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/CONECTORES";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean alta(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into PASAJEROS (NOMBRE,EDAD,PESO)"
				+ " values(?,?,?)";
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean flag = true;
		String query = "DELETE FROM PASAJEROS WHERE ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				flag = false;
		} catch (SQLException e) {
			flag = false;
			System.out.println("baja() -> No se ha podido borrar"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return flag; 
	}

	@Override
	public Pasajero obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO from PASAJEROS "
				+ "where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "pasajero con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> lista = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE,EDAD,PESO FROM PASAJEROS";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return lista;
	}

	
	@Override
	public boolean asignarCoche(int idPasajero, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "UPDATE PASAJEROS SET COCHE_ID=?"
				+ " WHERE ID=?";
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al asignar pasajero");
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean desasignarCoche(int idPasajero) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean flag = true;
		String query = "UPDATE PASAJEROS SET COCHE_ID=NULL WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, idPasajero);
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				flag = false;
		} catch (SQLException e) {
			flag = false;
			System.out.println("baja() -> No se ha podido borrar"
					+ " el id " + idPasajero);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return flag;
	}

	@Override
	public List<Pasajero> listarPasajeros(int idCoche) {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> lista = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE FROM PASAJEROS WHERE COCHE_ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				
				
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return lista;
	}

	@Override
	public void joinList() {
		if(!abrirConexion()){
			return;
		}	
		String query = "Select Coches.ID,coches.MATRICULA, GROUP_CONCAT(DISTINCT pasajeros.id)"
				+ " AS ID_Pasajeros from coches "
				+ "left join pasajeros on coches.id = pasajeros.coche_id "
				+ "WHERE coches.ID=pasajeros.COCHE_ID group by 1;";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println("coche id: " + rs.getInt(1)+" ---" 
								  +" Matricula: " + rs.getString(2)+" ---" 
								  +" Id Pasajero: " + rs.getString(3));
				
				
				
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
	}

	
	


}
