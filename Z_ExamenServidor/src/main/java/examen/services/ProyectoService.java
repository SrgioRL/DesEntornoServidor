package examen.services;

import java.util.List;

import examen.modelo.entities.Empleado;
import examen.modelo.entities.Proyecto;

public interface ProyectoService {
	Proyecto createOne(Proyecto proyecto);

	Proyecto findOne(int idProyecto);
	List<Proyecto> findAll();
	Empleado findDirector(int idProyecto);

	Proyecto updateOne(Proyecto proyecto);

	boolean deleteOne(int idProyecto);

}
