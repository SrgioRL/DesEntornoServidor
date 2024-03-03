package examen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import examen.modelo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	@Query("select e from Empleado e where e.idEmpleado not in (select ep.empleado.idEmpleado from EmpleadoEnProyecto ep)")
	List<Empleado> findEmpleadosSinProyecto();
}
