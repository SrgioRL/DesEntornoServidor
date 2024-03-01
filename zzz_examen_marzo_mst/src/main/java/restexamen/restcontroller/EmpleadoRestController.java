package restexamen.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import restexamen.modelo.dto.EmpleadoEnProyectoDto;
import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.EmpleadoEnProyecto;
import restexamen.service.EmpleadoEnProyectoService;
import restexamen.service.EmpleadoService;
import restexamen.service.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
public class EmpleadoRestController {
	
	// ¡¡No se muestra el HttpStatus!!
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	// CREATE
	
	@PostMapping("/altaEmpleado")
	public Empleado altaEmpleado(@RequestBody Empleado empleado) {
		
		return empleadoService.insertOne(empleado);
	}
	
	@PostMapping("/elegirEmpleado")
	public EmpleadoEnProyecto elegirEmpleado(@RequestBody EmpleadoEnProyectoDto empleadoEnProyectoDto) {
		
		EmpleadoEnProyecto empleado = new EmpleadoEnProyecto();
		empleado.setDiasPrevistos(proyectoService.findOne(empleadoEnProyectoDto.getIdProyecto()).getDiasPrevistos());
		empleado.setFechaIncorporacion(new Date());
		empleado.setEmpleado(empleadoService.findOne(empleadoEnProyectoDto.getIdEmpleado()));
		empleado.setProyecto(proyectoService.findOne(empleadoEnProyectoDto.getIdProyecto()));
		
		return empleadoEnProyectoService.insertOne(empleado);
	}
	
	// READ
	
	@GetMapping("/mostrarEmpleado/{idEmpleado}")
	public Empleado mostrarEmpleado(@PathVariable int idEmpleado) {
		
		return empleadoService.findOne(idEmpleado);
	}
	
	@GetMapping("/mostrarEmpleados")
	public List<Empleado> mostrarEmpleados() {
		
		return empleadoService.findAll();
	}
	
	// UPDATE
	
	@PutMapping("/modificarEmpleado")
	public Empleado modificarEmpleado(@RequestBody Empleado empleado) {
		
		return empleadoService.updateOne(empleado);
	}
	
	// DELETE
	
	@DeleteMapping("/eliminarEmpleado/{idEmpleado}")
	public String eliminarEmpleado(@PathVariable int idEmpleado) {
		
		if (empleadoService.deleteOne(idEmpleado)) {
			
			return "Eliminado con éxito";
		}
		else {
			
			return "Error";
		}
	}
	
}

