package net.sgsl.accesoDatos.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.sgsl.accesoDatos.entidades.Telefono;
@Repository
public interface telefonoServicios extends JpaRepository<Telefono, Long> { 
	@Query(value = "SELECT * FROM telefono_productor WHERE id_productor=?1",nativeQuery=true)
   List<Telefono> findByIdProductor(Long idProd);
	//Optional(Telefono) findByIdAndProductorId(Long id_telefono,Long id_productor);
	@Query(value = "SELECT * FROM telefono_productor WHERE id_telefono=?1 AND id_productor=?2",nativeQuery=true)
	Optional<Telefono> findByTelefonoAndProductor(Long id_telefono,Long id_productor);
}
