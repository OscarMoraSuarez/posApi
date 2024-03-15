package com.NetTools.API.Dominio.Inventario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Transactional
    public void sumarInventario(String codigo, int cantidad) {

        Inventario inventario=inventarioRepository.findByCodigo(codigo);

        if(inventario != null){
            int nuevaCantidadInventario=inventario.getCantidad()+cantidad;
            inventario.setCantidad(nuevaCantidadInventario);
        }else{
            inventario =new Inventario();
            inventario.setCodigo(codigo);
            inventario.setCantidad(cantidad);
        }
        inventarioRepository.save(inventario);

    }

    @Transactional
    public void eliminarStock(String codigo, int cantidad) {
        Inventario inventario = inventarioRepository.findByCodigo(codigo);

        if (inventario != null) {
            // Verificar si la cantidad a eliminar no es mayor que la cantidad actual en inventario
            if (cantidad <= inventario.getCantidad()) {
                int nuevaCantidadInventario = inventario.getCantidad() - cantidad;
                inventario.setCantidad(nuevaCantidadInventario);
                inventarioRepository.save(inventario);
            } else {
                throw new IllegalArgumentException("La cantidad especificada es mayor que la cantidad disponible en inventario");
            }
        } else {
            throw new IllegalArgumentException("No se encontró inventario para el producto con código: " + codigo);
        }
    }



}
