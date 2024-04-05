package com.NetTools.API.Dominio.Inventario;


import com.NetTools.API.Dominio.producto.Producto;
import com.NetTools.API.Dominio.ubicacion.Ubicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="inventario")
@Entity(name = "Inventario")
public class Inventario {

@Id
@ GeneratedValue(strategy = GenerationType.IDENTITY)
private Long inventarioId;
private int cantidad;
private Long  productoId;
private Long ubicacionId;
private String productoCodigo;




}
