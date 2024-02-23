package com.NetTools.API.controller;

import com.NetTools.API.Dominio.ubicacion.DatosRegistroUbicacion;
import com.NetTools.API.Dominio.ubicacion.RegistroUbicacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private RegistroUbicacionService service;

    @PostMapping
    public ResponseEntity registrarUbicacion(@RequestBody @Valid DatosRegistroUbicacion datosRegistroUbicacion) {
        var response=service.registrar(datosRegistroUbicacion);
        System.out.println(datosRegistroUbicacion);

        return ResponseEntity.ok(response);
    }


}
