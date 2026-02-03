CREATE TABLE regional (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE artist (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    type VARCHAR(20),
    regional_id INTEGER REFERENCES regional(id)
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    release_year INTEGER,
    cover_images TEXT[] 
);

CREATE TABLE album_artist (
    album_id INTEGER REFERENCES album(id) ON DELETE CASCADE,
    artist_id INTEGER REFERENCES artist(id) ON DELETE CASCADE,
    PRIMARY KEY (album_id, artist_id)
);

DO $$ 
BEGIN 
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='artist' AND column_name='regional_id') THEN
        ALTER TABLE artist ADD COLUMN regional_id INTEGER;
        ALTER TABLE artist ADD CONSTRAINT fk_artist_regional 
            FOREIGN KEY (regional_id) REFERENCES regional(id);
    END IF;
END $$;