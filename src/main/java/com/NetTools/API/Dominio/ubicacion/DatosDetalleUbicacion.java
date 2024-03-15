package com.NetTools.API.Dominio.ubicacion;

import com.NetTools.API.Dominio.categoria.Categoria;

public record DatosDetalleUbicacion(
        Long id,
        String codigoUbicacion

        ) {
        public DatosDetalleUbicacion(Ubicacion ubicacion){

            this(ubicacion.getUbicacionId(),ubicacion.getCodigoUbicacion());

        }




}
