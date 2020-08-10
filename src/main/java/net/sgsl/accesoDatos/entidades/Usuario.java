package net.sgsl.accesoDatos.entidades;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name ="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol roles;
	
	public Usuario() {
		super();
	}
	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Rol getRoles() {
		return roles;
	}
	public void setRoles(Rol roles) {
		this.roles = roles;
	}
}
