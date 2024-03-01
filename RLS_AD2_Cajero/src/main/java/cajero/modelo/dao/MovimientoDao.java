package cajero.modelo.dao;

import java.util.List;
import cajero.modelo.entity.Movimiento;

public interface MovimientoDao {
	
	List<Movimiento> findAll();
	Movimiento findById(int idMovimiento);
	boolean createOne(Movimiento movimiento);
}
