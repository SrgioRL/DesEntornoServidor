package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entities.Evento;
import eventos.modelo.repository.EventoRepository;

@Repository
public class EventoDaoImplMy8 implements EventoDao {

	@Autowired
	EventoRepository eRepo;

	@Override
	public Evento findById(int idEvento) {
		return eRepo.findById(idEvento).orElse(null);
	}

	@Override
	public int modificar(Evento evento) {
		return 0;
	}

	@Override
	public List<Evento> findAll() {
		return eRepo.findAll();
	}

	@Override
	public List<Evento> findByTipo(int idTipo) {
		return eRepo.findByTipo(idTipo);
	}

	@Override
	public List<Evento> findByActivo() {
		
		return eRepo.findByActivo();
	}

	@Override
	public List<Evento> findByDestacado() {
		return eRepo.findByDestacado();
	}

	@Override
	public List<Evento> findByActivoDestacado() {
		return eRepo.findByActivoDestacado();
	}

	
}
