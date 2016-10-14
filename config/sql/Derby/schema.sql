
--DROP TABLE lib.doc_send_list;
--DROP TABLE lib.department;
--DROP TABLE lib.content;
--DROP TABLE lib.doc_version;
--DROP TABLE lib.std_card;
--DROP TABLE lib.card;
--DROP TABLE lib.content_type;

--DROP SCHEMA lib RESTRICT;
CREATE SCHEMA lib AUTHORIZATION sa;

CREATE TABLE  lib.content_type (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  ext VARCHAR(3) NOT NULL,
  mime VARCHAR(50) NOT NULL,
  CONSTRAINT content_type_pk PRIMARY KEY (id)
);

CREATE TABLE  lib.card (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  reg_num BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL,
  CONSTRAINT card_pk PRIMARY KEY (id)
);

CREATE TABLE  lib.std_card (
  id BIGINT NOT NULL,
  doc_code VARCHAR(30) NOT NULL,
  CONSTRAINT std_card_pk PRIMARY KEY (id),
  CONSTRAINT std_card_fk FOREIGN KEY (id) REFERENCES lib.card
    ON DELETE CASCADE
);

CREATE TABLE  lib.doc_version (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  appro_date DATE NOT NULL,
  card_id BIGINT NOT NULL,
  CONSTRAINT doc_version_pk PRIMARY KEY (id),
  CONSTRAINT doc_version_fk FOREIGN KEY (card_id) REFERENCES lib.card
    ON DELETE CASCADE
);

CREATE TABLE lib.content (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  file_name VARCHAR(30) NOT NULL,
  content BLOB(16M) NOT NULL,
  version_id bigint NOT NULL,
  content_type_id bigint NOT NULL,
  CONSTRAINT content_pk PRIMARY KEY (id),
  CONSTRAINT content_content_type_fk FOREIGN KEY (content_type_id) REFERENCES lib.content_type,
  CONSTRAINT content_doc_version_fk FOREIGN KEY (version_id) REFERENCES lib.doc_version
    ON DELETE CASCADE
);

CREATE TABLE  lib.department (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(200) NOT NULL,
  number VARCHAR(18) NOT NULL,
  CONSTRAINT department_pk PRIMARY KEY (id)
);

CREATE TABLE  lib.doc_send_record (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  send_date DATE NOT NULL,
  version_id BIGINT NOT NULL,
  dep_id BIGINT NOT NULL,
  CONSTRAINT doc_send_record_pk PRIMARY KEY (id),
  CONSTRAINT doc_send_record_doc_version_fk FOREIGN KEY (version_id) REFERENCES lib.doc_version
    ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT doc_send_record_department_fk FOREIGN KEY (dep_id) REFERENCES lib.department
    ON DELETE CASCADE ON UPDATE RESTRICT
);