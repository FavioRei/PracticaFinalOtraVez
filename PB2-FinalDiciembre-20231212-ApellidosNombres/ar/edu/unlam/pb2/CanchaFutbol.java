package ar.edu.unlam.pb2;

import ar.edu.unlam.pb2.enums.Capacidad;
import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Piso;

public class CanchaFutbol extends Cancha{
	private Capacidad capacidad;

	public CanchaFutbol(Integer numero, Capacidad capacidad, Piso piso) {
		super(numero, Deporte.FUTBOL, piso);
		this.capacidad = capacidad;
		
	}

	public Capacidad getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Capacidad capacidad) {
		this.capacidad = capacidad;
	}

	public String getStringCapacidad() {
		return "" + this.capacidad + "";
	}



}
