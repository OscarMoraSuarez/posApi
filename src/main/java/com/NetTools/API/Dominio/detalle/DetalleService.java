package com.NetTools.API.Dominio.detalle;

import com.NetTools.API.Dominio.Inventario.InventarioService;
import com.NetTools.API.Infra.Exceptions.InsufficientInventoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleService {
    @Autowired
    InventarioService inventarioService;
    @Autowired
    DetalleRepository detalleRepository;

    @Transactional
    public List<DatosDetalleDetalle> registrarDetalles(List<DatosRegistroDetalle> datosRegistroDetalles) throws InsufficientInventoryException {
        LocalDateTime fechaVenta = LocalDateTime.now();
        List<DatosDetalleDetalle> detallesRegistrados = new ArrayList<>();

        if (datosRegistroDetalles.isEmpty()) {
            System.out.println("Los datos de la venta están vacíos");
            return detallesRegistrados;
        }

        for (DatosRegistroDetalle datosRegistroDetalle : datosRegistroDetalles) {
            //restar la cantidad de inventario usando el metodo sobrecargado restar inventario
            inventarioService.restarInventario(datosRegistroDetalle.codigoProducto(), datosRegistroDetalle.cantidad());
            DetalleVenta detalleVenta = new DetalleVenta(
                    datosRegistroDetalle.numeroVenta(),
                    datosRegistroDetalle.codigoProducto(),
                    datosRegistroDetalle.cantidad(),
                    datosRegistroDetalle.precioUnitario(),
                    datosRegistroDetalle.subTotal(),
                    fechaVenta);
            detalleRepository.save(detalleVenta);

            DatosDetalleDetalle detalleRegistrado = new DatosDetalleDetalle(
                    detalleVenta.getDetalleId(),
                    detalleVenta.getNumeroVenta(),
                    detalleVenta.getCodigoProducto(),
                    detalleVenta.getCantidad(),
                    detalleVenta.getPrecioUnitario(),
                    detalleVenta.getSubtotal(),
                    detalleVenta.getFechaVenta()
            );

            detallesRegistrados.add(detalleRegistrado);
        }

        return detallesRegistrados;
    }
}
