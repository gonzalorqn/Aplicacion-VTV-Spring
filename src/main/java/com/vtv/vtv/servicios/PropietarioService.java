package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.PropietarioDao;
import com.vtv.vtv.entidades.Propietario;

@Service
public class PropietarioService {
	
	@Autowired
	private PropietarioDao propietarioDao;
	
	@Transactional(readOnly = true)
	public List<Propietario> listarPropietarios(){
		return (List<Propietario>) propietarioDao.findAll();
	}
	
	@Transactional
	public void guardar(Propietario propietario) {
		propietarioDao.save(propietario);
	}
	
	@Transactional
	public void eliminar(Propietario propietario) {
		propietarioDao.delete(propietario);
	}
	
	@Transactional(readOnly = true)
	public Propietario encontrarPropietario(String dni) {
		return propietarioDao.findById(dni).orElse(null);
	}
}
