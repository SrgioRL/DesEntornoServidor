package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.modelo.entities.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
