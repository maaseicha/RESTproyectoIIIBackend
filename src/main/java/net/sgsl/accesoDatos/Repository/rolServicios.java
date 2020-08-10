package net.sgsl.accesoDatos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sgsl.accesoDatos.entidades.Rol;

@Repository
public interface rolServicios extends JpaRepository<Rol, Long>{

}
