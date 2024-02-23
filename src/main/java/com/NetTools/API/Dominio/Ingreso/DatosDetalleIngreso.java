package com.NetTools.API.Dominio.Ingreso;

import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;

public record DatosDetalleIngreso(
        Long ingresoId,
       Producto producto,
        int cantidad,
        Ubicacion ubicacion
) {
    public DatosDetalleIngreso(Ingreso ingreso) {
        this(ingreso.getIngresoId(), ingreso.getProducto(),ingreso.getCantidad(),ingreso.getUbicacion());
    }

}
