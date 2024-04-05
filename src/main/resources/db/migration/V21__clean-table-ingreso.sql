-- V2__vaciar_y_resetear_tabla_ingresos.sql

-- Vaciar la tabla ingresos
DELETE FROM ingreso;

-- Resetear los IDs de la tabla ingresos (ejemplo para MySQL)
ALTER TABLE ingreso AUTO_INCREMENT = 1;
