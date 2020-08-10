package net.sgsl.accesoDatos.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sgsl.accesoDatos.entidades.Productor;
import org.springframework.data.jpa.repository.Query;
 

@Repository
public interface productorServicios extends JpaRepository<Productor, Long>{
	Boolean existsByCedula(String Cedula);
	List<Productor> findByCedula(String cedula);
}
