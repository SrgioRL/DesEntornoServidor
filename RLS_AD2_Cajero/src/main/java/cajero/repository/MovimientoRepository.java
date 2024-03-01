package cajero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cajero.modelo.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	/**
	 * Realiza una consulta a la base de datos en la que selecciona y recupera todos los movimientos asociados a una cuenta específica.
	 * 
	 * @param idCuenta El ID de la cuenta para la cual se obtienen los movimientos.
	 * @return Lista de movimientos asociados a la cuenta.
	 */
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta.idCuenta=?1")
	public List<Movimiento> findAllMovs(int idCuenta);
	
}
