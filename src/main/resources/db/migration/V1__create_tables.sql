CREATE TABLE artist (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    type VARCHAR(20) -- SINGER ou BAND
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    release_year INTEGER,
    artist_id INTEGER REFERENCES artist(id)
);

CREATE TABLE album_cover_images (
    album_id INTEGER REFERENCES album(id),
    cover_images VARCHAR(255)
);