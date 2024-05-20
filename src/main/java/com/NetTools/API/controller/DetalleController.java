package com.NetTools.API.controller;

import com.NetTools.API.Dominio.detalle.DetalleRepository;
import com.NetTools.API.Dominio.detalle.DetalleVenta;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

    @Autowired
    private DetalleRepository detalleRepository;

    @GetMapping("/{numeroVenta}")
    public List<DetalleVenta> obtenerDetallesPorNumeroVenta(@PathVariable int numeroVenta) {
        System.out.println("el numero de venta es " + numeroVenta);
        List<DetalleVenta> detalles = detalleRepository.findByNumeroVenta(numeroVenta);
        System.out.println("Detalles: " + detalles);
        return detalles;
    }


}
