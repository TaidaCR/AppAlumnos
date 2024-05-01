package com.taida.web.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taida.web.mvc.dao.AlumnoRepository;
import com.taida.web.mvc.entity.Alumno;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	public List<Alumno> listadoAlumnos() {
		return alumnoRepository.findAll();
	}

	@Override
	public Alumno registro(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@Override
	public void elimino(Long id) {
		alumnoRepository.deleteById(id);
	}

	@Override
	public Alumno visualizarPorId(Long id) {
		return alumnoRepository.findById(id).get();
	}

}
