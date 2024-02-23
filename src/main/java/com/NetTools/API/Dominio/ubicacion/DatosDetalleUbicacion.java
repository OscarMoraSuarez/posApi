package com.NetTools.API.Dominio.ubicacion;

import com.NetTools.API.Dominio.categoria.Categoria;

public record DatosDetalleUbicacion(
        Long id,
        String codigoUbicacion,
        Categoria categoria,
        String zona

        ) {
        public DatosDetalleUbicacion(Ubicacion ubicacion){

            this(ubicacion.getUbicacionId(),ubicacion.getCodigoUbicacion(),ubicacion.getCategoria(),ubicacion.getZona());

        }




}
