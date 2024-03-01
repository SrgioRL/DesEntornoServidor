package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.javabeans.Tipo;

public interface TipoDao {
	List<Tipo>findAll();
	Tipo findById(int idTipo);
}
