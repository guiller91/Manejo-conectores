package es.conectores.controlador.interfaz;

import java.util.List;

import es.conectores.modelo.Pasajero;
/**
 * Controlador para la tabla de pasajeros de la bbdd
 * @author Guiller
 *
 */
public interface DaoPasajero {

	/**
	 * Dar de alta un pasajero en la bbdd.
	 * @param p - Representa al pasajero a dar de alta
	 * @return true - se ha añadido correctamente<p>false - en caso contrario</p>
	 */
	public boolean alta(Pasajero p);
	
	/**
	 * Dar de baja una persona en la bbdd mediante su ID.
	 * @param id - Id del pasajero a dar de baja
	 * @return true - se ha borrado correctamente<p>false - en caso contrario</p>
	 */
	public boolean baja(int id);
	
	/**
	 * Obtener una persona mediante su ID. 
	 * @param id - Id del pasajero a buscar
	 * @return Pasajero - Pasajero encontrada <p> Null - No encontrado </p>
	 */
	public Pasajero obtener(int id);
	
	/**
	 * Devuelve las personas que existen en la bbdd.
	 * @return List de Pasajero
	 */
	public List<Pasajero> listar();
	
	/**
	 * Asigna un coche a un pasajero.
	 * @param idPasajero - Id del pasajero a asignar al coche
	 * @param idCoche - Id del coche al que asignar al pasajero
	 * @return true - se ha añadido correctamente<p>false - en caso contrario</p>
	 */
	public boolean asignarCoche(int idPasajero, int idCoche);
	
	/**
	 * Desasigna un pasajero del coche al que pertenece.
	 * @param idPasajero - Id del pasajero a desasignar del coche
	 * @return true - se ha quitado correctamente<p>false - en caso contrario</p>
	 */
	public boolean desasignarCoche(int idPasajero);
	
	/**
	 * Listar todos los pasajeros de un coche.
	 * @param idCoche - Id del coche del que desea mostrar sus pasajeros
	 * @return Lista de pasajeros de el coche
	 */
	public List<Pasajero> listarPasajeros(int idCoche);
	
	/**
	 * Consulta a la base de datos que nos devolvera todos los coches con los 
	 * pasajeros que llevan
	 */
	public void joinList();
	
}
