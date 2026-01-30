-- 1. Remove a coluna antiga da tabela album
ALTER TABLE album DROP COLUMN IF EXISTS artist_id;

-- 2. Cria a tabela de junção
CREATE TABLE album_artist (
    album_id INTEGER REFERENCES album(id) ON DELETE CASCADE,
    artist_id INTEGER REFERENCES artist(id) ON DELETE CASCADE,
    PRIMARY KEY (album_id, artist_id)
);