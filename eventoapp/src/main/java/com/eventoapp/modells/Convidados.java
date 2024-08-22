package com.eventoapp.modells;



import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Convidados {
	
	@Id
	@NotEmpty
	private String rg;
	@NotEmpty
	private String NomedoComvidado;
	
	@ManyToOne
	private Eventos eventos;
	
	public Eventos getEventos() {
		return eventos;
	}
	public void setEventos(Eventos eventos) {
		this.eventos = eventos;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomedoComvidado() {
		return NomedoComvidado;
	}
	public void setNomedoComvidado(String nomedoComvidado) {
		NomedoComvidado = nomedoComvidado;
	}
	
	

}
