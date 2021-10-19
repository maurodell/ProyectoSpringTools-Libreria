package com.egg.libreria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreria.entidades.Autor;
import com.egg.libreria.exception.ErrorServicio;
import com.egg.libreria.repositorios.RepositorioAutor;

@Service
public class ServicioAutor {
	@Autowired
	public RepositorioAutor repositorioAutor;
	
	@Transactional
	public void agregarAutor(String nombre) throws ErrorServicio {
		try {
			if(nombre!=null) {
				Autor _autor = new Autor();
				_autor.setNombre(nombre);
				_autor.setAlta(true);
				
				repositorioAutor.save(_autor);
			}else {
				throw new ErrorServicio("Completar el nombre del Autor");
			}
		} catch (Exception e) {
			throw new ErrorServicio("El autor no se pudo agregar");
		}
	}
	
	@Transactional
	public void deshabilitarAutor(String id) throws ErrorServicio {
		try {
			Optional<Autor> consultarId = repositorioAutor.findById(id);
			if(consultarId.isPresent()) {
				Autor _autor = consultarId.get();
				_autor.setAlta(false);
				repositorioAutor.save(_autor);
			}else {
				throw new ErrorServicio("No se puede deshabilitar");
			}
		} catch (Exception e) {
			throw new ErrorServicio(e.getMessage());
		}
	}
	
	@Transactional
	public void habilitarAutor(String id) throws ErrorServicio {
		try {
			Optional<Autor> consultarId = repositorioAutor.findById(id);
			if(consultarId.isPresent()) {
				Autor _autor = consultarId.get();
				_autor.setAlta(true);
				repositorioAutor.save(_autor);
			}else {
				throw new ErrorServicio("No se puede habilitar");
			}
		} catch (Exception e) {
			throw new ErrorServicio(e.getMessage());
		}
	}
	
	@Transactional
	public void modificarAutor(String id, String nombre) throws ErrorServicio{
		try {
			Optional<Autor> consultaId = repositorioAutor.findById(id);
			if(consultaId.isPresent()) {
				Autor _autor = consultaId.get();
				_autor.setNombre(nombre);
				repositorioAutor.save(_autor);
			}else {
				throw new ErrorServicio("Error en el ID");
			}
		} catch (Exception e) {
			throw new ErrorServicio(e.getMessage());
		}
	}
}
