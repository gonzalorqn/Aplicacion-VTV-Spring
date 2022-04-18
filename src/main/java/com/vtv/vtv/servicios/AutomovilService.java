package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.AutomovilDao;
import com.vtv.vtv.entidades.Automovil;


@Service
public class AutomovilService {
	
	@Autowired
	private AutomovilDao automovilDao;

	@Transactional(readOnly = true)
	public List<Automovil> listarAutomoviles(){
		return (List<Automovil>) automovilDao.findAll();
	}
	
	@Transactional
	public void guardar(Automovil automovil) {
		automovilDao.save(automovil);
	}
	
	@Transactional
	public void eliminar(Automovil automovil) {
		automovilDao.delete(automovil);
	}
	
	@Transactional(readOnly = true)
	public Automovil encontrarAutomovil(Automovil automovil) {
		return automovilDao.findById(automovil.getDominio()).orElse(null);
	}
}
