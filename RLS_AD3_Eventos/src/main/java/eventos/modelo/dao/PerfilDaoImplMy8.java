package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entities.Perfil;
import eventos.modelo.repository.PerfilRepository;

@Repository
public class PerfilDaoImplMy8 implements PerfilDao{

	@Autowired
	PerfilRepository pRepo;
	
	@Override
	public Perfil findById(int idPerfil) {
		return pRepo.findById(idPerfil).orElse(null);
	}

	@Override
	public Perfil modificar(Perfil perfil) {
		return null;
	}

	@Override
	public List<Perfil> findAll() {
		return null;
	}

}



