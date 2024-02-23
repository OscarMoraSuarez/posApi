CREATE TABLE `ubicacion` (
                            `ubicacionId`  bigint NOT NULL auto_increment,
                            `codigoUbicacion` varchar(20) NOT NULL,
                            `categoriaId` bigint NOT NULL,
                            `zona` varchar(20),
                             primary key(ubicacionId)
);