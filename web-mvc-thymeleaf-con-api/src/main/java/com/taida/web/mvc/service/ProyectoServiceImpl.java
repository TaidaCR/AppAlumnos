package com.taida.web.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taida.web.mvc.dao.ProyectoDao;
import com.taida.web.mvc.entity.Proyecto;

@Service
public class ProyectoServiceImpl implements ProyectoService{

	@Autowired
	private ProyectoDao proyectoDao;
	
	@Override
	@Transactional
	public Proyecto add(Proyecto proyecto) {
		return proyectoDao.save(proyecto);
	}

	@Override
	public List<Proyecto> mostrarTodos() {
		return (List<Proyecto>) proyectoDao.findAll();
	}

	@Override
	public Proyecto eliminar(Long id) {
		Proyecto proyectoBorrado = proyectoDao.findById(id).orElse(null);
		proyectoDao.deleteById(id);
		return proyectoBorrado;
	}

	@Override
	@Transactional(readOnly = true)
	public Proyecto buscarPorId(Long id) {
		return proyectoDao.findById(id).orElse(null);
	}

	@Override
	public Boolean activo(Long id) {
		boolean sol = false;
		Optional <Proyecto> proyectoOptional = proyectoDao.findById(id);
		if (proyectoOptional.isPresent()) {
			sol=true;
		}
		return sol;
	}

	

}
