-- V2__agregar_campo_fechaVenta_en_ventas.sql

-- Agregar el campo fechaVenta a la tabla ventas
ALTER TABLE venta
    ADD fechaVenta TIMESTAMP;