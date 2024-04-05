package com.NetTools.API.Dominio.egreso;

public record DatosRegistroEgreso(
        Long productoId,
        Integer cantidad,
        Long ubicacionId,
        String concepto

) {
}
