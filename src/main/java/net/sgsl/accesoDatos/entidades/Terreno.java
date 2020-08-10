package net.sgsl.accesoDatos.entidades;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Terreno")
public class Terreno {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_terreno;
	
	@Column (name="direccion_terreno")
	private String direccion_terreno ;
	
	@Column (name="cant_hect")
	private Integer cant_hect;
        
        @ManyToOne
	@JoinColumn(name="id_productor")
	private Productor produc;
	
        @OneToMany(mappedBy="terr",fetch = FetchType.LAZY)
	private Set<Cultivo> cultivos;
        
	public Terreno() {
		super();
	}
	
	public Terreno(String direccion_terreno, Integer cant_hect, Set<Cultivo> cultivos) {
		super();
		this.direccion_terreno = direccion_terreno;
		this.cant_hect = cant_hect;
		this.cultivos = cultivos;
	}


	public Long getId_terreno() {
		return id_terreno;
	}

	public void setId_terreno(long id_terreno) {
		this.id_terreno = id_terreno;
	}

	public String getDireccion_terreno() {
		return direccion_terreno;
	}

	public void setDireccion_terreno(String direccion_terreno) {
		this.direccion_terreno = direccion_terreno;
	}

	public Integer getCant_hect() {
		return cant_hect;
	}

	public void setCant_hect(Integer cant_hect) {
		this.cant_hect = cant_hect;
	}
        
        public void setProduc(Productor produc) {
		this.produc = produc;
	}
        
        public Set<Cultivo> getCultivos() {
		return cultivos;
	}

	public void setCultivos(Set<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}
}
