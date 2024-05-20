package com.NetTools.API.Dominio.venta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal subTotal;
    private BigDecimal descuento;
    private BigDecimal total;
    private Integer mes;
    private Integer anio;
    @Column(name = "fechaVenta", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaVenta;

    public Venta(Long nuevoNumeroVenta, BigDecimal subtotal, BigDecimal descuento, BigDecimal total) {

        this.numeroVenta=nuevoNumeroVenta;
        this.subTotal=subtotal;
        this.descuento=descuento;
        this.total=total;
        this.fechaVenta=LocalDateTime.now();


    }
    
    @PrePersist
    public void prepPersist(){
        if(fechaVenta!=null){
            this.mes = fechaVenta.getMonthValue();
            this.anio = fechaVenta.getYear();
        }
    }

    @Override
    public String toString() {
        return "Venta{" +
                "ventaId=" + ventaId +
                ", numeroVenta=" + numeroVenta +
                ", subTotal=" + subTotal +
                ", descuento=" + descuento +
                ", total=" + total +
                '}';
    }
}
