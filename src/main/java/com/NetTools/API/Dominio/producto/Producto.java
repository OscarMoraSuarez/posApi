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
    //para marcar un campo de una clase como la clave primaria de la entidad.
    @Id
    //para especificar cómo se generará automáticamente el valor de la clave primaria
    // cuando se inserte una nueva fila
    // strategy de esta anotación se utiliza para especificar la estrategia de generación
    // de claves primarias que se utilizará. @GeneratedValue(strategy = GenerationType.IDENTITY):
    //Esta configuración específica de @GeneratedValue indica que la generación del valor de la clave
    // primaria se delegará a la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String codigo;
    private String descripcion;
    // "fetch" la categor+ia se carga de manera ansiosa, es decir  se recupera  tan pronto se re cuper la categoría
    // tan pronto se carga el producto
    @ManyToOne(fetch = FetchType.EAGER)
    // se utiliza para especificar la columna de la tabla de la entidad propietaria
    // se utilizará como clave foránea para mapear la relación con la entidad asociada.
    //se utiliza para especificar que la columna categoriaId de la tabla producto se utilizará
    // como clave foránea para mapear la relación con la entidad Categoria.
    ////////////////////////
    //name se utiliza para especificar el nombre de la columna en la tabla producto
    //que contendrá los valores de las claves foráneas.
    ///////////////////////
    //El atributo referencedColumnName se utiliza para especificar el nombre de la columna en la tabla
    //categoria que contendrá los valores de las claves primarias a los que se hará referencia.
    @JoinColumn(name = "categoriaId", referencedColumnName = "categoriaId")
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

    public void actualizarDatos(String codigo, String descripcion, Categoria categoria, String marca, BigDecimal precioEntrada, BigDecimal precioSalida, String imagePath) {
        if(codigo!=null){
            this.codigo= codigo;
        }
        if(descripcion!=null){
            this.descripcion = descripcion;
        }
        if(categoria!=null){
            this.categoria = categoria;
        }
        if(marca!=null){
            this.marca = marca;
        }

        if(precioEntrada!=null){
            this.precioEntrada=precioEntrada;
        }

        if( precioSalida!=null){
            this.precioSalida=precioSalida;
        }
        if(imagePath!=null){
            this.imagePath= imagePath;
        }
    }

    public void desactivarProducto() {
        this.activo=false;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria=categoria;
    }


}

