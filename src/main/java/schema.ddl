
    create table lines (
        LINEID integer not null auto_increment,
        LINENAME varchar(100) not null,
        primary key (LINEID)
    );

    create table posts (
        POSTID integer not null auto_increment,
        DATE datetime,
        DESCRIPTION varchar(1000) not null,
        TITLE varchar(100) not null,
        primary key (POSTID)
    );

    create table routes (
        ROUTEID integer not null auto_increment,
        ROUTECODE varchar(20),
        STARTTIME time,
        LINEID integer,
        primary key (ROUTEID)
    );

    create table stations (
        STATIONID integer not null auto_increment,
        STATIONCODE varchar(255) unique,
        STATIONNAME varchar(100),
        primary key (STATIONID)
    );

    create table stationsonlines (
        STATIONONLINEID integer not null auto_increment,
        STATIONORDERNUM integer,
        LINEID integer,
        STATIONID integer,
        primary key (STATIONONLINEID)
    );

    create table stops (
        STOPID integer not null auto_increment,
        ARRIVAL time,
        DEPARTURE time,
        ROUTEID integer,
        STATIONONLINEID integer,
        primary key (STOPID)
    );

    create table users (
        USERID integer not null auto_increment,
        EMAIL varchar(100) not null,
        FIRSTNAME varchar(100),
        LASTNAME varchar(100),
        PASSWD varchar(100) not null,
        REGDATE datetime,
        role enum('REGUSER','MANAGER', 'ADMIN'),
        USERNAME varchar(100) not null,
        primary key (USERID)
    );
    
    CREATE TABLE `transports` (
        `TRANSPORTID` int(10) auto_increment NOT NULL PRIMARY KEY,
        `ROUTEID` int(10) unsigned NOT NULL,
        `TRANSPORTCODE` varchar(20) NOT NULL,
        `STARTTIME` time NOT NULL,
        KEY `ROUTEID` (`ROUTEID`),
        CONSTRAINT `ROUTES_ROUTEID_fk`
        FOREIGN KEY (`ROUTEID`)
        REFERENCES `routes` (`ROUTEID`) ON DELETE CASCADE ON UPDATE CASCADE
    );

    alter table routes 
        add index FKC8DB974A5BB2AAE9 (LINEID), 
        add constraint FKC8DB974A5BB2AAE9 
        foreign key (LINEID) 
        references lines (LINEID);

    alter table stationsonlines 
        add index FKA114C9E1F2E79953 (STATIONID), 
        add constraint FKA114C9E1F2E79953 
        foreign key (STATIONID) 
        references stations (STATIONID);

    alter table stationsonlines 
        add index FKA114C9E15BB2AAE9 (LINEID), 
        add constraint FKA114C9E15BB2AAE9 
        foreign key (LINEID) 
        references lines (LINEID);

    alter table stops 
        add index FK68AF8B16D4397F3 (ROUTEID), 
        add constraint FK68AF8B16D4397F3 
        foreign key (ROUTEID) 
        references routes (ROUTEID);

    alter table stops 
        add index FK68AF8B175431379 (STATIONONLINEID), 
        add constraint FK68AF8B175431379 
        foreign key (STATIONONLINEID) 
        references stationsonlines (STATIONONLINEID);
