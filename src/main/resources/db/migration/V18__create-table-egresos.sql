CREATE TABLE egresos (
                         egresoId bigint(20) NOT NULL AUTO_INCREMENT,
                         ingresoId bigint(20) NOT NULL,
                         productoId int(11) NOT NULL,
                         cantidad bigint(20) NOT NULL,
                         ubicacionId int(11) NOT NULL,
                         fechaEgreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         concepto varchar(255),
                         PRIMARY KEY (egresoId)
);