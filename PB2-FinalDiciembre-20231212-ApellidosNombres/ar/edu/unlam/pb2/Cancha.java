package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ar.edu.unlam.pb2.enums.Capacidad;
import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Piso;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public abstract class Cancha implements Alquilable,Comparable<Cancha> {

	private Integer numero;
	private Set<Alquiler> alquileres;
	private Deporte deporte;
	private Double precio;
	private Piso piso;

	public Cancha(Integer numero, Deporte deporte, Piso piso) {
		this.numero = numero;
		this.deporte = deporte;
		this.alquileres = new HashSet<Alquiler>();
		this.piso = piso;
	}

	public void alquilar(Horario horario, Persona afiliado, Double senia) throws HorarioOcupadoException {
		if(!estaDisponible(horario)) {
			throw new HorarioOcupadoException();
		}
		
		Alquiler nuevoAlquiler = new Alquiler(horario, afiliado, senia);
		alquileres.add(nuevoAlquiler);
		
	}

	public Boolean estaDisponible(Horario horario) {
		for (Alquiler alquiler : alquileres) {
			if(alquiler.getHorario().equals(horario)) {
				return false;
			}
		}
		
		return true;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getNumero() {
		return numero;
	}
	@Override
	public int compareTo(Cancha o) {
		return this.getNumero().compareTo(o.getNumero());
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Cancha other = (Cancha) obj;
		return Objects.equals(numero, other.numero);
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public Double getRestanteAPagar(Horario horario) {
		Double seniaDada = getAlquilerPorHorario(horario).getSenia();
		return this.precio - seniaDada;
	}

	private Alquiler getAlquilerPorHorario(Horario horario) {
		for (Alquiler alquiler : alquileres) {
			if(alquiler.getHorario().equals(horario)) {
				return alquiler;
			}
		}
		return null;
	}

	public Integer horariosAlquilados() {
		return alquileres.size();
	}

	public Double getPrecio() {
		return precio;
	}

	public String pseudoToString(String datoFaltante) {
		if(this.deporte.equals(Deporte.FUTBOL)) {
			return "FUTBOL-11-1 ( " + this.deporte + " - " + datoFaltante + " - " + this.numero + ")";
		}else {
			return "PADEL-VIDRIADA-1 ( " + this.deporte + " - " + datoFaltante + " - " + this.numero + ")";
		}
	}


}
