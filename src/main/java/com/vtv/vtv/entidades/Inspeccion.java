package com.vtv.vtv.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "inspecciones", uniqueConstraints = { @UniqueConstraint(columnNames = { "numero", "dominio" }) })
public class Inspeccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	
	@Column(name = "fecha")
	@NotEmpty(message="Ingrese la fecha por favor.")
    private String fecha;
	
	@Column(name = "estado")
    private String estado;
	
	@Column(name = "dni_inspector")
    private String dniInspector;
	
	@Column(name = "dominio")
    private String dominio;

    public Inspeccion() { }

    public Inspeccion(int numero, String estado, String dniInspector, String dominio, String fecha){
    	super();
        this.numero = numero;
        this.estado = estado;
        this.dniInspector = dniInspector;
        this.dominio = dominio;
        this.fecha = fecha;
    }

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDniInspector() {
		return dniInspector;
	}

	public void setDniInspector(String dniInspector) {
		this.dniInspector = dniInspector;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
}
