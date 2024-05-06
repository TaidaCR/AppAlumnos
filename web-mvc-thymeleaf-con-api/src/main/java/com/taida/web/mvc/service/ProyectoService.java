package com.taida.web.mvc.service;

import java.util.List;

import com.taida.web.mvc.entity.Proyecto;

public interface ProyectoService {

	//CREAR
	public Proyecto add(Proyecto proyecto);
	
	//LEER
	public List<Proyecto> mostrarTodos();
	
	//ELIMINAR
	public Proyecto eliminar(Long id);
	
	//LEER
	public Proyecto buscarPorId(Long id);
	
	public Boolean activo(Long id);
}
