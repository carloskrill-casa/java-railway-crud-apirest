package com.carlos.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.apirest.apirest.Entities.Producto;
import com.carlos.apirest.apirest.Repositories.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> ObtenerAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto productoId (@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró producto ID : " + id));
        
    }
    
    

    @PostMapping
    public Producto creaProducto(@RequestBody Producto producto ){
 
        return productoRepository.save(producto);

    }
    
    @PutMapping("/{id}")
     public Producto productoModifica (@PathVariable Long id, @RequestBody Producto productoDetalle){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró producto ID : " + id));

        producto.setNombre(productoDetalle.getNombre());
        producto.setPrecio(productoDetalle.getPrecio());

        return productoRepository.save(producto);
        
    }

    @DeleteMapping("/{id}")
    public String productoEliminar (@PathVariable Long id){

         Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró producto ID : " + id));

        productoRepository.delete(producto);
        
        return "Producto fué eliminado !";
      
    }
    
 

}
