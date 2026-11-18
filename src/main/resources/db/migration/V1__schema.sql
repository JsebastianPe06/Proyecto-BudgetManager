CREATE TABLE users (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    email           TEXT    NOT NULL UNIQUE,
    password_hash   TEXT    NOT NULL,
    full_name       TEXT    NOT NULL,
    role            TEXT    NOT NULL,
    locked_until    TEXT,                        -- LocalDateTime en SQLite se guarda como TEXT
    failed_attempts INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE system_parameters (
    param_key   TEXT PRIMARY KEY NOT NULL,
    param_value TEXT NOT NULL
);