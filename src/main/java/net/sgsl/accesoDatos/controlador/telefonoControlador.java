package net.sgsl.accesoDatos.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import net.sgsl.accesoDatos.entidades.Telefono;
import net.sgsl.accesoDatos.exception.ResourceNotFoundException;
import net.sgsl.accesoDatos.Repository.productorServicios;
import net.sgsl.accesoDatos.Repository.telefonoServicios;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/productores/")
public class telefonoControlador {
	@Autowired
	private telefonoServicios telefonoServicio;
	@Autowired
	private productorServicios productorServicio;
	
	@GetMapping("telefonos")
	public ResponseEntity<List<Telefono>> getTelefonos(){
		List<Telefono> lista = telefonoServicio.findAll();
		return new ResponseEntity<List<Telefono>>(lista, new HttpHeaders(), HttpStatus.OK);
	}
	//buscartelefono
	@GetMapping("telefono/{id}")
	public ResponseEntity<Telefono> getTelefonoId(@PathVariable("id") Long id_telefono)throws ResourceNotFoundException{
		Telefono tel = telefonoServicio.findById(id_telefono).orElseThrow(()-> new ResourceNotFoundException("No Existe el Telefono con ese Id"));
		return new ResponseEntity<Telefono>(tel,HttpStatus.OK);
	}
	@GetMapping("productor/telefono/{id_productor}")
	public List<Telefono> getTelefonoIdProductor(@PathVariable("id_productor")Long id_productor)throws ResourceNotFoundException{
		return this.telefonoServicio.findByIdProductor(id_productor);
	}
	
	//crearTelefono
	@PostMapping("productor/{id_productor}/telefono")
	public @Valid Telefono crearTelefono(@PathVariable(value = "id_productor") Long id_productor,@Valid @RequestBody Telefono telefono) throws ResourceNotFoundException{
		return productorServicio.findById(id_productor).map(prod ->{
			telefono.setProductor(prod);
			return telefonoServicio.save(telefono);
		}).orElseThrow(()-> new ResourceNotFoundException("No existe un Productor con ese Id"));
	}
	//Actualizar
	@PutMapping("productor/{id_prod}/telefono/{id_tel}")
	public Telefono actualizarTelefono(@PathVariable(value = "id_prod")Long id_productor,
			@PathVariable(value = "id_tel")Long id_telefono, @Valid @RequestBody Telefono telefono) throws ResourceNotFoundException {
		if(!productorServicio.existsById(id_productor)) {
			throw new ResourceNotFoundException("No existe el productor con el ID: "+id_productor);
		}
		return telefonoServicio.findById(id_telefono).map(tel->{
			tel.setTelefono(telefono.getTelefono());
			return telefonoServicio.save(tel);
		}).orElseThrow(()-> new ResourceNotFoundException("No existe el telefono con el ID: "+id_telefono));
	}
	//Eliminar un Telefono
		@DeleteMapping("productor/{id_prod}/telefono/{id_tel}")
		public ResponseEntity<?> borrarTelefono(@PathVariable(value = "id_prod")Long id_productor,
				@PathVariable(value ="id_tel")Long id_telefono) throws ResourceNotFoundException{
			return telefonoServicio.findByTelefonoAndProductor(id_telefono,id_productor).map(tel->{
				telefonoServicio.delete(tel);
				return ResponseEntity.ok().build();
			}).orElseThrow(()-> new ResourceNotFoundException("No existe el Telefono con el ID: "+id_telefono+" o con el Productor con el ID: "+id_productor));
		}
}
