package com.NetTools.API.Dominio.Ingreso;

public record DatosRegistroIngreso(
        Long productoId,
        int cantidad,
        Long ubicacionId
) {
}
