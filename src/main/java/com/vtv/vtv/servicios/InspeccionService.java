package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.InspeccionDao;
import com.vtv.vtv.entidades.Inspeccion;


@Service
public class InspeccionService {

	@Autowired
	private InspeccionDao inspeccionDao;

	@Transactional(readOnly = true)
	public List<Inspeccion> listarInspecciones(){
		return (List<Inspeccion>) inspeccionDao.findAll();
	}
	
	@Transactional
	public void guardar(Inspeccion inspeccion) {
		inspeccionDao.save(inspeccion);
	}
	
	@Transactional
	public void eliminar(Inspeccion inspeccion) {
		inspeccionDao.delete(inspeccion);
	}
	
	@Transactional(readOnly = true)
	public Inspeccion encontrarInspeccion(int numero) {
		return inspeccionDao.findById(numero).orElse(null);
	}
}
