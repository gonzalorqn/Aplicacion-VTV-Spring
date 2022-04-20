package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vehiculos", uniqueConstraints = { @UniqueConstraint(columnNames = { "dominio" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehiculo {
	
	@Id
	private String dominio;
	
	@Column(name = "dni_propietario")
    private String dniPropietario;
	
	@Column(name = "estado_inspeccion")
	private String estadoInspeccion;
	
	public Vehiculo() { }
	
	public Vehiculo(String dominio, String dniPropietario, String estadoInspeccion) {
		super();
		this.dominio = dominio;
		this.dniPropietario = dniPropietario;
		this.estadoInspeccion = estadoInspeccion;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
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
