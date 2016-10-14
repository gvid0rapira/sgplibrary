DROP TABLE IF EXISTS `sgplibrary`.`content_type`;
CREATE TABLE  `sgplibrary`.`content_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EXT` varchar(3) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sgplibrary`.`document`;
CREATE TABLE  `sgplibrary`.`document` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DTYPE` varchar(31) DEFAULT NULL,
  `NAME` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sgplibrary`.`document_content`;
CREATE TABLE  `sgplibrary`.`document_content` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FILE_NAME` varchar(30) NOT NULL,
  `CONTENT` longblob NOT NULL,
  `DOCUMENT_ID` bigint(20) NOT NULL,
  `CONTENT_TYPE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOCUMENT_CONTENT_DOCUMENT_ID` (`DOCUMENT_ID`),
  KEY `FK_DOCUMENT_CONTENT_CONTENT_TYPE_ID` (`CONTENT_TYPE_ID`),
  CONSTRAINT `FK_DOCUMENT_CONTENT_CONTENT_TYPE_ID` FOREIGN KEY (`CONTENT_TYPE_ID`) REFERENCES `content_type` (`ID`),
  CONSTRAINT `FK_DOCUMENT_CONTENT_DOCUMENT_ID` FOREIGN KEY (`DOCUMENT_ID`) REFERENCES `document` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sgplibrary`.`std_document`;
CREATE TABLE  `sgplibrary`.`std_document` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_STD_DOCUMENT_ID` FOREIGN KEY (`ID`) REFERENCES `document` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--DROP TABLE IF EXISTS `sgplibrary`.`sequence`;
--CREATE TABLE  `sgplibrary`.`sequence` (
--  `SEQ_NAME` varchar(50) NOT NULL,
--  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
--  PRIMARY KEY (`SEQ_NAME`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;
