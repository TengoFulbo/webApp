package turismouy.svcentral.datatypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.utilidades.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSalida")
public class dataSalida implements Serializable {
	private String nombre;
    private int capacidad;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaAlta;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaSalida;
    private String lugarSalida;
    private List<dataActividad> actividades;

    public dataSalida(String nombre, int capacidad, LocalDate fechaAlta, LocalDate fechaSalida, String lugarSalida, List<dataActividad> actividades) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.fechaAlta = fechaAlta;
        this.fechaSalida = fechaSalida;
        this.lugarSalida = lugarSalida;
        this.actividades = actividades;
    }

    public dataSalida() {};

    public String getNombre() {
        return this.nombre;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }

    public LocalDate getFechaSalida() {
        return this.fechaSalida;
    }

    public String getLugarSalida() {
        return this.lugarSalida;
    }

	public List<dataActividad> getActividades() {
		return actividades;
	}
}
