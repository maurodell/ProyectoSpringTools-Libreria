package com.egg.libreria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreria.entidades.Editorial;
import com.egg.libreria.exception.ErrorServicio;
import com.egg.libreria.repositorios.RepositorioEditorial;

@Service
public class ServicioEditorial {
	Editorial editorial;
	
	@Autowired
	private RepositorioEditorial editorialRepositorio;
	
	@Transactional
	public void agregarEditorial(String nombre) throws ErrorServicio{

		validarNombre(nombre);
		
		editorial = new Editorial();
		editorial.setNombre(nombre);
		editorial.setAlta(true);
		
		editorialRepositorio.save(editorial);

	}
	
	//valida los datos ingresador
	public void validar(String nombre, Boolean alta) throws ErrorServicio{
		if(nombre==null || nombre.isEmpty()) {
			throw new ErrorServicio("Nombre vacío.");
		}
		if(alta == false) {
			throw new ErrorServicio("La editorial no esta dada de alta.");
		}
	}
	public void validarNombre(String nombre) throws ErrorServicio{
		if(nombre==null || nombre.isEmpty()) {
			throw new ErrorServicio("Nombre vacío.");
		}
	}
	@Transactional
	public void ModificarEditorial(String id, String nombre) throws ErrorServicio {
		validarNombre(nombre);
		
		Optional<Editorial> respuesta = editorialRepositorio.findById(id);
		if(respuesta.isPresent()) {
			editorial = respuesta.get();
			editorial.setNombre(nombre);
			
			editorialRepositorio.save(editorial);
		}else {
			throw new ErrorServicio("El editor no existe.");
		}
	}
	@Transactional
	public void DesabilitarEditorial(String id) throws ErrorServicio {
		Optional<Editorial> respuesta = editorialRepositorio.findById(id);
		if(respuesta.isPresent()) {
			editorial = respuesta.get();
			editorial.setAlta(false);
			
			editorialRepositorio.save(editorial);
		}else {
			throw new ErrorServicio("Error");
		}
	}
	@Transactional
	public void HabilitarEditorial(String id) throws ErrorServicio {
		Optional<Editorial> respuesta = editorialRepositorio.findById(id);
		if(respuesta.isPresent()) {
			editorial = respuesta.get();
			editorial.setAlta(true);
			
			editorialRepositorio.save(editorial);
		}else {
			throw new ErrorServicio("Error");
		}
	}
}
