package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entities.Perfil;

public interface PerfilDao {
	Perfil findById(int idPerfil);
	Perfil modificar(Perfil perfil);
	List<Perfil> findAll();
}
