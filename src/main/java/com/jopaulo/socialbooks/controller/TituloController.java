package com.jopaulo.socialbooks.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jopaulo.socialbooks.model.StatusTitulo;
import com.jopaulo.socialbooks.model.Titulo;
import com.jopaulo.socialbooks.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos; //vem do repository
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView("CadastroTitulo");
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView view = new ModelAndView("CadastroTitulo");
		view.addObject("mensagem", "...");
		return view;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}