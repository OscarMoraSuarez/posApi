package com.NetTools.API.Dominio.detalle;
import java.math.BigDecimal;

public record DatosRegistroDetalle(
        Long numeroVenta,
        String codigoProducto,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal subTotal
) {


}
