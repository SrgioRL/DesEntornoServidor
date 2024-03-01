package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entities.Reserva;
import eventos.modelo.repository.EventoRepository;
import eventos.modelo.repository.ReservaRepository;

@Repository
public class ReservaDaoImplMy8 implements ReservaDao {

	@Autowired
	ReservaRepository rRepo;

	@Autowired
	EventoRepository eRepo;

	@Autowired
	EventoDao eDao;

	@Override
	public Reserva findById(int idReserva) {
		return rRepo.findById(idReserva).orElse(null);
	}

	@Override
	public List<Reserva> findAll() {
		return rRepo.findAll();
	}

	@Override
	public boolean nuevaReserva(Reserva reserva, int idEvento) {

		try {
			rRepo.save(reserva);
		} catch (Exception e) {
		}
		return true;
	}

	@Override
	public List<Reserva> findByUsername(String username) {

		return rRepo.findByUsername(username);
	}

	@Override
	public int reservasUsuarioYEvento(String username, int idEvento) {
		List<Reserva> reservas = rRepo.findByUsernameIdEvento(username, idEvento);
		int cantidadTotal = 0;

		for (Reserva reserva : reservas) {
			cantidadTotal += reserva.getCantidad();
		}

		return cantidadTotal;
	}

	@Override
	public int findReservasEvento(int idEvento) {
		List<Reserva> reservas = rRepo.findByEvento(idEvento);
		int reservasTotales = 0;
		for (Reserva reserva : reservas) {
			reservasTotales += reserva.getCantidad();
		}
		return reservasTotales;
	}
	
	@Override
	public boolean delete(int idReserva) {
		try {
			rRepo.deleteById(idReserva);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
