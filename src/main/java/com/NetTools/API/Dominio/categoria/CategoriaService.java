package com.NetTools.API.Dominio.categoria;

import com.NetTools.API.Dominio.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public void actualizarProducto(Producto producto,String nombreCategoria){
        // buscar la categoría por nombre
        Categoria categoria=categoriaRepository.findByNombreCategoria(nombreCategoria);
        if(categoria != null){
            //asignar la categoría al producto
            producto.setCategoria(categoria);

        }//else{}



    }


}
