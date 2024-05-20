package com.NetTools.API.Dominio.producto;

public record DatosConteoProducto(
        Long count
) {
    public DatosConteoProducto(Long count) {
        this.count = count != null ? count : 0L; // Si count es null, establece el valor en cero
    }
}
