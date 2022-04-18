package com.vtv.vtv.dao;

import com.vtv.vtv.entidades.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, String>{
	
}
