package es.conectores.vista;

import java.util.Scanner;

import es.conectores.controlador.DaoBaseDatos;
import es.conectores.controlador.DaoCocheMySql;
import es.conectores.controlador.DaoPasajeroMySql;
import es.conectores.controlador.interfaz.DaoCoche;
import es.conectores.controlador.interfaz.DaoPasajero;
import es.conectores.modelo.Coche;
import es.conectores.modelo.Pasajero;

/**
 * Clase main de la aplicaci�n.
 *
 * @author guiller
 * @version 1.0
 */
public class Main {

	// dao para su uso en las funciones menuPrincipal() y subMenu()
	static DaoCoche dc = new DaoCocheMySql();
	static DaoPasajero dp = new DaoPasajeroMySql();

	public static void main(String[] args) {

		// Preparacion de la base de datos
		DaoBaseDatos dao = new DaoBaseDatos();

		if (!dao.abrirConexion()) {
			dao.crearBaseDatos();
			dao.checkTable();
		} else {
			System.out.println("Conectado a la base de datos");
			dao.checkTable();
		}

		//Invocamos el menu principal
		menuPrincipal();

	}

	public static void menuPrincipal() {
		try (Scanner sc = new Scanner(System.in)) {

			boolean continuar = true;
			int num;

			System.out.println("\n--------- GESTION DE COCHES ---------\n");
			do {
				System.out.println("\nElige un numero del menu:\n" + "1. Añadir nuevo coche\n"
						+ "2. Borrar coche por ID\n" + "3. Consultar coche por ID\n" + "4. Modificar Coche por ID\n"
						+ "5. Listado de coches\n" + "6. Gestion de pasajeros\n" + "7. Salir de la aplicacion");
				num = Integer.parseInt(sc.nextLine());
				Coche coche;
				switch (num) {
				case 1:
					coche = new Coche();
					System.out.println("Introduzca Matricula (7 digitos max)");
					coche.setMatricula(sc.nextLine());
					System.out.println("Introduzca Marca");
					coche.setMarca(sc.nextLine());
					if (dc.añadirCoche(coche)) {
						System.out.println("Coche añadido correctamente\n");
						}

					break;
				case 2:
					System.out.println("Introduzca ID del coche que desea borrar");
					if (dc.borrarCoche(Integer.parseInt(sc.nextLine()))) {
						System.out.println("Coche borrado correctamente\n");
						}
					break;
				case 3:
					System.out.println("Introduzca ID del coche que desea buscar");
					System.out.println(dc.buscarCoche(Integer.parseInt(sc.nextLine())));

					break;
				case 4:
					coche = new Coche();
					System.out.println("Introduce el ID del coche a modificar");
					coche.setId(Integer.parseInt(sc.nextLine()));
					System.out.println("Introduce la Matricula");
					coche.setMatricula(sc.nextLine());
					System.out.println("Introduzca Marca");
					coche.setMarca(sc.nextLine());
					if (dc.modificarCoche(coche)) {
						System.out.println("Coche modificado con exito");
						}
					break;
				case 5:
					System.out.println("Lista de coches : ");
					System.out.println(dc.list());
					break;
				case 6:
					subMenu();
					break;
				case 7:
					System.out.println("Saliendo de la aplicacion....");
					System.exit(0);
				default:
					System.out.println("Introduzca un numero del menu");
				}

			} while (continuar);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void subMenu() {

		try (Scanner sc2 = new Scanner(System.in)) {
			boolean flag = true;
			int num2, idPasaj, idCoche;

			Pasajero pasajero;
			System.out.println("\n--------- GESTION DE PASAJEROS ---------\n");
			do {
				System.out.println("\nElige un numero del menu:\n" + "1. Añadir nueva persona\n"
						+ "2. Borrar persona por ID\n" + "3. Consultar persona por ID\n"
						+ "4. Listar todas las personas\n" + "5. Añadir pasajero a coche\n"
						+ "6. Eliminar pasajero del coche\n" + "7. Listar pasajeros de un coche\n"
						+ "8. Listar Coches con sus pasajeros\n" + "9. Volver al menu anterior");
				num2 = Integer.parseInt(sc2.nextLine());

				switch (num2) {
				case 1:
					pasajero = new Pasajero();
					System.out.println("Introduzca nombre");
					pasajero.setNombre(sc2.nextLine());
					System.out.println("Introduzca Edad");
					pasajero.setEdad(Integer.parseInt(sc2.nextLine()));
					System.out.println("Introzca Peso");
					pasajero.setPeso(Double.parseDouble(sc2.nextLine()));
					if (dp.alta(pasajero)) {
						System.out.println("Persona añadida correctamente");
						}

					break;
				case 2:
					System.out.println("Introduzca ID de la persona que desea borrar");
					if (dp.baja(Integer.parseInt(sc2.nextLine()))) {
						System.out.println("Persona borrada correctamente");
						}
					break;
				case 3:
					System.out.println("Introduzca ID de la persona que desea buscar");
					System.out.println(dp.obtener(Integer.parseInt(sc2.nextLine())));
					break;
				case 4:
					System.out.println("Listado de personas:");
					System.out.println(dp.listar());
					break;
				case 5:
					System.out.println(dp.listar());
					System.out.println("Introduzca el Id del pasajero que quiere asignar");
					idPasaj = Integer.parseInt(sc2.nextLine());
					System.out.println("Los coches disponibles son: ");
					System.out.println(dc.list());
					System.out.println("Introduzca el id del coche que desea");
					idCoche = Integer.parseInt(sc2.nextLine());
					if (dp.asignarCoche(idPasaj, idCoche)) {
						System.out.println("Pasajero añadido al coche con exito");
						}
					break;
				case 6:

					dp.joinList();
					System.out.println("Introduzca el Id del pasajero que quiere desasignar del coche");
					idPasaj = Integer.parseInt(sc2.nextLine());

					if (dp.desasignarCoche(idPasaj)) {
						System.out.println("Pasajero desasignado");
						}
					break;

				case 7:
					System.out.println("Introduzca el id del coche que desea ver sus pasajeros");
					idCoche = Integer.parseInt(sc2.nextLine());
					System.out.println(dp.listarPasajeros(idCoche));

					break;
				case 8:
					dp.joinList();
					break;

				case 9:
					System.out.println("Volviendo al menu anterior.....");
					menuPrincipal();
					break;
				default:
					System.out.println("Introduzca un numero del menu");
				}

			}while (flag);
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
