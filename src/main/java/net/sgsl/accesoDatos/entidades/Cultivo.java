package net.sgsl.accesoDatos.entidades;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cultivo")
public class Cultivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cultivo;
	
	@Column(name = "nombre_cult")
	private String nombre_cult;
	@Column(name = "detalle_cult")
	private String detalle_cult;
	@Column(name = "cant_cult")
	private Integer cant_cult;
	@Column(name = "fecha_cult")
	private String fecha_cult;
	
        @ManyToOne
	@JoinColumn(name = "id_terreno")
	private Terreno terr;
        
	public Cultivo() {
		super();
	}
	public Cultivo(String nombre_cult, String detalle_cult, Integer cant_cult, String fecha_cult) {
		super();
		this.nombre_cult = nombre_cult;
		this.detalle_cult = detalle_cult;
		this.cant_cult = cant_cult;
		this.fecha_cult = fecha_cult;
	}
	
        public Long getId_cultivo() {
		return id_cultivo;
	}
	
        public void setId_cultivo(Long id_cultivo) {
		this.id_cultivo = id_cultivo;
	}
	
        public String getNombre_cult() {
		return nombre_cult;
	}
	
        public void setNombre_cult(String nombre_cult) {
		this.nombre_cult = nombre_cult;
	}
	
        public String getDetalle_cult() {
		return detalle_cult;
	}
	
        public void setDetalle_cult(String detalle_cult) {
		this.detalle_cult = detalle_cult;
	}
	
        public Integer getCant_cult() {
		return cant_cult;
	}
	
        public void setCant_cult(Integer cant_cult) {
		this.cant_cult = cant_cult;
	}
	
        public String getFecha_cult() {
		return fecha_cult;
	}
	
        public void setFecha_cult(String fecha_cult) {
		this.fecha_cult = fecha_cult;
	}
        
        public void setTerreno(Terreno terr) {
		this.terr = terr;
	}
}
