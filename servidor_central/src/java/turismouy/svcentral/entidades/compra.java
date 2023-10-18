package turismouy.svcentral.entidades;

import java.time.LocalDate;
import turismouy.svcentral.entidades.*;

import javax.persistence.*;

@Entity
public class compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate fecha;
	@Column
	private int cantTotal;
	@Column
	private int costoTotal;
	@Column
	private LocalDate vencimiento;
	
	@ManyToOne
	@JoinColumn(name = "paquete_Nombre")
	private paquete paquete;
	
	@ManyToOne
	@JoinColumn(name = "turista_Nombre")
	private turista turista;
	
	public compra(){};

	public compra(LocalDate fecha, int cantTotal, int costoTotal, LocalDate vencimiento) {
		this.fecha = fecha;
		this.cantTotal = cantTotal;
		this.costoTotal = costoTotal;
		this.vencimiento = vencimiento;
		this.paquete = null;
		this.turista = null;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCantTotal() {
		return cantTotal;
	}

	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}

	public paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(paquete paquete) {
		this.paquete = paquete;
	}

	public turista getTurista() {
		return turista;
	}

	public void setTurista(turista turista) {
		this.turista = turista;
	}
	
	
}
