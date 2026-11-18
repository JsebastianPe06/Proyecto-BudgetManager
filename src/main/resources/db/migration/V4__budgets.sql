CREATE TABLE budgets (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id         BIGINT NOT NULL,
    month           INTEGER NOT NULL,
    year            INTEGER NOT NULL,
    total_amount    TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(user_id, month, year)
);

CREATE TABLE category_limits (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    budget_id       BIGINT NOT NULL,
    category        TEXT NOT NULL,
    limit_amount    TEXT NOT NULL,
    FOREIGN KEY (budget_id) REFERENCES budgets(id) ON DELETE CASCADE,
    UNIQUE(budget_id, category)
);