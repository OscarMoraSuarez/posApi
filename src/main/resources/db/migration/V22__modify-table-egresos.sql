-- V3__eliminar_columna_ingresoId_en_egresos.sql

-- Eliminar la columna ingresoId de la tabla egresos
ALTER TABLE egresos DROP COLUMN ingresoId;
