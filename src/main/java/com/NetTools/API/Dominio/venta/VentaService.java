package com.NetTools.API.Dominio.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Transactional
    public Long registrarVentaPrincipal(DatosRegistroVenta datosRegistroVenta) {
        if (datosRegistroVenta == null) {
            // Utilizar un framework de logging como SLF4J con Logback o Log4j. Esto proporcionará un mejor control sobre la salida de log
            System.out.println("Los datos de la venta están vacíos");
            return null;
        }

        // Este método incrementa en uno el número de venta
        Long nuevoNumeroVenta = incrementarNumeroVenta(obtenerUltimoNumeroVenta());
        System.out.println("numero de venta:"+nuevoNumeroVenta);
        // Crear instancia de Venta
        Venta venta = new Venta(
                nuevoNumeroVenta, // Asignar el número de venta generado
                datosRegistroVenta.subTotal(),
                datosRegistroVenta.descuento(),
                datosRegistroVenta.total()
        );

        // Guardar la venta en la base de datos
        // Podrías capturar excepciones específicas (como DataAccessException)
        System.out.println("esta es la venta:"+venta.toString());
        ventaRepository.save(venta);

        return nuevoNumeroVenta;
    }

    private Long obtenerUltimoNumeroVenta() {
        return ventaRepository.findMaxNumeroVenta().orElseGet(() -> 0L);
    }


    private Long incrementarNumeroVenta(Long ultimoNumeroVenta) {
        // Si no hay registros, establecer como 1
        if (ultimoNumeroVenta == null) {
            return 1L;
        } else {
            return ultimoNumeroVenta + 1;
        }
    }

}
