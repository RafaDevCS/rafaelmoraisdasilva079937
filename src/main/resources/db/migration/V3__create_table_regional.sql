/* DO $$ 
BEGIN 
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='artist' AND column_name='regional_id') THEN
        ALTER TABLE artist ADD COLUMN regional_id INTEGER REFERENCES regional(id);
    END IF;
END $$; */

INSERT INTO regional (id, nome, ativo) VALUES 
(9, 'REGIONAL DE CUIABÁ', true), 
(31, 'REGIONAL DE GUARANTÃ DO NORTE', true),
(32, 'REGIONAL DE VILA RICA', true), 
(39, 'LDAPREGIONAL', true),
(27, 'DGPJCM - SEDE', true), 
(42, 'REGIONALDAP01', true),
(43, 'REGIONALLDAP02', true), 
(11, 'REGIONAL DE AGUA BOA', true),
(13, 'REGIONAL DE ALTO ARAGUAIA', true), 
(14, 'REGIONAL DE BARRA DO GARÇAS', true),
(15, 'REGIONAL DE CÁCERES', true), 
(10, 'REGIONAL DE VÁRZEA GRANDE', true),
(12, 'REGIONAL DE ALTA FLORESTA', true), 
(16, 'REGIONAL DE NOVA MUTUM', true),
(17, 'REGIONAL DE JUÍNA', true), 
(18, 'REGIONAL DE PONTES E LACERDA', true),
(19, 'REGIONAL DE CONFRESA', true), 
(20, 'REGIONAL DE RONDONÓPOLIS', true),
(21, 'REGIONAL DE SINOP', true), 
(22, 'REGIONAL DE TANGARÁ DA SERRA', true),
(29, 'REGIONAL DE PRIMAVERA DO LESTE', true), 
(30, 'REGIONAL DE PEIXOTO DE AZEVEDO', true),
(28, 'COORDENADORIA DE POLÍCIA COMUNITÁRIA', true), 
(33, 'COORDENADORIA METROPOLITANA DE PLANTÕES POLICIAIS', true),
(2, ' NÃO SE APLICA - CORREGEPOL', true), 
(4, ' NÃO SE APLICA - DAE', true),
(5, ' NÃO SE APLICA - DEE', true), 
(6, ' NÃO SE APLICA - DI', true),
(7, ' NÃO SE APLICA - DGPJCA', true), 
(8, ' NÃO SE APLICA - OUVIDORIA', true),
(23, ' NÃO SE APLICA - DGPJC', true), 
(1, ' NÃO SE APLICA - ACADEPOL', true),
(26, ' DGPJCI - SEDE', true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO artist (id, name, genre, type, regional_id) VALUES 
(1, 'Serj Tankian', 'Rock', 'BANDA', 9),
(2, 'Mike Shinoda', 'Rock/Pop', 'BANDA', 22),
(3, 'Michel Teló', 'Rock/Pop', 'SOLO', 42),
(4, 'Guns N’ Roses', 'Rock', 'BANDA', 9)
ON CONFLICT (id) DO NOTHING;

INSERT INTO album (id, title, cover_images) VALUES 
(1, 'Harakiri', ARRAY[]::TEXT[]),
(2, 'Black Blooms', ARRAY[]::TEXT[]),
(3, 'The Rough Dog', ARRAY[]::TEXT[]),
(4, 'The Rising Tied', ARRAY[]::TEXT[]),
(5, 'Post Traumatic', ARRAY[]::TEXT[]),
(6, 'Post Traumatic EP', ARRAY[]::TEXT[]),
(7, 'Where’d You Go', ARRAY[]::TEXT[]),
(8, 'Bem Sertanejo', ARRAY[]::TEXT[]),
(9, 'Bem Sertanejo - O Show (Ao Vivo)', ARRAY[]::TEXT[]),
(10, 'Bem Sertanejo - (1ª Temporada) - EP', ARRAY[]::TEXT[]),
(11, 'Use Your Illusion I', ARRAY[]::TEXT[]),
(12, 'Use Your Illusion II', ARRAY[]::TEXT[]),
(13, 'Greatest Hits', ARRAY[]::TEXT[])
ON CONFLICT (id) DO NOTHING;

INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (3, 1) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (4, 2) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (5, 2) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (6, 2) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (7, 2) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (8, 3) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (9, 3) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (10, 3) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (11, 4) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (12, 4) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (13, 4) ON CONFLICT DO NOTHING;

SELECT setval(pg_get_serial_sequence('regional', 'id'), coalesce(max(id), 1)) FROM regional;
SELECT setval(pg_get_serial_sequence('artist', 'id'), coalesce(max(id), 1)) FROM artist;
SELECT setval(pg_get_serial_sequence('album', 'id'), coalesce(max(id), 1)) FROM album;