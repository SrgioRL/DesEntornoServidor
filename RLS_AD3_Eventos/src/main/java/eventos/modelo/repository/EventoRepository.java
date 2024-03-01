package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

	@Query("select e from Evento e where e.tipo.idTipo = ?1")
	public List<Evento> findByTipo(int idTipo);
	
	@Query("select e.nombre, e.aforoMaximo, e.fechaInicio From Evento e JOIN e.tipo t WHERE t.idTipo = ?1")
	public List<Evento> findByTipoBueno(int idTipo);
	
	@Query("select e FROM Evento e WHERE e.estado = 'Activo'")
	public List<Evento> findByActivo();
	
	@Query("select e FROM Evento e WHERE e.destacado = 'S'")
	public List<Evento> findByDestacado();
	
	@Query("select e FROM Evento e where e.estado = 'Activo' AND e.destacado = 'S'")
	public List<Evento> findByActivoDestacado();
}
