package cajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajero.modelo.dao.CuentaDao;
import cajero.modelo.entity.Cuenta;
import cajero.modelo.entity.Movimiento;
import jakarta.servlet.http.HttpSession;

@Controller
public class CajeroController {

	@Autowired
	private CuentaDao cDao;

	/**
	 * Se toma la decisión de desarrollar todos los métodos a utilizar dentro del
	 * modelo DAO de cuenta, para conseguir limpieza y orden al no tener métodos repartidos
	 * por diferentes clases.
	 */	
	
	/**
	 * MOSTRAR INGRESAR
	 * 
	 * Muestra la vista html para la operación "ingresar".
	 *
	 * @return vista "ingresar"
	 */
	@GetMapping("/ingresar")
	public String mostrarIngresar() {
		return "ingresar";
	}

	/**
	 * PROCESAR INGRESAR
	 * 
	 * Procesa la operación "ingresar".
	 *
	 * Realiza un ingreso en la cuenta actual si la cantidad es mayor que cero.
	 * Actualiza el mensaje en los atributos flash según el resultado.
	 *
	 * @param session  La sesión HTTP que contiene la cuenta actual.
	 * @param ratt     Atributos flash para mostrar mensajes.
	 * @param cantidad Cantidad a ingresar.
	 * @return Redirecciona a la página principal después de procesar el ingreso.
	 */
	@PostMapping("/ingresar")
	public String procesarIngresar(HttpSession session, RedirectAttributes ratt, @RequestParam double cantidad) {
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");

		if (cantidad > 0) {
			if (cDao.ingresar(cuenta.getIdCuenta(), cantidad)) {
				ratt.addFlashAttribute("mensaje", "Ingreso realizado con éxito");
			} else {
				ratt.addFlashAttribute("mensaje", "Error al realizar el ingreso");
			}
		} else {
			ratt.addFlashAttribute("mensaje", "La cantidad debe ser mayor que cero");
		}

		return "redirect:/home";
	}

	/**
	 * MOSTRAR RETIRAR
	 * 
	 * Muestra la vista html para la operación "retirar".
	 *
	 * @return vista "retirar".
	 */
	@GetMapping("/retirar")
	public String mostrarRetirar() {
		return "retirar";
	}

	/**
	 * PROCESAR RETIRAR
	 * 
	 * Procesa la operación "retirar".
	 *
	 * Realiza una retirada en la cuenta actual si la cantidad es mayor que cero.
	 * Actualiza el mensaje en los atributos flash según el resultado.
	 *
	 * @param session  La sesión HTTP que contiene la cuenta actual.
	 * @param ratt     Atributos flash utilizados para mostrar mensajes.
	 * @param cantidad La cantidad a retirar.
	 * @return Redirecciona a la página principal después de procesar el ingreso.
	 */
	@PostMapping("/retirar")
	public String procesarRetirar(HttpSession session, RedirectAttributes ratt, @RequestParam double cantidad) {
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");

		if (cuenta != null) {
			if (cantidad > 0) {
				if (cDao.retirar(cuenta.getIdCuenta(), cantidad)) {
					ratt.addFlashAttribute("mensaje", "Retirada realizada con éxito");
				} else {
					ratt.addFlashAttribute("mensaje", "Error al realizar la retirada");
				}
			} else {
				ratt.addFlashAttribute("mensaje", "La cantidad debe ser mayor que cero");
			}
		}

		return "redirect:/home";
	}

	/**
	 * MOSTRAR TRANSFERENCIA
	 * 
	 * Muestra la vista html para la operación "transferencia".
	 *
	 * @return vista "transferencia".
	 */
	@GetMapping("/transferencia")
	public String mostrarTransferencia() {
		return "transferencia";
	}

	/**
	 * PROCESAR TRANSFERENCIA
	 * 
	 * Procesa la operación "transferencia".
	 *
	 * Realiza una transferencia desde la cuenta actual a la cuenta de destino.
	 * Actualiza el mensaje en los atributos flash según el resultado.
	 *
	 * @param session         La sesión HTTP que contiene la cuenta de origen.
	 * @param ratt            Atributos flash utilizados para enviar mensajes.
	 * @param idCuentaDestino El ID de la cuenta de destino para la transferencia.
	 * @param cantidad        Cantidad a transferir.
	 * @return Redirecciona a la página principal después de procesar el ingreso.
	 * 
	 */
	@PostMapping("/transferencia")
	public String procesarTransferencia(HttpSession session, RedirectAttributes ratt, @RequestParam int idCuentaDestino,
			@RequestParam double cantidad) {
		Cuenta cuentaOrigen = (Cuenta) session.getAttribute("cuenta");

		if (cuentaOrigen != null) {
			if (cantidad > 0) {
				if (cDao.transferencia(cuentaOrigen, idCuentaDestino, cantidad)) {
					ratt.addFlashAttribute("mensaje", "Transferencia realizada con éxito");
				} else {
					ratt.addFlashAttribute("mensaje", "Error al realizar la transferencia");
				}
			} else {
				ratt.addFlashAttribute("mensaje", "La cantidad debe ser mayor que cero");
			}
		}

		return "redirect:/home";
	}

	/**
	 * VER MOVIMIENTOS
	 * 
	 * Muestra los movimientos y el saldo de la cuenta actual.
	 *
	 * Obtiene los movimientos y el saldo de la cuenta actual y los agrega al modelo
	 * para reflejarlos en la vista "movimientos".
	 *
	 * @param session La sesión HTTP que contiene la cuenta actual.
	 * @param model   El modelo utilizado para agregar atributos a la vista.
	 * @return La vista "movimientos" con la información de los movimientos y el
	 *         saldo.
	 */
	@GetMapping("/movimientos")
	public String verMovimientos(HttpSession session, Model model) {
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");

		List<Movimiento> movimientos = cDao.verMovimientos(cuenta.getIdCuenta());

		model.addAttribute("movimientos", movimientos);
		model.addAttribute("saldo", cuenta.getSaldo());
		return "movimientos";

	}
}
