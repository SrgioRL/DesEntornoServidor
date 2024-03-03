package examen.modelo.dto;

import java.util.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoEnProyectoDto {

	private int idProyecto;
	private int idEmpleado;
	private int diasPrevistos;
	private Date fechaIncorporacion;

}
