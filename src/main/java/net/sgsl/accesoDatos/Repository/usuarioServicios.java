package net.sgsl.accesoDatos.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sgsl.accesoDatos.entidades.Usuario;

@Repository
public interface usuarioServicios extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByUsername(String username);
	Boolean existsByUsername(String username);
}
