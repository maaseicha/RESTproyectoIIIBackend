package net.sgsl.accesoDatos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sgsl.accesoDatos.Repository.productorServicios;
import net.sgsl.accesoDatos.Repository.terrenoServicios;
import net.sgsl.accesoDatos.exception.ResourceNotFoundException;

import net.sgsl.accesoDatos.entidades.Terreno;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/terrenos/")
public class terrenoControlador {
	
	@Autowired
	private terrenoServicios terrenoServicio;
	@Autowired
	private productorServicios productorServicio;
        
	 // listado de todos los terrenos
	@GetMapping ("buscarTerrenos")
	public List< Terreno > getTerrenos(){
		 return this.terrenoServicio.findAll();
	}	
	// listar el terreno buscado por su id	
	@GetMapping ("buscarTerreno/{id}")
	public ResponseEntity<Terreno> getTerrenoId(@PathVariable(value = "id") Long id_terreno)
			throws ResourceNotFoundException{
				Terreno terrenos = terrenoServicio.findById(id_terreno)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el terreno con el id ::"+ id_terreno));
				return ResponseEntity.ok().body(terrenos);
	}
        //Lista por IdProductor
	@GetMapping("terreno/{id_productor}")
	public List<Terreno> getTerrenoIdProductor(@PathVariable(value = "id_productor")Long id_productor){
		return this.terrenoServicio.findByIdProductor(id_productor);
	}
	// save terreno
	@PostMapping("{id_productor}/terreno")
	public Terreno crearTerreno(@PathVariable(value = "id_productor")Long id_productor,@Valid @RequestBody Terreno terreno)throws ResourceNotFoundException {
		return productorServicio.findById(id_productor).map(pro->{
			terreno.setProduc(pro);
			return terrenoServicio.save(terreno);
		}).orElseThrow(()-> new ResourceNotFoundException("No existe el Productor con el ID: "+id_productor));
	}
	// update terreno mediante su id	
	@PutMapping("{id_productor}/terreno/{id_terreno}")
	public Terreno updateTerreno(@PathVariable(value = "id_terreno") Long id_terreno ,
			@PathVariable(value="id_productor")Long id_productor,
			@Valid @RequestBody Terreno terrenoDetails) throws ResourceNotFoundException{
		if(!productorServicio.existsById(id_productor)) {
			throw new ResourceNotFoundException("No existe el Productor con el ID: "+id_productor);
		}
		return terrenoServicio.findById(id_terreno).map(terr->{
			terr.setCant_hect(terrenoDetails.getCant_hect());
			terr.setDireccion_terreno(terrenoDetails.getDireccion_terreno());
			return terrenoServicio.save(terr);
		}).orElseThrow(() -> new ResourceNotFoundException("No existe el terreno con el id :"+ id_terreno));
	}	
	// delete terreno mediante su id 
	@DeleteMapping("eliminarTerreno/{id}")
	public Map<String,Boolean> deleteTerreno(@PathVariable(value = "id") Long id_terreno) throws ResourceNotFoundException{
		
		Terreno terreno = terrenoServicio.findById(id_terreno)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el terreno con el id :"+ id_terreno));
		        this.terrenoServicio.delete(terreno);
		        
		        Map<String,Boolean> response = new HashMap<>();
		        response.put("delete", Boolean.TRUE);
		   return response;
	}
}