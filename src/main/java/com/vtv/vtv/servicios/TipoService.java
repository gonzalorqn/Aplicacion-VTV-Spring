package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.TipoDao;
import com.vtv.vtv.entidades.Tipo;

@Service
public class TipoService {

	@Autowired
	private TipoDao tipoDao;

	@Transactional(readOnly = true)
	public List<Tipo> listarTipos(){
		return (List<Tipo>) tipoDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Tipo encontrarTipo(Tipo tipo) {
		return tipoDao.findById(tipo.getIdTipo()).orElse(null);
	}
}
