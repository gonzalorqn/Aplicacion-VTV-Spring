package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.InspectorDao;
import com.vtv.vtv.entidades.Inspector;

@Service
public class InspectorService {
	
	@Autowired
	private InspectorDao inspectorDao;
	
	@Transactional(readOnly = true)
	public List<Inspector> listarInspectores(){
		return (List<Inspector>) inspectorDao.findAll();
	}
	
	@Transactional
	public void guardar(Inspector inspector) {
		inspectorDao.save(inspector);
	}
	
	@Transactional
	public void eliminar(Inspector inspector) {
		inspectorDao.delete(inspector);
	}
	
	@Transactional(readOnly = true)
	public Inspector encontrarInspector(Inspector inspector) {
		return inspectorDao.findById(inspector.getDni()).orElse(null);
	}
}
