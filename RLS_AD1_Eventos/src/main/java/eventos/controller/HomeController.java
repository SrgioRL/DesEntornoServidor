package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.javabeans.Evento;

@Controller
public class HomeController {
	
	@Autowired
	private EventoDao edao;

	
	/**
	 * MOSTRAR PAGINA PRINCIPAL (HOME)
	 * 
	 * Muestra la página de inicio (Home) con la lista de eventos activos y
	 * cancelados.
	 * 
	 * Utiliza el método findAll para obtener todos los eventos. Luego, separa los
	 * eventos en dos listas, una para eventos activos y otra para eventos
	 * cancelados, basándose en el estado de cada evento. Agrega las listas al
	 * modelo y muestra la vista "home".
	 * 
	 * @param model (el modelo utilizado para pasar datos a la vista)
	 * @return home (vista de la página de inicio con la lista de eventos activos y
	 *         cancelados)
	 */
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Evento> evActivo = new ArrayList<>();
		List<Evento> evCancelado = new ArrayList<>();
		for (Evento evento : edao.findAll()) {
			if (evento.getEstado().equalsIgnoreCase("Activo")) {
				evActivo.add(evento);
			} else
				evCancelado.add(evento);
		}
		model.addAttribute("eventosActivos", evActivo);
		model.addAttribute("evCancelados", evCancelado);
		return "home";
	}

}