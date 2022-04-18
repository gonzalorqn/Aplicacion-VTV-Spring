package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.ModeloDao;
import com.vtv.vtv.entidades.Modelo;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloDao modeloDao;

	@Transactional(readOnly = true)
	public List<Modelo> listarModelos(){
		return (List<Modelo>) modeloDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Modelo encontrarModelo(Modelo modelo) {
		return modeloDao.findById(modelo.getIdModelo()).orElse(null);
	}
}
