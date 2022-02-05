package es.conectores.controlador.interfaz;

import java.util.List;

import es.conectores.modelo.Coche;
/**
 * Controlador para la parte de coches de la bbdd
 * @author Guiller
 *
 */
public interface DaoCoche {

	/**
	 * A�ade un coche a la bbdd
	 * @param c - Coche a añadir
	 * @return true - se ha añadido correctamente<p>false - en caso contrario</p>
	 */
	public boolean añadirCoche(Coche c);
	
	/**
	 * Busca un coche mediante su id
	 * @param id - Id del coche a buscar
	 * @return Coche - En caso de encontrarle<p>Null - En caso contrario</p>
	 */
	public Coche buscarCoche(int id);
	
	/**
	 * Recibe un coche, al que buscara usando la ID y modificara con los datos introducidos
	 * @param c - Coche con la id del coche deseado y demas datos a modificar
	 * @returntrue - se ha modificado correctamente<p>false - en caso contrario</p>
	 */
	public boolean modificarCoche(Coche c);
	
	/**
	 * Borra un coche de la bbdd mediante su id.
	 * @param id - Id del coche a borrar
	 * @return true - se ha borrado correctamente<p>false - en caso contrario</p>
	 */
	public boolean borrarCoche(int id);
	
	/**
	 * Devolvera una lista de todos los coches en la bbdd
	 * @return Lista de coches
	 */
	public List<Coche> list();
}