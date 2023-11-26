package turismouy.svcentral.datatypes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCategoria")
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
