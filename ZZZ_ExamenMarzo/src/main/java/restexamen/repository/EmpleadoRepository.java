package restexamen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restexamen.modelo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
