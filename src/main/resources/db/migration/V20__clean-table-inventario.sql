-- V1__vaciar_y_resetear_tabla_inventario.sql

-- Vaciar la tabla inventario
DELETE FROM inventario;

-- Resetear los IDs de la tabla inventario (ejemplo para MySQL)
ALTER TABLE inventario AUTO_INCREMENT = 1;