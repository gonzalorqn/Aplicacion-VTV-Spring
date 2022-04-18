package com.vtv.vtv.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtv.vtv.dao.PersonaDao;
import com.vtv.vtv.entidades.Persona;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaDao personaDao;

	@Transactional(readOnly = true)
	public List<Persona> listarPersonas(){
		return (List<Persona>) personaDao.findAll();
	}
	
	@Transactional
	public void guardar(Persona persona) {
		personaDao.save(persona);
	}
	
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}
	
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		return personaDao.findById(persona.getDni()).orElse(null);
	}
}
