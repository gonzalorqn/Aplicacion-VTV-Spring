package com.vtv.vtv.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tipos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_tipo" }) })
@PrimaryKeyJoinColumn(referencedColumnName = "id_marca")
public class Tipo extends Modelo {
	
	@Column(name = "id_tipo")
	private int idTipo;
	
	@Column(name = "nombre_tipo")
	private String nombreTipo;
	
	public Tipo() { }
	
	public Tipo(int idMarca, String nombreMarca, int idModelo, String nombreModelo, int idTipo, String nombreTipo) {
		super(idMarca, nombreMarca, idModelo, nombreModelo);
		this.idTipo = idTipo;
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
}
