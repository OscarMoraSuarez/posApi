package com.NetTools.API.Dominio.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;



public record DatosRegistroProducto(

        @NotBlank
        String codigo,
        @NotBlank
        String descripcion,
        @NotNull
        String nombreCategoria,
        String marca,
        @NotNull
        BigDecimal precioEntrada,
        @NotNull
        BigDecimal precioSalida,
        MultipartFile imagen



) {

}
