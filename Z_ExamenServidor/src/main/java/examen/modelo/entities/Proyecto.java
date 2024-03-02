package examen.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name="proyectos")
@NamedQuery(name="Proyecto.findAll", query="SELECT p FROM Proyecto p")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="id_proyecto")
	private int idProyecto;

	private String descripcion;

	@Column(name="dias_previstos")
	private int diasPrevistos;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//uni-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="director")
	private Empleado empleado;

}