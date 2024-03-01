package restexamen.modelo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpleadoEnProyectoDto {
	
	@EqualsAndHashCode.Include
	private int idEntrada;
	
	private int diasPrevistos;
	private Date fechaIncorporacion;
	private int idEmpleado;
	private int idProyecto;
	
}
