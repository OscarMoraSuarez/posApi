package com.NetTools.API.Dominio.Ingreso;

import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="ingreso")
@Entity(name="Ingreso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ingresoId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productoId")
    private Producto producto;
    private int  cantidad;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ubicacionId")
    private Ubicacion ubicacion;

    public Ingreso(Producto producto, int cantidad, Ubicacion ubicacion) {

        this.producto=producto;
        this.cantidad=cantidad;
        this.ubicacion=ubicacion;

    }


}
