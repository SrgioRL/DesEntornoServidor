package examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import examen.modelo.entities.Empleado;
import examen.modelo.entities.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
	
	@Query("select p.empleado from Proyecto p where p.idProyecto =?1")
	Empleado findDirectorDeProyecto(int idProyecto);


}
