package com.vtv.vtv.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "propietarios")
@PrimaryKeyJoinColumn(referencedColumnName = "dni")
public class Propietario extends Persona{
	
	@Column(name = "exento")
	private boolean exento;
	
	//private List<Automovil> autos;
	
	public Propietario() { }

	public Propietario(String nombre, String apellido, String dni, boolean exento) {
		super(nombre, apellido, dni);
		this.exento = exento;
		//this.autos = new ArrayList<Automovil>();
	}
	
	public void setExento(boolean exento) {
		this.exento = exento;
	}

	public boolean getExento() {
		return exento;
	}
	
	/*public List<Automovil> getAutos() {
		return autos;
	}*/
}
