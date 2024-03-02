package com.NetTools.API.controller;

import com.NetTools.API.Dominio.Ingreso.DatosDetalleIngreso;
import com.NetTools.API.Dominio.Ingreso.DatosRegistroIngreso;
import com.NetTools.API.Dominio.Ingreso.RegistroIngresoService;
import com.NetTools.API.Dominio.Inventario.Inventario;
import com.NetTools.API.Dominio.Inventario.InventarioRepository;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class IngresoController {

@Autowired
private RegistroIngresoService registroIngresoService;
@Autowired
private InventarioRepository inventarioRepository;

    @PostMapping("/inventario")
    public ResponseEntity<DatosDetalleIngreso> registrarIngreso(@RequestBody @Valid DatosRegistroIngreso datosRegistroIngreso) throws ProductNotFoundException, LocationNotFoundException {
        var response = registroIngresoService.registrar(datosRegistroIngreso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarInventario(@PathVariable Long id){
        Inventario inventario =inventarioRepository.getReferenceById(id);
        inventarioRepository.delete(inventario);
        return ResponseEntity.noContent().build();
    }


}
