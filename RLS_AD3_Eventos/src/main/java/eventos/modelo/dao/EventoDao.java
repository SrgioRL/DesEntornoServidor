package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entities.Evento;

public interface EventoDao {
	Evento findById(int idEvento);
	int modificar(Evento evento);
	List<Evento> findAll();
	List<Evento> findByTipo(int idTipo);
	List<Evento> findByActivo();
	List<Evento> findByDestacado();
	List<Evento> findByActivoDestacado();
	
	
	
}
