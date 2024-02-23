CREATE TABLE `ingreso` (
                             `inventarioId` bigint NOT NULL auto_increment,
                             `productoId` bigint NOT NULL,
                             `cantidad` bigint NOT NULL,
                             `ubicacionId`bigint NOT NULL,
                             primary key(inventarioId)
);