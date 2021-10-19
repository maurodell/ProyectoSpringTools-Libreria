package com.egg.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreria.entidades.Editorial;

@Repository
public interface RepositorioEditorial extends JpaRepository<Editorial, String>{
	
	@Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
	public RepositorioEditorial buscarEditorialPorNombre(@Param("nombre") String Nombre);
}
