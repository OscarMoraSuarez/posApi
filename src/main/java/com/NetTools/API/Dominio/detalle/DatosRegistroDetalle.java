package com.NetTools.API.Dominio.detalle;

import java.math.BigDecimal;

public record DatosRegistroDetalle(
        Integer numeroVenta,
        String codigoPro,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subtotal
) {
}
