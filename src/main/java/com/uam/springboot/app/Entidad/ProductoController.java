package com.uam.springboot.app.Entidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;

	@PostMapping("/productos")
	public void agregarProducto(@RequestBody final List<Producto> produtos){
		productoRepositorio.saveAll(produtos);
	}
	
	@GetMapping("/productos")
	public List<Producto> findProductos(){
		return productoRepositorio.findAll();
			
	}
	
	
	@GetMapping("/productos/{productoId}")
	public Producto findProducto(@PathVariable final String productoId) {
		return productoRepositorio.findById(productoId).orElseGet(Producto::new);
	}
}
