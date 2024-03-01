package restexamen.service;

import java.util.List;

import restexamen.modelo.entities.Empleado;

public interface EmpleadoService {
	
	// Create
	Empleado insertOne(Empleado empleado);
		
	// Read
	Empleado findOne(int idEmpleado);
	List<Empleado> findAll();
		
	// Update
	Empleado updateOne(Empleado empleado);
		
	// Delete
	boolean deleteOne(int idEmpleado);

}
