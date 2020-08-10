package net.sgsl.accesoDatos.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.sgsl.accesoDatos.Repository.rolServicios;
import net.sgsl.accesoDatos.entidades.Rol;
import net.sgsl.accesoDatos.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/usuarios/")
public class rolControlador {
	@Autowired
	private rolServicios rolServicio;
	@GetMapping("roles")
	public List<Rol> getRoles() {
		return this.rolServicio.findAll();
	}
	
	@PostMapping("rol")
	public Rol crearRol(@Valid @RequestBody Rol rol) {
		return this.rolServicio.save(rol);
	}
	
	@PutMapping("rol/{id}")
	public ResponseEntity<Rol> updateRol(@PathVariable(value = "id")Long id_rol,@Valid @RequestBody Rol rolDetails)
			throws ResourceNotFoundException{
		Rol rol = rolServicio.findById(id_rol)
				.orElseThrow(()->new ResourceNotFoundException("No existe ese Rol con el Id: "+id_rol));
		rol.setDetalle(rolDetails.getDetalle());
		return ResponseEntity.ok(this.rolServicio.save(rol));
		
	}
}
