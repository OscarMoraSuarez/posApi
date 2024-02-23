package com.NetTools.API.Dominio.Ingreso;


import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.producto.ProductoRepository;
import com.NetTools.API.Dominio.Inventario.InventarioService;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import com.NetTools.API.Dominio.ubicacion.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public DatosDetalleIngreso registrar(DatosRegistroIngreso datos) {

        Producto producto= productoRepository.findByProductoId(datos.productoId());
        Ubicacion ubicacion =ubicacionRepository.findByUbicacionId(datos.ubicacionId());

        if (producto==null){
            System.out.println("no existe un producto con ese id");
        }
        if (ubicacion==null){
            System.out.println("no existe una ubicacion con ese id");
        }
        var ingreso=new Ingreso(producto,datos.cantidad(),ubicacion);
        ingresoRepository.save(ingreso);
        inventarioService.actualizarInventario(producto.getCodigo(),datos.cantidad());

        return new DatosDetalleIngreso(ingreso);

    }




}
