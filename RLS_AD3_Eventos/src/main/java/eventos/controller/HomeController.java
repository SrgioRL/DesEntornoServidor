package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.PerfilDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entities.Evento;
import eventos.modelo.entities.Tipo;
import eventos.modelo.entities.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	PerfilDao pDao;

	@Autowired
	EventoDao eDao;

	@Autowired
	TipoDao tDao;

	@Autowired
	UsuarioDao uDao;

	
	/**
	 * PÁGINA DE INICIO
	 * 
	 * Página de inicio de la aplicación, que muestra una lista de eventos destacados.
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param aut Objeto de autenticación que proporciona información sobre el usuario autenticado.
	 * @param model El modelo para pasar datos a la vista.
	 * @param ratt Objeto RedirectAttributes para añadir atributos que estarán disponibles después de una redirección.
	 * @return La vista "home" que muestra la página de inicio.
	 */
	@GetMapping({"","/","/home"})
	public String home(Authentication aut, Model model, RedirectAttributes ratt) {

		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);

		List<Evento> listaDestac = eDao.findByDestacado();
		model.addAttribute("listaDestac", listaDestac);

		if (aut != null) {
			// Acceder a las propiedades de Authentication solo si no es null
			for (GrantedAuthority ele : aut.getAuthorities()) {
				System.out.println("ROL : " + ele.getAuthority());
			}
			ratt.addFlashAttribute("mensaje", aut.getAuthorities());
		}
		return "home";
	}

	/**
	 * MOSTRAR FORMULARIO DE INICIO DE SESIÓN
	 * 
	 * Muestra la página de inicio de sesión. 
	 * Si se detecta un parámetro erróneo añade un mensaje de error al modelo para informar al usuario 
	 * del fallo en el parámetro usuario o contraseña.
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param error Un parámetro opcional para mostrar un error en el inicio de sesión.
	 * @param model El modelo para pasar datos a la vista.
	 * @return La vista "formLogin" que muestra el formulario de inicio de sesión.
	 */
	@GetMapping("/login")
	public String mostrarFormLogin(@RequestParam(value = "error", required = false) String error, Model model) {
		
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		
	    if (error != null) {
	    	        model.addAttribute("mensajeError", "Usuario o contraseña incorrectos");
	    }
	    return "formLogin";
	}
	
	/**
	 * GESTIONAR CIERRE DE SESIÓN
	 * 
	 * Maneja el proceso de cierre de sesión para el usuario. 
	 * Elimina el atributo "usuario" de la sesión y luego invalida la sesión para que no se mantenga ninguna información del usuario. 
	 * Redirige al usuario a la página de inicio.
	 * 
	 * 
	 * @param sesion El objeto HttpSession que representa la sesión actual del usuario.
	 * @return La vista "home", que redirige al usuario tras el cierre de sesión.
	 */
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {

		sesion.removeAttribute("usuario");
		sesion.invalidate();

		return "home";
	}
		
	/**
	 * MOSTRAR FORMULARIO DE REGISTRO
	 * 
	 * Muestra la página de formulario de registro. 
	 * Añade un nuevo objeto Usuario al modelo para ser utilizado en el formulario, permitiendo introducir los datos necesarios para el registro. 
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param model El modelo para pasar datos a la vista.
	 * @return La vista "formSignup" que muestra el formulario de registro.
	 */
	@GetMapping("/signup")
	public String registrar(Model model) {
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		
		model.addAttribute("usuario", new Usuario());
		return "formSignup";

	}

	/**
	 * PROCESAR REGISTRO DE USUARIO
	 * 
	 * Maneja la solicitud de registro de un nuevo usuario. Verifica primero si el usuario ya existe en la base de datos
	 * utilizando su nombre de usuario. 
	 * Si el usuario ya existe, se redirige de nuevo al formulario de registro con un mensaje de error. 
	 * Si el usuario no existe, el nuevo usuario se guarda en la base de datos.
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param usuario Objeto Usuario que contiene la información proporcionada por el formulario de registro.
	 * @param ratt Objeto RedirectAttributes para pasar mensajes de feedback a la vista tras la redirección.
	 * @return Redirección al formulario de registro con un mensaje apropiado dependiendo del resultado del proceso de registro.
	 */
	@PostMapping("/signup")
	public String proRegistrar(Usuario usuario, RedirectAttributes ratt, Model model) {
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		
	    if (uDao.findById(usuario.getUsername()) != null) {
	        ratt.addFlashAttribute("mensaje1", "Este usuario ya existe.");
	        return "redirect:/signup";
	    } else {
	        usuario.setEnabled(1);
	        usuario.setFechaRegistro(new Date());
	        usuario.addPerfil(pDao.findById(3));
	        usuario.setPassword("{noop}" + usuario.getPassword());

	        uDao.registro(usuario);
	        ratt.addFlashAttribute("mensaje2", "Registro realizado correctamente.");
	        return "redirect:/signup";
	    }
	}
}
