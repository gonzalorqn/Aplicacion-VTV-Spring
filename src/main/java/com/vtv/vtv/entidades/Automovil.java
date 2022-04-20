package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "automoviles")
@PrimaryKeyJoinColumn(referencedColumnName = "dominio")
public class Automovil extends Vehiculo {
	
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private Tipo tipo;
	
	public Automovil() { }
	
	public Automovil(String dominio, String dniPropietario, String estadoInspeccion, Tipo tipo) {
		super(dominio, dniPropietario, estadoInspeccion);
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
