
-- Карточка
INSERT INTO lib.card (reg_num, name)
    VALUES (1, 'Стандарт 1');
INSERT INTO lib.std_card (id, doc_code)
    VALUES (
    (SELECT id FROM lib.card WHERE reg_num = 1), 
    'СТД 0001');

-- Версия документа
INSERT INTO lib.doc_version (appro_date, card_id)
    VALUES ('2010-05-07', 
    (SELECT id FROM lib.card WHERE reg_num = 1));

-- Содержимое версии
INSERT INTO lib.content (file_name, version_id, content_type_id, content)
    VALUES ('сто0001.tif', 
    (SELECT id FROM lib.doc_version WHERE appro_date = '2010-05-07'),
    (SELECT id FROM lib.content_type WHERE mime = 'image/tiff'),
    CAST (X'FFFF' AS BLOB));