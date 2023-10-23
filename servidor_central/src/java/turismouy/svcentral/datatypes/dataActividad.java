package turismouy.svcentral.datatypes;

import java.time.LocalDate;
import java.util.List;

import turismouy.svcentral.utilidades.estadoActividad;

public class dataActividad {
	private String nombre;
	private String desc;
	private int duracion;
	private int costoUni;
	private String ciudad;
	private LocalDate fechaCrea;
	private String fechaS;
	private estadoActividad estado;
	private dataDepartamento departamento;
	private dataUsuario proveedor;
	private List<dataSalida>DtSalidas;
	private List<dataPaquete>DtPaquetes;
	private List<String> dtCategorias;
	
	
	public dataActividad(String nombre, String desc, int duracion, int costoUni, String ciudad, LocalDate fechaCrea, estadoActividad estado, dataDepartamento departamento, dataUsuario proveedor, List<dataSalida>DtSalidas, List<dataPaquete>DtPaquetes, List<String> dtCategorias) {
		this.nombre = nombre;
		this.desc = desc;
		this.duracion = duracion;
		this.costoUni = costoUni;
		this.ciudad = ciudad;
		this.fechaCrea = fechaCrea;
		this.estado = estado;
		this.departamento = departamento;
		this.proveedor = proveedor;
		this.DtSalidas = DtSalidas;
		this.DtPaquetes = DtPaquetes;
		this.dtCategorias = dtCategorias;
	}


	public String getNombre() {
		return nombre;
	}


	public String getDesc() {
		return desc;
	}


	public int getDuracion() {
		return duracion;
	}


	public int getCostoUni() {
		return costoUni;
	}


	public String getCiudad() {
		return ciudad;
	}

	public LocalDate getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(LocalDate fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public String getFechaS() {
		return this.fechaS;
	}

	public void setFechaS(String fechaS) {
		this.fechaS = fechaS;
	}

	public estadoActividad getEstado() {
		return this.estado;
	}

	public dataDepartamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(dataDepartamento departamento) {
		this.departamento = departamento;
	}


	public dataUsuario getProveedor() {
		return proveedor;
	}


	public void setProveedor(dataUsuario proveedor) {
		this.proveedor = proveedor;
	}


	public List<dataSalida> getDtSalidas() {
		return DtSalidas;
	}


	public void setDtSalidas(List<dataSalida> dtSalidas) {
		DtSalidas = dtSalidas;
	}


	public List<dataPaquete> getDtPaquetes() {
		return DtPaquetes;
	}


	public void setDtPaquetes(List<dataPaquete> dtPaquetes) {
		DtPaquetes = dtPaquetes;
	}

	public void setDtCategorias(List<String> dtCategorias) {
		this.dtCategorias = dtCategorias;
	}

	public List<String> getDtCategorias() {
		return this.dtCategorias;
	}
}
