ALTER TABLE transactions ADD COLUMN satisfied INTEGER DEFAULT 1;
ALTER TABLE transactions ADD COLUMN reflection_note TEXT;