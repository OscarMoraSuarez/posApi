package com.NetTools.API.Dominio.producto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

        Producto findByProductoId(@NotNull @Valid Long productoId);
        Page<Producto> findByActivoTrue(Pageable paginacion);
        Producto getReferenceById(@NotNull Long id);
        Producto getReferenceByCodigo(String codigo);
        Producto getProductoByCodigo(String codigo);

}
