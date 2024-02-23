package com.NetTools.API.Dominio.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="categoria")
@Entity(name="categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    @Column(name = "nombreCategoria")
    private String nombreCategoria;

    public Categoria(DatosRegistroCategoria datosRegistroCategoria){
    this.nombreCategoria= datosRegistroCategoria.nombreCategoria();
    }
    public Categoria(String nombreCategoria){
        this.nombreCategoria=nombreCategoria;
    }


}
