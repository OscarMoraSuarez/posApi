package db.migration

DELETE FROM flyway_schema_history WHERE success = FALSE;
