package turismouy.svcentral.datatypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataUsuario")
public class dataUsuario implements Serializable {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String imagenBase64;
    private String nacionalidad;
    private LocalDate nacimiento;
    private boolean isProveedor;
    private String descripcion;
    private String url;
    private List<dataActividad> actividades;
    private List<dataSalida> salidas;


    public dataUsuario(     String nickname, String nombre,
                            String apellido, String email,
                            String nacionalidad,
                            LocalDate nacimiento, boolean isProveedor,
                            String descripcion,
                            String url,
                            String imagen,
                            List<dataActividad> actividades,
                            List<dataSalida> salidas
                        ) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.nacimiento = nacimiento;
        this.isProveedor = isProveedor;
        this.descripcion = descripcion;
        this.url = url;
        this.imagenBase64 = imagen;
        this.actividades = actividades;
        this.salidas = salidas;
    };

    public dataUsuario() {};

    public String getNickname(){
        return this.nickname;
    };

    public String getNombre(){
        return this.nombre;
    };

    public String getApellido(){
        return this.apellido;
    };

    public String getEmail(){
        return this.email;
    };
    
    public String getNacionalidad(){
        return this.nacionalidad;
    }

    public LocalDate getNacimiento(){
        return this.nacimiento;
    };

    public boolean getisProveedor(){
        return this.isProveedor;
    };

    public String getDescripcion(){
        return this.descripcion;
    };

    public String getUrl(){
        return this.url;
    };

    public String getImagenBase64() {
        return this.imagenBase64;
    };

    public List<dataActividad> getActividades(){
        return this.actividades;
    };

    public List<dataSalida> getSalidas(){
        return this.salidas;
    };

}
