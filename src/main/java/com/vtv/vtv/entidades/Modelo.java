package com.vtv.vtv.entidades;

import javax.persistence.*;

@Entity
@Table(name = "modelos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_modelo" }) })
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(referencedColumnName = "id_marca")
public abstract class Modelo extends Marca {
	
	@Column(name = "id_modelo")
	private int idModelo;
	
	@Column(name = "nombre_modelo")
	private String nombreModelo;
	
	public Modelo() { }
	
	public Modelo(int idMarca, String nombreMarca, int idModelo, String nombreModelo) {
		super(idMarca, nombreMarca);
		this.idModelo = idModelo;
		this.nombreModelo = nombreModelo;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getNombreModelo() {
		return nombreModelo;
	}

	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}
}
