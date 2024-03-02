package examen.services;

import java.util.List;
import examen.modelo.entities.Empleado;

public interface EmpleadoService {

	Empleado createOne(Empleado empleado);

	Empleado findOne(int idEmpleado);
	List<Empleado> findAll();

	Empleado updateOne(Empleado empleado);

	boolean deleteOne(int idEmpleado);

}
