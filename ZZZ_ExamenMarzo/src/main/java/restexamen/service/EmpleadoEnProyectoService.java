package restexamen.service;

import java.util.List;

import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoService {
	
	// Create
	EmpleadoEnProyecto insertOne(EmpleadoEnProyecto empleadoEnProyecto);
		
	// Read
	EmpleadoEnProyecto findOne(int idEntrada);
	List<EmpleadoEnProyecto> findAll();
	List<Empleado> findEmpleados(int idProyecto);
		
	// Update
	EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto);
		
	// Delete
	boolean deleteOne(int idEntrada);

}
