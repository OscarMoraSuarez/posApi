package com.NetTools.API.Dominio.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record DatosActualizarProducto(
        @NotNull
        Long id,

        String codigo,

        String descripcion,
        String categoria,
        String marca,
        @NumberFormat
        BigDecimal precioEntrada,
        @NumberFormat
        BigDecimal precioSalida,
        String imagePath
) {



}
