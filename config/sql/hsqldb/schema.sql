-- ”дал€ютс€ все объекты схемы, но не сама схема.
DROP SCHEMA IF EXISTS public CASCADE;
 
CREATE TABLE  content_type (
  id BIGINT NOT NULL IDENTITY,
  ext VARCHAR(3) NOT NULL,
  mime VARCHAR(50) NOT NULL
);

CREATE TABLE  card (
  id BIGINT NOT NULL IDENTITY,
  reg_num BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE  std_card (
  ID BIGINT NOT NULL,
  DOC_CODE VARCHAR(30) NOT NULL,
  FOREIGN KEY (ID) REFERENCES card
    ON DELETE CASCADE
);

CREATE TABLE  doc_version (
  id BIGINT NOT NULL IDENTITY,
  appro_date DATE NOT NULL,
  card_id BIGINT NOT NULL,
  FOREIGN KEY (card_id) REFERENCES card
);

CREATE TABLE content (
  id BIGINT NOT NULL IDENTITY,
  file_name VARCHAR(30) NOT NULL,
  content BLOB(16M) NOT NULL,
  version_id bigint NOT NULL,
  content_type_id bigint NOT NULL,
  FOREIGN KEY (content_type_id) REFERENCES content_type,
  FOREIGN KEY (version_id) REFERENCES doc_version
);

CREATE TABLE  department (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR(200) NOT NULL,
  number VARCHAR(18) NOT NULL
);

CREATE TABLE  doc_send_list (
  version_id BIGINT NOT NULL,
  dep_id BIGINT NOT NULL,
  send_date DATE NOT NULL,
  FOREIGN KEY (version_id) REFERENCES doc_version,
  FOREIGN KEY (dep_id) REFERENCES department
);