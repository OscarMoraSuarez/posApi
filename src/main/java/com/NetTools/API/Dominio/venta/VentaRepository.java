package com.NetTools.API.Dominio.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT COALESCE(MAX(v.numeroVenta), 0) FROM Venta v")
    Optional<Long> findMaxNumeroVenta();
}