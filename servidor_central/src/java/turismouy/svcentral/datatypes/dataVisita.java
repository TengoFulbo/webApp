package turismouy.svcentral.datatypes;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import turismouy.svcentral.entidades.actividad;
import turismouy.svcentral.entidades.salida;
import turismouy.svcentral.utilidades.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataVisita")
public class dataVisita implements Serializable {
    private Long id;
    private int cantidad;
    private actividad actividad;
    private salida salida;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaUltima;

    public dataVisita() {};

    public dataVisita(Long id, int cantidad, actividad actividad, LocalDateTime fechaUltima) {
        this.id = id;
        this.cantidad = cantidad;
        this.actividad = actividad;
        this.salida = null;
        this.fechaUltima = fechaUltima;
    };

    public dataVisita(Long id, int cantidad, salida salida, LocalDateTime fechaUltima) {
        this.id = id;
        this.cantidad = cantidad;
        this.actividad = null;
        this.salida = salida;
        this.fechaUltima = fechaUltima;
    };

    public Long getId() {
        return this.id;
    };

    public int getCantidad() {
        return this.cantidad;
    };

    public actividad getActividad() {
        return this.actividad;
    };

    public salida getSalida() {
        return this.salida;
    };

    public LocalDateTime getFechaUltima() {
        return this.fechaUltima;
    }
}
