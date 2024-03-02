package examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.modelo.entities.Empleado;
import examen.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public Empleado createOne(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado findOne(int idEmpleado) {
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado updateOne(Empleado empleado) {
		try {
			return empleadoRepository.save(empleado);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteOne(int idEmpleado) {
		try {
			if (findOne(idEmpleado) != null) {
				empleadoRepository.deleteById(idEmpleado);
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

}
