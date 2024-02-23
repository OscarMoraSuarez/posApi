package com.NetTools.API.Dominio.venta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "venta")
@Entity(name="Venta")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;
    @Column(name="numeroVenta")
    private Long numeroVenta;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal total;

    public Venta(BigDecimal subtotal, BigDecimal descuento, BigDecimal total) {
        this.subtotal=subtotal;
        this.descuento=descuento;
        this.total=total;

    }
}
