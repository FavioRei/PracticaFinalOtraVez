package ar.edu.unlam.pb2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import ar.edu.unlam.pb2.enums.Capacidad;
import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Horario;
import ar.edu.unlam.pb2.enums.Paredes;
import ar.edu.unlam.pb2.enums.Piso;
import ar.edu.unlam.pb2.exception.CapacidadMaximaAlcanzadaException;
import ar.edu.unlam.pb2.exception.CodigoAfiliadoDuplicadoException;
import ar.edu.unlam.pb2.exception.DniExistenteException;
import ar.edu.unlam.pb2.exception.HorarioOcupadoException;
import ar.edu.unlam.pb2.exception.NumeroDeCanchaDuplicadoException;
import ar.edu.unlam.pb2.interfaces.*;
import ar.edu.unlam.pb2.*;

public class TestCases {
	/*
	 * No pueden existir varios afiliados con el mismo
	 * codigo, o mismo DNI. Los afiliados pueden aprovechar las instalaciones para
	 * jugar Padel o Futbol. Para ello es necesario alquilar una cancha. Se debe
	 * abonar entre el 20 y el 80 % del costo del alquiler de la cancha, a modo de
	 * reserva. Solo un afiliado al club puede alquilarlas. Al finalizar el horario,
	 * el afiliado debe abonar el saldo restante y la cancha pasa a estar disponible
	 * en ese horario.
	 *  Las canchas de Padel pueden ser de piso sintetico o cemento,
	 * y pueden tener paredes vidriadas o de cemento Las canchas de futbol pueden
	 * ser de piso sintetico o cemento, y su capacidad para 5, 7 u 11 jugadores por
	 * equipo. No se debe permitir alquilar una cancha en un horario no disponible.
	 * 
	 */

	@Test
	public void queSePuedaAgregarUnaCanchaDePadel() throws NumeroDeCanchaDuplicadoException {
		Club unlam = new Club("Club Deportivo UNLAM");
		Alquilable canchaPadel1 = new CanchaPadel(1,Paredes.VIDRIADA,Piso.CEMENTO);
		
		
		unlam.agregarCancha(canchaPadel1);
		
		assertTrue(unlam.getCanchas().contains(canchaPadel1));
		
	}

