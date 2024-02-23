package com.NetTools.API.Dominio.ubicacion;

import com.NetTools.API.Dominio.categoria.Categoria;
import com.NetTools.API.Dominio.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroUbicacionService {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Transactional
    public DatosDetalleUbicacion registrar(DatosRegistroUbicacion datos){

        Categoria categoria=seleccionarCategoria(datos);
        if (categoria == null) {
            System.out.println("No existe esa categoria");
            // Aquí deberías manejar el caso en el que la categoría no existe.
        }

        Ubicacion ubicaion=new Ubicacion(datos.codigoUbicacion(),categoria,datos.zona());
        ubicacionRepository.save(ubicaion);
        return new DatosDetalleUbicacion(
                ubicaion.getUbicacionId(),
                ubicaion.getCodigoUbicacion(),
                ubicaion.getCategoria(),
                ubicaion.getZona()
        );

    }

    private Categoria seleccionarCategoria(DatosRegistroUbicacion datos) {
        if (datos.categoriaId() != null) {
            // Utilizamos el nuevo método del repositorio para buscar la categoría por su ID
            return categoriaRepository.findByCategoriaId(datos.categoriaId());
        }
        // Aquí puedes manejar el caso en el que datos.categoriaId() es nulo,
        // dependiendo de tus requisitos de negocio.
        return null;
    }

}
