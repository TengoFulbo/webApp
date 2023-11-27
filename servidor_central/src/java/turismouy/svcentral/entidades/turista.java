package turismouy.svcentral.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.*;

import turismouy.svcentral.datatypes.dataInscripcion;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;

    @Entity
    @DiscriminatorValue("T")
    public class turista extends usuario {
        @Column
        private String nacionalidad;

        @OneToMany(mappedBy = "turista", fetch = FetchType.EAGER)
        private List<inscripcion> inscripciones = new ArrayList<inscripcion>();
        
        @OneToMany(mappedBy = "turista")
        private List<compra> compra = new ArrayList<compra>();

    public turista(String nickname, String nombre, String apellido, String email, String nacionalidad, LocalDate nacimiento) {
        super(nickname, nombre, apellido, email, nacimiento);
        this.nacionalidad = nacionalidad;
        this.inscripciones = new ArrayList<inscripcion>();
        this.compra = new ArrayList<compra>();
    }

    public turista(String nickname, String nombre, String apellido, String email, String nacionalidad, LocalDate nacimiento, String password) {
        super(nickname, nombre, apellido, email, nacimiento, password);
        this.nacionalidad = nacionalidad;
        this.inscripciones = new ArrayList<inscripcion>();
        this.compra = new ArrayList<compra>();
    }

    // Constructor vacío pedido por JPA.
    public turista() {}

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void addInscripcion(inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
    }

    public void setInscripciones(List<inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public dataUsuario toDataType() {
        List<dataSalida> dataSalidas = new ArrayList<dataSalida>();
        List<dataInscripcion> dataInscripciones = new ArrayList<dataInscripcion>();
        if (this.inscripciones != null){
            for (inscripcion inscripcion : this.inscripciones) {
                dataSalida dtSalida = inscripcion.getSalida().toDataTypeWithoutActividades();
                dataSalidas.add(dtSalida);
                dataInscripciones.add(inscripcion.toDatatype());
            }
        } else {
            dataSalidas = null;
        }

		// Hacemos el encode para que el datatype no tenga que hacerlo :D
		String imagenBase64 = "";
		if (this.imagen != null) {
			imagenBase64 = Base64.getEncoder().encodeToString(this.imagen.getData());
		}
        
        dataUsuario dt = new dataUsuario(
                            this.nickname,
                            this.nombre,
                            this.apellido,
                            this.email,
                            this.nacionalidad,
                            this.nacimiento,
                            false, 
                            null,
                            null,
                            imagenBase64,
                            dataInscripciones,
                            null,
                            dataSalidas);

        return dt;
    }

	public List<compra> getCompras() {
		return compra;
	}
	
	public void addCompra(compra compra) {
		this.compra.add(compra);
	}

	public void setCompra(List<compra> compra) {
		this.compra = compra;
	}
}
