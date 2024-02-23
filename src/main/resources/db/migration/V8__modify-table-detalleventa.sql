-- Modificar nombres y tipos de las columnas en la tabla detalleVenta
ALTER TABLE detalleVenta
    CHANGE COLUMN ventaId numeroVenta bigint NOT NULL,
    CHANGE COLUMN productoId codigoProducto varchar(255) NOT NULL,
    DROP COLUMN fecha;
-- Modificar la clave primaria
ALTER TABLE detalleVenta
    DROP PRIMARY KEY,
    ADD PRIMARY KEY(detalleId);