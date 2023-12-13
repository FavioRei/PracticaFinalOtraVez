package ar.edu.unlam.pb2;

import java.util.Map;
import java.util.Set;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public class Club {
	private String nombre;
	private Integer capacidad;

	private Set<Alquilable> canchas;
	private Map<String, Persona> afiliados;

	public Club(String nombre) {
	}

	public void agregarCancha(Alquilable cancha) {
	}

	public Boolean existeCancha(Integer codigoCancha) {
		return null;
	}

	public Alquilable getCancha(Integer numeroCancha) {
		return null;
	}

	public Set<Alquilable> getCanchasDisponibles(Deporte deporte, Horario horario) {
		return null;
	}
	
	public Map<String, Double> obtenerTotalesPorCancha(){
		return null;
	}
}
