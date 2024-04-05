package com.NetTools.API.Dominio.venta;

import com.NetTools.API.Dominio.detalle.DatosRegistroDetalle;
import java.math.BigDecimal;
import java.util.List;


public record DatosRegistroVenta(
        BigDecimal subTotal,
        BigDecimal descuento,
        BigDecimal total,
        List<DatosRegistroDetalle> detallesVenta


) {
}
