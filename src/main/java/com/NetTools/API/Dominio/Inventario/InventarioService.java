package com.NetTools.API.Dominio.Inventario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Transactional
    public void actualizarInventario(String codigo, int cantidad) {

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


}
