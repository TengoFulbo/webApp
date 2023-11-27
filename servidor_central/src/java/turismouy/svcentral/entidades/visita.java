package turismouy.svcentral.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private actividad actividad;
    private salida salida;
    private LocalDate fechaUltima;

    public visita() {};

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

    public LocalDate getFechaUltima() {
        return this.fechaUltima;
    };

    public void setActividad(actividad actividad) {
        this.actividad = actividad;
    };

    public void setSalida(salida salida) {
        this.salida = salida;
    };

    public void setFechaUltima(LocalDate fechaUltima) {
        this.fechaUltima = fechaUltima;
    }

    public void aumentarVisita() {
        this.cantidad = cantidad + 1;
    };
}
