CREATE TABLE savings_transactions (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    goal_id         BIGINT NOT NULL,
    amount          TEXT NOT NULL,
    transaction_date TEXT NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES savings_goals(id) ON DELETE CASCADE
);