DROP DATABASE IF EXISTS `test`;
CREATE DATABASE  IF NOT EXISTS `test`;
 use `test`;

-- 
-- Table structure for table `posts`
-- 

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
    `POSTID` INT (10) NOT NULL AUTO_INCREMENT, 
    `TITLE` VARCHAR (100) NULL , 
    `DESCRIPTION` VARCHAR (1000) NULL , 
    `DATE` VARCHAR (60) NULL , 
    `IMGSRC` VARCHAR (60) NULL ,
    PRIMARY KEY (POSTID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `posts`
-- 
LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES ('2', 'Merkel condemns US over EU insult', 'U insultPolice in Kiev, 6 FebMasked protesters march through Kiev, 6 FebruaryPrevious image | Pause | Next image3 / 4Germanys Angela Merkel says a US officials apparent insult of the EUs work in Ukraine in a leaked recording is "totally unacceptable". ', '2014-03-05 11:19:46.729', 'Jellyfish.jpg'), ('3', 'Earliest UK human footprints found', 'Scientists discover the earliest evidence of human footprints outside of Africa on the Norfolk coast in Eastern England. ', '2014-03-03 12:40:07.875', ''), ('4', 'Sochi prepares for opening ceremony', 'The costliest Winter Olympics in history officially open in Russia on Friday with a lavish ceremony in Sochi.', '2014-03-05 11:58:58.237', 'unflagged.png'), ('5', 'Environment chief faces floods anger', 'There is anger directed at Environment Agency chairman Lord Smith ahead of his visit to the flood-hit Somerset Levels.', '2014-02-21 15:18:52', NULL), ('6', 'Cameron: Seven months to save UK', 'David Cameron makes an emotional appeal to Scottish voters to "save the most extraordinary country in history" in Septembers independence vote.', '2014-03-03 12:40:12.821', ''), ('7', 'Violent clashes at Rio fares protest', 'Hundreds of Brazilian demonstrators clash with riot police in Rio de Janeiro, in a protest against a rise in public transport fares. ', '2014-03-03 12:40:10.511', ''), ('8', 'Mass grave found in Michoacan state', 'Mexico police find four severed heads and a mass grave in the troubled western state of Michoacan, where vigilantes are fighting a notorious drug cartel. ', '2014-03-05 11:22:42.948', 'IP-banner.jpg'), ('9', 'Pacific castaway health worsens', 'The castaway who says he survived more than a year adrift in the Pacific has been readmitted to hospital for health checks. ', '2014-03-05 13:58:56.548', '1AqEe6CNFXk.jpg'), ('10', 'Anderson film opens Berlin festival', 'Wes Andersons latest movie The Grand Budapest Hotel opens the Berlin Film Festival to rave reviews.', '2014-03-05 11:21:33.232', 'Tulips.jpg'), ('11', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '2014-03-05 11:20:41.688', 'Lighthouse.jpg'), ('12', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '2014-03-05 11:20:23.783', 'Koala.jpg'), ('13', 'National Gallery buys first US work', 'The National Gallery makes its first ever acquisition of a painting by an American artist - a 1912 work by George Bellows. ', '2014-03-05 11:18:49.139', 'Chrysanthemum.jpg'), ('14', '\'No target\' in UK animal tests plan', 'The UK government launches its plan to replace, refine and reduce animals in research, but campaigners are disappointed. ', '2014-03-05 11:19:18.86', 'Desert.jpg'), ('15', 'Salmon born with \'magnetic map\'', 'Scientists believe that Pacific salmon sense changes in intensity and angle of the Earths magnetic field to find their way in the ocean.', '2014-03-05 11:19:32.342', 'Hydrangeas.jpg'), ('16', 'US military funds vanishing tech', 'The US military is funding a project to develop electronics that can self-destruct like the secret messages in the Mission Impossible TV show. ', '2014-03-05 11:20:06.336', 'Jellyfish.jpg'), ('27', 'LesnyakOleg', 'LesnyakOlegLesnyakOlegLesnyakOlegLesnyakOleg', '2014-03-03 12:38:23.089', 'LesnyakOleg.jpg');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;


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
INSERT INTO `lines` VALUES ('1', 'L\'viv - Stryy'), ('2', 'Stryy - L\'viv'), ('3', 'Pisochne - Sknyliv'), ('4', 'L\'viv - Stryy1'), ('5', 'Stryy - L\'viv1'), ('6', 'Pisochne - Sknyliv1'), ('7', 'L\'viv - Stryy2'), ('8', 'Stryy - L\'viv2'), ('9', 'Pisochne - Sknyliv2'), ('10', 'L\'viv - Stryy3'), ('11', 'Stryy - L\'viv3'), ('12', 'Pisochne - Sknyliv3'), ('13', 'L\'viv - Stryy4'), ('14', 'L\'viv - Stryy5'), ('15', 'L\'viv - Stryy6'), ('16', 'Stryy - L\'viv7'), ('17', 'Stryy - L\'viv8'), ('18', 'Stryy - L\'viv9'), ('19', 'Pisochne - Sknyliv4'), ('20', 'Pisochne - Sknyliv5'), ('21', 'Pisochne - Sknyliv6');
/*!40000 ALTER TABLE `lines` ENABLE KEYS */;
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
INSERT INTO `stationsonlines` VALUES ('1', '1', '17', '1'), ('2', '1', '16', '2'), ('3', '1', '15', '3'), ('4', '1', '14', '4'), ('5', '1', '13', '5'), ('6', '1', '12', '6'), ('7', '1', '11', '7'), ('8', '1', '10', '8'), ('9', '1', '9', '9'), ('10', '1', '8', '10'), ('11', '1', '7', '11'), ('12', '1', '6', '12'), ('13', '1', '5', '13'), ('14', '1', '4', '14'), ('15', '1', '3', '15'), ('16', '1', '2', '16'), ('17', '1', '1', '17'), ('18', '2', '1', '1'), ('19', '2', '2', '2'), ('20', '2', '3', '3'), ('21', '2', '4', '4'), ('22', '2', '5', '5'), ('23', '2', '6', '6'), ('24', '2', '7', '7'), ('25', '2', '8', '8'), ('26', '2', '9', '9'), ('27', '2', '10', '10'), ('28', '2', '11', '11'), ('29', '2', '12', '12'), ('30', '2', '13', '13'), ('31', '2', '14', '14'), ('32', '2', '15', '15'), ('33', '2', '16', '16'), ('34', '2', '17', '17'), ('38', '3', '5', '1'), ('39', '3', '16', '2'), ('40', '4', '5', '1'), ('41', '4', '16', '2'), ('42', '5', '5', '1'), ('43', '5', '16', '2'), ('44', '6', '5', '1'), ('45', '6', '16', '2'), ('46', '7', '5', '1'), ('47', '7', '16', '2'), ('48', '8', '5', '1'), ('49', '8', '16', '2'), ('50', '9', '5', '1'), ('51', '9', '16', '2'), ('52', '10', '5', '1'), ('53', '10', '16', '2'), ('54', '11', '5', '1'), ('55', '11', '16', '2'), ('56', '12', '5', '1'), ('57', '12', '16', '2'), ('58', '13', '5', '1'), ('59', '13', '16', '2'), ('60', '14', '5', '1'), ('61', '14', '16', '2'), ('62', '15', '5', '1'), ('63', '15', '16', '2'), ('64', '16', '5', '1'), ('65', '16', '16', '2'), ('66', '17', '5', '1'), ('67', '17', '16', '2'), ('68', '18', '5', '1'), ('69', '18', '16', '2'), ('70', '19', '5', '1'), ('71', '19', '16', '2'), ('72', '20', '5', '1'), ('73', '20', '16', '2'), ('74', '21', '5', '1'), ('75', '21', '16', '2');
/*!40000 ALTER TABLE `stationsonlines` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `routes`
-- 

DROP TABLE IF EXISTS `routes`;
CREATE TABLE `routes` (
    `ROUTEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINEID` INT (10) unsigned NOT NULL , 
    `ROUTECODE` VARCHAR (20) NOT NULL , 
    `ROUTENAME` VARCHAR (53) NOT NULL ,
    `STATIONSTARTID` INT (10) unsigned NOT NULL ,
    `STATIONENDID` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (ROUTEID),
    CONSTRAINT `ROUTES_ibfk_1`
    FOREIGN KEY (`LINEID`)
    REFERENCES `lines` (`LINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `ROUTES_ibfk_2`
    FOREIGN KEY (`STATIONSTARTID`)
    REFERENCES `stations` (`STATIONID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `ROUTES_ibfk_3`
    FOREIGN KEY (`STATIONENDID`)
    REFERENCES `stations` (`STATIONID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `routes`
-- 
LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES ('1', '1', '1000000000001', 'Sknyliv-Stryy', '16', '1'), ('2', '2', '1000000000002', 'Pyatnychany-Stryy', '3', '1'), ('3', '2', '1000000000003', 'Stryy-Pyatnychany', '1', '3'), ('4', '2', '1000000000004', 'Stryy-Pyatnychany', '1', '3'), ('5', '1', '1000000000005', '', '1', '17'), ('6', '1', '100000000006', '', '1', '17'), ('7', '1', '1000000000007', '', '1', '17'), ('8', '1', '1000000000008', '', '1', '17'), ('9', '1', '1000000000009', '', '1', '17'), ('10', '1', '1000000000010', '', '1', '17'), ('11', '1', '1000000000011', '', '1', '17'), ('12', '1', '1000000000012', '', '1', '17'), ('13', '1', '1000000000013', '', '1', '17'), ('14', '1', '1000000000014', '', '1', '17'), ('15', '1', '1000000000015', '', '1', '17'), ('16', '1', '1000000000016', '', '1', '17'), ('17', '1', '1000000000017', '', '1', '17'), ('18', '1', '1000000000018', '', '1', '17'), ('19', '1', '1000000000019', '', '1', '17'), ('20', '1', '1000000000020', '', '1', '17'), ('21', '1', '1000000000021', '', '1', '17'), ('22', '1', '1000000000022', '', '1', '17'), ('23', '1', '1000000000023', '', '1', '17'), ('24', '1', '1000000000024', '', '1', '17'), ('25', '1', '1000000000025', '', '1', '17'), ('26', '1', '1000000000026', '', '1', '17'), ('27', '1', '1000000000027', '', '1', '17'), ('28', '1', '1000000000028', '', '1', '17'), ('29', '1', '1000000000029', '', '1', '17'), ('30', '1', '1000000000030', '', '1', '17'), ('31', '1', '1000000000031', '', '1', '17'), ('32', '1', '1000000000032', '', '1', '17'), ('33', '1', '1000000000033', '', '1', '17'), ('34', '1', '1000000000034', '', '1', '17'), ('35', '1', '1000000000035', '', '1', '17'), ('36', '1', '1000000000036', '', '1', '17'), ('37', '1', '1000000000037', '', '1', '17'), ('38', '1', '1000000000038', '', '1', '17'), ('39', '1', '1000000000039', '', '1', '17'), ('40', '1', '1000000000040', '', '1', '17'), ('41', '1', '1000000000041', '', '1', '17'), ('42', '1', '1000000000042', '', '1', '17'), ('43', '1', '1000000000043', '', '1', '17'), ('44', '1', '1000000000044', '', '1', '17'), ('45', '1', '1000000000045', '', '1', '17');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
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
INSERT INTO `transports` VALUES ('1', '1', 'T000000001', '12:00:00', '11', '150', '110', '25.00'), ('2', '1', 'T000000002', '16:00:00', '13', '120', '130', '35.00'), ('3', '2', 'T000000003', '12:00:00', '12', '130', '120', '45.00'), ('4', '2', 'T000000004', '16:00:00', '14', '110', '160', '55.00'), ('5', '3', 'T000000044', '16:00:00', '15', '140', '150', '25.00'), ('6', '1', 'T000000011', '12:00:00', '16', '155', '140', '35.00'), ('7', '1', 'T000000012', '16:00:00', '17', '145', '130', '45.00'), ('8', '2', 'T000000013', '12:00:00', '18', '160', '170', '55.00'), ('9', '2', 'T000000014', '16:00:00', '19', '170', '120', '35.00'), ('10', '3', 'T000000015', '16:00:00', '110', '50', '160', '22.00'), ('11', '1', 'T000000021', '12:00:00', '120', '50', '150', '25.00'), ('12', '1', 'T000000023', '16:00:00', '130', '50', '170', '33.00'), ('13', '2', 'T000000022', '12:00:00', '140', '50', '140', '44.00'), ('14', '2', 'T000000025', '16:00:00', '150', '50', '150', '25.00'), ('15', '3', 'T000000024', '16:00:00', '160', '50', '130', '27.00');
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
INSERT INTO `users` VALUES 
('1', 'tranzero', 'Oleg', 'Lesniak', 'mail@mail.com', '1144', '2014-02-02 11:25:26.0', 'MANAGER'),
 ('2', 'garasym', 'Yaroslav', 'Garasym', 'test8@mail.com', '2555', '2014-02-05 12:25:26.0', 'MANAGER'),
 ('3', 'mykhaylo.partyka', 'Mykhaylo', 'Partyka', 'test9@mail.com', '3544', '2014-02-05 12:27:26.0', 'ADMIN'),
 ('4', 'iryna', 'Iryna', 'Bautista', 'iri@gmail.com', '7733', '2014-02-05 13:25:26.0', 'ADMIN'),
 ('5', 'nastya', 'Anastasya', 'Pankiy', 'nastya@mail.com', '5533', '2014-02-27 15:25:26.0', 'REGUSER'),
 ('6', 'yuriy', 'Yuriy', 'Logazyak', 'test6@gmail.com', '13777', '2014-02-27 16:25:10.0', 'MANAGER'), 
('7', 'taras', 'Taras', 'Savitskyy', 'taras7@yahoo.com', '1133', '2014-02-27 16:35:26.0', 'ADMIN'), 
('8', 'roman', 'Roman', 'Parashchak', 'roman@mail.com', '2244', '2014-02-27 16:37:26.0', 'ADMIN'), 
('9', 'petro', 'Petro', 'Matyash', 'mtsh@mail.com', '8899', '2014-02-28 17:00:09.0', 'MANAGER'), 
('10', 'nazar', 'Nazar', 'Vrublevskiy', 'nazar1@mail.com', '8877', '2014-02-28 17:25:26.0', 'REGUSER'),
 ('11', 'mykhailo', 'Mykhaylo', 'Kozar', 'myk@mail.com', '4699', '2014-02-28 18:25:04.0', 'REGUSER'),
 ('12', 'denys', 'Denys', 'Nyckolskiy', 'test12@mail.com', '1277', '2014-02-28 14:23:26.0', 'REGUSER'),
 ('13', 'lyubomyr', 'Lyubomyr', 'Pentsko', 'test13@mail.com', '1111', '2014-02-28 14:25:00.0', 'REGUSER'),
 ('14', 'test1', 'Kravchina', 'Dmytro', 'test01@mail.com', '1144', '2014-03-02 17:25:26.0', 'ADMIN'),
 ('15', 'test2', 'Krachkovska', 'Viktoria', 'test02@mail.com', '1144', '2014-03-02 17:28:26.0', 'REGUSER');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Table structure for table `orders`
-- 

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `ORDERID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERID` INT (10) unsigned NOT NULL , 
    `ORDERDATE` TIMESTAMP NOT NULL ,
    PRIMARY KEY (ORDERID),
    CONSTRAINT `ORDERID_ibfk_1`
    FOREIGN KEY (`USERID`)
    REFERENCES `users` (`USERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `orders`
-- 
LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('1', '1', '2014-02-10 19:24:50.0'), ('2', '1', '2014-02-10 19:24:52.0'), ('3', '1', '2014-02-10 21:24:50.0'), ('4', '3', '2014-02-10 23:24:50.0'), ('5', '2', '2014-02-10 17:24:50.0');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Table structure for table `responses`
-- 

DROP TABLE IF EXISTS `responses`;
CREATE TABLE `responses` (
    `RESPONSEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERID` INT (10) unsigned NOT NULL , 
    `TRIPID` INT (10) unsigned NOT NULL , 
    `COMMENT` VARCHAR (200) NOT NULL , 
    `DATE` DATE NULL , 
    `checked` BIT NOT NULL ,
    PRIMARY KEY (RESPONSEID),
    CONSTRAINT `fk1_`
    FOREIGN KEY (`USERID`)
    REFERENCES `users` (`USERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk2_`
    FOREIGN KEY (`TRIPID`)
    REFERENCES `trips` (`TRIPID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `responses`
-- 
LOCK TABLES `responses` WRITE;
/*!40000 ALTER TABLE `responses` DISABLE KEYS */;
INSERT INTO `responses` VALUES ('1', '1', '2', 'good', '2014-02-04', true),
 ('2', '1', '6', 'wonderful', '2014-02-11', true), 
('3', '10', '11', 'good', '2014-02-04', true),
 ('4', '8', '13', 'wonderful', '2014-02-18', true),
 ('5', '4', '2', 'wonderful', '2014-02-05', true), 
('6', '7', '9', 'This was a wonderful trip', '2014-02-11', true),
 ('7', '9', '9', 'wonderful', '2014-02-05', true), 
('8', '4', '4', 'This was a wonderful trip', '2014-02-05', true);
/*!40000 ALTER TABLE `responses` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `tickets`
-- 

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
    `TICKETID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `TICKETNAME` VARCHAR (100) NOT NULL , 
    `ORDERID` INT (10) unsigned NOT NULL , 
    `TRIPID` INT (10) unsigned NOT NULL , 
    `CUSTOMERFIRSTNAME` VARCHAR (100) NOT NULL , 
	`CUSTOMERLASTNAME` VARCHAR (100) NOT NULL ,
    `SEATTYPE` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (TICKETID),
    CONSTRAINT `fk1`
    FOREIGN KEY (`ORDERID`)
    REFERENCES `orders` (`ORDERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk2`
    FOREIGN KEY (`TRIPID`)
    REFERENCES `trips` (`TRIPID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `tickets`
-- 
LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;


/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;