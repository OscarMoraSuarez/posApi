package com.NetTools.API.Dominio.venta;

import com.NetTools.API.Dominio.detalle.DetalleVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface VentaRepository extends JpaRepository<Venta, Long> {
    // trae el maximo numero de la tabla
    @Query("SELECT COALESCE(MAX(v.numeroVenta), 0) FROM Venta v")
    Optional<Long> findMaxNumeroVenta();
    Page<Venta> findByMesAndAnio(int mes, int anio, Pageable pageable);

    // Consulta para obtener todas las ventas y detalles relacionados por número de venta
    @Query("SELECT v FROM Venta v WHERE v.numeroVenta = :numeroVenta")
    List<Venta> findByNumeroVenta(@Param("numeroVenta") int numeroVenta);
}


