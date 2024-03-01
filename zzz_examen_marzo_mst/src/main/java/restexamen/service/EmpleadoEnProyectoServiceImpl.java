package restexamen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restexamen.modelo.entities.Empleado;
import restexamen.modelo.entities.EmpleadoEnProyecto;
import restexamen.repository.EmpleadoEnProyectoRepository;

@Service
public class EmpleadoEnProyectoServiceImpl implements EmpleadoEnProyectoService {

	@Autowired
	private EmpleadoEnProyectoRepository empleadoEnProyectoRepository;
	
	// CREATE
	
	@Override
	public EmpleadoEnProyecto insertOne(EmpleadoEnProyecto empleadoEnProyecto) {
		
		return empleadoEnProyectoRepository.save(empleadoEnProyecto);
	}

	// READ
	
	@Override
	public EmpleadoEnProyecto findOne(int idEntrada) {
		
		return empleadoEnProyectoRepository.findById(idEntrada).orElse(null);
	}

	@Override
	public List<EmpleadoEnProyecto> findAll() {
		
		return empleadoEnProyectoRepository.findAll();
	}
	
	@Override
	public List<Empleado> findEmpleados(int idProyecto) {
		
		return empleadoEnProyectoRepository.findEmpleadosEnProyecto(idProyecto);
		
	}

	// UPDATE
	
	@Override
	public EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto) {
	
		try {
			
			if (findOne(empleadoEnProyecto.getIdEntrada()) != null) {	
				return empleadoEnProyectoRepository.save(empleadoEnProyecto);
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
	public boolean deleteOne(int idEntrada) {
		
		try {
			
			if (findOne(idEntrada) != null) {
			empleadoEnProyectoRepository.deleteById(idEntrada);
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
	public EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto) {
	
		try {
			
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
			
		} catch (Exception e) {
			
			return null;
		}
	}
	*/
	
	/*
	@Override
	public EmpleadoEnProyecto updateOne(EmpleadoEnProyecto empleadoEnProyecto) {
		
		if (findOne(empleadoEnProyecto.getIdEntrada()) != null) {
			
			return empleadoEnProyectoRepository.save(empleadoEnProyecto);
		}
		else {
			
			return null;
		}
	}
	*/

	/*
	@Override
	public boolean deleteOne(int idEntrada) {
		
		try {
			empleadoEnProyectoRepository.deleteById(idEntrada);
			
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}
	*/

}
