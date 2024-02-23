package com.NetTools.API.Dominio.venta;

import java.math.BigDecimal;

public record DatosDetalleVenta(

        Integer numeroVenta,
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal total

) {






}
