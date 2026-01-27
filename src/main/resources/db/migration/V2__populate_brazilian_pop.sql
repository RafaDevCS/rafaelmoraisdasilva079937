-- Inserindo Artistas
INSERT INTO artist (name, genre, type) VALUES ('Sandy & Junior', 'Pop', 'BAND');
INSERT INTO artist (name, genre, type) VALUES ('Rouge', 'Pop', 'BAND');
INSERT INTO artist (name, genre, type) VALUES ('Kelly Key', 'Pop', 'SINGER');
INSERT INTO artist (name, genre, type) VALUES ('Wanessa Camargo', 'Pop', 'SINGER');
INSERT INTO artist (name, genre, type) VALUES ('KLB', 'Pop', 'BAND');

-- Inserindo Álbuns (IDs baseados na ordem de inserção acima)
INSERT INTO album (title, release_year, artist_id) VALUES ('As Quatro Estações', 1999, 1);
INSERT INTO album (title, release_year, artist_id) VALUES ('Sandy & Junior (2001)', 2001, 1);
INSERT INTO album (title, release_year, artist_id) VALUES ('Rouge', 2002, 2);
INSERT INTO album (title, release_year, artist_id) VALUES ('C''est La Vie', 2003, 2);
INSERT INTO album (title, release_year, artist_id) VALUES ('Kelly Key', 2001, 3);
INSERT INTO album (title, release_year, artist_id) VALUES ('W', 2005, 4);
INSERT INTO album (title, release_year, artist_id) VALUES ('KLB (2000)', 2000, 5);