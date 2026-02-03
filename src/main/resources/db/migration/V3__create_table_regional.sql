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
(1, 'Engenheiros do Hawaii', 'Rock', 'BAND', 9),
(2, 'Skank', 'Rock/Pop', 'BAND', 22),
(3, 'Jota Quest', 'Rock/Pop', 'BAND', 42),
(4, 'Pitty', 'Rock', 'SINGER', 9),
(5, 'Charlie Brown Jr.', 'Rock/Skate', 'BAND', 9),
(6, 'CPM 22', 'Hardcore', 'BAND', 33),
(7, 'Detonautas Roque Clube', 'Rock', 'BAND', 31)
ON CONFLICT (id) DO NOTHING;

INSERT INTO album (id, title, cover_images) VALUES 
(1, '10.000 Destinos (Ao Vivo)', ARRAY[]::TEXT[]),
(2, 'Admirável Chip Novo', ARRAY[]::TEXT[]),
(3, 'Imunidade Musical', ARRAY[]::TEXT[])
ON CONFLICT (id) DO NOTHING;

INSERT INTO album_artist (album_id, artist_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (2, 4) ON CONFLICT DO NOTHING;
INSERT INTO album_artist (album_id, artist_id) VALUES (3, 5) ON CONFLICT DO NOTHING;

SELECT setval(pg_get_serial_sequence('regional', 'id'), coalesce(max(id), 1)) FROM regional;
SELECT setval(pg_get_serial_sequence('artist', 'id'), coalesce(max(id), 1)) FROM artist;
SELECT setval(pg_get_serial_sequence('album', 'id'), coalesce(max(id), 1)) FROM album;