	@Test(expected = NumeroDeCanchaDuplicadoException.class)
	public void queAlIntentarAgregarUnaCanchaConUnNumeroExistenteSeLanceUnaNumeroDeCanchaDuplicadoException() throws NumeroDeCanchaDuplicadoException {
		Club unlam = new Club("Club Deportivo UNLAM");
		Alquilable canchaPadel1 = new CanchaPadel(1,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaPadel2 = new CanchaPadel(1,Paredes.CEMENTO,Piso.SINTETICO);
		
		
		unlam.agregarCancha(canchaPadel1);
		unlam.agregarCancha(canchaPadel2);
		
		
		
	}

	@Test
	public void queSePuedaConsultarLasCanchasDePadelDisponibles() throws NumeroDeCanchaDuplicadoException {
		Club unlam = new Club("Club Deportivo UNLAM");
		Alquilable canchaPadel1 = new CanchaPadel(1,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaPadel2 = new CanchaPadel(2,Paredes.VIDRIADA,Piso.CEMENTO);
		
		Set<Alquilable> listaEsperada = new TreeSet<Alquilable>();
		listaEsperada.add(canchaPadel1);
		listaEsperada.add(canchaPadel2);
		
		unlam.agregarCancha(canchaPadel1);
		unlam.agregarCancha(canchaPadel2);
		
		
		
	    assertEquals(listaEsperada, unlam.getCanchasDisponibles(Deporte.PADEL, Horario.BLOQUE1));
	
	}

	@Test
	public void queUnaPersonaSePuedaAfiliarAlClub() throws CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException {
		Club unlam = new Club("Club Deportivo UNLAM");
		
		Persona afiliado1 = new Persona(44432233, "Juan", "Alvarez");//dni, nombre, apellido
		String codAfiliadoAuxiliar1 = "333";
		
		unlam.agregarAfiliado(afiliado1, codAfiliadoAuxiliar1);
		
		assertTrue(unlam.getAfiliados().containsKey(codAfiliadoAuxiliar1));
		assertTrue(unlam.getAfiliados().containsValue(afiliado1));
		
	}

	@Test
	public void queUnAfiliadoPuedaAlquilarUnaCanchaDePadel() throws NumeroDeCanchaDuplicadoException, HorarioOcupadoException, CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException {
    	Club unlam = new Club("Club Deportivo UNLAM");
		
		Persona afiliado1 = new Persona(44432233, "Juan", "Alvarez");//dni, nombre, apellido
		String codAfiliadoAuxiliar1 = "333";
		Alquilable canchaPadel1 = new CanchaPadel(1,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaPadel2 = new CanchaPadel(2,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaPadel3 = new CanchaPadel(3,Paredes.VIDRIADA,Piso.CEMENTO);
		
		Set<Alquilable> listaEsperada = new TreeSet<Alquilable>();
		listaEsperada.add(canchaPadel2);
		listaEsperada.add(canchaPadel3);
		
		unlam.agregarAfiliado(afiliado1, codAfiliadoAuxiliar1);
		unlam.agregarCancha(canchaPadel1);
		canchaPadel1.setPrecio(1000.0);
		unlam.agregarCancha(canchaPadel2);
		canchaPadel2.setPrecio(1000.0);
		unlam.agregarCancha(canchaPadel3);
		canchaPadel2.setPrecio(1000.0);
		
		
		canchaPadel1.alquilar(Horario.BLOQUE1, afiliado1, 500.0);
		
		assertEquals(listaEsperada, unlam.getCanchasDisponibles(Deporte.PADEL, Horario.BLOQUE1));
	}

	@Test
	public void queSePuedaAveriguarCuantoDebePagarPorLaCanchaDeFutbol11AlquiladaConsiderandoLaSenia() throws CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException, NumeroDeCanchaDuplicadoException, HorarioOcupadoException {
    	Club unlam = new Club("Club Deportivo UNLAM");
		
		Persona afiliado1 = new Persona(44432233, "Juan", "Alvarez");//dni, nombre, apellido
		String codAfiliadoAuxiliar1 = "333";
		Alquilable canchaFutbo1 = new CanchaFutbol(1,Capacidad.ONCE,Piso.CEMENTO);
		Double ve = 700.0;

		
		unlam.agregarAfiliado(afiliado1, codAfiliadoAuxiliar1);
		unlam.agregarCancha(canchaFutbo1);
		canchaFutbo1.setPrecio(1000.0);

		
		
		canchaFutbo1.alquilar(Horario.BLOQUE1, afiliado1, 300.0);
		
		assertEquals(ve, ((Cancha) canchaFutbo1).getRestanteAPagar(Horario.BLOQUE1));
	}

	@Test
	public void queSePuedaObtenerUnMapaConLosTotalesRecaudadosPorCadaCancha() throws NumeroDeCanchaDuplicadoException, HorarioOcupadoException, CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException {
		// TODO: para la key utilizar el siguiente formato:
		// Futbol: FUTBOL-11-1 (Deporte-Capacidad-numero)
		// Padel: PADEL-VIDRIADA-1 (Deporte-Paredes-numero)
		
		Club unlam = new Club("Club Deportivo UNLAM");
					
		Persona afiliado1 = new Persona(44432233, "Juan", "Alvarez");//dni, nombre, apellido
		String codAfiliadoAuxiliar1 = "333";	
		Alquilable canchaPadel1 = new CanchaPadel(1,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaPadel2 = new CanchaPadel(2,Paredes.VIDRIADA,Piso.CEMENTO);
		Alquilable canchaFutbo1 = new CanchaFutbol(3,Capacidad.ONCE,Piso.CEMENTO);
				
				
		unlam.agregarAfiliado(afiliado1, codAfiliadoAuxiliar1);
		unlam.agregarCancha(canchaPadel1);
		canchaPadel1.setPrecio(1000.0);
		unlam.agregarCancha(canchaPadel2);
		canchaPadel2.setPrecio(1200.0);
		unlam.agregarCancha(canchaFutbo1);
		canchaFutbo1.setPrecio(8000.0);
				
				
		canchaPadel1.alquilar(Horario.BLOQUE1, afiliado1, 500.0);
		canchaPadel1.alquilar(Horario.BLOQUE2, afiliado1, 500.0);
		canchaPadel2.alquilar(Horario.BLOQUE1, afiliado1, 700.0);
		canchaFutbo1.alquilar(Horario.BLOQUE1, afiliado1, 2000.0);
		
		//hacer el for de comprobacion xd
		//unlam.obtenerTotalesPorCancha()
		Integer i = 0;
		
		for (Map.Entry<String, Double> entry : unlam.obtenerTotalesPorCancha().entrySet()) {
			String key = entry.getKey();
			Double val = entry.getValue();
			
			switch (i) {
			case 0:
				assertEquals("FUTBOL-11-1 ( FUTBOL - ONCE - 3)", key);
				assertEquals(8000.0, val, 0.1);
				break;
			case 1:
				assertEquals("PADEL-VIDRIADA-1 ( PADEL - VIDRIADA - 1)", key);
				assertEquals(2000.0, val, 0.1);
				break;
			case 2:
				assertEquals("PADEL-VIDRIADA-1 ( PADEL - VIDRIADA - 2)", key);
				assertEquals(1200.0, val, 0.1);
				break;

			
		}
			i++;
		}
	}

	@Test
	public void queSePuedanObtenerLasPersonasAfiliadasAlClubOrdenadasPorApellidoAscendente()  throws CapacidadMaximaAlcanzadaException, DniExistenteException, CodigoAfiliadoDuplicadoException {
		Club unlam = new Club("Club Deportivo UNLAM");
		
		Persona afiliado1 = new Persona(44432233, "Juan", "Alvarez");//dni, nombre, apellido 1
		Persona afiliado2 = new Persona(44432234, "Pepe", "Perez");//dni, nombre, apellido 4
		Persona afiliado3 = new Persona(44432235, "Rodrigo", "Gomez");//dni, nombre, apellido 2
		Persona afiliado4 = new Persona(44432236, "Lucia", "Paz");//dni, nombre, apellido 3

		String codAfiliadoAuxiliar1 = "333";
		String codAfiliadoAuxiliar2 = "332";
		String codAfiliadoAuxiliar3 = "331";
		String codAfiliadoAuxiliar4 = "330";
		

		unlam.agregarAfiliado(afiliado1, codAfiliadoAuxiliar1);
		unlam.agregarAfiliado(afiliado2, codAfiliadoAuxiliar2);
		unlam.agregarAfiliado(afiliado3, codAfiliadoAuxiliar3);
		unlam.agregarAfiliado(afiliado4, codAfiliadoAuxiliar4);
		
		unlam.getAfiliados();
		
		Integer i = 0;
		
	
		for (Persona afiliados : unlam.getAfiliadosPorApellido()) {
			
			switch (i) {
			case 0:
				assertEquals(afiliado1, afiliados);
	
				break;
			case 1:
				assertEquals(afiliado3, afiliados);
				break;
			case 2:
				assertEquals(afiliado4, afiliados);
				break;
			case 3:
				assertEquals(afiliado2, afiliados);
				break;
			}
			i++;
		}
		
	}

}