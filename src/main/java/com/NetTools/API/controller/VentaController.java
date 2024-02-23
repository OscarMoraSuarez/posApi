package com.NetTools.API.controller;


import com.NetTools.API.Dominio.venta.DatosRegistroVenta;
import com.NetTools.API.Dominio.venta.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venta")
public class VentaController {

private VentaService ventaService;

//Inyerccion de dependencias en el constructor
public  VentaController(VentaService ventaService){

    this.ventaService=ventaService;

}
//metodo para manejar solicitudes POST con JSON
    @PostMapping
    public ResponseEntity registrarVenta(@RequestBody @Valid DatosRegistroVenta datosRegistroVenta ){

        var response=ventaService.registrar(datosRegistroVenta);
        return  ResponseEntity.ok(response);
    }

}
