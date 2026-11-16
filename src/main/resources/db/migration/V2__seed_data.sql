INSERT INTO categories (name, is_universal, user_id) VALUES
('Necesidades', 1, NULL),
('Mercado', 1, NULL),
('Ocio', 1, NULL),
('Cultura', 1, NULL),
('Restaurantes', 1, NULL),
('Extras', 1, NULL);

INSERT INTO system_parameters (param_key, param_value, description) VALUES
('BASE_CURRENCY', 'COP', 'Moneda por defecto del sistema'),
('ALERT_WARNING_THRESHOLD', '0.80', 'Porcentaje de consumo para estado Advertencia'),
('ALERT_RISK_THRESHOLD', '1.00', 'Porcentaje de consumo para estado Riesgo');