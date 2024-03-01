package restexamen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoRepository extends JpaRepository<EmpleadoEnProyecto, Integer> {
	
	@Query("select eep.empleado from EmpleadoEnProyecto eep where eep.proyecto.idProyecto = ?1 ")
	List<Empleado> findEmpleadosEnProyecto (int idProyecto);

}
