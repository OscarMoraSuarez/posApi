package com.NetTools.API.Dominio.egreso;

import com.NetTools.API.Dominio.Ingreso.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EgresoRepository extends JpaRepository<Egreso, Long > {

}
