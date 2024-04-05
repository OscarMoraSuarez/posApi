package com.NetTools.API.Dominio.Inventario;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Inventario findByProductoIdAndUbicacionId(Long productoId, Long ubicacionId);
    Inventario findByProductoCodigoAndUbicacionId(String codsigo,Long ubicacionId);
    Inventario findByProductoCodigo(String codigo);
}

