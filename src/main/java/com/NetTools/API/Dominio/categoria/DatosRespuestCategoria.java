package com.NetTools.API.Dominio.categoria;

public record DatosRespuestCategoria(
        Long idCategoria,
        String nombreCategoria
) {
    public DatosRespuestCategoria(Categoria categoria){
        this(categoria.getCategoriaId(), categoria.getNombreCategoria());
    }
}
