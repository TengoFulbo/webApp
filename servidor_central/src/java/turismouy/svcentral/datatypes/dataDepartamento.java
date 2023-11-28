package turismouy.svcentral.datatypes;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataDepartamento")
public class dataDepartamento implements Serializable {
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

    public dataDepartamento() {};

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
