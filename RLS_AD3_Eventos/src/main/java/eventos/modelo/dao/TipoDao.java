package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entities.Tipo;

public interface TipoDao {

	Tipo findById(int idTipo);
	
	List<Tipo> findAll();
	
}
