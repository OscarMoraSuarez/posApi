package com.NetTools.API.Dominio.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    Inventario findByCodigo(String codigo);

}
