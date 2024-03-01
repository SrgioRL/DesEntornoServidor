package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entities.Usuario;
import eventos.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao {

	@Autowired
	UsuarioRepository uRepo;

	@Override
	public Usuario findById(String username) {
		return uRepo.findById(username).orElse(null);
	}

	@Override
	public boolean registro(Usuario usuario) {
		if (findById(usuario.getUsername()) == null) {
			uRepo.save(usuario);
			return true;
		}
		return false;
	}

	@Override
	public Usuario login(String username, String password) {
		return uRepo.findByUsernamePassword(username, password);
	}

	@Override
	public Usuario findByUsername(String username) {
		return uRepo.findbyUsername(username);
	}

}
