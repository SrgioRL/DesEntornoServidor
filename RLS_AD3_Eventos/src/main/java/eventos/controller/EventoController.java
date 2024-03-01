package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entities.Evento;
import eventos.modelo.entities.Reserva;
import eventos.modelo.entities.Tipo;
import eventos.modelo.entities.Usuario;
import eventos.modelo.repository.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	UsuarioDao uDao;

	@Autowired
	TipoDao tDao;

	@Autowired
	EventoDao eDao;

	@Autowired
	EventoRepository eRepo;

	@Autowired
	ReservaDao rDao;

	/**
	 * VER DETALLES
	 * 
	 * Gestiona la vista que muestra los detalles de un evento específico por su ID.
	 * Recupera el evento y la lista de tipos de eventos de la base de datos para mostrarlos en la vista.
	 * Calcula las reservas restantes permitidas para el usuario en el evento, teniendo en cuenta un máximo
	 * de reservas y las reservas ya realizadas. Además, determina el número de plazas disponibles en el evento
	 * y prepara una lista de opciones de reservas para que el usuario elija. Todos estos datos se añaden al modelo
	 * para ser accesibles en la vista.
	 * 
	 * @param idEvento El ID del evento a visualizar.
	 * @param model El modelo para pasar datos a la vista.
	 * @param aut Objeto de autenticación para obtener el nombre de usuario actual.
	 * @param reserva Objeto reserva que puede usarse para realizar una reserva.
	 * @return El nombre de la vista "eventoDetalles".
	 */
	@GetMapping("/detalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento, Model model, Authentication aut, Reserva reserva) {
		
		Evento evento = eDao.findById(idEvento);
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		model.addAttribute("evento", evento);
		
		String username = aut.getName();
				//Reservas totales disponibles
				int maxReservas = 10;
				int reservasRestantes = 0;
				int reservasHechas = rDao.reservasUsuarioYEvento(username, idEvento);
				
				reservasRestantes = maxReservas - reservasHechas;
				
				model.addAttribute("reservasRestantes", reservasRestantes);
				
				int quedan = evento.getAforoMaximo() - rDao.findReservasEvento(idEvento);
				model.addAttribute("quedan", quedan);
								
				List<Integer> opciones = new ArrayList<>();
		        for (int i = 1; i <= reservasRestantes; i++) {
		            opciones.add(i);
		        }
		        model.addAttribute("opciones", opciones);
		        
		return "eventoDetalles";
	}
	
	
	/**
	 * PROCESAR RESERVA
	 * 
	 * Procesa la solicitud de reserva para un evento específico según su ID.
	 * Recoge el usuario autenticado y el evento para el cual se está haciendo la reserva.
	 * Valida si la cantidad especificada es válida teniendo en cuenta el máximo de reservas permitidas
	 * y el aforo máximo del evento. Si la cantidad es válida, crea y guarda la reserva con los detalles.
	 * Redirige a la página de detalle del evento con mensajes adaptados en caso de éxito o error.
	 * 
	 * @param idEvento El ID del evento para el cual se realiza la reserva.
	 * @param model El modelo para pasar datos a la vista.
	 * @param rat Objeto RedirectAttributes para pasar atributos a la vista de redirección.
	 * @param aut Objeto de autenticación para obtener el nombre de usuario actual.
	 * @param cantidad La cantidad de entradas a reservar.
	 * @param observaciones Observaciones proporcionadas por el usuario al hacer la reserva.
	 * @return Ruta de redirección.
	 */
	@PostMapping("/reservar/{id}")
	public String procVerEvento(@PathVariable("id") int idEvento, Model model,RedirectAttributes rat, Authentication aut, @RequestParam(required = false) Integer cantidad, @RequestParam String observaciones) {
		
		//Recoger el usuario logeado.
		String username = aut.getName();
		Usuario usuario = uDao.findByUsername(username);
		
		//Recoger el evento para el que vamos a reservar.
		Evento evento = eDao.findById(idEvento);
		model.addAttribute("evento", evento);
		
		//Condicion para cuando la cantidad a reservar es 0.
		if(cantidad == null) {
			rat.addFlashAttribute("mensaje1", "Has alcanzado el máximo de 10 reservas para este evento");
			return "redirect:/eventos/detalle/{id}";
		}
		
		//Reservas restantes de un evento.
		int quedan = evento.getAforoMaximo() - rDao.findReservasEvento(idEvento);
		model.addAttribute("quedan", quedan);
		
		//Condicion para cuando las reservas restantes son 0.
		if(quedan == 0 ) {
			rat.addFlashAttribute("mensaje3", "No quedan entradas para este evento");
			return "redirect:/eventos/detalle/{id}";
		}
		
		//Condicion para cuando las reservas que quedan son menores que la cantidad que se quiere reservar.
		if(quedan < cantidad) {
			rat.addFlashAttribute("mensaje4", "Solo quedan " + quedan + " entradas");
			return "redirect:/eventos/detalle/{id}";
		}

		//Se procede a realizar la reserva si no se han cumplido las condiciones de error.
		Reserva reserva = new Reserva();
		rDao.nuevaReserva(reserva, idEvento);
		
		reserva.setCantidad(cantidad);
		reserva.setEvento(evento);
		reserva.setPrecioVenta(evento.getPrecio());
		reserva.setUsuario(usuario);
		reserva.setObservaciones(observaciones);
		rDao.nuevaReserva(reserva, idEvento);
		rat.addFlashAttribute("mensaje2", "Reserva realizada correctamente");

		return "redirect:/eventos/detalle/{id}";
	}

	/**
	 * MOSTRAR EVENTOS ACTIVOS
	 * 
	 * Obtiene una lista de eventos activos de la base de datos para mostrarlos en la vista. 
	 * Si hay eventos activos disponibles, los añade al modelo y retorna
	 * la vista correspondiente. 
	 * En caso contrario, añade un mensaje al modelo y redirige a la página principal.
	 * 
	 * @param model El modelo para pasar datos a la vista.
	 * @return La vista "eventoActivo" con eventos activos o redirección a la página principal si no hay eventos activos.
	 */
	@GetMapping("/activos")
	public String mostrarActivos(Model model) {
		
//Se añade esta lista de tipos para que en el desplegable del navbar aparezcan los tipos en todas las vistas que contengan ese navbar.
//De lo contrario, el desplegable aparece vacío.
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);

		List<Evento> activo = eDao.findByActivo();

		if (activo != null) {
			model.addAttribute("activo", activo);
			return "eventoActivo";
		} else {
			model.addAttribute("mensaje", "No hay eventos activos");
			return "forward:/";
		}
	}

	/**
	 * MOSTRAR EVENTOS DESTACADOS
	 * 
	 * Obtiene una lista de eventos destacados de la base de datos para mostrarlos en la vista. 
	 * Si existen eventos destacados, los añade al modelo y muestra la vista correspondiente. 
	 * En caso contrario, añade un mensaje al modelo y redirige a la página principal.
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param model El modelo para pasar datos a la vista.
	 * @return La vista "eventoDestacado" con los eventos destacados, o redirige a la página principal si no hay eventos destacados disponibles.
	 */
	@GetMapping("/destacados")
	public String mostrarDestacados(Model model) {

//Se añade esta lista de tipos para que en el desplegable del navbar aparezcan los tipos en todas las vistas que contengan ese navbar.
//De lo contrario, el desplegable aparece vacío.
		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		
		List<Evento> destacado = eDao.findByDestacado();

		if (destacado != null) {
			model.addAttribute("destacado", destacado);
			return "eventoDestacado";
		} else {
			model.addAttribute("mensaje", "No hay eventos destacados");
			return "forward:/";
		}
	}

	/**
	 * VER MIS RESERVAS
	 * 
	 * Recupera y muestra las reservas realizadas por el usuario autenticado. 
	 * Obtiene el usuario autenticado por su nombre de usuario y recupera todas sus reservas
	 * de la base de datos para mostrarlas en la vista.
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param model El modelo para pasar datos a la vista.
	 * @param usuario El usuario cuyas reservas se van a mostrar. Se actualiza con el usuario autenticado.
	 * @param aut Objeto de autenticación que proporciona el nombre de usuario autenticado.
	 * @return La vista "misReservas" que muestra las reservas del usuario.
	 */
	@GetMapping("/misReservas")
	public String verReservas(Model model, Usuario usuario, Authentication aut) {

		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);

		String username = aut.getName();
		usuario = uDao.findByUsername(username);
		List<Reserva> reservas = rDao.findByUsername(username);
		model.addAttribute("reservas", reservas);

		return "misReservas";
	}
	
	
	/**
	 * CANCELAR RESERVA
	 * 
	 * Permite al usuario autenticado cancelar una reserva específica por su ID. 
	 * Si la operación de cancelación se realiza correctamente añade un mensaje indicando el resultado. 
	 * 
	 * Añade una lista de tipos al modelo para asegurar que el desplegable del navbar muestre 
	 * los tipos de eventos en todas las vistas que contengan ese navbar, evitando que aparezca vacío.
	 * 
	 * @param idReserva El ID de la reserva que se va a cancelar.
	 * @param model El modelo para pasar datos a la vista.
	 * @param aut Objeto de autenticación que proporciona el nombre de usuario autenticado.
	 * @return La vista "misReservas" actualizada con el resultado de la operación de cancelación y la lista actualizada de reservas del usuario.
	 */
	@GetMapping("/cancelar/{id}")
	public String cancelarReserva(@PathVariable("id") int idReserva, Model model, Authentication aut) {

		List<Tipo> listaTipos = tDao.findAll();
		model.addAttribute("listaTipos", listaTipos);
		
		String username = aut.getName();
	    if (rDao.delete(idReserva)) {
	        model.addAttribute("mensaje", "Reserva cancelada correctamente");
	    } else {
	        model.addAttribute("mensaje", "El evento NO se ha podido eliminar");
	    }
	    List<Reserva> reservas = rDao.findByUsername(username);
	    model.addAttribute("reservas", reservas);
	    return "misReservas"; 
	}


}
