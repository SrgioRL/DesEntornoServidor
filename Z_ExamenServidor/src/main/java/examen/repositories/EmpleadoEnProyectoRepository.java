package examen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import examen.modelo.entities.Empleado;
import examen.modelo.entities.EmpleadoEnProyecto;

public interface EmpleadoEnProyectoRepository extends JpaRepository<EmpleadoEnProyecto, Integer>{
	
	@Query("select ep.empleado from EmpleadoEnProyecto ep where ep.proyecto.idProyecto = ?1 ")
	List<Empleado> findEmpleadosEnProyecto (int idProyecto);

}
