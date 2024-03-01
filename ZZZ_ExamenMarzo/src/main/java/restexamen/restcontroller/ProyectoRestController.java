package restexamen.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.Proyecto;
import restexamen.service.EmpleadoEnProyectoService;
import restexamen.service.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
public class ProyectoRestController {
	
	// ¡¡No se muestra el HttpStatus!!
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	
	// CREATE
	
	
	// READ
	
	@GetMapping("/mostrarProyecto/{idProyecto}")
	public Proyecto mostrarEmpleado(@PathVariable int idProyecto) {
			
		return proyectoService.findOne(idProyecto);
	}
		
	@GetMapping("/mostrarProyectos")
	public List<Proyecto> mostrarProyectos() {
			
		return proyectoService.findAll();
	}
	
	@GetMapping("/mostrarDirector/{idProyecto}")
	public Empleado mostrarDirector(@PathVariable int idProyecto) {
		
		return proyectoService.findDirector(idProyecto);
	}
	
	@GetMapping("/mostrarEmpleados/{idProyecto}")
	public List<Empleado> mostrarEmpleados(@PathVariable int idProyecto) {
		
		return empleadoEnProyectoService.findEmpleados(idProyecto);
	}
		
	// UPDATE
	
		
	// DELETE
		
	@DeleteMapping("/eliminarProyecto/{idProyecto}")
	public String eliminarProyecto(@PathVariable int idProyecto) {
			
		if (proyectoService.deleteOne(idProyecto)) {
				
			return "Eliminado con éxito";
		}
		else {
				
			return "Error";
		}
	}

}
