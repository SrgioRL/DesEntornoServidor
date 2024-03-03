package examen.services;

import java.util.List;

import examen.modelo.entities.Empleado;
import examen.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoService {
	
	EmpleadoEnProyecto createOne(EmpleadoEnProyecto empleadoEnProyecto);

	EmpleadoEnProyecto findOne(int idEntrada);
	List<EmpleadoEnProyecto> findAll();
	List<Empleado> findEmpleadosEnProyecto(int idProyecto);

	EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto);

	boolean deleteOne(int idEntrada);

}
	