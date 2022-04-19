package com.vtv.vtv.entidades;

import javax.persistence.*;

@Entity
@Table(name = "modelos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_modelo" }) })
public class Modelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modelo")
	private int idModelo;
	
	@Column(name = "nombre_modelo")
	private String nombreModelo;
	
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;
	
	public Modelo() { }
	
	public Modelo(int idModelo, String nombreModelo, Marca marca) {
		this.idModelo = idModelo;
		this.nombreModelo = nombreModelo;
		this.marca = marca;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
