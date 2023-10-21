package turismouy.svcentral.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String nombre;
    @ManyToMany()
    public List<actividad> actividades;

    // Constructor vacio pedido por JPA.
    public categoria() {};

    public categoria(String nombre) {
        this.nombre = nombre;
        this.actividades = new ArrayList<actividad>();
    };

    public String getNombre(){
        return this.nombre;
    };

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

	public List<actividad> getActividades() {
		return actividades;
	}
	
	public void addActividad(actividad actividad) {
		this.actividades.add(actividad);
	}

	public void setActividades(List<actividad> actividades) {
		this.actividades = actividades;
	}
    
    
    
    
    
}
