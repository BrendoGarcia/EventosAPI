package com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.modells.Convidados;
import com.eventoapp.modells.Eventos;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventosRepository;

import jakarta.validation.Valid;

@Controller
public class EventosController {
		
		@Autowired
		private EventosRepository er;
		
		@Autowired
		private ConvidadoRepository cr;

	@RequestMapping(value="/cadastrareventos", method=RequestMethod.GET)
	public String form() {
		return "eventos/FormEvento";
	}
	
	
	@RequestMapping(value="/cadastrareventos", method=RequestMethod.POST)
	public String form(@Valid Eventos eventos, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("Mensagem", "Erro Verifique os Campos digitados!");
			return "redirect:/cadastrareventos";
		}
		er.save(eventos);
		attributes.addFlashAttribute("Mensagem", "Evento Adicionado com Sucesso!");
		
		return "redirect:/cadastrareventos";
	}
	
	@RequestMapping("/")
	public String pag(){
		return "eventos/index";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("eventos/index");
		Iterable<Eventos> eventos = er.findAll();
		mv.addObject("Eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}" , method=RequestMethod.GET)
	public ModelAndView detalhesdoEvento(@PathVariable ("codigo") long codigo) {
		Eventos eventos = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("eventos/detalhesdoEvento");
		mv.addObject("Eventos", eventos);
		Iterable<Convidados> convidados = cr.findByEventos(eventos);
		mv.addObject("Convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}" , method=RequestMethod.POST)
	public String detalhesdoEventoPost(@PathVariable ("codigo") long codigo, @Valid Convidados convidados, BindingResult result, RedirectAttributes attributes ) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("Mensagem", "Erro Verifique os Campos digitados!");
			return "redirect:/{codigo}";
		}
		Eventos eventos = er.findByCodigo(codigo);
		convidados.setEventos(eventos);
		cr.save(convidados);
		attributes.addFlashAttribute("Mensagem", "Adicionado com Sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarevento")
	public String deletarevento(Long codigo) {
	    Eventos eventos = er.findByCodigo(codigo);
	    er.delete(eventos);
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deletarconvidados")
	public String deletarconvidados(String rg) {
		Convidados convidados = cr.findByRg(rg);
		cr.delete(convidados);
		Eventos eventos = convidados.getEventos();
		long codigolong = eventos.getCodigo();
		String codigo = "" + codigolong;
		return "redirect:/"+codigo;
	}
	
	
}
