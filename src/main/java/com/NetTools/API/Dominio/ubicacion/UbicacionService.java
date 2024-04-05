package com.NetTools.API.Dominio.ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UbicacionService {
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Transactional
    public DatosDetalleUbicacion registrar(DatosRegistroUbicacion datosRegistroUbicacion){
        System.out.println("desde ubicacion service " + datosRegistroUbicacion);
        Ubicacion ubicaion=new Ubicacion(datosRegistroUbicacion.codigoUbicacion());
        ubicacionRepository.save(ubicaion);
        return new DatosDetalleUbicacion(
                ubicaion.getUbicacionId(),
                ubicaion.getCodigoUbicacion()
        );
    }

}
