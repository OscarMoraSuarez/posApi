package com.NetTools.API.controller;

import com.NetTools.API.Dominio.detalle.DatosRegistroDetalle;

import com.NetTools.API.Dominio.detalle.DetalleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalle")
public class DetalleController {
    @Autowired
    DetalleService detalleService;

    @PostMapping
    public ResponseEntity registrarDetalle(@RequestBody @Valid DatosRegistroDetalle datosRegistroDetalle){

       var response=detalleService.registrarDetalle(datosRegistroDetalle);
       return ResponseEntity.ok(response);

    }

}
