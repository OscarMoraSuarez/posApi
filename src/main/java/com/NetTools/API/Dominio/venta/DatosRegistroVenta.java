package com.NetTools.API.Dominio.venta;

import java.math.BigDecimal;

public record DatosRegistroVenta(
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal total


) {
}
