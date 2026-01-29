CREATE TABLE regional (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

ALTER TABLE artist ADD COLUMN regional_id INTEGER REFERENCES regional(id);

-- Inserindo dados de exemplo para bater com o formato esperado
INSERT INTO regional (nome, ativo) VALUES ('Sul', true);
INSERT INTO regional (nome, ativo) VALUES ('Sudeste', true);
INSERT INTO regional (nome, ativo) VALUES ('Nordeste', true);

