package turismouy.svcentral.entidades;

import javax.persistence.*;

import turismouy.svcentral.utilidades.utilPassword;

import java.time.LocalDate;

// @MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)

public class usuario{
	@Id @Column (name="NICKNAME")
	protected String nickname;
	@Column
	protected String nombre;
	@Column
	protected String apellido;
	@Column 
	protected String email;
	protected LocalDate nacimiento;
	protected String password;
	
	@OneToOne
	@JoinColumn(name = "imagen_id")
	private imagen imagen;
	
	// Constructor vacio pedido por JPA.
	public usuario(){};

	public usuario(String nickname,String nombre,String apellido, String email, LocalDate nacimiento) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nacimiento = nacimiento;
		this.password = utilPassword.encriptar(nickname);
	}
	
	public usuario(String nickname, String nombre, String apellido, String email, LocalDate nacimiento, String password) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nacimiento = nacimiento;
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}