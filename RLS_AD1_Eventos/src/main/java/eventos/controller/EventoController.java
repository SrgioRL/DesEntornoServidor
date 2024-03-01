package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabeans.Evento;

@Controller
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;

	/**
	 * VISTA FORMULARIO NUEVO EVENTO
	 * 
	 * Muestra la vista de un formulario para generar un nuevo evento. * @return
	 * nuevo (vista de dicho formulario)
	 */

	@GetMapping("/nuevo")
	public String formNuevo() {
		return "nuevo";
	}

	/**
	 * PROCESAR NUEVO EVENTO
	 * 
	 * Procesa la creación de un nuevo evento. Recibe un objeto de tipo Evento y: -
	 * Establece el estado del evento como "Activo". - Asigna el tipo de evento
	 * según el id. - Inserta el evento en la lista correspondiente.
	 * 
	 * @param evento (el objeto Evento que se va a procesar)
	 * @return redirect:/ , que redirige a Home después de procesar el evento.
	 */

	@PostMapping("/nuevo")
	public String procAlta(Evento evento) {
		evento.setEstado("Activo");
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));
		if ((edao.insert(evento) == 1)) {
			System.out.println(evento);
		}
		return "redirect:/";
	}

	/**
	 * DETALLES EVENTO
	 * 
	 * Retorna una vista con los detalles de un evento según la ID que recibe.
	 * 
	 * Con el método findById localiza el evento con el mismo iD que recibe, y si el
	 * evento existe lo agrega al modelo y muestra la vista "detalles". Si el evento
	 * no se encuentra en la lista, redirige a la página principal.
	 * 
	 * @param idEvento (iD del evento que se va a mostrar en detalle)
	 * @param model    (el modelo utilizado para pasar datos a la vista)
	 * @return detalles (vista detallada del evento. Si no encuentra evento redirige
	 *         a la página principal)
	 */

	@GetMapping("/detalle/{id}")
	public String detallesEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "detalles";
		} else
			return "forward:/";
	}

	/**
	 * VISTA FORMULARIO EDICION EVENTO
	 * 
	 * Retorna una vista para editar un evento según la ID que recibe.
	 * 
	 * Utiliza el método findById para localizar el evento con el mismo ID que
	 * recibe. Si el evento existe, lo agrega al modelo y muestra la vista "editar".
	 * Si el evento no se encuentra, redirige a la página principal.
	 * 
	 * @param idEvento (el ID del evento que se va a editar)
	 * @param model    (el modelo utilizado para pasar datos a la vista)
	 * @return editar (vista con formulario para editar el evento. Si no se
	 *         encuentra el evento, redirige a la página principal)
	 */

	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "editar";
		} else
			return "forward:/";
	}

	/**
	 * PROCESAR EDICION EVENTO
	 * 
	 * Procesa la edición de un evento según la ID que recibe.
	 * 
	 * Utiliza el método findById para localizar el evento con el mismo ID que
	 * recibe. Si el evento existe, actualiza sus atributos con la información
	 * proporcionada en los "set" de "evActual" usando el método updateOne().
	 * 
	 * @param evento   (el objeto Evento a editar)
	 * @param idEvento (el ID del evento que se está editando)
	 * @return redirect:/ (redirige a la página principal después de editar el
	 *         evento)
	 */

	@PostMapping("/editar/{id}")
	public String procEditarEvento(@ModelAttribute Evento evento, @PathVariable("id") int idEvento) {
		Evento evActual = edao.findById(idEvento);

		if (evActual != null) {
			evActual.setNombre(evento.getNombre());
			evActual.setDescripcion(evento.getDescripcion());
			evActual.setFechaInicio(evento.getFechaInicio());
			evActual.setDuracion(evento.getDuracion());
			evActual.setDireccion(evento.getDireccion());
			evActual.setDestacado(evento.getDestacado());
			evActual.setAforoMaximo(evento.getAforoMaximo());
			evActual.setMinimoAsistencia(evento.getMinimoAsistencia());
			evActual.setPrecio(evento.getPrecio());
			evActual.setTipo(tdao.findById(evento.getTipo().getIdTipo()));

			edao.updateOne(evActual);
		}
		return "redirect:/";
	}

	/**
	 * CANCELAR EVENTO
	 * 
	 * Cancela un evento según la ID que recibe.
	 * 
	 * Utiliza el método findById para localizar el evento con el mismo ID que
	 * recibe. Si el evento existe, establece su estado como "cancelado" y lo agrega
	 * al modelo que se usará para mostrar una tabla de eventos cancelados.
	 * 
	 * @param idEvento (el ID del evento que se va a cancelar)
	 * @param model    (el modelo utilizado para pasar datos a la vista)
	 * @return redirect:/ (redirige a la página principal después de cancelar el
	 *         evento)
	 */
	@GetMapping("/cancelar/{id}")
	public String cancelarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			evento.setEstado("Cancelado");
			model.addAttribute("evCancelados", evento);
		}
		return "redirect:/";
	}

	/**
	 * ACTIVAR EVENTO
	 * 
	 * Activa un evento cancelado previamente.
	 * 
	 * Utiliza el método findById para localizar el evento con el mismo ID que
	 * recibe. Si el evento existe, establece su estado como "activo", por lo que se
	 * muestra de nuevo en la tabla de eventos activos.
	 * 
	 * @param idEvento (el ID del evento que se va a activar)
	 * @param model    (el modelo utilizado para pasar datos a la vista)
	 * @return redirect:/ (redirige a la página principal después de activar el
	 *         evento)
	 */
	@GetMapping("/activar/{id}")
	public String activarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			evento.setEstado("activo");
			model.addAttribute("eventosActivos", evento);
		}
		return "redirect:/";
	}

	/**
	 * ELIMINAR EVENTO
	 * 
	 * Elimina un evento según la ID que recibe.
	 * 
	 * Utiliza el método delete para eliminar el evento con el mismo ID que recibe.
	 * Si se elimina con éxito, redirige a la página principal.
	 * 
	 * @param idEvento (el ID del evento que se va a eliminar)
	 * @return redirect:/ (redirige a la página principal después de eliminar el
	 *         evento)
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento) {
		if (edao.delete(idEvento) == 1) {
		}
		return "redirect:/";
	}

	/**
	 * FORMATO FECHAS
	 * 
	 * Método para el formateo de fechas
	 * 
	 * @param binder.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
