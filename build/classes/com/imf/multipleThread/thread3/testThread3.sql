CREATE TABLE `effective` (
  `ruleEffectiveID` BIGINT NOT NULL AUTO_INCREMENT,
  `effectiveHotelID` INT NOT NULL,
  `effectDate` DATETIME NOT NULL,
  `balanceType` VARCHAR(255) NOT NULL,
  `valueData` DOUBLE NOT NULL,
  `ruleRequestID` BIGINT NOT NULL,
  `dataChange_CreateTime` DATETIME NOT NULL,
  `dataChange_LastTime` DATETIME NOT NULL,
  PRIMARY KEY  (`ruleEffectiveID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;


CREATE TABLE `Request` (
  `ruleRequestID` BIGINT NOT NULL AUTO_INCREMENT,
  `fromDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `requestHotelID` INT NOT NULL,
  `requestDate` DATETIME NOT NULL,
  `dataChange_CreateTime` DATETIME NOT NULL,
  `dataChange_LastTime` DATETIME NOT NULL,
  PRIMARY KEY  (`ruleRequestID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;
SELECT * FROM Request
SELECT COUNT(*) FROM Request
#delete from request where ruleRequestID>1
INSERT INTO Request(fromDate,endDate,STATUS,requestHotelID,requestDate,dataChange_CreateTime,dataChange_LastTime)
VALUES('2016-03-01','2016-12-30',30,101,'2016-03-01',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
SELECT * FROM Request WHERE DataChange_LastTime>='2016-03-01' AND DataChange_LastTime<='2016-03-03' ORDER BY ruleRequestID DESC LIMIT 100,100

CREATE TABLE `RequestItem` (
  `ruleRequestItemID` BIGINT NOT NULL AUTO_INCREMENT,
  `ruleRequestID` INT NOT NULL,
  `balanceType` VARCHAR(255) NOT NULL,
  `valueData` DOUBLE NOT NULL,
  `dataChange_CreateTime` DATETIME NOT NULL,
  `dataChange_LastTime` DATETIME NOT NULL,
  PRIMARY KEY  (`ruleRequestItemID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;

SELECT * FROM RequestItem
#delete from RequestItem where ruleRequestItemID >1 

INSERT INTO RequestItem(ruleRequestID,balanceType,valueData,dataChange_CreateTime,dataChange_LastTime)VALUES(1,'PP',0.4,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());