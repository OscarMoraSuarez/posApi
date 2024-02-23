package com.NetTools.API.Dominio.categoria;

import com.NetTools.API.Dominio.producto.Producto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoriaRepository extends JpaRepository<Categoria,Long> {


    Page<Categoria> findAll(Pageable pageable);
    Categoria findByCategoriaId(@NotNull @Valid Long categoriaId);

    Categoria findByNombreCategoria(String nombre);

    @Query("SELECT p FROM Producto p JOIN p.categoria c WHERE c.categoriaId = :categoriaId")
    Page<Producto> findProductosByCategoriaId(@Param("categoriaId") Long categoriaId, Pageable pageable);



}

