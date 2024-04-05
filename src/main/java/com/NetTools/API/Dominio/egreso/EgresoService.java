package com.NetTools.API.Dominio.egreso;

import com.NetTools.API.Dominio.Inventario.Inventario;
import com.NetTools.API.Dominio.Inventario.InventarioRepository;
import com.NetTools.API.Dominio.Inventario.InventarioService;
import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.producto.ProductoRepository;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import com.NetTools.API.Dominio.ubicacion.UbicacionRepository;
import com.NetTools.API.Infra.Exceptions.InsufficientInventoryException;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EgresoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Autowired
    InventarioService inventarioService;
    @Autowired
    EgresoRepository egresoRepository;
    @Autowired
    InventarioRepository inventarioRepository;
    @Transactional
    public Egreso registrarEgresoService(DatosRegistroEgreso datos ) throws ProductNotFoundException, LocationNotFoundException, InsufficientInventoryException {
        Producto producto = productoRepository.findByProductoId(datos.productoId());
        if (producto == null) {
            throw new ProductNotFoundException("No existe un producto con el Id " + datos.productoId());
        }

        Ubicacion ubicacion = ubicacionRepository.findByUbicacionId(datos.ubicacionId());
        if (ubicacion == null) {
            throw new LocationNotFoundException("No existe una ubicación con el Id " + datos.ubicacionId());
        }

        Boolean respuesta = inventarioService.verificarInventario(datos.productoId(), datos.ubicacionId(), datos.cantidad());
        if(Boolean.TRUE.equals(respuesta)){
            Egreso egreso= new Egreso(producto, datos.cantidad(), ubicacion, datos.concepto());
            inventarioService.restarInventario(producto.getProductoId(), datos.cantidad(), ubicacion.getUbicacionId(), producto.getCodigo());

            egresoRepository.save(egreso);
            return egreso;
        } else {
            throw new InsufficientInventoryException("Cantidad insuficiente en el inventario para el producto " + datos.productoId() + " en la ubicación " + datos.ubicacionId());
        }
    }

}
