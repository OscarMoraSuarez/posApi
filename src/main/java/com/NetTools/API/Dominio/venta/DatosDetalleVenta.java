package com.NetTools.API.Dominio.venta;

import java.math.BigDecimal;

public record DatosDetalleVenta(

        Long numeroVenta,
        BigDecimal subTotal,
        BigDecimal descuento,
        BigDecimal total


) {


}
