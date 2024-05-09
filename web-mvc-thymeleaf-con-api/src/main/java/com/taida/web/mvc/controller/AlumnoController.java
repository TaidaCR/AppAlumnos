package com.taida.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.taida.web.mvc.entity.Alumno;
import com.taida.web.mvc.service.AlumnoService;
import com.taida.web.mvc.service.ProyectoService;

@Controller
public class AlumnoController {
	
	@Autowired 
	private AlumnoService alumnoService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	
	@GetMapping({"/proyectos"})
	public String listaProy(Model modelo) {
		modelo.addAttribute("proy", proyectoService.mostrarTodos());
		return "proyectos";
	}
	
	@GetMapping({"/alumnos","/"})
	public String listarAlumnos(Model modelo) {
		modelo.addAttribute("estudiantes", alumnoService.listadoAlumnos());
		return "alumnos";
	}
	
	@GetMapping("/alumno/new")
	public String registrarAlumno(Model modelo) {
		Alumno alumno = new Alumno();
		modelo.addAttribute("alumno", alumno);
		return "crear_alumno";
	}
	
	@GetMapping("/alumno/delete/{id}")
	public String eliminarAlumno(@PathVariable(name="id") Long id) {
		alumnoService.elimino(id);
		return "redirect:/alumnos";
	}
	
	@GetMapping("/alumno/edit/{id}")
	public String formularioEditarAlumno(Model modelo, @PathVariable(name="id") Long id) {
		Alumno alumno = alumnoService.visualizarPorId(id);
        modelo.addAttribute("alumno", alumno);
        return "editar_alumno";
	}
	
	@PostMapping("/alumno/new")
	public String guardarAlumno(@ModelAttribute("alumno")Alumno alumno) {
		alumnoService.registro(alumno);
		return "redirect:/alumnos";
	}

	@PostMapping("/alumno/edit/{id}")
	public String modificarAlumno(@PathVariable(name="id") Long id, @ModelAttribute("alumno") Alumno alumno) {
		Alumno alumnoModif = alumnoService.visualizarPorId(id);
		
		alumnoModif.setNombre(alumno.getNombre());
		alumnoModif.setApellido(alumno.getApellido());
		alumnoModif.setTlf(alumno.getTlf());
		alumnoModif.setEmail(alumno.getEmail());
		alumnoModif.setDni(alumno.getDni());
		alumnoModif.setFecha_nacimiento(alumno.getFecha_nacimiento());
		
		alumnoService.registro(alumnoModif);
		return "redirect:/alumnos";
	}
}

