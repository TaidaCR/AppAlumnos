package com.taida.web.mvc.controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taida.web.mvc.entity.Proyecto;
import com.taida.web.mvc.service.ProyectoService;

@RestController
@RequestMapping("/apiProyecto")
@CrossOrigin(origins = "**")
public class ApiRestController {
	
	@Autowired
	private ProyectoService service;
	
	@GetMapping("/listaProyectos")
	public List<Proyecto> listadoProyectos(){
		return service.mostrarTodos();
	}
	
	@GetMapping({"/proyectos"})
	public String listaProy(Model modelo) {
		modelo.addAttribute("proy", service.mostrarTodos());
		return "proyectos";
	}
	
	@GetMapping("/proyectos/{id}")
	public Proyecto mostrarPorId(Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping("/proyect")
	public Proyecto crearNuevo(Proyecto proyecto) {
		return service.add(proyecto);
	}
	
	@PutMapping("/proyectos/{id}")
	public Proyecto modificar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
		Proyecto proyectoAModificar = service.buscarPorId(id);
		proyectoAModificar.setNombre(proyecto.getNombre());
		proyectoAModificar.setDescripcion(proyecto.getDescripcion());
		proyectoAModificar.setFecha_inicio(proyecto.getFecha_inicio());
		proyectoAModificar.setFecha_fin(proyecto.getFecha_fin());
		proyectoAModificar.setActivo(proyecto.getActivo());
		
		return service.add(proyectoAModificar);
	}
	
	@DeleteMapping("/proyectos/{id}")
	public Proyecto delete(@PathVariable Long id) {
		return service.eliminar(id);
	}
}
