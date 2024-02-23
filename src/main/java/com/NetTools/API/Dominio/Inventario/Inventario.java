package com.NetTools.API.Dominio.Inventario;


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
private String codigo;
private int cantidad;


}
