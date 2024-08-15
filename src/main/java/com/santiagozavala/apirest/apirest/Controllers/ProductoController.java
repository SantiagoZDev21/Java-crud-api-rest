package com.santiagozavala.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.santiagozavala.apirest.apirest.Repositories.ProductoReposiroty;
import com.santiagozavala.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoReposiroty productoReposiroty;

    @GetMapping 
    public List<Producto> obtenerProductos(){
        return productoReposiroty.findAll();
    }

    @GetMapping("/(id)")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoReposiroty.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: "+id));
    }

    @PostMapping 
    public Producto crearProducto(@RequestBody Producto producto){
        return productoReposiroty.save(producto);
    }

    @PutMapping("/(id)")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoReposiroty.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el id: "+id));
        
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoReposiroty.save(producto);
    }

    @DeleteMapping("/(id)")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoReposiroty.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el id: "+id));

        productoReposiroty.delete(producto);
        return "El producto con el ID: "+id+" ha sido eliminado correctamente";
    }


}
