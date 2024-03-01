package eventos.modelo.javabeans;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Evento implements Serializable{
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private int idEvento;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private int duracion;
	private String direccion;
	private String estado;
	private String destacado;
	private int aforoMaximo;
	private int minimoAsistencia;
	private double precio;
	private Tipo tipo;

}
