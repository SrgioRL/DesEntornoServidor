package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entities.Reserva;

public interface ReservaDao {
	
	Reserva findById(int idReserva);
	List<Reserva> findAll();
	List<Reserva> findByUsername(String username);
	boolean nuevaReserva(Reserva reserva, int idEvento);
	int reservasUsuarioYEvento(String username, int idEvento);
	int findReservasEvento(int idEvento);
	boolean delete(int idReserva);
}
