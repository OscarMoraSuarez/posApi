package com.NetTools.API.Dominio.producto;

import com.NetTools.API.Dominio.categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Table(name="producto")
@Entity(name = "Producto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="productoId")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String codigo;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoriaId",referencedColumnName = "categoriaId")
    private Categoria categoria;
    private String marca;
    private BigDecimal precioEntrada;
    private BigDecimal precioSalida;
    private String imagePath;
    private boolean activo;

    public Producto(String codigo,String descripcion,Categoria categoria,String marca,BigDecimal precioEntrada,BigDecimal precioSalida,String imagePath) {
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.categoria=categoria;
        this.marca=marca;
        this.precioEntrada=precioEntrada;
        this.precioSalida=precioSalida;
        this.imagePath=imagePath;
        this.activo=true;
    }

    public Producto(DatosRegistroProducto datosRegistroProducto) {
    }

    public void actualizarDatos(DatosActualizarProducto datosActualizarProducto) {
        if(datosActualizarProducto.codigo()!=null){
            this.codigo= datosActualizarProducto.codigo();
        }
        if(datosActualizarProducto.descripcion()!=null){
            this.descripcion= datosActualizarProducto.descripcion();
        }
        if(datosActualizarProducto.categoria()!=null){
            this.descripcion= datosActualizarProducto.categoria();
        }
        if(datosActualizarProducto.marca()!=null){
            this.marca= datosActualizarProducto.marca();
        }
        if(datosActualizarProducto.marca()!=null){
            this.marca= datosActualizarProducto.marca();
        }
        if(datosActualizarProducto.precioEntrada()!=null){
            this.precioEntrada=datosActualizarProducto.precioEntrada();
        }

        if(datosActualizarProducto.precioSalida()!=null){
            this.precioSalida=datosActualizarProducto.precioSalida();
        }
        if(datosActualizarProducto.imagePath()!=null){
            this.imagePath= datosActualizarProducto.imagePath();
        }
    }

    public void desactivarProducto() {
        this.activo=false;
    }
}

