package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.VehiculoDao;
import com.vtv.vtv.entidades.Vehiculo;

@Service
public class VehiculoService {
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Transactional(readOnly = true)
	public List<Vehiculo> listarVehiculos(){
		return (List<Vehiculo>) vehiculoDao.findAll();
	}
	
	@Transactional
	public void guardar(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);
	}
	
	@Transactional
	public void eliminar(Vehiculo vehiculo) {
		vehiculoDao.delete(vehiculo);
	}
	
	@Transactional(readOnly = true)
	public Vehiculo encontrarVehiculo(Vehiculo vehiculo) {
		return vehiculoDao.findById(vehiculo.getDominio()).orElse(null);
	}
}
