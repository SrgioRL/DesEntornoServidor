package examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import examen.modelo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
