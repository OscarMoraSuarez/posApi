package com.NetTools.API.Dominio.egreso;

import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "egresos")
@Entity(name = "Egreso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Egreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long egresoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId")
    Producto producto;
    int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionId")
    Ubicacion ubicacion;
    @Column(name = "fechaEgreso", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaEgreso;
    String concepto;




    public Egreso(Producto producto, Integer cantidad, Ubicacion ubicacion, String concepto) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.fechaEgreso = LocalDateTime.now();
        this.concepto=concepto;
    }


}
