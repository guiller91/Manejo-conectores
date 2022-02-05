package es.conectores.modelo;

import java.util.Objects;

public class Coche {
	
	private int id;
	private String matricula;
	private String marca;
	
	public Coche() {
		super();
	}
	
	public Coche(int id, String matricula, String marca) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marca, matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id && Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula);
	}

	@Override
	public String toString() {
		return " ID: "+ id + " - Marca: " + marca + " - Matricula: " + matricula + "\n";
	}
	
	

}
