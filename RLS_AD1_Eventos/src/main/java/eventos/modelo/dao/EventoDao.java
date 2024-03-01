package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.javabeans.Evento;

public interface EventoDao {
	Evento findById(int idEvento);
	List<Evento> findAll();
	int insert(Evento evento);
	int delete(int idEvento);
	int updateOne(Evento evento);

}
