package com.NetTools.API.Dominio.Ingreso;


import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.producto.ProductoRepository;
import com.NetTools.API.Dominio.Inventario.InventarioService;
import com.NetTools.API.Dominio.ubicacion.DatosDetalleUbicacion;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import com.NetTools.API.Dominio.ubicacion.UbicacionRepository;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.Location;
import java.util.Optional;

@Service
public class RegistroIngresoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired
    private InventarioService inventarioService;





    @Transactional
    public DatosDetalleIngreso registrar(DatosRegistroIngreso datos)throws ProductNotFoundException, LocationNotFoundException{
        Producto producto = productoRepository.findByProductoId(datos.productoId());
        Ubicacion ubicacion = ubicacionRepository.findByUbicacionId(datos.ubicacionId());

        if (producto == null) {
            throw new ProductNotFoundException("No existe un producto con el Id " + datos.productoId());
        }

        if (ubicacion == null) {
            throw new LocationNotFoundException("No existe un producto con el Id " + datos.ubicacionId());
        }

        var ingreso = new Ingreso(producto, datos.cantidad(), ubicacion);
        ingresoRepository.save(ingreso);
        inventarioService.sumarInventario(producto.getCodigo(), datos.cantidad());

        return new DatosDetalleIngreso(ingreso);
    }

    @Transactional
    public ResponseEntity<String> eliminarStock(Long ingresoId, Long productoId, int cantidad) {
        System.out.println(ingresoId+"; "+productoId+";"+cantidad);
        // Buscar ingreso por Id
        Optional<Ingreso> optionalIngreso = ingresoRepository.findById(ingresoId);
        if (optionalIngreso.isPresent()) {
            Ingreso ingreso = optionalIngreso.get();
            System.out.println("ingreso encontrado :"+"cantidad: "+ingreso.getCantidad()+" storedProductoId: "+ingreso.getProducto().getProductoId()+" "+productoId);
            if (ingreso.getProducto()!=null) {
                // Validar que la cantidad a restar no sea mayor que la cantidad actual
                System.out.println("el producto no es null");
                if (cantidad <= ingreso.getCantidad()) {
                    // Restar cantidad
                    ingreso.setCantidad(ingreso.getCantidad() - cantidad);
                    ingresoRepository.save(ingreso);
                    inventarioService.eliminarStock(ingreso.getProducto().getCodigo(), cantidad);
                    return ResponseEntity.ok("Cantidad actualizada correctamente");
                } else {
                    return ResponseEntity.badRequest()
                            .body("La cantidad especificada es mayor que la cantidad disponible en el ingreso");
                }
            } else {
                return ResponseEntity.badRequest()
                        .body("El producto especificado no coincide con el producto del ingreso");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El producto especificado no coincide con el producto del ingreso");
        }
    }

}
