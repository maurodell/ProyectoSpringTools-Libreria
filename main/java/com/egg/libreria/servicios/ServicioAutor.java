package com.egg.libreria.servicios;

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
	public void agregarAutor(String nombre) throws ErrorServicio{
		try {
			if(nombre!=null) {
				Autor _autor = new Autor();
				_autor.setNombre(nombre);
				_autor.setAlta(true);
			}else {
				throw new ErrorServicio("Completar el nombre del Autor");
			}
		} catch (Exception e) {
			throw new ErrorServicio("El autor no se pudo agregar");
		}
	}
}
