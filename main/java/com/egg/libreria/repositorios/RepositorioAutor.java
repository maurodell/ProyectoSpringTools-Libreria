package com.egg.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreria.entidades.Autor;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor, String>{
	
	@Query("SELECT c FROM Autor c Where c.id = :id")
	public Autor buscarAutor(@Param("id") String id);
}
