package restexamen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
	
	@Query("select p.empleado from Proyecto p where p.idProyecto =?1")
	Empleado findDirectorPorProyecto(int idProyecto);

}
