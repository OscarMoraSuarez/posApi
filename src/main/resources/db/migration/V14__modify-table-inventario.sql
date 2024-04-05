-- Eliminar la columna 'codigo'
ALTER TABLE inventario DROP COLUMN codigo;

-- Agregar la columna 'productoId' de tipo int
ALTER TABLE inventario ADD productoId INT NOT NULL;

-- Agregar la columna 'ubicacionId' de tipo int
ALTER TABLE inventario ADD ubicacionId INT NOT NULL;
