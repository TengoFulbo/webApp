package turismouy.svcentral.entidades;

import javax.persistence.*;

@Entity
public class categoria {
    @Id
    private String nombre;

    // Constructor vacio pedido por JPA.
    public categoria() {};

    public categoria(String nombre) {
        this.nombre = nombre;
    };

    public String getNombre(){
        return this.nombre;
    };

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
