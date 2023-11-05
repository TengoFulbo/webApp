package turismouy.svcentral.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class compra_cupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private actividad actividad;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private compra compra;

    private int cantidad;

    public compra_cupo() {};

    public compra_cupo(actividad actividad, int cantidad) {
        this.actividad = actividad;
        this.cantidad = cantidad;
    }

    public void setActividad(actividad actividad) {
        this.actividad = actividad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCompra(compra compra) {
        this.compra = compra;
    }

    public int getId() {
        return this.id;
    }

    public actividad getActividad() {
        return this.actividad;
    }

    public compra getCompra() {
        return this.compra;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void disminuirCupos(int valor) {
        this.cantidad = this.cantidad - valor;
    }
}
