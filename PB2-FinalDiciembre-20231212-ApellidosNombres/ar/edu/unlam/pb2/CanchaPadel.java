package ar.edu.unlam.pb2;

import ar.edu.unlam.pb2.enums.Deporte;
import ar.edu.unlam.pb2.enums.Paredes;
import ar.edu.unlam.pb2.enums.Piso;
 
public class CanchaPadel extends Cancha {
	private Paredes paredes;

	public CanchaPadel(Integer numero, Paredes paredes, Piso piso) {
		super(numero, Deporte.PADEL, piso);
		this.paredes = paredes;
	}

	public String getStringParedes() {
		return "" + this.paredes + "";
	}



}
