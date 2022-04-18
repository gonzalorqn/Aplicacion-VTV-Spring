package com.vtv.vtv.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vtv.vtv.entidades.Propietario;

public interface PropietarioDao extends CrudRepository<Propietario, String> {
	/*@Query("SELECT p FROM personas p WHERE p.dni = (:dni)")
	public abstract Propietario findByDni(@Param("dni") String dni);*/

}
