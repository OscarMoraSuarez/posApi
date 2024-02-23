package com.NetTools.API.Dominio.ubicacion;

import com.NetTools.API.Dominio.categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="ubicacion")
@Entity(name="Ubicacion")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="ubicacionId")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ubicacionId;
    private String codigoUbicacion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoriaId")
    private Categoria categoria;
    private String zona;

    public Ubicacion(String codigoUbicacion,Categoria categoria, String zona) {

        this.codigoUbicacion=codigoUbicacion;
        this.categoria=categoria;
        this.zona=zona;


    }
}
