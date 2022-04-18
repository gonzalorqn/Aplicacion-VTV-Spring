package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "personas", uniqueConstraints = { @UniqueConstraint(columnNames = { "dni" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {

	@Column(name = "nombre")
	@NotEmpty(message="Ingrese el nombre por favor.")
	private String nombre;
	
	@Column(name = "apellido")
	@NotEmpty(message = "Ingrese el apellido por favor.")
	private String apellido;
	
	@Id
	private String dni;
	
	public Persona() { }
	
	public Persona(String nombre, String apellido, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
