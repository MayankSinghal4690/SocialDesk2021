SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `employee` VALUES (110,'niveditasinghal7@gmail.com','Nivedita','Team01'),(113,'ritusinghal123@gmail.com','Ritu','Team01'),(90,'admin@gmail.com','Admin','TeamAdmin'),(114,'nikeet.keshari@gmail.com','Nikeet','Team02'),(115,'salonidharva611@gmail.com','Saloni','Team02'),(116,'maheshtamse.13@gmail.com','Mahesh','Team01'),(117,'divasjindal@gmail.com','Divas','Team01'),(121,'pranathi.bora@gmail.com','Pranathi','Team02'),(122,'sumit.kumar@gmail.com','Sumit','Team03'),(123,'akash.jain@gmail.com','Akash','Team03'),(124,'akshay.vaje@gmail.com','Akashay','Team03'),(125,'swati.kadu@gmail.com','Swati','Team03'),(126,'codingchallenge15@gmail.com','Harry_Potter','Team02');

INSERT INTO `location` VALUES (1,'Pune',5),(2,'Banglore',1),(3,'Mumbai',1);

INSERT INTO `office` VALUES ('112','Yerwada',2,1),('113','Magarpatta',4,1),('114','Banglore-Office',3,2);

INSERT INTO `floor` VALUES ('Yer1',10,100,10,'112','available'),('Yer2',10,100,10,'112','available'),('Mag1',10,100,10,'113','available'),('Mag2',10,100,10,'113','available'),('Mag3',10,100,10,'113','blocked'),('Mag4',10,100,10,'113','blocked'),('Ban1',10,100,10,'114','blocked'),('Ban2',10,100,10,'114','available'),('Ban3',10,100,10,'114','available');

INSERT INTO `chair` VALUES

('1',2,1,'south','Yer1','112'),
('2',3,1,'south','Yer1','112'),
('3',5,1,'south','Yer1','112'),
('4',6,1,'south','Yer1','112'),
('5',2,4,'south','Yer1','112'),
('6',3,4,'south','Yer1','112'),
('7',5,4,'south','Yer1','112'),
('8',6,4,'south','Yer1','112'),


('9',2,0,'north','Yer1','112'),
('10',3,0,'north','Yer1','112'),
('11',5,0,'north','Yer1','112'),
('12',6,0,'north','Yer1','112'),
('13',2,3,'north','Yer1','112'),
('14',3,3,'north','Yer1','112'),
('15',5,3,'north','Yer1','112'),
('16',6,3,'north','Yer1','112'),


('17',0,0,'east','Yer1','112'),
('18',0,1,'east','Yer1','112'),
('19',0,3,'east','Yer1','112'),
('20',0,4,'east','Yer1','112'),


('21',8,0,'north-west','Yer1','112'),
('22',8,3,'north-west','Yer1','112'),


('23',8,1,'south-west','Yer1','112'),
('24',8,4,'south-west','Yer1','112'),


('25',9,0,'north-east','Yer1','112'),
('26',9,3,'north-east','Yer1','112'),


('27',9,1,'south-east','Yer1','112'),
('28',9,4,'south-east','Yer1','112'),



('116',1,1,'north','Yer2','112'),('117',2,3,'north','Yer2','112'),('118',1,2,'south','Yer2','112'),('119',2,1,'south','Yer2','112'),('120',4,5,'north','Yer2','112'),('121',5,5,'north','Yer2','112'),('122',6,7,'south','Yer2','112'),('123',7,1,'south','Yer2','112'),('124',8,3,'north','Yer2','112'),('125',8,5,'north','Yer2','112'),

('126',1,1,'north','Mag1','113'),('127',2,3,'north','Mag1','113'),('128',1,2,'south','Mag1','113'),('129',2,1,'south','Mag1','113'),('130',4,5,'north','Mag1','113'),('131',5,5,'north','Mag1','113'),('132',6,7,'south','Mag1','113'),('133',7,1,'south','Mag1','113'),('134',8,3,'north','Mag1','113'),('135',8,5,'north','Mag1','113'),

('136',1,1,'north','Mag2','113'),('137',2,3,'north','Mag2','113'),('138',1,2,'south','Mag2','113'),('139',2,1,'south','Mag2','113'),('140',4,5,'north','Mag2','113'),('141',5,5,'north','Mag2','113'),('142',6,7,'south','Mag2','113'),('143',7,1,'south','Mag2','113'),('144',8,3,'north','Mag2','113'),('145',8,5,'north','Mag2','113'),

('146',1,1,'north','Ban2','114'),('147',2,3,'north','Ban2','114'),('148',1,2,'south','Ban2','114'),('149',2,1,'south','Ban2','114'),('150',4,5,'north','Ban2','114'),('151',5,5,'north','Ban2','114'),('152',6,7,'south','Ban2','114'),('153',7,1,'south','Ban2','114'),('154',8,3,'north','Ban2','114'),('155',8,5,'north','Ban2','114'),

('156',1,1,'north','Ban3','114'),('157',2,3,'north','Ban3','114'),('158',1,2,'south','Ban3','114'),('159',2,1,'south','Ban3','114'),('160',4,5,'north','Ban3','114'),('161',5,5,'north','Ban3','114'),('162',6,7,'south','Ban3','114'),('163',7,1,'south','Ban3','114'),('164',8,3,'north','Ban3','114'),('165',8,5,'north','Ban3','114')
;

SET FOREIGN_KEY_CHECKS = 1;
