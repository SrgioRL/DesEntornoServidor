package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import eventos.modelo.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	
	@Query("select r FROM Reserva r WHERE r.usuario.username = ?1")
	List<Reserva> findByUsername(String username);
	
	@Query("select r From Reserva r Where r.usuario.username = ?1 AND r.evento.idEvento = ?2")
	List<Reserva> findByUsernameIdEvento(String username, int idEvento);

	@Query("select r FROM Reserva r WHERE r.evento.idEvento = ?1")
	List<Reserva> findByEvento(int idEvento);
	
}
