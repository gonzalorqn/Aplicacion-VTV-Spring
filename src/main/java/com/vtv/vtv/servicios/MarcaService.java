package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.MarcaDao;
import com.vtv.vtv.entidades.Marca;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaDao marcaDao;

	@Transactional(readOnly = true)
	public List<Marca> listarMarcas(){
		return (List<Marca>) marcaDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Marca encontrarMarca(Marca marca) {
		return marcaDao.findById(marca.getIdMarca()).orElse(null);
	}
}
