package com.NetTools.API.controller;



import com.NetTools.API.Dominio.detalle.DatosRegistroDetalle;
import com.NetTools.API.Dominio.detalle.DetalleService;
import com.NetTools.API.Dominio.venta.DatosRegistroVenta;
import com.NetTools.API.Dominio.venta.Venta;
import com.NetTools.API.Dominio.venta.VentaRepository;
import com.NetTools.API.Dominio.venta.VentaService;
import com.NetTools.API.Infra.Exceptions.InsufficientInventoryException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {

private final VentaService ventaService;
@Autowired
private VentaRepository ventaRepository;
@Autowired
private DetalleService detalleService;

//Inyeccion de dependencias en el constructor
public  VentaController(VentaService ventaService){

    this.ventaService=ventaService;

}
//metodo para manejar solicitudes POST con JSON
@PostMapping
public ResponseEntity registrarVenta(@RequestBody @Valid DatosRegistroVenta datosRegistroVenta) throws InsufficientInventoryException {
    System.out.println(datosRegistroVenta);
    // Registrar la venta principal y obtener el número de venta generado
    Long numeroVenta = ventaService.registrarVentaPrincipal(datosRegistroVenta);

    if (numeroVenta != null) {


        // Obtener la lista de detalles de venta
        List<DatosRegistroDetalle> detallesVenta = datosRegistroVenta.detallesVenta();

        // Asociar el número de venta con cada detalle de venta
        List<DatosRegistroDetalle> detallesVentaActualizados = new ArrayList<>();
        for (DatosRegistroDetalle detalleVenta : detallesVenta) {
            // Crear una nueva instancia de DatosRegistroDetalle con el numeroVenta actualizado
            System.out.println(detalleVenta.toString());
            DatosRegistroDetalle detalleActualizado = new DatosRegistroDetalle(
                    numeroVenta,
                    detalleVenta.codigoProducto(),
                    detalleVenta.cantidad(),
                    detalleVenta.precioUnitario(),
                    detalleVenta.subTotal()
            );
            detallesVentaActualizados.add(detalleActualizado);
        }


        detalleService.registrarDetalles(detallesVentaActualizados);

        // Devolver una respuesta exitosa
        return ResponseEntity.ok("Venta registrada exitosamente");
    } else {
        // Devolver una respuesta de error si no se pudo registrar la venta principal
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la venta");
    }
}





    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarVenta(@PathVariable Long id) {
        Venta venta = ventaRepository.getReferenceById(id);
        if (venta != null) {
            ventaRepository.delete(venta);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Mensaje", "La venta " + id + " ha sido eliminada exitosamente");
            return ResponseEntity.noContent().headers(headers).build();
        } else {
            return ResponseEntity.notFound().build(); // La venta no existe
        }
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

}
