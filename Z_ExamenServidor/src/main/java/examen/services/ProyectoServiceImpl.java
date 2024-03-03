package examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.modelo.entities.Empleado;
import examen.modelo.entities.Proyecto;
import examen.repositories.EmpleadoRepository;
import examen.repositories.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements ProyectoService {

	@Autowired
	private ProyectoRepository proyectoRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public Proyecto createOne(Proyecto proyecto) {
		if (proyecto.getEmpleado() != null) {
			Empleado empleado = empleadoRepository.findById(proyecto.getEmpleado().getIdEmpleado()).orElse(null);
			proyecto.setEmpleado(empleado);
		}
		return proyectoRepository.save(proyecto);
	}

	@Override
	public Proyecto findOne(int idProyecto) {
		return proyectoRepository.findById(idProyecto).orElse(null);
	}

	@Override
	public List<Proyecto> findAll() {
		return proyectoRepository.findAll();
	}

	@Override
	public Proyecto updateOne(Proyecto proyecto) {
		try {
			return proyectoRepository.save(proyecto);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Proyecto updateDirector(int idProyecto, int idEmpleado) {
		Proyecto proyecto = proyectoRepository.findById(idProyecto).orElse(null);
		Empleado nuevoDirector = empleadoRepository.findById(idEmpleado).orElse(null);

		proyecto.setEmpleado(nuevoDirector);
		return proyectoRepository.save(proyecto);
	}

	@Override
	public boolean deleteOne(int idProyecto) {
		try {
			if (findOne(idProyecto) != null) {
				proyectoRepository.deleteById(idProyecto);
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Empleado findDirector(int idProyecto) {
		return proyectoRepository.findDirectorDeProyecto(idProyecto);
	}

}
