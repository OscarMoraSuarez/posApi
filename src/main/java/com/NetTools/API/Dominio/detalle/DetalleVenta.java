package com.NetTools.API.Dominio.detalle;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="DetalleVenta")
@Table(name="detalleventa")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="detalleId")
@Getter
@Setter

public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleId;
    private Integer numeroVenta;
    private String codigoProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private LocalDateTime fechaVenta;

    public DetalleVenta(Integer numeroVenta, String codigoPro, Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal,LocalDateTime fechaVenta) {
        this.numeroVenta=numeroVenta;
        this.codigoProducto=codigoPro;
        this.cantidad=cantidad;
        this.precioUnitario=precioUnitario;
        this.subtotal=subtotal;
        this.fechaVenta=fechaVenta;
    }
}
