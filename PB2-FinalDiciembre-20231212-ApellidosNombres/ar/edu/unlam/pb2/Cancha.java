package ar.edu.unlam.pb2;

import java.util.Set;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Piso;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public abstract class Cancha implements Alquilable {

	private Integer numero;
	private Set<Alquiler> alquileres;
	private Deporte deporte;
	private Double precio;
	private Piso piso;

	public Cancha(Integer numero, Deporte deporte) {
	}

	public void alquilar(Horario horario, Persona afiliado, Double senia) throws HorarioOcupadoException {
	}

	public Boolean estaDisponible(Horario horario) {
		return null;
	}

}
