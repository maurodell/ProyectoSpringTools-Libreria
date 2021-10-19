package com.egg.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreria.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
	
	@Query("SELECT c FROM Libro c WHERE c.libro.id :id")
	public List<Libro> buscarLibroPorId(@Param("id") String id);
	
	@Query("SELECT a FROM Libro a WHERE a.alta = true")
	public List<Libro> buscarLibroActivo();
}
