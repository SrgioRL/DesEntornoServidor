package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import eventos.modelo.javabeans.Tipo;

@Repository
public class TipoDaoImplList implements TipoDao {
	private List<Tipo> lista;

	public TipoDaoImplList() {
		lista = new ArrayList<>();
		cargarLista();
	}

	private void cargarLista() {
		lista.add(new Tipo(1, "Concierto", "Espectáculo musical"));
		lista.add(new Tipo(2, "Exposición", "Exposiciones en museo"));
		lista.add(new Tipo(3, "Visita Guiada", "Visita guiada de carácter cultural"));
		lista.add(new Tipo(4, "Boda", "Eventos nupciales"));
	}

	/**
	 * Retorna una lista de todos los tipos de eventos disponibles.
	 * 
	 * @return lista (contiene todos los tipos de eventos)
	 */
	@Override
	public List<Tipo> findAll() {
		return lista;
	}

	/**
	 * Encuentra y retorna un tipo de evento específico según su ID.
	 * 
	 * Recorre la lista de tipos y compara el ID proporcionado con el ID de cada
	 * tipo. Si encuentra un tipo con el mismo ID, lo devuelve. Si no se encuentra
	 * ningún tipo con el ID dado, retorna null.
	 * 
	 * @param idTipo el ID del tipo de evento que se va a buscar.
	 * @return el Tipo de evento correspondiente al ID dado, o null si no se
	 *         encuentra ningún tipo con ese ID.
	 */
	@Override
	public Tipo findById(int idTipo) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdTipo() == idTipo)
				return lista.get(i);
		}
		return null;
	}

}
