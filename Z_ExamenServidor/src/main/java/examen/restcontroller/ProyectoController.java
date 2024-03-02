package examen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examen.services.EmpleadoEnProyectoService;
import examen.services.ProyectoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/proyecto")
public class ProyectoController {
	
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;
	

}
