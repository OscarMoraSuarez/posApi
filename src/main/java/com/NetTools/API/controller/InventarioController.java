package com.NetTools.API.controller;


import com.NetTools.API.Dominio.Inventario.DatosEliminarStock;
import com.NetTools.API.Dominio.Inventario.Inventario;
import com.NetTools.API.Dominio.Inventario.InventarioRepository;
import com.NetTools.API.Dominio.Inventario.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    InventarioService inventarioService;
    @GetMapping
    public ResponseEntity<Page<Inventario>> listadoInventario(Pageable paginacion) {
        Page<Inventario> inventario = inventarioRepository.findAll(paginacion);
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Integer> cantidadProducto(@PathVariable String codigo) {
        System.out.println("este es el codigo "+ codigo);
        int cantidad = inventarioService.getCantidadProducto(codigo);
        System.out.println("cantidad: " + cantidad);
        return ResponseEntity.ok(cantidad);
    }

   /* @DeleteMapping
    public void eliminarStockController(@RequestBody @Valid DatosEliminarStock datosEliminarStock) {
        Long productoId= datosEliminarStock.productoId();
        int cantidad = inventarioService.getCantidadProducto(productoId);
        if(cantidad!=null && cantidad>0){



        }
        //ResponseEntity<>

        //return ResponseEntity.ok(cantidad);
        //inventarioService.eliminarStockService();
    }*/



}
