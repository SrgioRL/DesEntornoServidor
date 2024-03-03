package examen.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examen.modelo.entities.Empleado;
import examen.services.EmpleadoEnProyectoService;
import examen.services.EmpleadoService;
import examen.services.ProyectoService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;

	/*
	 * CRUD HECHO
	 * Empleados sin proyecto HECHO
	 * 
	 */
	
	//CRUD
	@GetMapping({"","/"})
	public List<Empleado> all() {
		return empleadoService.findAll();
	}
	
	@PostMapping("/alta")
	public Empleado create(@RequestBody Empleado empleado) {
		return empleadoService.createOne(empleado);
	}
	
	@GetMapping("/uno/{idEmpleado}")
	public Empleado read(@PathVariable int idEmpleado) {
		return empleadoService.findOne(idEmpleado);
	}
	
	@PutMapping("/modificar")
	public Empleado update(@RequestBody Empleado empleado) {
		return empleadoService.updateOne(empleado);
	}
	
	@DeleteMapping("/eliminar/{idEmpleado}")
	public String delete(@PathVariable int idEmpleado) {
		if (empleadoService.deleteOne(idEmpleado))
			return "Empleado eliminado correctamente";
		else
			return "El empleado no se ha podido eliminar";
	}
	
	//EMPLEADOS SIN PROYECTO
	@GetMapping("/sinproyecto")
	public List<Empleado> empleadosSinProyecto(){
		return empleadoService.findEmpleadosSinProyecto();
	}
	

	
	
	
	
	

}
