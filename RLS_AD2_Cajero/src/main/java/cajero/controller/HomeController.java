package cajero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajero.modelo.dao.CuentaDao;
import cajero.modelo.entity.Cuenta;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private CuentaDao cDao;

	/**
	 * MOSTRAR HOME
	 * 
	 * Muestra la vista "home" que refleja las opciones para la cuenta actual.
	 *
	 * @param model   El modelo utilizado para agregar atributos a la vista.
	 * @param session La sesión HTTP que contiene la cuenta actual.
	 * @return La vista "home".
	 */
	@GetMapping( "/home" )
	public String home(Model model, HttpSession session) {
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
		model.addAttribute("cuenta", cuenta);
		return "home";
	}

	/**
	 * MOSTRAR LOGIN
	 * 
	 * Muestra la vista "login".
	 *
	 * @return La vista "login" para el inicio de sesión.
	 */
	@GetMapping({"/", "/login"})
	public String mostrarLogin() {
		return "login";
	}

	/**
	 * PROCESAR LOGIN
	 * 
	 * Procesa el inicio de sesión utilizando el ID de cuenta. Si la cuenta existe,
	 * la establece en la sesión y redirige a la página principal. Si la cuenta no
	 * existe, muestra un mensaje de error y redirige de nuevo a la página de login.
	 *
	 * @param ratt     Atributos flash utilizados para mostrar mensajes.
	 * @param sesion   La sesión HTTP donde se almacena la cuenta actual.
	 * @param idCuenta El ID de la cuenta proporcionado para el inicio de sesión.
	 * @return Redirecciona a la página principal si se realiza el inicio de sesión,
	 *         o redirige a la página de login si hay algún error.
	 */
	@PostMapping("/login")
	public String procesarLogin(RedirectAttributes ratt, HttpSession sesion, @RequestParam int idCuenta) {
		Cuenta cuenta = cDao.login(idCuenta);
		if (cuenta != null) {
			sesion.setAttribute("cuenta", cuenta);
			return "redirect:/home";
		}
		ratt.addFlashAttribute("mensaje", "La cuenta solicitada no existe");
		return "redirect:/login";
	}

	/**
	 * LOGOUT
	 * 
	 * Cierra la sesión actual, eliminando la cuenta e invalidando la sesión.
	 * Redirige a la vista "login" después de cerrar la sesión.
	 *
	 * @param sesion La sesión HTTP que se cerrará.
	 * @return La vista "login" después de cerrar la sesión.
	 */
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "login";
	}
}