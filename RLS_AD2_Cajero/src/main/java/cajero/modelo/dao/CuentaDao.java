package cajero.modelo.dao;

import java.util.List;

import cajero.modelo.entity.Cuenta;
import cajero.modelo.entity.Movimiento;

public interface CuentaDao {
	
	Cuenta login(int idCuenta);

	List<Cuenta> findAll();

	Cuenta findById(int idCuenta);

	boolean createOne(Cuenta cuenta);

	boolean ingresar(int idCuenta, double cantidad);

	boolean retirar(int idCuenta, double cantidad);

	boolean transferencia(Cuenta cuentaOrigen, int idCuentaDestino, double cantidad);

	List<Movimiento> verMovimientos(int idCuenta);

}
