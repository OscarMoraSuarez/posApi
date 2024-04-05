package com.NetTools.API.Dominio.egreso;

import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;

public record DatosDetalleEgreso(
        Long egresoId,
        Producto producto,
        Integer cantidad,
        Ubicacion ubicacion,
        java.time.LocalDateTime fechaIngreso,
        String concepto
) {
    public DatosDetalleEgreso(Egreso egreso) {
        this(egreso.getEgresoId(), egreso.getProducto(),egreso.getCantidad(), egreso.getUbicacion(),egreso.getFechaEgreso(),egreso.getConcepto());
    }
}
