CREATE TABLE savings_goals (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id         BIGINT NOT NULL,
    name            TEXT NOT NULL,
    target_amount   TEXT NOT NULL,
    current_amount  TEXT NOT NULL DEFAULT '0',
    deadline        TEXT, -- Fecha en formato String (yyyy-MM-dd)
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);