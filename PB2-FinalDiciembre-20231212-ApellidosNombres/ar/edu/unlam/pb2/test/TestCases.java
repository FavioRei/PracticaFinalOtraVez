package ar.edu.unlam.pb2.test;

import org.junit.Test;

import ar.edu.unlam.pb2.exception.NumeroDeCanchaDuplicadoException;

public class TestCases {
	/*
	 * Se pide realizar un sistema para Club Deportivo UNLAM. El club cuenta con
	 * capacidad para 500 afiliados No pueden existir varios afiliados con el mismo
	 * codigo, o mismo DNI. Los afiliados pueden aprovechar las instalaciones para
	 * jugar Padel o Futbol. Para ello es necesario alquilar una cancha. Se debe
	 * abonar entre el 20 y el 80 % del costo del alquiler de la cancha, a modo de
	 * reserva. Solo un afiliado al club puede alquilarlas. Al finalizar el horario,
	 * el afiliado debe abonar el saldo restante y la cancha pasa a estar disponible
	 * en ese horario. Las canchas de Padel pueden ser de piso sintetico o cemento,
	 * y pueden tener paredes vidriadas o de cemento Las canchas de futbol pueden
	 * ser de piso sintetico o cemento, y su capacidad para 5, 7 u 11 jugadores por
	 * equipo. No se debe permitir alquilar una cancha en un horario no disponible.
	 * 
	 */

	@Test
	public void queSePuedaAgregarUnaCanchaDePadel() {
	}

	@Test
	public void queAlIntentarAgregarUnaCanchaConUnNumeroExistenteSeLanceUnaNumeroDeCanchaDuplicadoException() {
	}

	@Test
	public void queSePuedaConsultarLasCanchasDePadelDisponibles() {
	}

	@Test
	public void queUnaPersonaSePuedaAfiliarAlClub() {
	}

	@Test
	public void queUnAfiliadoPuedaAlquilarUnaCanchaDePadel() {
	}

	@Test
	public void queSePuedaAveriguarCuantoDebePagarPorLaCanchaDeFutbol11AlquiladaConsiderandoLaSenia() {

	}

	@Test
	public void queSePuedaObtenerUnMapaConLosTotalesRecaudadosPorCadaCancha() {
		// TODO: para la key utilizar el siguiente formato:
		// Futbol: FUTBOL-11-1 (Deporte-Capacidad-numero)
		// Padel: PADEL-VIDRIADA-1 (Deporte-Paredes-numero)
	}

	@Test
	public void queSePuedanObtenerLasPersonasAfiliadasAlClubOrdenadasPorApellidoAscendente() {

	}

}