package examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.modelo.entities.EmpleadoEnProyecto;
import examen.repositories.EmpleadoEnProyectoRepository;

@Service
public class EmpleadoEnProyectoServiceImpl implements EmpleadoEnProyectoService {
	
	@Autowired
	private EmpleadoEnProyectoRepository empleadoEnProyectoRepository;

	@Override
	public EmpleadoEnProyecto createOne(EmpleadoEnProyecto empleadoEnProyecto) {
		return empleadoEnProyectoRepository.save(empleadoEnProyecto);
	}

	@Override
	public EmpleadoEnProyecto findOne(int idEntrada) {
		return empleadoEnProyectoRepository.findById(idEntrada).orElse(null);
	}

	@Override
	public List<EmpleadoEnProyecto> findAll() {
		return empleadoEnProyectoRepository.findAll();
	}

	@Override
	public EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto) {
		try {
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteOne(int idEntrada) {
		try {
			if (findOne(idEntrada) != null) {
				empleadoEnProyectoRepository.deleteById(idEntrada);
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

}
