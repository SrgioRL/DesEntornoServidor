package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.entities.Evento;
import eventos.modelo.entities.Tipo;
import eventos.modelo.repository.EventoRepository;
import eventos.modelo.repository.TipoRepository;

@Controller
public class TipoController {

	@Autowired
	EventoRepository eRepo;

	@Autowired
	EventoDao eDao;

	@Autowired
	TipoDao tDao;

	@Autowired
	TipoRepository tRepo;

	/**
	 * MOSTRAR EVENTOS POR TIPO
	 * 
	 * Muestra una lista de eventos filtrados por un tipo específico según el idTipo. 
	 * Recupera el tipo de evento de la base de datos utilizando el idTipo proporcionado. 
	 * Si el tipo de evento existe, añade este tipo al modelo junto con una lista completa de tipos de eventos 
	 * para usarla en el desplegable del navbar y una lista de eventos de ese tipo. 
	 * 
	 * 
	 * @param idTipo El ID del tipo de evento a mostrar.
	 * @param model El modelo para pasar datos a la vista.
	 * @return La vista "TiposEventos" con eventos del tipo especificado o redirección a la página principal si el tipo no existe.
	 */
	@GetMapping("/tipo/{id}")
	public String verPorTipo(@PathVariable("id") int idTipo, Model model) {

		Tipo tipo = tDao.findById(idTipo);

		if (tipo != null) {

			model.addAttribute("tipo", tipo);
			List<Tipo> listaTipos = tDao.findAll();
			model.addAttribute("listaTipos", listaTipos);

			List<Evento> eventoTipo = eDao.findByTipo(idTipo);
			model.addAttribute("evento", eventoTipo);

			return "TiposEventos";
		} else {
			model.addAttribute("mensaje", "No se encuentra el evento.");
			return "forward:/";
		}

	}

}
