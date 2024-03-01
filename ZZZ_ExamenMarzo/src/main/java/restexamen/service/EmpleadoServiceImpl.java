package restexamen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restexamen.modelo.entities.Empleado;
import restexamen.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	// CREATE
	
	@Override
	public Empleado insertOne(Empleado empleado) {
		
		return empleadoRepository.save(empleado);
	}

	// READ
	
	@Override
	public Empleado findOne(int idEmpleado) {
		
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}

	@Override
	public List<Empleado> findAll() {
		
		return empleadoRepository.findAll();
	}

	// UPDATE
	
	@Override
	public Empleado updateOne(Empleado empleado) {
		
		try {
			
			if (findOne(empleado.getIdEmpleado()) != null) {
				return empleadoRepository.save(empleado);
			}
			else {
				return null;
			}
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	// DELETE
	
	@Override
	public boolean deleteOne(int idEmpleado) {
		
		try {
			
			if (findOne(idEmpleado) != null) {
			empleadoRepository.deleteById(idEmpleado);
			return true;
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			
			return false;
		}
	}
	
	/*
	@Override
	public Empleado updateOne(Empleado empleado) {
		
		try {
			
			return empleadoRepository.save(empleado);
			
		} catch (Exception e) {
			
			return null;
		}
	}
	*/
	
	/*
	@Override
	public Empleado updateOne(Empleado empleado) {
		
		if (findOne(empleado.getIdEmpleado()) != null) {
			
			return empleadoRepository.save(empleado);
		}
		else {
			
			return null;
		}
	}
	*/

	/*
	@Override
	public boolean deleteOne(int idEmpleado) {
		
		try {
			empleadoRepository.deleteById(idEmpleado);
			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	*/
	
}
