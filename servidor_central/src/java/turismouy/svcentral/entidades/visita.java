package turismouy.svcentral.entidades;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import turismouy.svcentral.datatypes.dataVisita;

@Entity
public class visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    @OneToOne(mappedBy = "visita")
    private actividad actividad;
    @OneToOne(mappedBy = "visita")
    private salida salida;
    private LocalDateTime fechaUltima;

    public visita() {
        this.cantidad = 0;
        this.actividad = null;
        this.salida = null;
        this.fechaUltima = null;
    };

    public visita(actividad actividad) {
        this.cantidad = 0;
        this.actividad = actividad;
        this.salida = null;
        this.fechaUltima = null;
    };

    public visita(salida salida) {
        this.cantidad = 0;
        this.actividad = null;
        this.salida = salida;
        this.fechaUltima = null;
    };

    public Long getId() {
        return this.id;
    };

    public int getCantidad() {
        return this.cantidad;
    };

    public actividad geActividad() {
        return this.actividad;
    };

    public salida getSalida() {
        return this.salida;
    };

    public LocalDateTime getFechaUltima() {
        return this.fechaUltima;
    };

    public void setActividad(actividad actividad) {
        this.actividad = actividad;
    };

    public void setSalida(salida salida) {
        this.salida = salida;
    };

    public void setFechaUltima(LocalDateTime fechaUltima) {
        this.fechaUltima = fechaUltima;
    }

    public void aumentarVisita() {
        this.cantidad = cantidad + 1;
        this.fechaUltima = LocalDateTime.now();
    };

    public dataVisita toDataType() {
        dataVisita visita = null;

        if (this.actividad != null) {
            visita = new dataVisita(this.id, this.cantidad, this.actividad, this.fechaUltima);
        } else if (this.salida != null) {
            visita = new dataVisita(this.id, this.cantidad, this.salida, this.fechaUltima);
        }

        return visita;
    }
}
