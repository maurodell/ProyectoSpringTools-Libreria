package com.egg.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.libreria.entidades.Autor;
import com.egg.libreria.entidades.Editorial;
import com.egg.libreria.entidades.Libro;
import com.egg.libreria.lenguaje.Lenguaje;
import com.egg.libreria.servicios.ServicioLibro;

@Controller
@RequestMapping("/libro")//-->se aclara la url, es decir miweb.com/libro
public class LibroControlador {
	
	@Autowired
	private ServicioLibro libroServicio;
	
	@GetMapping("/lista")//-->llamado a una petición, estamos trayendo algo.
	public String lista(ModelMap modelo) {
		List<Libro> listarTodo = libroServicio.listarTodos();
		modelo.addAttribute("libros", listarTodo);
		
		return "list-book";//-->retorna la vista, es decir el archivo html
	}
	
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, String id, @RequestParam Long isbn, @RequestParam String titulo, Integer anio, 
			Integer ejemplares, Integer ejemplaresPrestados, Boolean alta, Autor autor, Editorial editorial, Lenguaje lenguaje){
		
		try {
			libroServicio.registrar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, alta, autor, editorial, lenguaje);
			modelo.put("exito", "Registro Exitoso");
		} catch (Exception e) {
			modelo.put("error", "Hubo un error");
		}
		return "form-libros";
	}
	
}
