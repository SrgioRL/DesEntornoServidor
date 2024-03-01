package eventos.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="eventos")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVENTO")
	private int idEvento;

	@Column(name="AFORO_MAXIMO")
	private int aforoMaximo;

	private String descripcion;

	private String destacado;

	private String direccion;

	private int duracion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	@Column(name="MINIMO_ASISTENCIA")
	private int minimoAsistencia;

	private String nombre;

	private int precio;

	//uni-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private Tipo tipo;



}