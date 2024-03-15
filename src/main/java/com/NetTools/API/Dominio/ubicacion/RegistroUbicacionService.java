package com.NetTools.API.Dominio.ubicacion;

import com.NetTools.API.Dominio.categoria.Categoria;
import com.NetTools.API.Dominio.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroUbicacionService {
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Transactional
    public DatosDetalleUbicacion registrar(DatosRegistroUbicacion datos){

        Ubicacion ubicaion=new Ubicacion(datos.codigoUbicacion());
        ubicacionRepository.save(ubicaion);
        return new DatosDetalleUbicacion(
                ubicaion.getUbicacionId(),
                ubicaion.getCodigoUbicacion()
        );
    }

}
