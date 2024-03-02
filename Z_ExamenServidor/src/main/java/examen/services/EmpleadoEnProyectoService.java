package examen.services;

import java.util.List;
import examen.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoService {
	
	EmpleadoEnProyecto createOne(EmpleadoEnProyecto empleadoEnProyecto);

	EmpleadoEnProyecto findOne(int idEntrada);
	List<EmpleadoEnProyecto> findAll();

	EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto);

	boolean deleteOne(int idEntrada);

}
	