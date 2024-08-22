package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.modells.Eventos;

public interface EventosRepository extends CrudRepository<Eventos, Long> {
	Eventos findByCodigo(long codigo);

}
