CREATE TABLE `producto` (
                            `productoId` bigint NOT NULL auto_increment,
                            `codigo` varchar(20) NOT NULL,
                            `descripcion` varchar(50) NOT NULL,
                            `categoriaId` varchar(25) NOT NULL,
                            `marca` varchar(25) DEFAULT NULL,
                            `precioEntrada` int(15) NOT NULL,
                            `precioSalida` int(15) NOT NULL,
                            `imagePath` varchar(250) NOT NULL,
                            primary key(productoId)
);