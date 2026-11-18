CREATE TABLE transactions (
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id           BIGINT NOT NULL,
    type              TEXT NOT NULL,
    amount            TEXT NOT NULL,
    category          TEXT NOT NULL,
    priority          TEXT NOT NULL,
    transaction_date  TEXT NOT NULL,
    planned           INTEGER NOT NULL DEFAULT 0,
    recurrent         INTEGER NOT NULL DEFAULT 0,
    recurrent_day     INTEGER,
    created_at        TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_transactions_user_date
    ON transactions(user_id, transaction_date);

CREATE INDEX idx_transactions_user_category
    ON transactions(user_id, category);