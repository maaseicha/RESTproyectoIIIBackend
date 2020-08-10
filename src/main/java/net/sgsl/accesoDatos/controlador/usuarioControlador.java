package net.sgsl.accesoDatos.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sgsl.accesoDatos.Repository.rolServicios;
import net.sgsl.accesoDatos.Repository.usuarioServicios;
import net.sgsl.accesoDatos.entidades.Usuario;
import net.sgsl.accesoDatos.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios/")
public class usuarioControlador {
	@Autowired 
	private usuarioServicios usuarioServicio;
	
	@Autowired
	private rolServicios rolServicio;
	
	@GetMapping("buscarusuarios")
	public List<Usuario> getUsuarios() {
		return this.usuarioServicio.findAll();
	}
	
	@GetMapping("/buscarusuario/{username}")
	public ResponseEntity<Usuario> getUsuarioId(@PathVariable(value = "username") String username)
		throws ResourceNotFoundException{
			Usuario usuario = usuarioServicio.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundException("No existe Productor con el id ::"+username));
			return ResponseEntity.ok().body(usuario);
	}
	
	@PostMapping("usuario/{id_rol}")
	public Usuario crearUsuario(@PathVariable(value = "id_rol")Long id_rol,@Valid @RequestBody Usuario usuario)
			throws ResourceNotFoundException {
		if(usuarioServicio.existsByUsername(usuario.getUsername())) {
			throw new ResourceNotFoundException("Ya existe un usuario con ese Username");
		}
		return rolServicio.findById(id_rol).map(ro->{
			usuario.setRoles(ro);
			return usuarioServicio.save(usuario);
		}).orElseThrow(()-> new ResourceNotFoundException("No se encuentra el Rol"));
	}
	
	@PutMapping("usuario/{username}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "username")String username,@Valid @RequestBody Usuario usuarioDetails)
	throws ResourceNotFoundException{
		Usuario usuario = usuarioServicio.findByUsername(username)
				.orElseThrow(()-> new ResourceNotFoundException("No existe un Usuario con el Username: "+username));
		usuario.setUsername(usuarioDetails.getUsername());
		usuario.setPassword(usuarioDetails.getPassword());
		
		return ResponseEntity.ok(this.usuarioServicio.save(usuario));
	}
}
