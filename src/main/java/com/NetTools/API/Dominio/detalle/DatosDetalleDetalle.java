package com.NetTools.API.Dominio.detalle;

import java.math.BigDecimal;

public record DatosDetalleDetalle(

        Long detalleId,
        Integer numeroVenta,
        String codigoProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal,
        java.time.LocalDateTime fecha


) {
}
