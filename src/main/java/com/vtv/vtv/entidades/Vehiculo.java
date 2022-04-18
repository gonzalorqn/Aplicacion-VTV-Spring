package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vehiculos", uniqueConstraints = { @UniqueConstraint(columnNames = { "dominio" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehiculo {
	
	@Id
	private String dominio;
	
	@Column(name = "marca")
	@NotEmpty(message="Ingrese la marca por favor.")
	private String marca;
	
	@Column(name = "modelo")
	@NotEmpty(message="Ingrese el modelo por favor.")
	private String modelo;
	
	@Column(name = "dni_propietario")
    private String dniPropietario;
	
	@Column(name = "estado_inspeccion")
	private String estadoInspeccion;
	
	public Vehiculo() { }
	
	public Vehiculo(String dominio, String marca, String modelo, String dniPropietario, String estadoInspeccion) {
		super();
		this.dominio = dominio;
		this.marca = marca;
		this.modelo = modelo;
		this.dniPropietario = dniPropietario;
		this.estadoInspeccion = estadoInspeccion;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDniPropietario() {
		return dniPropietario;
	}

	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}

	public String getEstadoInspeccion() {
		return estadoInspeccion;
	}

	public void setEstadoInspeccion(String estadoInspeccion) {
		this.estadoInspeccion = estadoInspeccion;
	}
}
