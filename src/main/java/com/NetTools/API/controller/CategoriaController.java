package com.NetTools.API.controller;

import com.NetTools.API.Dominio.categoria.Categoria;
import com.NetTools.API.Dominio.categoria.CategoriaRepository;
import com.NetTools.API.Dominio.categoria.DatosRegistroCategoria;
import com.NetTools.API.Dominio.categoria.DatosRespuestCategoria;
import com.NetTools.API.Dominio.producto.DatosDetalleProducto;
import com.NetTools.API.Dominio.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    // metodo para registrar una categoria
    @Autowired
    private CategoriaRepository categoriaRepository;
    @PostMapping
    public void registrarCategoria(@RequestBody DatosRegistroCategoria datosRegistroCategoria){
        Categoria categoria=new Categoria(datosRegistroCategoria);
        categoriaRepository.save(categoria);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestCategoria>> listarCategorias(
            @PageableDefault(size = 20, page = 0, sort = "categoriaId") Pageable paginacion) {
        Page<Categoria> categoriasPage = categoriaRepository.findAll(paginacion);
        Page<DatosRespuestCategoria> datosRespuestaCategoria = categoriasPage.map(DatosRespuestCategoria::new);
        return ResponseEntity.ok(datosRespuestaCategoria);
    }


    // metodo que devuelve porductos por categoria
    @GetMapping("/{nombre}")
    public ResponseEntity<Page<DatosDetalleProducto>> obtenerPorCategoria(
            @PathVariable String nombre,
            @PageableDefault(size = 2, page = 0, sort = "descripcion", direction = Sort.Direction.ASC) Pageable paginacion) {
        Categoria categoria = categoriaRepository.findByNombreCategoria(nombre);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        Long categoriaId = categoria.getCategoriaId();
        Page<Producto> productosPage = categoriaRepository.findProductosByCategoriaId(categoriaId, paginacion);

        Page<DatosDetalleProducto> datosDetalleProductoPage = productosPage.map(DatosDetalleProducto::new);

        return ResponseEntity.ok(datosDetalleProductoPage);
    }




}
