SET FOREIGN_KEY_CHECKS = 0;
drop table if exists booking;
drop table if exists chair;
drop table if exists floor;
drop table if exists office;
drop table if exists location;
drop table if exists employee;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF not exists `employee` (
                                          `empid` int NOT NULL auto_increment,
                                          `empemail` varchar(255) DEFAULT NULL,
                                          `empname` varchar(255) DEFAULT NULL,
                                          `teamid` varchar(255) DEFAULT NULL,
                                          PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE IF not exists `location` (
                                          `locid` int NOT NULL auto_increment,
                                          `locname` varchar(255) DEFAULT NULL,
                                          `numoffice` int DEFAULT NULL,
                                          PRIMARY KEY (`locid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE IF not exists `office` (
                                        `offid` varchar(255) NOT NULL,
                                        `offname` varchar(255) DEFAULT NULL,
                                        `numfloor` int DEFAULT NULL,
                                        `locid` int DEFAULT NULL,
                                        PRIMARY KEY (`offid`),
                                        KEY `FKl4c4orh7vprorctobcwewy0ni` (`locid`),
                                        CONSTRAINT `FKl4c4orh7vprorctobcwewy0ni` FOREIGN KEY (`locid`) REFERENCES `location` (`locid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE IF not exists `floor` (
                                       `floorid` varchar(255) NOT NULL,
                                       `laycol` int DEFAULT NULL,
                                       `numdesks` int DEFAULT NULL,
                                       `layrow` int DEFAULT NULL,
                                       `offid` varchar(255) DEFAULT NULL,
                                       `status` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`floorid`),
                                       KEY `FKryka21lw6mwpk43wofikkwo2a` (`offid`),
                                       CONSTRAINT `FKryka21lw6mwpk43wofikkwo2a` FOREIGN KEY (`offid`) REFERENCES `office` (`offid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE IF not exists `chair` (
                                       `chairid` varchar(255) NOT NULL,
                                       `coordinate_x` int DEFAULT NULL,
                                       `corrdinate_y` int DEFAULT NULL,
                                       `rotation` varchar(255) DEFAULT NULL,
                                       `floorid` varchar(255) DEFAULT NULL,
                                       `offid` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`chairid`),
                                       KEY `FKcbt68jps7e0xg1jhgymsjbfb4` (`floorid`),
                                       KEY `FK85r4t118u6r24s97xx68xvhqx` (`offid`),
                                       CONSTRAINT `FK85r4t118u6r24s97xx68xvhqx` FOREIGN KEY (`offid`) REFERENCES `office` (`offid`),
                                       CONSTRAINT `FKcbt68jps7e0xg1jhgymsjbfb4` FOREIGN KEY (`floorid`) REFERENCES `floor` (`floorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF not exists `booking` (
                                         `bookingid` int NOT NULL auto_increment,
                                         `status` varchar(255) DEFAULT NULL,
                                         `enddate` date DEFAULT NULL,
                                         `startdate` date DEFAULT NULL,
                                         `chairid` varchar(255) DEFAULT NULL,
                                         `empid` int DEFAULT NULL,
                                         `floorid` varchar(255) DEFAULT NULL,
                                         PRIMARY KEY (`bookingid`),
                                         KEY `FKnvaam604rqnm7v4wft79trfea` (`chairid`),
                                         KEY `FKshp1f2a8ix992owgaoxgcna3f` (`empid`),
                                         KEY `FK1u6f6je8ulld1gm0w2h4ei4qy` (`floorid`),
                                         CONSTRAINT `FK1u6f6je8ulld1gm0w2h4ei4qy` FOREIGN KEY (`floorid`) REFERENCES `floor` (`floorid`),
                                         CONSTRAINT `FKnvaam604rqnm7v4wft79trfea` FOREIGN KEY (`chairid`) REFERENCES `chair` (`chairid`),
                                         CONSTRAINT `FKshp1f2a8ix992owgaoxgcna3f` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

