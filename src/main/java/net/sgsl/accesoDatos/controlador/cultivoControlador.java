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

import net.sgsl.accesoDatos.Repository.cultivoServicios;
import net.sgsl.accesoDatos.Repository.terrenoServicios;
import net.sgsl.accesoDatos.entidades.Cultivo;
import net.sgsl.accesoDatos.entidades.Terreno;
import net.sgsl.accesoDatos.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/terrenos/")
public class cultivoControlador {
	@Autowired
	private cultivoServicios cultivoServicio;
	
	@Autowired
	private terrenoServicios terrenoServicio;
	
	//getCultivos
	@GetMapping("buscarcultivo")
	public List<Cultivo> getCultivos(){
		return this.cultivoServicio.findAll();
	}
		
	//getPCultivoId
	@GetMapping("buscarcultivo/{id}")
	public ResponseEntity<Cultivo> getCultivoId(@PathVariable(value = "id") Long id_cultivo)
		throws ResourceNotFoundException{
			Cultivo cultivo = cultivoServicio.findById(id_cultivo)
			.orElseThrow(() -> new ResourceNotFoundException("No existe el Cultivo con el id ::"+id_cultivo));
			return ResponseEntity.ok().body(cultivo);
	}
        
        //Cultivo por id Terreno
	@GetMapping("cultivoterreno/{id_terreno}")
	public List<Cultivo> getCultivosTerreno(@PathVariable(value = "id_terreno")Long id_terreno){
        	return this.cultivoServicio.findByIdTerreno(id_terreno);
	}
		
	//crearCultivo
	@PostMapping("terreno/{id_terr}/cultivo")
	public Cultivo crearCultivo(@PathVariable(value = "id_terr")Long id_terreno,@Valid @RequestBody Cultivo cultivo) throws ResourceNotFoundException{
		return terrenoServicio.findById(id_terreno).map(terr->{
			cultivo.setTerreno(terr);
			return cultivoServicio.save(cultivo);
		}).orElseThrow(()->new ResourceNotFoundException("No existe el Terreno con el ID: "+id_terreno));
	}
				
	//updateCultivo
	@PutMapping("terreno/{id_terr}/cultivo/{id_cul}")
	public Cultivo updateCultivo(@PathVariable(value="id_terr")Long id_terreno,
		@PathVariable(value = "id_cul") Long id_cultivo,
		@Valid @RequestBody Cultivo cultivoDetails) throws ResourceNotFoundException{
		if(!terrenoServicio.existsById(id_terreno)) {
			throw new ResourceNotFoundException("No existe el Terreno con el ID: "+id_terreno);
		}
		return cultivoServicio.findById(id_cultivo).map(cult->{
			cult.setCant_cult(cultivoDetails.getCant_cult());
			cult.setDetalle_cult(cultivoDetails.getDetalle_cult());
			cult.setFecha_cult(cultivoDetails.getFecha_cult());
			cult.setNombre_cult(cultivoDetails.getNombre_cult());
			return cultivoServicio.save(cult);
		}).orElseThrow(()-> new ResourceNotFoundException("No existe el Cultivo con el ID: "+id_cultivo));			
	}
	
        //deleteCultivo
	@DeleteMapping("terreno/{id_terr}/cultivo/{id_cul}")
	public ResponseEntity<?> deleteCultivo(@PathVariable(value="id_terr")Long id_terreno,
			@PathVariable(value = "id_cul")Long id_cultivo) throws ResourceNotFoundException{
		return cultivoServicio.findByCultivoAndTerreno(id_cultivo,id_terreno).map(cul->{
			cultivoServicio.delete(cul);
			return ResponseEntity.ok().build();
		}).orElseThrow(()->new ResourceNotFoundException("No existe el Terreno con el ID: "+id_terreno+" o el Cultivo con el Id: "+id_cultivo));
	}
}
