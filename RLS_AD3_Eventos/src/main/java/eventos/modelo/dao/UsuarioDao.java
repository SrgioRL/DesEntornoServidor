package eventos.modelo.dao;

import eventos.modelo.entities.Usuario;

public interface UsuarioDao {

	Usuario findById(String username);
	
	boolean registro(Usuario usuario);
	
	Usuario login(String username, String password);

	Usuario findByUsername(String username);
}
