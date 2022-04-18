package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "automoviles")
@PrimaryKeyJoinColumn(referencedColumnName = "dominio")
public class Automovil extends Vehiculo {
	
	@Column(name = "tipo")
	@NotEmpty(message="Ingrese el tipo por favor.")
	private String tipo;
	
	public Automovil() { }
	
	public Automovil(String dominio, String marca, String modelo, String dniPropietario, String estadoInspeccion, String tipo) {
		super(dominio, marca, modelo, dniPropietario, estadoInspeccion);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
