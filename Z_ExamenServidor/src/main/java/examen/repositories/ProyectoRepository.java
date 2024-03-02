package examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import examen.modelo.entities.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{

}
