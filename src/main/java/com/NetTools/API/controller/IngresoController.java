package com.NetTools.API.controller;

import com.NetTools.API.Dominio.Ingreso.*;
import com.NetTools.API.Dominio.Inventario.DatosEliminarStock;
import com.NetTools.API.Dominio.Inventario.InventarioRepository;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngresoController {

@Autowired
private IngresoService ingresoService;
@Autowired
private InventarioRepository inventarioRepository;

    // Controlador de Ingreso
    @PostMapping("/ingreso")
    public ResponseEntity<DatosDetalleIngreso> registrarIngreso(@RequestBody @Valid DatosRegistroIngreso datosRegistroIngreso) throws ProductNotFoundException, LocationNotFoundException {

            DatosDetalleIngreso ingreso = ingresoService.registrar(datosRegistroIngreso);
            return ResponseEntity.ok(ingreso);
    }

    /*@PutMapping("/ingreso")
    @Transactional
    public ResponseEntity<DatosDetalleDeleteStock> eliminarIngreso(@RequestBody @Valid DatosDeleteStock datosDeleteStock){
        Long ingresoId= datosDeleteStock.ingresoId();
        Long productoId= datosDeleteStock.productoId();
        int cantidad=datosDeleteStock.cantidad();
        registroIngresoService.eliminarStock(ingresoId,productoId,cantidad);
        DatosDetalleDeleteStock response= new DatosDetalleDeleteStock(HttpStatus.OK.value(),"Stock elimnado correctamente");
        return ResponseEntity.ok(response);
    }*/


}
