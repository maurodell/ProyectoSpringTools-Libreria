package com.egg.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreria.entidades.Autor;
import com.egg.libreria.entidades.Editorial;
import com.egg.libreria.entidades.Libro;
import com.egg.libreria.exception.ErrorServicio;
import com.egg.libreria.lenguaje.Lenguaje;
import com.egg.libreria.repositorios.LibroRepositorio;

@Service
public class ServicioLibro {
	
	//el autowired inicializa la interface LibroRepositorio
	@Autowired
	private LibroRepositorio repositorioLibro;
	
	//guardar un libro-->dar alta
	@Transactional//-->inicializa un commit con la base de datos
	public void registrar(Long isbn, String titulo, Integer anio, Integer ejemplares, 
			Integer ejemplaresPrestados, Boolean alta, Autor autor, Editorial editorial, Lenguaje lenguaje) throws ErrorServicio{
		
		Libro entidad = new Libro();
		
			if(isbn == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(titulo == null || titulo.isEmpty()) {
				throw new ErrorServicio("Completar campo");
			}
			if(anio == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(ejemplares == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(ejemplaresPrestados == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(autor == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(editorial == null) {
				throw new ErrorServicio("Completar campo");
			}
			if(lenguaje == null) {
				throw new ErrorServicio("Completar campo");
			}
			
			entidad.setIsbn(isbn);
			entidad.setTitulo(titulo);
			entidad.setAnio(anio);
			entidad.setEjemplares(ejemplares);
			entidad.setEjemplaresPrestados(ejemplaresPrestados);
			entidad.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
			entidad.setAlta(true);
			entidad.setAutor(autor);
			entidad.setEditorial(editorial);
			entidad.setLenguaje(lenguaje);
			
			repositorioLibro.save(entidad);

	}
	@Transactional
	public void modificarLibro(String id, String titulo, Integer anio, Integer ejemplares) throws ErrorServicio{
		validar(titulo, anio, ejemplares);
		Optional<Libro> respuesta = repositorioLibro.findById(id);
		
			if(respuesta.isPresent()) {
				Libro libro = respuesta.get();
				libro.setTitulo(titulo);
				libro.setAnio(anio);
				libro.setEjemplares(ejemplares);
		
				repositorioLibro.save(libro);
			}else {
				throw new ErrorServicio("No se encontró el usuario solicitado");
			}

	}
	@Transactional
	public void deshabilitar(String id) throws ErrorServicio {
		Optional<Libro> respuesta = repositorioLibro.findById(id);
		if(respuesta.isPresent()) {
			Libro libro = respuesta.get();
			libro.setAlta(false);
			repositorioLibro.save(libro);
		}else {
			throw new ErrorServicio("Id no encontradó");
		}
	}
	@Transactional
	public void habilitar(String id) throws ErrorServicio {
		Optional<Libro> respuesta = repositorioLibro.findById(id);
		if(respuesta.isPresent()) {
			Libro libro = respuesta.get();
			libro.setAlta(true);
			repositorioLibro.save(libro);
		}else {
			throw new ErrorServicio("Id no encontradó");
		}
	}
	
	//este metodo valida los campos
	public void validar(String titulo, Integer anio, Integer ejemplares) throws ErrorServicio {
		if(titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("Completar campo");
		}
		if(anio == null) {
			throw new ErrorServicio("Completar campo");
		}
		if(ejemplares == null) {
			throw new ErrorServicio("Completar campo");
		}
	}
	//no va a hacer una modificacion en nuestra base de datos por eso se aclara que la transacción va a ser solo de lectura
	@Transactional(readOnly = true)
	public List<Libro> listarActivos(){
		return repositorioLibro.buscarLibroActivo();
	}
	
	@Transactional(readOnly = true)
	public List<Libro> listarTodos(){
		return repositorioLibro.findAll();
	}
}
