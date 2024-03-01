package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	@Query("select u from Usuario u where u.username = ?1 AND u.password = ?1")
	public Usuario findByUsernamePassword(String username, String password);

	@Query("select u from Usuario u where u.username = ?1")
	public Usuario findbyUsername(String username);
}
