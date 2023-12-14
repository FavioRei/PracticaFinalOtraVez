package ar.edu.unlam.pb2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.exception.CapacidadMaximaAlcanzadaException;
import ar.edu.unlam.pb2.exception.CodigoAfiliadoDuplicadoException;
import ar.edu.unlam.pb2.exception.DniExistenteException;
import ar.edu.unlam.pb2.exception.NumeroDeCanchaDuplicadoException;
import ar.edu.unlam.pb2.interfaces.Alquilable;

public class Club {
	private String nombre;
	private Integer capacidad;

	private Set<Alquilable> canchas;
	private Map<String, Persona> afiliados;
//
	public Club(String nombre) {
		this.nombre = nombre;
		this.canchas = new TreeSet<Alquilable>();
		this.afiliados = new TreeMap<String, Persona>();
	}
//
	public void agregarAfiliado(Persona persona, String codAfiliado) throws CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException {
		if(this.afiliados.size() == 500) {
			throw new CapacidadMaximaAlcanzadaException();
		}
		if(existeDniAfiliado(persona.getDni())) { 
			throw new DniExistenteException();
		}
		if(existeCodAfiliado(codAfiliado)){
			throw new CodigoAfiliadoDuplicadoException();
		}
		afiliados.put(codAfiliado, persona);
		
	}
//
    private boolean existeCodAfiliado(String codAfiliado2) {   	
    	
    	for (Map.Entry<String, Persona> entry : afiliados.entrySet()) {
			String key = entry.getKey();
			Persona val = entry.getValue();
			if(key.equals(codAfiliado2)) {
				return true;
			}
		}
    	
		return false;
	}
//
	private boolean existeDniAfiliado(Integer dni) {
    	
    	for (Map.Entry<String, Persona> entry : afiliados.entrySet()) {
			String key = entry.getKey();
			Persona val = entry.getValue();
			if(val.getDni().equals(dni)) {
				return true;
			}
		}
    	
    	return false;
    }
    
//
	public void agregarCancha(Alquilable cancha) throws NumeroDeCanchaDuplicadoException {
	
			if(existeCancha(cancha.getNumero())) {
				throw new NumeroDeCanchaDuplicadoException();
			}
		
		canchas.add(cancha);
	}
//
	public Boolean existeCancha(Integer codigoCancha) {
		for (Alquilable alquilable : canchas) {
			if(alquilable.getNumero() == codigoCancha) {
				return true;
			}
		}
		return false;
	}
//
	public Alquilable getCancha(Integer numeroCancha) {
		for (Alquilable alquilable : canchas) {
			if(alquilable.getNumero() == numeroCancha) {
				return alquilable;
			}
		}
		return null;
	}
//
	public Set<Alquilable> getCanchasDisponibles(Deporte deporte, Horario horario) {
		Set<Alquilable> listaNueva = new TreeSet<Alquilable>();
		for (Alquilable alquilable : canchas) {
			if(alquilable.estaDisponible(horario) && alquilable.getDeporte().equals(deporte)) {
				listaNueva.add(alquilable);
			}
		}
		
		return listaNueva;
	}
	
	public Map<String, Double> obtenerTotalesPorCancha(){
		Map<String, Double> totalesPorCancha = new HashMap<String, Double>();
		Double totalRecaudado;
		
		for (Alquilable alquilable : canchas) {
			totalRecaudado = ((Cancha) alquilable).getPrecio()*((Cancha) alquilable).horariosAlquilados();
			
			if(alquilable instanceof CanchaFutbol) {
				totalesPorCancha.put(((Cancha) alquilable).pseudoToString(((CanchaFutbol)alquilable).getStringCapacidad()), totalRecaudado);
			}else {
				totalesPorCancha.put(((Cancha) alquilable).pseudoToString(((CanchaPadel)alquilable).getStringParedes()), totalRecaudado);
			}
		}
		
		return totalesPorCancha;
	}
//
	public Set<Alquilable> getCanchas() {
		return canchas;
	}
//
	public Map<String, Persona> getAfiliados() {
		return afiliados;
	}
//
	public Set<Persona> getAfiliadosPorApellido() {
		Set afiliadosOrdenadosPorApellido = new TreeSet<Persona>();
		for (Map.Entry<String, Persona> entry : this.afiliados.entrySet()) {
			String key = entry.getKey();
			Persona val = entry.getValue();
			afiliadosOrdenadosPorApellido.add(val);
		}
		return afiliadosOrdenadosPorApellido;
	}
}
