package com.vtv.vtv.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tipos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_tipo" }) })
public class Tipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo")
	private int idTipo;
	
	@Column(name = "nombre_tipo")
	private String nombreTipo;
	
	@ManyToOne
	@JoinColumn(name="id_modelo")
	private Modelo modelo;
	
	public Tipo() { }
	
	public Tipo(int idTipo, String nombreTipo, Modelo modelo) {
		this.idTipo = idTipo;
		this.nombreTipo = nombreTipo;
		this.modelo = modelo;
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

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
