package com.NetTools.API.Dominio.detalle;

import java.math.BigDecimal;

public record DatosDetalle(


        Long numeroVenta,
        String codigoProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal


) {
}
