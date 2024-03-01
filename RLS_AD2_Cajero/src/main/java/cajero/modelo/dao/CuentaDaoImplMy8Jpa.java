package cajero.modelo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajero.modelo.entity.Cuenta;
import cajero.modelo.entity.Movimiento;
import cajero.repository.CuentaRepository;
import cajero.repository.MovimientoRepository;

@Repository
public class CuentaDaoImplMy8Jpa implements CuentaDao {

	@Autowired
	private CuentaRepository cRepo;
	@Autowired
	private MovimientoRepository mRepo;

	/**
	 * Se toma la decisión de desarrollar todos los métodos a utilizar dentro del
	 * modelo DAO de cuenta, para conseguir limpieza y orden al no tener métodos
	 * repartidos por diferentes clases.
	 */

	/**
	 * LOGIN
	 * 
	 * Recupera una cuenta por su ID para el proceso de login.
	 *
	 * @param idCuenta El ID de la cuenta para iniciar sesión.
	 * @return La cuenta correspondiente al ID proporcionado, o null si no existe.
	 */
	@Override
	public Cuenta login(int idCuenta) {
		return cRepo.findByCuenta(idCuenta);
	}

	/**
	 * BUSCAR TODAS LAS CUENTAS
	 * 
	 * Obtiene todas las cuentas en la base de datos.
	 *
	 * @return Lista con todas las cuentas.
	 */
	@Override
	public List<Cuenta> findAll() {
		return cRepo.findAll();
	}

	/**
	 * BUSCAR CUENTA POR ID
	 * 
	 * Busca una cuenta por su ID.
	 *
	 * @param idCuenta El ID de la cuenta a buscar.
	 * @return La cuenta correspondiente al ID proporcionado, o null si no existe.
	 */
	@Override
	public Cuenta findById(int idCuenta) {
		return cRepo.findById(idCuenta).orElse(null);
	}

	/**
	 * CREAR UNA NUEVA CUENTA
	 * 
	 * Crea una nueva cuenta en el sistema, o modifica una cuenta si ya existe.
	 *
	 * @param cuenta La cuenta a crear o modificar.
	 * @return true si se crea, o false si no.
	 */
	@Override
	public boolean createOne(Cuenta cuenta) {
		cRepo.save(cuenta);
		return true;
	}

	/**
	 * INGRESAR
	 * 
	 * Realiza un ingreso en una cuenta existente y crea y registra el movimiento.
	 *
	 * @param idCuenta El ID de la cuenta en la que se realizará el ingreso.
	 * @param cantidad La cantidad a ingresar.
	 * @return true si se completa el ingreso, o false si no.
	 */
	@Override
	public boolean ingresar(int idCuenta, double cantidad) {
		Cuenta cuenta = cRepo.findById(idCuenta).orElse(null);

		if (cuenta != null) {
			cuenta.setSaldo(cuenta.getSaldo() + cantidad);

			cRepo.save(cuenta);

			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setFecha(new Date());
			movimiento.setCantidad(cantidad);
			movimiento.setOperacion("Ingreso");

			mRepo.save(movimiento);

			return true;
		}

		return false;
	}

	/**
	 * RETIRAR
	 * 
	 * Realiza una retirada de una cuenta existente y crea y registra el movimiento.
	 *
	 * @param idCuenta El ID de la cuenta de la que se realizará la retirada.
	 * @param cantidad La cantidad a retirar.
	 * @return true si se completa la retirada, o false si no.
	 */
	@Override
	public boolean retirar(int idCuenta, double cantidad) {
		Cuenta cuenta = cRepo.findById(idCuenta).orElse(null);

		if (cuenta != null && cuenta.getSaldo() >= cantidad) {
			cuenta.setSaldo(cuenta.getSaldo() - cantidad);
			cRepo.save(cuenta);

			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setFecha(new Date());
			movimiento.setCantidad(cantidad);
			movimiento.setOperacion("Retirada");

			mRepo.save(movimiento);

			return true;
		}

		return false;
	}

	/**
	 * TRANSFERENCIA
	 * 
	 * Realiza una transferencia entre dos cuentas y crea y registra el movimiento
	 * en ambas.
	 *
	 * @param cuentaOrigen    La cuenta de origen de la transferencia.
	 * @param idCuentaDestino El ID de la cuenta de destino de la transferencia.
	 * @param cantidad        La cantidad a transferir.
	 * @return true si la transferencia fue exitosa, false de lo contrario.
	 */
	@Override
	public boolean transferencia(Cuenta cuentaOrigen, int idCuentaDestino, double cantidad) {
		Cuenta cuentaDestino = cRepo.findById(idCuentaDestino).orElse(null);

		if (cuentaDestino != null) {
			if (cuentaOrigen.getSaldo() >= cantidad) {
				cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);
				cRepo.save(cuentaOrigen);

				cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);
				cRepo.save(cuentaDestino);

				Movimiento movimientoEnviado = new Movimiento();
				movimientoEnviado.setCuenta(cuentaOrigen);
				movimientoEnviado.setFecha(new Date());
				movimientoEnviado.setCantidad(cantidad);
				movimientoEnviado.setOperacion("Cargo por transferencia");
				mRepo.save(movimientoEnviado);

				Movimiento movimientoRecibido = new Movimiento();
				movimientoRecibido.setCuenta(cuentaDestino);
				movimientoRecibido.setFecha(new Date());
				movimientoRecibido.setCantidad(cantidad);
				movimientoRecibido.setOperacion("Abono por transferencia");
				mRepo.save(movimientoRecibido);

				return true;
			}
		}

		return false;
	}

	/**
	 * VER MOVIMIENTOS
	 * 
	 * Obtiene todos los movimientos asociados a una cuenta.
	 *
	 * @param idCuenta El ID de la cuenta para la cual se obtienen los movimientos.
	 * @return Lista de movimientos asociados a la cuenta.
	 */
	@Override
	public List<Movimiento> verMovimientos(int idCuenta) {
		return mRepo.findAllMovs(idCuenta);
	}

}
