package com.NetTools.API.Dominio.detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleRepository extends JpaRepository<DetalleVenta, Long> {
    @Query("SELECT d FROM DetalleVenta d WHERE d.numeroVenta = :numeroVenta")
    List<DetalleVenta> findByNumeroVenta(@Param("numeroVenta") int numeroVenta);

}
