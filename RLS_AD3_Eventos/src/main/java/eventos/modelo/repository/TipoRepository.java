package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entities.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}
