package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entities.Tipo;
import eventos.modelo.repository.TipoRepository;

@Repository
public class TipoDaoImplMy8 implements TipoDao {

	@Autowired
	TipoRepository tRepo;
	
	@Override
	public Tipo findById(int idTipo) {
		return tRepo.findById(idTipo).orElse(null);
	}

	@Override
	public List<Tipo> findAll() {
		return tRepo.findAll();
	}

}
