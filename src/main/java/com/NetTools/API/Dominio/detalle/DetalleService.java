package com.NetTools.API.Dominio.detalle;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class DetalleService {
@Autowired
DetalleRepository detalleRepository;
@Transactional
public  DatosDetalleDetalle  registrarDetalle(DatosRegistroDetalle datosRegistroDetalle){

    if (datosRegistroDetalle == null) {
        System.out.println("Los datos de la venta están vacíos");
        return null;
    }
    LocalDateTime fechaVenta=LocalDateTime.now();
    DetalleVenta detalleVenta= new DetalleVenta(
            datosRegistroDetalle.numeroVenta(),
            datosRegistroDetalle.codigoPro(),
            datosRegistroDetalle.cantidad(),
            datosRegistroDetalle.precioUnitario(),
            datosRegistroDetalle.subtotal(),
            fechaVenta
    );

    detalleRepository.save(detalleVenta);

    return new DatosDetalleDetalle(
            detalleVenta.getDetalleId(),
            detalleVenta.getNumeroVenta(),
            detalleVenta.getCodigoProducto(),
            detalleVenta.getCantidad(),
            detalleVenta.getPrecioUnitario(),
            detalleVenta.getSubtotal(),
            detalleVenta.getFechaVenta()
    );

}
}
