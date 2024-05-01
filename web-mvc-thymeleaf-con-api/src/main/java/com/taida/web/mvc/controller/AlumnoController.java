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

@Controller
public class AlumnoController {
	
	@Autowired 
	private AlumnoService alumnoService;
	
	@GetMapping({"/alumnos","/"})
	public String listarAlumnos(Model modelo) {
		modelo.addAttribute("estudiantes", alumnoService.listadoAlumnos());
		return "alumnos";
	}
	
	@PostMapping("/alumno/new")
	public String registrarAlumno(Model modelo) {
		Alumno alumno = new Alumno();
		modelo.addAttribute("alumn", alumno);
		return "crear_alumno";
	}
	
	@GetMapping("/alumno/delete/{id}")
	public String eliminarAlumno(@PathVariable Long id) {
		alumnoService.elimino(id);
		return "redirect:/alumnos";
	}
	
	@GetMapping("/alumno/show/{id}")
	public String mostrarAlumno(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("estudiante", alumnoService.visualizarPorId(id));
		return "visualizar_alumno";
	}
	
	@PostMapping("/alumno/edit/{id}")
	public String modificarAlumno(@PathVariable Long id, @ModelAttribute("alumn") Alumno alumno) {
		Alumno alumnoModif = alumnoService.visualizarPorId(id);
		
		alumnoModif.setNombre(alumno.getNombre());
		alumnoModif.setApellido(alumno.getApellido());
		alumnoModif.setTlf(alumno.getTlf());
		alumnoModif.setEmail(alumno.getEmail());
		alumnoModif.setDni(alumno.getDni());
		alumnoModif.setFecha_nacimiento(alumno.getFecha_nacimiento());
		
		alumnoService.registro(alumno);
		return "redirect:/alumnos";
	}
}

