package com.NetTools.API.Dominio.ubicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUbicacion(
        @NotBlank
        String codigoUbicacion,
        @NotNull
        @Valid
        Long categoriaId,
        String zona
) {

}
