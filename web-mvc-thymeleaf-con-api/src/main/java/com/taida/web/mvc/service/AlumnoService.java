package com.taida.web.mvc.service;

import java.util.List;

import com.taida.web.mvc.entity.Alumno;

public interface AlumnoService {
	//LA VOY A USAR??
	public List<Alumno> listadoAlumnos();
	
	public Alumno registro(Alumno alumno);
	
	public void elimino(Long id);
	
	public Alumno visualizarPorId(Long id);
}
