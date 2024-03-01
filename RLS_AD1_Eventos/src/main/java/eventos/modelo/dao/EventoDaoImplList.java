package eventos.modelo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import eventos.modelo.javabeans.Evento;

@Repository
public class EventoDaoImplList implements EventoDao {

	public List<Evento> lista;
	private static int idAuto;

	static {
		idAuto = 0;
	}

	public EventoDaoImplList() throws ParseException {
		lista = new ArrayList<>();
		cargarLista();
	}

	/**
	 * CARGA DE DATOS EN LISTA
	 * 
	 * Carga datos en una lista ya que no tenemos acceso a una BBDD.
	 * 
	 * Utiliza objetos SimpleDateFormat para parsear fechas. TipoDaoImplList para
	 * obtener información sobre los tipos de evento.
	 * 
	 * @throws ParseException si ocurre un error al analizar las fechas.
	 */
	private void cargarLista() throws ParseException {
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		TipoDaoImplList tipoDao = new TipoDaoImplList();
		lista.add(new Evento(1, "Concierto Rammstein",
				"Concierto de final de gira, por primera y última vez en Córdoba", fecha.parse("15/12/2023"), 1,
				"C/ Ejemplo 1", "Activo", "S", 5000, 100, 180, tipoDao.findById(1)));
		lista.add(new Evento(2, "'Historia y memoria con nombre de mujer'",
				"Exposición homenaje a las cordobesas represaliadas en la Guerra Civil", fecha.parse("27/11/2023"), 5,
				"C/ Prueba 23", "Activo", "S", 150, 15, 5.5, tipoDao.findById(2)));
		lista.add(new Evento(3, "Medina Azahara", "Visita nocturna al conjunto arqueológico Madinat al-Zahra",
				fecha.parse("18/11/2023"), 1, "C/ Omeyas 15", "Activo", null, 30, 10, 20, tipoDao.findById(3)));
		lista.add(new Evento(4, "Boda Rojas-Zamora", "Ceremonia en el Alcázar de los Reyes Cristianos",
				fecha.parse("25/11/2023"), 1, "C/ Inventada 43", "Activo", "S", 150, 20, 0, tipoDao.findById(4)));
		lista.add(new Evento(5, "Mezquita-Catedal de Córdoba", "Visita guiada a la famosa Mezquita-Catedral cordobesa",
				fecha.parse("11/12/2023"), 1, "C/ Judería 3", "Cancelado", null, 25, 8, 15, tipoDao.findById(3)));
		idAuto = 6;
	}

	/**
	 * BUSCAR UN EVENTO POR ID
	 * 
	 * Encuentra y devuelve un evento según su ID.
	 * 
	 * Recorre la lista de eventos y compara el ID proporcionado con el ID de cada
	 * evento. Si encuentra un evento con el mismo ID, lo devuelve. Si no se
	 * encuentra ningún evento con el ID dado, retorna null.
	 * 
	 * @param idEvento
	 * @return el Evento correspondiente al ID dado, o null si no se encuentra
	 *         ningún evento con ese ID.
	 */
	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	/**
	 * BUSCAR TODOS LOS EVENTOS
	 * 
	 * Devuelve una lista de todos los eventos disponibles.
	 * 
	 * @return lista (contiene todos los eventos disponibles)
	 */
	@Override
	public List<Evento> findAll() {
		return lista;
	}

	/**
	 * INSERTAR EVENTO
	 * 
	 * Inserta un nuevo evento en la lista si no existe.
	 * 
	 * Verifica si la lista ya contiene el evento. Si no lo contiene, asigna un
	 * nuevo ID al evento y lo agrega a la lista.
	 * 
	 * @param evento (evento a insertar).
	 * @return 1 si se inserta con éxito, 0 si el evento ya existe en la lista.
	 */
	@Override
	public int insert(Evento evento) {
		if (!lista.contains(evento)) {
			evento.setIdEvento(idAuto++);
			lista.add(evento);
			return 1;
		}
		return 0;
	}

	/**
	 * ELIMINAR EVENTO
	 * 
	 * Elimina un evento de la lista según su ID.
	 * 
	 * Utiliza el método findById para encontrar el evento con el mismo ID que se
	 * proporciona. Si el evento no se encuentra, retorna 0. De lo contrario,
	 * elimina el evento de la lista y retorna 1 si se elimina con éxito, o 0 si no
	 * se elimina.
	 * 
	 * @param idEvento
	 * @return 1 si se elimina con éxito, 0 si el evento no se encuentra en la
	 *         lista.
	 */
	@Override
	public int delete(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null)
			return 0;

		return lista.remove(evento) ? 1 : 0;
	}

	/**
	 * ACTUALIZAR EVENTO
	 * 
	 * Actualiza un evento en la lista.
	 * 
	 * Utiliza el método indexOf para encontrar la posición del evento en la lista.
	 * Si no se encuentra, retorna 0. De lo contrario, actualiza el evento en la
	 * lista y retorna 1 si se actualiza con éxito.
	 * 
	 * @param evento el evento a actualizar.
	 * @return 1 si se actualiza con éxito, 0 si el evento no se encuentra en la
	 *         lista.
	 */
	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		if (pos == -1)
			return 0;
		lista.set(pos, evento);
		return 1;
	}

}
