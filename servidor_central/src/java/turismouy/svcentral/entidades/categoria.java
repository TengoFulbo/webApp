package turismouy.svcentral.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;

@Entity
public class categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String nombre;

    // @JoinTable(
	// 	name = "categoria_actividad",
    //     joinColumns = @JoinColumn(name = "fk_categoria"),
    //     inverseJoinColumns = @JoinColumn(name = "fk_actividad")
	// 	)
	// @ManyToMany()
	@ManyToMany(mappedBy = "categorias", targetEntity = actividad.class)
    private List<actividad> actividades; 

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
    
    public List<actividad> getActividades() {
		return actividades;
	}

    public void addActividad(actividad actividad) {
        actividades.add(actividad);
    }
/*    
	public void remplazarActividad(actividad act) {
		int indice = 0;
		for(actividad actividad : this.actividades) {
			if(actividad.getNombre().equals(act.getNombre())) {
				System.out.println(this.actividades.get(indice).getNombre());
				this.actividades.remove(indice);
				this.actividades.add(act);
			}
			indice++;
		}
	}
*/
	public void setActividades(List<actividad> actividades) {
		this.actividades = actividades;
	}

	public dataCategoria toDataType() {
        List<dataActividad> dataActividades = new ArrayList<dataActividad>();

        if (this.actividades != null) {
            for (actividad actividad : this.actividades) {
                dataActividades.add(actividad.toDataType());
            }
        } else { dataActividades = null; }

        dataCategoria dt = new dataCategoria(this.nombre);
        return dt;
    }
}
