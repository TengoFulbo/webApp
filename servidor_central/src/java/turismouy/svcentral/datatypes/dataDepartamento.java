package turismouy.svcentral.datatypes;

import java.util.List;

public class dataDepartamento {
    private String nombre;
    private String descripcion;
    private String url;
    private List<dataActividad> actividades;

    public dataDepartamento(String nombre, String descripcion, String url, List<dataActividad> actividades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.actividades = actividades;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getUrl() {
        return this.url;
    }

    public List<dataActividad> getActividades(){
        return this.actividades;
    };
}
