package com.NetTools.API.controller;


import com.NetTools.API.Dominio.egreso.DatosDetalleEgreso;
import com.NetTools.API.Dominio.egreso.DatosRegistroEgreso;
import com.NetTools.API.Dominio.egreso.Egreso;
import com.NetTools.API.Dominio.egreso.EgresoService;
import com.NetTools.API.Infra.Exceptions.InsufficientInventoryException;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EgresoController {
    @Autowired
    EgresoService egresoService;




    @DeleteMapping("/egresos")
    public ResponseEntity<DatosDetalleEgreso> registroEgresoController(@RequestBody @Valid DatosRegistroEgreso datosRegistroEgreso) throws ProductNotFoundException, LocationNotFoundException, InsufficientInventoryException {
        System.out.println(datosRegistroEgreso);
        Egreso egreso=egresoService.registrarEgresoService(datosRegistroEgreso);

        return ResponseEntity.ok(new DatosDetalleEgreso(egreso));
    }

}
