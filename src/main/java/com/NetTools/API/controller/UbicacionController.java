package com.NetTools.API.controller;

import com.NetTools.API.Dominio.ubicacion.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @PostMapping
    public ResponseEntity registrarUbicacion(@RequestBody @Valid DatosRegistroUbicacion datosRegistroUbicacion) {
        var response= ubicacionService.registrar(datosRegistroUbicacion);
        System.out.println(datosRegistroUbicacion);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleUbicacion>> listarUbicaciones(
            @PageableDefault(size = 20, page = 0, sort = "ubicacionId",direction = Sort.Direction.ASC) Pageable paginacion) {

        return ResponseEntity.ok(ubicacionRepository.findAll(paginacion).map(DatosDetalleUbicacion::new));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUbicacion(@PathVariable Long id){
        Ubicacion ubicaion =ubicacionRepository.getReferenceById(id);
        ubicacionRepository.delete(ubicaion);
        return ResponseEntity.noContent().build();
    }



}
