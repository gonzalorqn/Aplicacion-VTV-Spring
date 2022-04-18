package com.vtv.vtv.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inspectores")
@PrimaryKeyJoinColumn(referencedColumnName = "dni")
public class Inspector extends Persona {
	
	@Column(name = "numero")
	@NotNull(message="Ingrese el número por favor.")
	private int numero;
	
	@Column(name = "salario")
	@NotNull(message="Ingrese el salario por favor.")
	private int salario;
	
	public Inspector() {}
	
	public Inspector(String nombre, String apellido, String dni, int numero, int salario) {
		super(nombre, apellido, dni);
		this.numero = numero;
		this.salario = salario;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}	
}
