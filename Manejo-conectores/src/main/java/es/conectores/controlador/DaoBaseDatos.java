package es.conectores.controlador;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controlador para la verificaci�n de la bbdd deseada.
 * De no encontrarla la creara.
 * @author Guiller
 *
 */
public class DaoBaseDatos {
	
	private Connection conexion;
	/**
	 * Creara una BBDD llamada CONECTORES en localhost:3306
	 * @return  @return True - Se ha podido crear CONECTORES <p>False - En caso contrario</p>
	 */
	public boolean crearBaseDatos(){
		String url = "jdbc:mysql://localhost:3306/";
		String usuario = "root";
		String password = "";
		try {
			String sql = "CREATE DATABASE CONECTORES";
			conexion = DriverManager.getConnection(url,usuario,password);
			Statement st = conexion.createStatement();
			st.executeUpdate(sql);
			cerrarConexion();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Intentara conectarse a la bbdd localhost:3306/CONECTORES
	 * @return True - Se ha podido conectar a CONECTORES <p>False - En caso contrario</p>
	 */
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/CONECTORES";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {			
			return false;
		}
		return true;
	}
	
	/**
	 * Cerrar la conexion en la bbdd 
	 * @return True - Se ha podido desconectar<p>False - En caso contrario</p>
	 */
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * Creara las tablas ya diseñadas en la bbdd.
	 * @param tabla - PASAJEROS o COCHES, para crear las tablas.
	 */
	public void crearTabla(String tabla) {
		
		String pasajeros = "CREATE TABLE PASAJEROS( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "NOMBRE VARCHAR(20) NOT NULL, "
				+ "EDAD INTEGER NOT NULL, "
				+ "PESO DOUBLE NOT NULL, "
				+ "COCHE_ID INTEGER, "
				+ "FOREIGN KEY (COCHE_ID) REFERENCES COCHES(ID))";
		String coches = "CREATE TABLE COCHES( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "MATRICULA VARCHAR(7) NOT NULL, "
				+ "MARCA VARCHAR(20) NOT NULL)";
		try{
			
			abrirConexion();
			Statement st = conexion.createStatement();
			if(tabla.equals("PASAJEROS")) {
				st.executeUpdate(pasajeros);
			}else if(tabla.equals("COCHES")) {
				st.executeUpdate(coches);
			}
			
		}catch (SQLException e) {
			System.out.println("checkTables()" + e.getMessage());
		}
		cerrarConexion();
		
	}
	
	/**
	 * Buscara en la bbdd las tablas COCHES y PASAJEROS.
	 * Si no las encuentra, las creara.
	 */
	public void checkTable() {
		
		try {
			abrirConexion();
			String[] tables = {"COCHES","PASAJEROS"};
			DatabaseMetaData metaData = conexion.getMetaData();
			for (int i=0; i<tables.length; i++) {
				ResultSet rs = metaData.getTables(null,null,tables[i],null);
				if(!rs.next()) {
					crearTabla(tables[i]);
				}
			}
			
		}catch (SQLException e) {
			System.out.println("checkTables() =" + e.getMessage());
		}
		cerrarConexion();
	}
	
	
	

}
