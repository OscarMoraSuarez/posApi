CREATE TABLE `venta` (
                              `ventaId` bigint NOT NULL auto_increment,
                              `numeroVenta` bigint,
                              `subtotal` double NOT NULL,
                              `descuento` double NOT NULL,
                              `total` double NOT NULL,
                              primary key(ventaId)
);