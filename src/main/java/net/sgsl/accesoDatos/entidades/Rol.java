package net.sgsl.accesoDatos.entidades;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long id_rol;
	
	@Column(name="detalle")
	private String detalle;
	
	@OneToMany(mappedBy="roles",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Usuario> usuario;
	
	public Rol() {
		super();
	}

	public Rol(String detalle) {
		super();
		this.detalle = detalle;
	}
	public Long getId_rol() {
		return id_rol;
	}

	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}

	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}
}
