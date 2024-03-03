package examen.restcontroller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examen.modelo.dto.DirectorDto;
import examen.modelo.dto.EmpleadoEnProyectoDto;
import examen.modelo.entities.Empleado;
import examen.modelo.entities.EmpleadoEnProyecto;
import examen.modelo.entities.Proyecto;
import examen.services.EmpleadoEnProyectoService;
import examen.services.EmpleadoService;
import examen.services.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto")
public class ProyectoController {

	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	
	/*
	 * CRUD HECHO
	 * 
	 * Empleados de un proyecto HECHO
	 * Director de un proyecto HECHO
	 * 
	 * Añadir empleado a proyecto HECHO
	 * Eliminar empleado de un proyecto HECHO
	 * Modificar director de un proyecto  HECHO
	 * 
	 */

	// CRUD
	@GetMapping({ "", "/" })
	public List<Proyecto> all() {
		return proyectoService.findAll();
	}

	@PostMapping("/alta")
	public Proyecto create(@RequestBody Proyecto proyecto) {
		return proyectoService.createOne(proyecto);
	}

	@GetMapping("/uno/{idEmpleado}")
	public Proyecto read(@PathVariable int idEmpleado) {
		return proyectoService.findOne(idEmpleado);
	}

	@PutMapping("/modificar")
	public Proyecto update(@RequestBody Proyecto proyecto) {
		return proyectoService.updateOne(proyecto);
	}

	@DeleteMapping("/eliminar/{idProyecto}")
	public String delete(@PathVariable int idProyecto) {
		if (proyectoService.deleteOne(idProyecto))
			return "Proyecto eliminado correctamente";
		else
			return "El proyecto no se ha podido eliminar";
	}

	//AÑADIR EMPLEADO A PROYECTO
	@PostMapping("/añadirempleado")
	public EmpleadoEnProyecto añadirEmpleado(@RequestBody EmpleadoEnProyectoDto empleadoEnProyectoDto) {

		EmpleadoEnProyecto empleado = new EmpleadoEnProyecto();
		empleado.setEmpleado(empleadoService.findOne(empleadoEnProyectoDto.getIdEmpleado()));
		empleado.setProyecto(proyectoService.findOne(empleadoEnProyectoDto.getIdProyecto()));
		empleado.setDiasPrevistos(proyectoService.findOne(empleadoEnProyectoDto.getIdProyecto()).getDiasPrevistos());
		empleado.setFechaIncorporacion(new Date());
		return empleadoEnProyectoService.createOne(empleado);
	}
	
	//VER DIRECTOR DE UN PROYECTO
	@GetMapping("/director/{idProyecto}")
	public Empleado directorProyecto(@PathVariable int idProyecto) {
		return proyectoService.findDirector(idProyecto);
		
	}
	
	//EMPLEADOS DE UN PROYECTO
	@GetMapping("/empleados/{idProyecto}")
	public List<Empleado> empleadosDeProyecto (@PathVariable int idProyecto){
		return empleadoEnProyectoService.findEmpleadosEnProyecto(idProyecto);
		
	}
	
	//MODIFICAR DIRECTOR DE UN PROYECTO
	@PutMapping("/cambiardirector/{idProyecto}")
	public Proyecto cambiarDirector(@PathVariable int idProyecto, @RequestBody DirectorDto directorDto) {
	    return proyectoService.updateDirector(idProyecto, directorDto.getIdEmpleado());
	}

	//ELIMINAR EMPLEADO DE UN PROYECTO
	@DeleteMapping("/eliminarempleado/{idEntrada}")
	public String eliminarEmpleado(@PathVariable int idEntrada) {
		if (empleadoEnProyectoService.deleteOne(idEntrada))
			return "Empleado eliminado correctamente";
		else
			return "El empleado no se ha podido eliminar";
	}
	

}
