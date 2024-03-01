package restexamen.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the empleados_en_proyecto database table.
 * 
 */
@Entity
@Table(name="empleados_en_proyecto")
@NamedQuery(name="EmpleadoEnProyecto.findAll", query="SELECT e FROM EmpleadoEnProyecto e")
public class EmpleadoEnProyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_entrada")
	private int idEntrada;

	@Column(name="dias_previstos")
	private int diasPrevistos;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_incorporacion")
	private Date fechaIncorporacion;

	//uni-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	//uni-directional many-to-one association to Proyecto
	@ManyToOne
	@JoinColumn(name="id_proyecto")
	private Proyecto proyecto;

	public EmpleadoEnProyecto() {
	}

	public int getIdEntrada() {
		return this.idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public int getDiasPrevistos() {
		return this.diasPrevistos;
	}

	public void setDiasPrevistos(int diasPrevistos) {
		this.diasPrevistos = diasPrevistos;
	}

	public Date getFechaIncorporacion() {
		return this.fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Override
	public String toString() {
		return "EmpleadoEnProyecto [idEntrada=" + idEntrada + ", diasPrevistos=" + diasPrevistos
				+ ", fechaIncorporacion=" + fechaIncorporacion + ", empleado=" + empleado + ", proyecto=" + proyecto
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEntrada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoEnProyecto other = (EmpleadoEnProyecto) obj;
		return idEntrada == other.idEntrada;
	}

}