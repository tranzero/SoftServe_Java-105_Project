DROP DATABASE IF EXISTS `test`;
CREATE DATABASE  IF NOT EXISTS `test`;
 use `test`;
-- 
-- Table structure for table `lines`
-- 

DROP TABLE IF EXISTS `lines`;
CREATE TABLE `lines` (
    `LINEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINENAME` VARCHAR (100) NOT NULL ,
    PRIMARY KEY (LINEID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `lines`
-- 
LOCK TABLES `lines` WRITE;
/*!40000 ALTER TABLE `lines` DISABLE KEYS */;
INSERT INTO `lines` VALUES ('1', 'L\'viv - Stryy'), ('2', 'Stryy - L\'viv'), ('3', 'Pisochne - Sknyliv');
/*!40000 ALTER TABLE `lines` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `orders`
-- 

-- 
-- Table structure for table `posts`
-- 

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
    `POSTID` INT (10) NOT NULL AUTO_INCREMENT, 
    `TITLE` VARCHAR (100) NULL , 
    `DESCRIPTION` VARCHAR (1000) NULL , 
    `DATE` DATE NULL ,
    PRIMARY KEY (POSTID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `posts`
-- 
LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES ('1', 'TestNews', 'TestTestTestTestTestTestTestTest', '2003-01-20'), ('2', 'Merkel condemns US over EU insult', 'U insultPolice in Kiev, 6 FebMasked protesters march through Kiev, 6 FebruaryPrevious image | Pause | Next image3 / 4Germany\'s Angela Merkel says a US official\'s apparent insult of the EU\'s work in Ukraine in a leaked recording is "totally unacceptable". ', '2014-02-07'), ('3', 'Earliest UK human footprints found', 'Scientists discover the earliest evidence of human footprints outside of Africa on the Norfolk coast in Eastern England. ', '2014-02-07'), ('4', 'Sochi prepares for opening ceremony', 'The costliest Winter Olympics in history officially open in Russia on Friday with a lavish ceremony in Sochi.', '2014-02-07'), ('5', 'Environment chief faces floods anger', 'There is anger directed at Environment Agency chairman Lord Smith ahead of his visit to the flood-hit Somerset Levels.', '2014-02-07'), ('6', 'Cameron: Seven months to save UK', 'David Cameron makes an emotional appeal to Scottish voters to "save the most extraordinary country in history" in September\'s independence vote.', '2014-02-07'), ('7', 'Violent clashes at Rio fares protest', 'Hundreds of Brazilian demonstrators clash with riot police in Rio de Janeiro, in a protest against a rise in public transport fares. ', '2014-02-07'), ('8', 'Mass grave found in Michoacan state', 'Mexico police find four severed heads and a mass grave in the troubled western state of Michoacan, where vigilantes are fighting a notorious drug cartel. ', '2014-02-07'), ('9', 'Pacific castaway health worsens', 'The castaway who says he survived more than a year adrift in the Pacific has been readmitted to hospital for health checks. ', '2014-02-07'), ('10', 'Anderson film opens Berlin festival', 'Wes Anderson\'s latest movie The Grand Budapest Hotel opens the Berlin Film Festival to rave reviews.', '2014-02-07'), ('11', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '2014-02-07'), ('12', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '2014-02-07'), ('13', 'National Gallery buys first US work', 'The National Gallery makes its first ever acquisition of a painting by an American artist - a 1912 work by George Bellows. ', '2014-02-07'), ('14', '\'No target\' in UK animal tests plan', 'The UK government launches its plan to replace, refine and reduce animals in research, but campaigners are disappointed. ', '2014-02-07'), ('15', 'Salmon born with \'magnetic map\'', 'Scientists believe that Pacific salmon sense changes in intensity and angle of the Earth\'s magnetic field to find their way in the ocean.', '2014-02-07'), ('16', 'US military funds \'vanishing\' tech', 'The US military is funding a project to develop electronics that can self-destruct like the secret messages in the Mission Impossible TV show. ', '2014-02-07');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `routes`
-- 

DROP TABLE IF EXISTS `routes`;
CREATE TABLE `routes` (
    `ROUTEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINEID` INT (10) unsigned NOT NULL , 
    `ROUTECODE` VARCHAR (20) NOT NULL , 
    `STARTTIME` TIME NOT NULL ,
    PRIMARY KEY (ROUTEID),
    CONSTRAINT `ROUTES_ibfk_1`
    FOREIGN KEY (`LINEID`)
    REFERENCES `lines` (`LINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `routes`
-- 
LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES ('1', '1', '1000000000001', '17:38:00'), ('2', '2', '1000000000002', '05:20:00'), ('3', '2', '1000000000044', '04:20:00');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stations`
-- 

DROP TABLE IF EXISTS `stations`;
CREATE TABLE `stations` (
    `STATIONID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `STATIONCODE` VARCHAR (20) NOT NULL , 
    `STATIONNAME` VARCHAR (100) NOT NULL ,
    PRIMARY KEY (STATIONID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stations`
-- 
LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES ('1', '0000000000001', 'Stryy'), ('2', '0000000000002', 'Ugers\'ko'), ('3', '0000000000003', 'Pyatnychany'), ('4', '0000000000004', 'Bil\'che-Vol.'), ('5', '0000000000005', 'Pisochne'), ('6', '0000000000006', 'Myckolayiv-Dnist.'), ('7', '0000000000007', 'Zadorognia'), ('8', '0000000000008', 'Cherkasy-Lv.'), ('9', '0000000000009', 'Dmytriya'), ('10', '0000000000010', 'Shchirets\'-1'), ('11', '0000000000011', 'Shchirets\'-2'), ('12', '0000000000012', 'Semenivka'), ('13', '0000000000013', 'Z.P. Pustomyty'), ('14', '0000000000014', 'Glynna-Navaria'), ('15', '0000000000015', 'Oboroshin'), ('16', '0000000000016', 'Sknyliv'), ('17', '0000000000017', 'L\'viv');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stationsonlines`
-- 

DROP TABLE IF EXISTS `stationsonlines`;
CREATE TABLE `stationsonlines` (
    `STATIONONLINEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINEID` INT (10) unsigned NOT NULL , 
    `STATIONID` INT (10) unsigned NOT NULL , 
    `STATIONORDERNUM` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (STATIONONLINEID),
    CONSTRAINT `STATIONSONLINES_ibfk_1`
    FOREIGN KEY (`LINEID`)
    REFERENCES `lines` (`LINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `STATIONSONLINES_ibfk_2`
    FOREIGN KEY (`STATIONID`)
    REFERENCES `stations` (`STATIONID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stationsonlines`
-- 
LOCK TABLES `stationsonlines` WRITE;
/*!40000 ALTER TABLE `stationsonlines` DISABLE KEYS */;
INSERT INTO `stationsonlines` VALUES ('1', '1', '17', '1'), ('2', '1', '16', '2'), ('3', '1', '15', '3'), ('4', '1', '14', '4'), ('5', '1', '13', '5'), ('6', '1', '12', '6'), ('7', '1', '11', '7'), ('8', '1', '10', '8'), ('9', '1', '9', '9'), ('10', '1', '8', '10'), ('11', '1', '7', '11'), ('12', '1', '6', '12'), ('13', '1', '5', '13'), ('14', '1', '4', '14'), ('15', '1', '3', '15'), ('16', '1', '2', '16'), ('17', '1', '1', '17'), ('18', '2', '1', '1'), ('19', '2', '2', '2'), ('20', '2', '3', '3'), ('21', '2', '4', '4'), ('22', '2', '5', '5'), ('23', '2', '6', '6'), ('24', '2', '7', '7'), ('25', '2', '8', '8'), ('26', '2', '9', '9'), ('27', '2', '10', '10'), ('28', '2', '11', '11'), ('29', '2', '12', '12'), ('30', '2', '13', '13'), ('31', '2', '14', '14'), ('32', '2', '15', '15'), ('33', '2', '16', '16'), ('34', '2', '17', '17'), ('38', '3', '5', '1'), ('39', '3', '16', '2');
/*!40000 ALTER TABLE `stationsonlines` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stops`
-- 

DROP TABLE IF EXISTS `stops`;
CREATE TABLE `stops` (
    `STOPID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `ROUTEID` INT (10) unsigned NOT NULL , 
    `STATIONONLINEID` INT (10) unsigned NOT NULL , 
    `ARRIVAL` TIME NOT NULL , 
    `DEPARTURE` TIME NOT NULL ,
    PRIMARY KEY (STOPID),
    CONSTRAINT `STOPS_ibfk_1`
    FOREIGN KEY (`ROUTEID`)
    REFERENCES `routes` (`ROUTEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `STOPS_ibfk_2`
    FOREIGN KEY (`STATIONONLINEID`)
    REFERENCES `stationsonlines` (`STATIONONLINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stops`
-- 
LOCK TABLES `stops` WRITE;
/*!40000 ALTER TABLE `stops` DISABLE KEYS */;
INSERT INTO `stops` VALUES ('1', '1', '1', '00:00:00', '00:00:00'), ('2', '1', '2', '00:09:00', '00:10:00'), ('3', '1', '3', '00:12:00', '00:13:00'), ('4', '1', '4', '00:24:00', '00:25:00'), ('5', '1', '7', '00:37:00', '00:38:00'), ('6', '1', '12', '01:01:00', '01:02:00'), ('7', '1', '13', '01:09:00', '01:10:00'), ('8', '1', '14', '01:17:00', '01:18:00'), ('9', '1', '17', '02:33:00', '00:00:00'), ('10', '2', '18', '00:00:00', '00:00:00'), ('11', '2', '21', '00:17:00', '00:18:00'), ('12', '2', '22', '00:25:00', '00:26:00'), ('13', '2', '23', '00:33:00', '00:34:00'), ('14', '2', '28', '01:00:00', '01:02:00'), ('15', '2', '31', '01:15:00', '01:16:00'), ('16', '2', '32', '01:23:00', '01:24:00'), ('17', '2', '33', '01:30:00', '01:31:00'), ('18', '2', '34', '01:41:00', '00:00:00'), ('19', '3', '38', '00:00:00', '00:00:00'), ('20', '3', '39', '01:10:00', '00:00:00');
/*!40000 ALTER TABLE `stops` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `transports`
-- 

DROP TABLE IF EXISTS `transports`;
CREATE TABLE `transports` (
    `TRANSPORTID` INT (10) NOT NULL AUTO_INCREMENT, 
    `ROUTEID` INT (10) unsigned NOT NULL , 
    `TRANSPORTCODE` VARCHAR (20) NOT NULL , 
    `STARTTIME` TIME NOT NULL , 
    `SEATCLASS1` INT (10) NOT NULL , 
    `SEATCLASS2` INT (10) NOT NULL , 
    `SEATCLASS3` INT (10) NOT NULL , 
    `GENPRICE` DECIMAL (7, 2) NOT NULL ,
    PRIMARY KEY (TRANSPORTID),
    CONSTRAINT `ROUTES_ROUTEID_fk`
    FOREIGN KEY (`ROUTEID`)
    REFERENCES `routes` (`ROUTEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `transports`
-- 
LOCK TABLES `transports` WRITE;
/*!40000 ALTER TABLE `transports` DISABLE KEYS */;
INSERT INTO `transports` VALUES ('1', '1', 'T000000001', '12:00:00', '10', '50', '150', '25.00'), ('2', '1', 'T000000001', '16:00:00', '10', '50', '150', '25.00'), ('3', '2', 'T000000002', '12:00:00', '10', '50', '150', '25.00'), ('4', '2', 'T000000002', '16:00:00', '10', '50', '150', '25.00'), ('5', '3', 'T000000044', '16:00:00', '10', '50', '150', '25.00');
/*!40000 ALTER TABLE `transports` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `trips`
-- 

DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips` (
    `TRIPID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `TRANSPORTID` INT (10) NOT NULL , 
    `REMSEATCLASS1` INT (10) NOT NULL , 
    `REMSEATCLASS2` INT (10) NOT NULL , 
    `REMSEATCLASS3` INT (10) NOT NULL , 
    `STARTDATE` DATE NOT NULL ,
    PRIMARY KEY (TRIPID),
    CONSTRAINT `transports_transportid_fk`
    FOREIGN KEY (`TRANSPORTID`)
    REFERENCES `transports` (`TRANSPORTID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `trips`
-- 
LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
INSERT INTO `trips` VALUES ('1', '1', '10', '50', '150', '2013-02-20'), ('2', '1', '10', '50', '150', '2013-02-21'), ('3', '1', '10', '50', '150', '2013-02-22'), ('4', '1', '10', '50', '150', '2013-02-23'), ('5', '2', '10', '50', '150', '2013-02-20'), ('6', '2', '10', '50', '150', '2013-02-21'), ('7', '2', '10', '50', '150', '2013-02-22'), ('8', '2', '10', '50', '150', '2013-02-23'), ('9', '3', '10', '50', '150', '2013-02-20'), ('10', '3', '10', '50', '150', '2013-02-21'), ('11', '3', '10', '50', '150', '2013-02-22'), ('12', '3', '10', '50', '150', '2013-02-23'), ('13', '4', '10', '50', '150', '2013-02-20'), ('14', '4', '10', '50', '150', '2013-02-21'), ('15', '4', '10', '50', '150', '2013-02-22'), ('16', '4', '10', '50', '150', '2013-02-23');
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `users`
-- 

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `USERID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERNAME` VARCHAR (100) NOT NULL , 
    `FIRSTNAME` VARCHAR (100) NOT NULL , 
    `LASTNAME` VARCHAR (100) NOT NULL , 
    `EMAIL` VARCHAR (100) NOT NULL , 
    `PASSWD` VARCHAR (100) NOT NULL , 
    `REGDATE` TIMESTAMP NOT NULL , 
    `ROLE` enum('REGUSER','MANAGER','ADMIN') NOT NULL ,
    PRIMARY KEY (USERID),
    UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `users`
-- 
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1', 'tanzero', 'Oleg', 'Lesniak', 'test@mail.first', '', '2014-01-14 03:41:35.0', 'REGUSER'), ('2', 'yherasym', 'Yaroslav', 'Herasym', 'test@mail.second', '', '2014-01-14 03:42:46.0', 'MANAGER'), ('3', 'mychaylo.partyka', 'Mychaylo', 'Partyka', 'test@mail.third', '', '2014-01-14 03:43:46.0', 'ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `ORDERID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERID` INT (10) unsigned NOT NULL , 
    `TRIPID` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (ORDERID),
    CONSTRAINT `ORDERID_ibfk_1`
    FOREIGN KEY (`USERID`)
    REFERENCES `users` (`USERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `ORDERID_ibfk_2`
    FOREIGN KEY (`TRIPID`)
    REFERENCES `trips` (`TRIPID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `orders`
-- 
LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('1', '1', '1'), ('2', '1', '1'), ('3', '1', '2'), ('4', '3', '2'), ('5', '2', '3');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;