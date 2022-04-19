package com.vtv.vtv.entidades;

import javax.persistence.*;

@Entity
@Table(name = "marcas", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_marca" }) })
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marca")
	private int idMarca;
	
	@Column(name = "nombre_marca")
	private String nombreMarca;
	
	public Marca() { }
	
	public Marca(int idMarca, String nombreMarca) {
		super();
		this.idMarca = idMarca;
		this.nombreMarca = nombreMarca;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}
}
