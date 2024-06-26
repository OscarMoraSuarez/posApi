package com.NetTools.API.Dominio.producto;

import com.NetTools.API.Dominio.categoria.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

        Producto findByProductoId(@NotNull @Valid Long productoId);
        Page<Producto> findByActivoTrue(Pageable paginacion);
        List<Producto> findByActivoTrue();
        Producto getReferenceById(@NotNull Long id);
        Producto getReferenceByCodigo(String codigo);
        Producto getProductoByCodigo(String codigo);
        @Query("SELECT COUNT(p) FROM Producto p")
        long contarProductos();
        @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
        Page<Producto> findByCategoria(@Param("categoria") Categoria categoria, Pageable pageable);


}
