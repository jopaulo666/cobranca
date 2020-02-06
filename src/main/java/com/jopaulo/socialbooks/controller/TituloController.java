package com.jopaulo.socialbooks.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jopaulo.socialbooks.model.StatusTitulo;
import com.jopaulo.socialbooks.model.Titulo;
import com.jopaulo.socialbooks.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos; //vem do repository
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		view.addObject(new Titulo());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "...");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView view = new ModelAndView("PesquisaTitulos");
		view.addObject("titulos", todosTitulos);
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Titulo titulo) {
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		view.addObject(titulo);
		return view;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}