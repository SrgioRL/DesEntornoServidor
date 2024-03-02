package examen.services;

import java.util.List;
import examen.modelo.entities.Proyecto;

public interface ProyectoService {
	Proyecto createOne(Proyecto proyecto);

	Proyecto findOne(int idProyecto);
	List<Proyecto> findAll();

	Proyecto updateOne(Proyecto proyecto);

	boolean deleteOne(int idProyecto);

}
