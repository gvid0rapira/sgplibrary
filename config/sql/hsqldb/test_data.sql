
-- Карточка
INSERT INTO card (reg_num, name)
    VALUES (1, 'Стандарт 1');
INSERT INTO std_card (id, doc_code)
    VALUES (
    (SELECT id FROM card WHERE reg_num = 1), 
    'СТД 0001');

-- Версия документа
INSERT INTO doc_version (appro_date, card_id)
    VALUES ('2010-05-07', 
    (SELECT id FROM card WHERE reg_num = 1));

-- Содержимое версии
INSERT INTO content (file_name, version_id, content_type_id, content)
    VALUES ('сто0001.tif', 
    (SELECT id FROM doc_version WHERE appro_date = '2010-05-07'),
    (SELECT id FROM content_type WHERE mime = 'image/tiff'),
    X'1abACD34');