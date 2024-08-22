package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.modells.Convidados;
import com.eventoapp.modells.Eventos;

public interface ConvidadoRepository extends CrudRepository<Convidados, String> {
	Iterable<Convidados> findByEventos(Eventos eventos);
	Convidados findByRg(String rg);
	
}
