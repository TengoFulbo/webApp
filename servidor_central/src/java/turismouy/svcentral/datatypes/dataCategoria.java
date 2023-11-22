package turismouy.svcentral.datatypes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class dataCategoria implements Serializable {
    private String nombre;

    public dataCategoria(String nombre) {
        this.nombre = nombre;
    }

    public dataCategoria() {};

    public String getNombre() {
        return this.nombre;
    }
}
