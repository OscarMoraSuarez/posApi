package com.NetTools.API.Dominio.Ingreso;


import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.producto.ProductoRepository;
import com.NetTools.API.Dominio.Inventario.InventarioService;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import com.NetTools.API.Dominio.ubicacion.UbicacionRepository;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.Location;

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
        inventarioService.actualizarInventario(producto.getCodigo(), datos.cantidad());

        return new DatosDetalleIngreso(ingreso);
    }




}
