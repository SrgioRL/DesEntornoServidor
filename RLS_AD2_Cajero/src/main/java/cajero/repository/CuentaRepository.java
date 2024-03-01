package cajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cajero.modelo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

	/**
	 * Realiza una consulta a la base de datos en la que selecciona y monta un objeto de la clase Cuenta basado en su ID.
	 * 
	 * @param idCuenta El ID de la cuenta a buscar en la base de datos.
	 * @return La cuenta correspondiente al ID proporcionado.
	 */
	@Query("SELECT c FROM Cuenta c WHERE c.idCuenta = ?1")
	public Cuenta findByCuenta(int idCuenta);

}
