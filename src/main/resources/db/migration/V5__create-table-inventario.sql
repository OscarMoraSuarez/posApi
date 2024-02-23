CREATE TABLE `inventario` (
                           `inventarioId` bigint NOT NULL auto_increment,
                           `codigo` varchar(25) NOT NULL unique,
                           `cantidad` bigint NOT NULL,
                           primary key(inventarioId)
);