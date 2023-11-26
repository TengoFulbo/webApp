package turismouy.svcentral.entidades;

import javax.persistence.*;

import turismouy.svcentral.EMFactory;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.manejadores.ActividadManejador;
import turismouy.svcentral.utilidades.estadoActividad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class actividad {
	@Id // Dice que es PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	long id;
	private String nombreA;
	@Column
	private String descripcion;
	@Column
	private int duracion;
	@Column
	private int costeUni;
	@Column
	private String ciudad;
	@Column
	private String urlVideo;
	private LocalDate fechaCrea;
	estadoActividad estado;
	@ManyToOne() // Un proveedor puede tener muchas actividades.
	private proveedor proveedor;
	@ManyToOne() // Un departamento puede tener muchas actividades.
	private departamento departamento;

	@ManyToMany(mappedBy = "actividades")
	private List<paquete> paquetes = new ArrayList<paquete>();
	
	@JoinTable(
			name = "actividad_salida",
			joinColumns = @JoinColumn(name = "FK_ACTIVIDAD"),
			inverseJoinColumns = @JoinColumn(name = "FK_SALIDA")
			)
	// @ManyToMany(fetch = FetchType.EAGER)
	@ManyToMany()
    private List<salida> salidas = new ArrayList<salida>();;

	// @ManyToMany(mappedBy = "actividades", targetEntity = categoria.class)
    @JoinTable(
		name = "categoria_actividad",
        joinColumns = @JoinColumn(name = "fk_actividad"),
        inverseJoinColumns = @JoinColumn(name = "fk_categoria")
	)
	@ManyToMany()
	private List<categoria> categorias = new ArrayList<categoria>();


    public actividad(){};
	
	public actividad(String nombre, String descripcion, int duracion, int costeUni, String ciudad, LocalDate fechaCrea, List<categoria> categorias) {
		super();
		this.nombreA = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costeUni = costeUni;
		this.ciudad = ciudad;
		this.fechaCrea = fechaCrea;
		this.urlVideo = "https://www.youtube.com/watch?v=uZZMZ4PyOfw&ab_channel=EngagementHub";
		// this.paquetes = new ArrayList<paquete>();
		// this.salidas = new ArrayList<salida>();
		this.estado = estadoActividad.AGREGADA;
		this.categorias = categorias;
	}

	public actividad(String nombre, String descripcion, int duracion, int costeUni, String ciudad, String video, LocalDate fechaCrea, List<categoria> categorias) {
		super();
		this.nombreA = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costeUni = costeUni;
		this.ciudad = ciudad;
		this.fechaCrea = fechaCrea;
		this.urlVideo = video;
		// this.paquetes = new ArrayList<paquete>();
		// this.salidas = new ArrayList<salida>();
		this.estado = estadoActividad.AGREGADA;
		this.categorias = categorias;
	}

	public String getNombre() {
		return nombreA;
	}
	public void setNombre(String nombre) {
		this.nombreA = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getCosteUni() {
		return costeUni;
	}
	public void setCosteUni(int costeUni) {
		this.costeUni = costeUni;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public LocalDate getFechaCrea() {
		return fechaCrea;
	}
	public void setFechaCrea(LocalDate fechaCrea) {
		this.fechaCrea = fechaCrea;
	}
	public proveedor getProveedor() {
		return this.proveedor;
	}
	public void setProveedor(proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public departamento getDepartamento() {
		return this.departamento;
	}
	public void setDepartamento(departamento deptamento) {
		this.departamento = deptamento;
	}
	public void addPaquete(paquete paquete) {
		paquetes.add(paquete);
	}
	public List<paquete> getPaquetes() {
		return this.paquetes;
	}
	public void setPaquetes(List<paquete> paquetes) {
		this.paquetes = paquetes;
	}
	public void addSalida(salida salida) {
		salidas.add(salida);
	}
	public List<salida> getSalidas() {
		return this.salidas;
	}
	public void setSalidas(List<salida> salidas) {
		this.salidas = salidas;
	}
	public estadoActividad getEstado(){
		return this.estado;
	}
	public void setEstado(estadoActividad estado){
		this.estado = estado;
	}
	public void setCategorias(List<categoria> categorias) {
		this.categorias = categorias;
	}
	public List<categoria> getCategorias() {
		return this.categorias;
	}
	public dataActividad toDataType() {
		
		List<dataSalida>DtSalidas = this.crearListaSalidaParaDt(this);
		
		List<dataPaquete> DtPaquetes = this.crearListaPaqueteParaDt(this);
		List<String> dtCategorias = new ArrayList<String>();

		actividad actividad = ActividadManejador.getinstance().getActividad(this.nombreA);
		// List<categoria> categorias = null;
		// if (this.categorias == null) {
		// 	categorias = actividad.getCategorias();
		// }

		for (categoria categoria : actividad.getCategorias()) {
			// log.info("[actividad toDataType] categoria: " + categoria.getNombre());
			dtCategorias.add(categoria.getNombre());
		}

		dataActividad dt = new dataActividad(
			this.nombreA,
			this.descripcion,
			this.duracion,
			this.costeUni,
			this.ciudad,
			this.fechaCrea,
			this.estado,
			this.urlVideo,
			this.departamento.toDataTypeWithoutActividades(),
			this.proveedor.toDataTypeWithoutCollections(),
			DtSalidas,
			DtPaquetes,
			dtCategorias);

		return dt;
		
	}
	
	public List<dataSalida> crearListaSalidaParaDt(actividad actividad){
	    	
	    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
		    EntityManager em = factory.createEntityManager();
		    EntityTransaction tx = em.getTransaction();
		    
		    tx.begin();
		    
		    actividad act = em.createQuery("SELECT a FROM actividad a LEFT JOIN FETCH a.salidas WHERE a.nombreA = '" + actividad.getNombre() + "'",actividad.class).getSingleResult();
		    List<salida> LSalidas = act.getSalidas();
		    
			tx.commit();
			em.close();
			
			List<dataSalida> LDtSalida = new ArrayList<dataSalida>();
			for(salida salida : LSalidas) {
				LDtSalida.add(salida.toDataTypeWithoutActividades());
			}
			
			return LDtSalida;
	    }
	
	public List<dataPaquete> crearListaPaqueteParaDt(actividad actividad){
    	
    	EntityManagerFactory factory = EMFactory.getEntityManagerFactory();
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    
	    tx.begin();
	    
	    actividad act = em.createQuery("SELECT a FROM actividad a LEFT JOIN FETCH a.paquetes WHERE a.nombreA = '" + actividad.getNombre() + "'",actividad.class).getSingleResult();
	    List<paquete> LPaquetes = act.getPaquetes();
	    
		tx.commit();
		em.close();
		
		List<dataPaquete> LDtPaquetes = new ArrayList<dataPaquete>();
		for(paquete paquete : LPaquetes) {
			LDtPaquetes.add(paquete.toDataTypeWithoutActividades());
		}
		
		return LDtPaquetes;
    }

	
}