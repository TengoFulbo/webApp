package turismouy.svcentral.datatypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.entidades.turista;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInscripcion")
public class dataInscripcion implements Serializable{
	private LocalDate fecha;
	private int cant;
	private int costo;
	private salida salida;
	private turista turista;
	
	public dataInscripcion() {}
	
	public dataInscripcion(LocalDate fecha, int cant, int costo, salida salida, turista turista) {
		this.fecha = fecha;
		this.cant = cant;
		this.costo = costo;
		this.salida = salida;
		this.turista = turista;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public int getCant() {
		return cant;
	}

	public int getCosto() {
		return costo;
	}

	public salida getSalida() {
		return salida;
	}

	public turista getTurista() {
		return turista;
	}

	
	
	
}
