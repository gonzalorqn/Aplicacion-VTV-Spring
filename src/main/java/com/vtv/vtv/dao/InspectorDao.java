package com.vtv.vtv.dao;

import org.springframework.data.repository.CrudRepository;

import com.vtv.vtv.entidades.Inspector;

public interface InspectorDao extends CrudRepository<Inspector, String> {
	/*@Query("SELECT p FROM personas p WHERE p.dni = (:dni)")
	public abstract Inspector findByDni(@Param("dni") String dni);*/

}
