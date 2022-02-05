package es.conectores.modelo;

import java.util.Objects;

public class Pasajero {
	
	private int id;
	private String nombre;
	private int edad;
	private double peso;
	
	public Pasajero() {
		super();
	}
	
	public Pasajero(int id, String nombre, int edad, double peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return " ID: "+ id +" - Nombre: " + nombre + " - Edad: " + edad +" - Peso: " + peso +"\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, id, nombre, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		return edad == other.edad && id == other.id && Objects.equals(nombre, other.nombre) && peso == other.peso;
	}
	
	
	

}
