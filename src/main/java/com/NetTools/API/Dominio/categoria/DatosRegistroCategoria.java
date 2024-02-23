package com.NetTools.API.Dominio.categoria;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroCategoria(
        @NotNull
        String nombreCategoria) {

        public static DatosRegistroCategoria fromString(String nombreCategoria) {
                return new DatosRegistroCategoria(nombreCategoria);
        }

}
