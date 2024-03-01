package eventos.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "usuarios")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private String username;

	private String apellidos;

	private String direccion;

	private int enabled;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;

	private String nombre;

	private String password;

	// uni-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(name = "usuario_perfiles", joinColumns = { @JoinColumn(name = "USERNAME") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_PERFIL") })
	private List<Perfil> perfiles;

	public void addPerfil(Perfil perfil) {
		if (perfiles == null)
			perfiles = new ArrayList<>();

		perfiles.add(perfil);
	}

	public void removePerfil(Perfil perfil) {
		if (perfiles == null)
			perfiles = new ArrayList<>();

		perfiles.remove(perfil);
	}

}