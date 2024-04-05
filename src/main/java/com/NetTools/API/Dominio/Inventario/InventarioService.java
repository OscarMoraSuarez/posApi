package com.NetTools.API.Dominio.Inventario;


import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.producto.ProductoRepository;
import com.NetTools.API.Infra.Exceptions.InsufficientInventoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public void sumarInventario(Long productoId, int cantidad,Long ubicacionId, String productoCodigo) {

        Inventario inventario=inventarioRepository.findByProductoIdAndUbicacionId(productoId,ubicacionId);
        Producto producto=productoRepository.findByProductoId(productoId);

        if(inventario != null){
            int nuevaCantidadInventario=inventario.getCantidad()+cantidad;
            inventario.setCantidad(nuevaCantidadInventario);
        }else{
            inventario =new Inventario();
            inventario.setProductoId(productoId);
            inventario.setCantidad(cantidad);
            inventario.setUbicacionId(ubicacionId);
            inventario.setProductoCodigo(productoCodigo);
        }
        inventarioRepository.save(inventario);

    }

    @Transactional
    public void restarInventario(Long productoId, Integer cantidad, Long ubicacionId, String codigo) throws InsufficientInventoryException {
        // Buscar el inventario por productoId y ubicacionId
        Inventario inventario = inventarioRepository.findByProductoIdAndUbicacionId(productoId, ubicacionId);

        // Verificar si el inventario existe
        if (inventario == null) {
            throw new InsufficientInventoryException("No hay inventario disponible para el producto y ubicación especificados.");
        }

        // Si la cantidad enviada desde el cliente es mayor que la cantidad en inventario
        if (cantidad > inventario.getCantidad()) {
            // Establecer la cantidad de inventario disponible en 0
            inventario.setCantidad(0);
        } else {
            // Actualizar la cantidad en inventario
            inventario.setCantidad(inventario.getCantidad() - cantidad);
        }

        // Guardar el inventario actualizado
        inventarioRepository.save(inventario);
    }

    @Transactional
    public void restarInventario(String codigo, Integer cantidad) throws InsufficientInventoryException {


        // Buscar el inventario por productoId y ubicacionId
        Inventario inventario = inventarioRepository.findByProductoCodigoAndUbicacionId(codigo, 5L);

        // Verificar si el inventario existe
        if (inventario == null) {
            throw new InsufficientInventoryException("No hay inventario disponible para el producto y ubicación especificados.");
        }

        // Si la cantidad enviada desde el cliente es mayor que la cantidad en inventario
        if (cantidad > inventario.getCantidad()) {
            // Establecer la cantidad de inventario disponible en 0
            inventario.setCantidad(0);
        } else {
            // Actualizar la cantidad en inventario
            inventario.setCantidad(inventario.getCantidad() - cantidad);
        }

        // Guardar el inventario actualizado
        inventarioRepository.save(inventario);
    }


    public Boolean verificarInventario(Long productoId, Long ubicacionId, int cantidad) throws InsufficientInventoryException {
        Inventario inventario = inventarioRepository.findByProductoIdAndUbicacionId(productoId, ubicacionId);

        if (inventario == null || inventario.getCantidad() < cantidad) {
            throw new InsufficientInventoryException("Cantidad insuficiente en el inventario para el producto " + productoId + " en la ubicación " + ubicacionId);

        }
        return true;
    }



    public int getCantidadProducto(String codigo) {
        Inventario inventario = inventarioRepository.findByProductoCodigo(codigo);
        if (inventario != null  && inventario.getCantidad() > 0 ) {
            return inventario.getCantidad();
        } else {
            return 0; // Devuelve 0 si el inventario no existe o la cantidad es null
        }
    }





}
