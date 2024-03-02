package examen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examen.services.EmpleadoEnProyectoService;
import examen.services.EmpleadoService;
import examen.services.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	
	/*
	 * Lista empleados
	 * Lista proyectos
	 * Empleados de un proyecto
	 * Empleados sin proyecto
	 * Director de un proyecto
	 * 
	 * CRUD empleados/proyectos
	 * 
	 * Añadir empleado a proyecto
	 * Modificar director de un proyecto
	 * 
	 */

}
