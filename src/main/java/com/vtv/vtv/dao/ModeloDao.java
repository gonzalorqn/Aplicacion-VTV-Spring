package com.vtv.vtv.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vtv.vtv.entidades.Marca;
import com.vtv.vtv.entidades.Modelo;

public interface ModeloDao extends CrudRepository<Modelo, Integer> {
	
	List<Modelo> findByMarca(Marca marca);
}
