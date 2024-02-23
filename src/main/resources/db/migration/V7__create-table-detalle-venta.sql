CREATE TABLE `detalleVenta` (
                            `detalleId` bigint NOT NULL auto_increment,
                            `ventaId` bigint NOT NULL,
                            `productoId` bigint NOT NULL,
                            `cantidad` int NOT NULL,
                            `precioUnitario` float DEFAULT NULL,
                            `subtotal` float NULL,
                            `fecha` timestamp,
                             primary key(detalleId)
);