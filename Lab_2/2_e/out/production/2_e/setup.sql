-- setup.sql


-- -----------------------------------------------------
-- Julia Dressel and Max Parker
-- Lab 2c
-- tables.sql
-- -----------------------------------------------------


-- CREATE DATABASE

-- -----------------------------------------------------
-- Table affiliation
-- -----------------------------------------------------
USE jdressel_db;

DROP TABLE IF EXISTS reviewer_interest;
DROP TABLE IF EXISTS manuscript_final;
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS secondary_author;
DROP TABLE IF EXISTS manuscript_review;
DROP TABLE IF EXISTS reviewer;
DROP TABLE IF EXISTS manuscript;
DROP TABLE IF EXISTS RICodes;
DROP TABLE IF EXISTS editor;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS affiliation;

-- -----------------------------------------------------
-- Table affiliation
-- -----------------------------------------------------
CREATE TABLE affiliation 
( 
  affiliation_ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (affiliation_ID)
);


-- -----------------------------------------------------
-- Table author
-- -----------------------------------------------------
CREATE TABLE author (
  author_ID INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  affiliation_ID INT,
  PRIMARY KEY (author_ID),
  FOREIGN KEY (affiliation_ID)
    REFERENCES affiliation (affiliation_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table editor
-- -----------------------------------------------------
CREATE TABLE editor(
  editor_ID INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (editor_ID)
);

-- -----------------------------------------------------
-- Table RICodes
-- -----------------------------------------------------
CREATE TABLE RICodes
  ( RI_Code        MEDIUMINT NOT NULL AUTO_INCREMENT,
    interest    varchar(64) NOT NULL,
    PRIMARY KEY (RI_Code)
  );


-- -----------------------------------------------------
-- Table manuscript
-- -----------------------------------------------------
CREATE TABLE manuscript(
  manuscript_ID INT NOT NULL AUTO_INCREMENT,
  editor_ID INT,
  author_ID INT NOT NULL,
  title VARCHAR(45) NOT NULL,
  status VARCHAR(45) NOT NULL,
  status_time_stamp DATETIME NOT NULL,
  RI_Code MEDIUMINT NOT NULL NOT NULL,
  PRIMARY KEY (manuscript_ID),
  FOREIGN KEY (editor_ID)
    REFERENCES editor (editor_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (author_ID)
    REFERENCES author (author_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (RI_Code)
    REFERENCES RICodes (RI_Code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table reviewer
-- -----------------------------------------------------
CREATE TABLE reviewer (
  reviewer_ID INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NULL,
  affiliation_ID INT NULL,
  PRIMARY KEY (reviewer_ID),
  FOREIGN KEY (affiliation_ID)
    REFERENCES affiliation (affiliation_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table manuscript_review
-- -----------------------------------------------------
CREATE TABLE manuscript_review (
  manuscript_ID INT NOT NULL,
  reviewer_ID INT NOT NULL,
  date_assigned DATETIME NOT NULL,
  appropriateness INT NULL,
  clarity INT NULL,
  methodology INT NULL,
  contribution INT NULL,
  recommendation VARCHAR(10) NULL,
  date_reviewed DATETIME NULL,
  PRIMARY KEY (manuscript_ID, reviewer_ID),
  FOREIGN KEY (manuscript_ID)
    REFERENCES manuscript (manuscript_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (reviewer_ID)
    REFERENCES reviewer (reviewer_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table secondary_author
-- -----------------------------------------------------
CREATE TABLE secondary_author (
  manuscript_ID INT NOT NULL,
  rank INT NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (manuscript_ID, rank),
  FOREIGN KEY (manuscript_ID)
    REFERENCES manuscript (manuscript_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table issue
-- -----------------------------------------------------
CREATE TABLE issue (
  pub_year INT NOT NULL,
  period_num INT NOT NULL,
  total_pages INT NULL,
  print_date DATETIME NULL,
  PRIMARY KEY (pub_year, period_num));


-- -----------------------------------------------------
-- Table manuscript_final
-- -----------------------------------------------------
CREATE TABLE manuscript_final (
  manuscript_ID INT NOT NULL,
  num_pages INT NULL,
  order_in_issue INT NULL,
  start_page INT NULL,
  pub_year INT NULL,
  period_num INT NULL,
  PRIMARY KEY (manuscript_ID),
  FOREIGN KEY (manuscript_ID)
    REFERENCES manuscript (manuscript_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (pub_year , period_num)
    REFERENCES issue (pub_year , period_num)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table reviewer_interest
-- -----------------------------------------------------
CREATE TABLE reviewer_interest (
  reviewer_ID INT NOT NULL,
  RI_Code MEDIUMINT NOT NULL,
  PRIMARY KEY (reviewer_ID, RI_Code),
  FOREIGN KEY (reviewer_ID)
    REFERENCES reviewer (reviewer_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (RI_Code)
    REFERENCES RICodes (RI_Code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
    -- -----------------------------------------------------
-- INSERT VALUES INTO DB
-- -----------------------------------------------------


INSERT INTO RICodes (interest) VALUES
('Agricultural engineering'),
('Biochemical engineering'),
('Biomechanical engineering'),
('Ergonomics'),
('Food engineering'),
('Bioprocess engineering'),
('Genetic engineering'),
('Human genetic engineering'),
('Metabolic engineering'),
('Molecular engineering'),
('Neural engineering'),
('Protein engineering'),
('Rehabilitation engineering'),
('Tissue engineering'),
('Aquatic and environmental engineering'),
('Architectural engineering'),
('Civionic engineering'),
('Construction engineering'),
('Earthquake engineering'),
('Earth systems engineering and management'),
('Ecological engineering'),
('Environmental engineering'),
('Geomatics engineering'),
('Geotechnical engineering'),
('Highway engineering'),
('Hydraulic engineering'),
('Landscape engineering'),
('Land development engineering'),
('Pavement engineering'),
('Railway systems engineering'),
('River engineering'),
('Sanitary engineering'),
('Sewage engineering'),
('Structural engineering'),
('Surveying'),
('Traffic engineering'),
('Transportation engineering'),
('Urban engineering'),
('Irrigation and agriculture engineering'),
('Explosives engineering'),
('Biomolecular engineering'),
('Ceramics engineering'),
('Broadcast engineering'),
('Building engineering'),
('Signal Processing'),
('Computer engineering'),
('Power systems engineering'),
('Control engineering'),
('Telecommunications engineering'),
('Electronic engineering'),
('Instrumentation engineering'),
('Network engineering'),
('Neuromorphic engineering'),
('Engineering Technology'),
('Integrated engineering'),
('Value engineering'),
('Cost engineering'),
('Fire protection engineering'),
('Domain engineering'),
('Engineering economics'),
('Engineering management'),
('Engineering psychology'),
('Ergonomics'),
('Facilities Engineering'),
('Logistic engineering'),
('Model-driven engineering'),
('Performance engineering'),
('Process engineering'),
('Product Family Engineering'),
('Quality engineering'),
('Reliability engineering'),
('Safety engineering'),
('Security engineering'),
('Support engineering'),
('Systems engineering'),
('Metallurgical Engineering'),
('Surface Engineering'),
('Biomaterials Engineering'),
('Crystal Engineering'),
('Amorphous Metals'),
('Metal Forming'),
('Ceramic Engineering'),
('Plastics Engineering'),
('Forensic Materials Engineering'),
('Composite Materials'),
('Casting'),
('Electronic Materials'),
('Nano materials'),
('Corrosion Engineering'),
('Vitreous Materials'),
('Welding'),
('Acoustical engineering'),
('Aerospace engineering'),
('Audio engineering'),
('Automotive engineering'),
('Building services engineering'),
('Earthquake engineering'),
('Forensic engineering'),
('Marine engineering'),
('Mechatronics'),
('Nanoengineering'),
('Naval architecture'),
('Sports engineering'),
('Structural engineering'),
('Vacuum engineering'),
('Military engineering'),
('Combat engineering'),
('Offshore engineering'),
('Optical engineering'),
('Geophysical engineering'),
('Mineral engineering'),
('Mining engineering'),
('Reservoir engineering'),
('Climate engineering'),
('Computer-aided engineering'),
('Cryptographic engineering'),
('Information engineering'),
('Knowledge engineering'),
('Language engineering'),
('Release engineering'),
('Teletraffic engineering'),
('Usability engineering'),
('Web engineering'),
('Systems engineering');


INSERT INTO affiliation (name) VALUES ("Fringilla Limited"),("Elit Elit Corp."),("Nunc Ullamcorper Consulting"),("Ornare Lectus Institute"),("Sociis Natoque Penatibus Inc."),("Erat Semper Rutrum Inc."),("Malesuada Id Erat Corporation"),("Mattis Ornare Lectus Company"),("In Ltd"),("Mauris Industries");

INSERT INTO author (first_name,last_name,address,email,affiliation_ID) VALUES ("Jordan","Erickson","853-3707 In Rd.","enim@necluctus.org",5),("Blythe","Velasquez","293-2799 Nascetur Ave","orci@penatibusetmagnis.org",10),("Candace","Kinney","279-824 Vitae, Rd.","ac@luctusut.edu",5),("Flavia","Holt","P.O. Box 849, 3652 Morbi St.","Nunc.quis@Lorem.edu",9),("Pearl","Ball","P.O. Box 188, 1857 A Street","consequat@Quisque.edu",7),("Casey","Whitaker","P.O. Box 686, 8740 Et, Av.","Cum.sociis@posuerecubilia.com",8),("Hedda","Shaw","Ap #763-2728 Tellus Av.","nisi@vitae.ca",6),("Graiden","Brewer","P.O. Box 124, 8009 Nunc Rd.","amet.faucibus@rutrum.com",5),("Fredericka","Mayer","830 Ornare Rd.","ornare.In@scelerisque.ca",2),("Mercedes","Levy","244-1307 Magna Ave","in.faucibus@quisaccumsanconvallis.com",6);

INSERT INTO editor (first_name,last_name) VALUES ("Kirsten","Wilcox"),("Zelda","Vaughan"),("Imelda","Washington"),("Martin","Hubbard"),("Chava","Workman"),("Timon","Nielsen"),("Iona","Reid"),("Dorian","Fuller"),("Lynn","Castro"),("Carter","Joyner");

INSERT INTO reviewer (first_name,last_name,email,affiliation_ID) VALUES ("Jordan","Ochoa","Integer@nibhlaciniaorci.co.uk",1),("Thaddeus","Fuentes","ad.litora@nasceturridiculus.ca",7),("Stacey","Michael","Mauris.non@semvitae.edu",4),("Destiny","Bauer","placerat@rhoncusProinnisl.net",9),("Yasir","Rosa","tristique.senectus.et@arcuvel.ca",5),("Jin","Cummings","Vivamus@Donec.co.uk",1),("Indigo","Bird","lorem.semper.auctor@orciadipiscing.ca",5),("Lilah","Villarreal","risus.Nunc.ac@estMauriseu.com",4),("Suki","Valenzuela","id.sapien@Sed.edu",8),("Dante","Flynn","dui.Cum.sociis@velit.ca",3);

INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES (3,98),(7,87),(8,108),(6,37),(6,107),(8,63),(10,36),(5,30),(4,24),(9,38);
INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES (5,115),(3,92),(7,21),(8,3),(7,88),(6,10),(8,79),(3,42),(1,69),(10,104);
INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES (3,52),(8,88),(3,72),(8,15),(3,74),(10,25),(5,104),(9,5),(10,48);
INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES (3,43),(5,101),(10,121),(9,67),(10,4),(6,75),(2,66),(5,48),(10,6),(1,11);
INSERT INTO reviewer_interest (reviewer_ID,RI_Code) VALUES (10,49),(8,84),(1,100),(9,49),(8,30),(10,124),(7,75),(7,76),(7,123),(4,14);

INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (9,1,"tortor. Nunc commodo","published","2018-04-11 17:21:46",119),(6,3,"luctus","under review","2017-07-25 06:06:21",48),(7,7,"magna. Sed eu","under review","2016-06-24 19:17:24",26),(5,9,"nibh","submitted","2017-04-21 19:22:31",124),(2,7,"dictum augue","scheduled for publication","2018-03-26 00:05:53",57),(2,7,"nisl. Maecenas","in typesetting","2017-01-11 01:42:34",5),(3,10,"luctus ut,","rejected","2017-06-26 03:36:23",120),(8,9,"mollis non,","under review","2016-05-12 18:30:21",47),(4,5,"ut aliquam iaculis,","scheduled for publication","2017-04-26 04:14:51",41),(4,4,"elit, dictum","published","2017-12-03 10:14:01",77);
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (2,7,"tellus. Suspendisse sed","accepted","2017-10-07 05:08:06",19),(4,10,"sit amet,","scheduled for publication","2016-12-28 09:32:00",121),(7,10,"Fusce fermentum","accepted","2017-07-29 17:44:43",46),(3,2,"dolor quam, elementum","under review","2016-10-25 12:12:26",17),(8,3,"non dui","submitted","2016-08-22 06:08:19",31),(3,3,"nonummy ac,","accepted","2016-10-02 06:09:34",62),(1,10,"velit. Cras lorem","submitted","2016-06-24 03:11:04",20),(10,9,"diam dictum sapien.","scheduled for publication","2017-10-17 15:46:53",75),(2,3,"eu","accepted","2017-02-26 11:13:38",109),(10,4,"ut","published","2018-02-26 16:03:46",91);
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (10,10,"mi lorem,","accepted","2016-10-21 06:21:43",59),(7,9,"diam","in typesetting","2017-06-30 05:01:59",124),(8,9,"lorem","in typesetting","2017-12-23 13:36:38",114),(7,3,"Nullam enim.","accepted","2016-11-05 04:24:04",93),(1,2,"orci quis lectus.","rejected","2017-01-25 06:36:15",48),(10,10,"pede,","rejected","2017-03-19 10:35:50",88),(4,2,"purus ac","in typesetting","2016-07-03 11:38:50",96),(5,4,"Lorem","rejected","2018-03-24 09:32:36",18),(1,10,"vitae semper egestas,","submitted","2017-10-17 14:11:44",25),(7,10,"scelerisque, lorem","under review","2018-01-23 23:38:19",119);
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (6,3,"est. Nunc","scheduled for publication","2016-08-26 23:06:32",94),(10,1,"quis","submitted","2017-07-03 12:47:27",42),(3,8,"scelerisque, lorem ipsum","in typesetting","2016-08-01 22:36:40",16),(1,7,"a","published","2016-05-26 00:02:37",67),(10,3,"velit. Sed malesuada","in typesetting","2018-03-15 13:41:31",42),(2,9,"dignissim tempor","accepted","2017-12-21 06:04:03",81),(1,4,"Pellentesque habitant","in typesetting","2018-04-14 05:30:32",108),(1,1,"ac turpis egestas.","under review","2017-07-28 10:20:05",12),(6,10,"aliquet","published","2016-11-07 17:02:39",11),(10,8,"et","in typesetting","2016-07-02 22:22:39",79);

INSERT INTO secondary_author (manuscript_ID,rank,first_name,last_name) VALUES (18,4,"Jessamine","Garza"),(33,1,"Kylie","Ortega"),(8,6,"Raymond","Lowery"),(38,1,"Keegan","Mcknight"),(2,5,"Gwendolyn","Chen"),(7,5,"Rylee","Castaneda"),(25,4,"Jackson","Odonnell"),(23,7,"Hadassah","Mayo"),(38,3,"Daniel","Pickett"),(22,5,"Lester","Nelson");
INSERT INTO secondary_author (manuscript_ID,rank,first_name,last_name) VALUES (37,1,"Germaine","Fowler"),(29,4,"Vanna","Blair"),(6,6,"Aspen","Potts"),(5,6,"Michael","Chambers"),(38,2,"Aurelia","Frederick"),(4,3,"Charissa","Solis"),(5,3,"Rhona","Levy"),(28,4,"Keaton","Vasquez"),(35,4,"Tatum","Ayala"),(13,5,"Marah","Knowles");

INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (3,2,"2017-12-16 20:13:05",9,5,4,6,"reject","2018-03-06 20:22:04"),(21,6,"2017-09-16 22:52:49",4,3,7,2,"accept","2016-12-11 11:17:23"),(11,6,"2016-09-28 10:18:08",7,1,3,8,"accept","2017-01-24 04:47:46"),(29,1,"2017-06-11 06:24:37",9,7,2,3,"reject","2016-05-21 18:04:42"),(19,10,"2018-03-28 12:27:31",1,6,2,3,"reject","2016-06-22 13:24:24"),(18,3,"2017-06-24 21:38:04",5,9,5,8,"reject","2016-08-04 15:13:28"),(31,8,"2017-09-22 06:59:17",4,7,8,3,"reject","2017-03-27 07:07:57"),(18,4,"2017-01-26 11:01:08",6,2,3,3,"reject","2016-08-17 16:41:15"),(24,2,"2016-11-15 06:50:45",6,3,1,9,"accept","2018-03-20 21:54:12"),(36,3,"2017-07-26 08:22:39",7,6,8,4,"reject","2017-01-10 16:13:12");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (2,8,"2016-05-18 10:58:34",2,10,5,5,"reject","2017-06-14 15:52:03"),(32,3,"2017-03-07 21:19:41",2,10,10,5,"reject","2018-04-23 10:08:10"),(8,6,"2017-07-10 22:46:33",9,2,5,4,"reject","2017-06-17 23:46:08"),(31,4,"2017-12-24 01:56:13",1,9,3,5,"reject","2017-10-26 13:30:40"),(15,5,"2017-11-14 14:43:05",7,2,3,7,"accept","2017-11-29 16:09:00"),(31,6,"2016-06-19 05:10:06",2,1,3,8,"accept","2018-02-06 23:44:26"),(38,6,"2017-10-19 11:00:21",2,3,7,3,"reject","2017-02-27 17:31:28"),(31,7,"2016-11-04 04:03:34",5,1,7,7,"accept","2017-02-18 14:19:54"),(39,5,"2017-02-08 10:28:14",5,4,2,6,"accept","2017-04-14 01:04:07"),(16,4,"2017-02-04 15:55:05",2,5,3,4,"reject","2016-10-26 18:14:45");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (14,5,"2016-10-24 09:31:29",3,7,7,2,"reject","2017-11-19 05:07:08"),(33,10,"2017-01-19 01:20:14",9,3,2,9,"accept","2017-04-23 01:30:57"),(15,3,"2016-09-02 20:02:34",1,6,6,8,"reject","2017-03-03 09:41:04"),(9,5,"2016-08-27 02:42:18",8,4,1,1,"accept","2016-06-17 21:24:26"),(20,4,"2017-04-04 11:32:49",9,7,7,2,"reject","2017-12-17 11:33:26"),(15,10,"2018-03-09 03:37:47",8,1,6,2,"reject","2017-10-01 12:27:53"),(12,10,"2017-02-22 16:53:24",1,2,3,4,"reject","2016-08-22 03:32:21"),(33,6,"2016-10-11 23:09:09",3,5,6,6,"reject","2016-11-17 16:48:23"),(39,8,"2017-04-25 03:14:50",3,8,3,4,"reject","2016-06-01 16:11:50"),(33,7,"2017-02-27 07:41:13",2,8,4,5,"reject","2018-02-14 22:22:43");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (35,10,"2017-10-04 03:57:55",3,2,1,1,"reject","2017-12-19 15:29:38"),(17,7,"2016-06-15 21:09:48",9,2,4,8,"accept","2016-06-28 16:54:01"),(9,8,"2016-05-24 23:16:12",2,3,3,3,"reject","2017-04-16 16:01:09"),(12,2,"2017-05-20 10:09:55",4,5,9,4,"accept","2017-12-11 18:18:21"),(15,9,"2017-03-10 03:49:03",1,8,2,1,"accept","2017-01-02 16:07:44"),(25,3,"2017-12-29 05:41:14",10,2,6,3,"accept","2017-09-07 10:14:34"),(7,9,"2017-06-04 03:42:56",4,3,6,3,"accept","2016-09-30 23:22:03"),(6,3,"2017-10-20 11:47:29",7,2,4,2,"accept","2017-03-20 08:45:32"),(22,9,"2017-12-03 04:46:53",5,10,8,6,"accept","2017-05-30 19:37:08");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (16,7,"2017-04-07 05:14:00",5,3,10,2,"accept","2016-07-10 13:40:52"),(34,1,"2018-01-09 12:53:06",3,5,10,7,"accept","2017-10-12 16:42:25"),(12,8,"2018-01-31 08:09:05",8,2,10,8,"reject","2017-11-24 12:15:43"),(26,10,"2018-02-10 22:16:36",5,2,2,4,"reject","2017-10-30 20:50:32"),(11,8,"2017-09-24 15:42:06",1,10,5,9,"accept","2017-01-04 22:48:58"),(1,6,"2016-09-23 07:01:34",10,7,2,8,"reject","2017-03-16 15:50:13"),(3,3,"2017-07-08 06:22:31",9,2,2,3,"reject","2018-02-26 21:31:32"),(35,5,"2016-10-26 13:36:46",6,5,7,5,"accept","2018-03-20 04:06:07");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (16,3,"2016-06-12 13:40:37",5,2,10,10,"reject","2017-11-05 07:42:16"),(40,1,"2017-09-01 04:12:39",10,9,6,6,"accept","2017-09-29 11:11:14"),(14,10,"2018-04-12 01:02:18",6,1,1,5,"accept","2016-08-25 20:31:06"),(22,4,"2017-05-21 00:01:30",1,10,9,3,"accept","2017-10-05 08:49:20"),(5,6,"2017-07-06 08:57:47",4,5,5,1,"accept","2016-07-30 20:06:36"),(38,10,"2017-11-02 04:13:19",4,7,10,6,"accept","2016-06-17 14:02:15"),(29,4,"2016-12-16 16:03:26",1,6,8,8,"accept","2016-11-30 22:26:33"),(16,9,"2016-12-17 10:53:56",8,4,3,9,"reject","2017-05-28 02:26:12");
INSERT INTO manuscript_review (manuscript_ID,reviewer_ID,date_assigned,appropriateness,clarity,methodology,contribution,recommendation,date_reviewed) VALUES (9,10,"2017-06-25 03:00:18",6,6,8,9,"reject","2017-10-31 04:12:54"),(7,5,"2016-06-20 06:00:55",5,1,4,4,"reject","2016-11-10 23:44:32"),(1,4,"2017-05-19 23:57:56",9,8,3,5,"accept","2016-12-14 23:45:54"),(22,2,"2017-12-06 10:37:44",4,2,10,7,"reject","2018-04-18 09:58:17"),(31,10,"2017-03-21 20:00:16",7,4,8,2,"reject","2017-10-03 18:47:00"),(21,1,"2018-02-06 23:16:27",2,5,1,5,"reject","2016-08-28 17:36:58"),(23,4,"2017-03-14 13:21:44",1,1,3,9,"accept","2017-07-24 07:33:14"),(22,3,"2017-09-08 08:51:56",10,4,10,9,"reject","2016-10-02 20:52:14"),(8,2,"2016-11-30 05:52:06",5,1,4,1,"reject","2016-11-14 22:49:21");

INSERT INTO issue (pub_year,period_num,total_pages,print_date) VALUES (2010,1,58,"2017-05-31 16:42:50"),(2010,2,95,"2016-07-01 13:52:30"),(2010,3,67,"2016-07-09 13:52:28"),(2010,4,72,"2017-11-30 22:06:34"),(2011,1,2,"2016-06-21 05:40:29"),(2011,2,31,"2017-12-12 09:32:29"),(2011,3,22,"2016-12-18 10:06:29"),(2011,4,80,"2016-10-05 19:01:01"),(2012,1,64,"2017-07-22 21:54:59"),(2012,2,41,"2017-10-09 14:39:57");
INSERT INTO issue (pub_year,period_num,total_pages,print_date) VALUES (2012,3,18,"2016-05-05 16:50:38"),(2012,4,46,"2017-03-06 13:46:33"),(2013,1,94,"2016-06-08 00:49:54"),(2013,2,97,"2016-05-29 22:30:49"),(2013,3,78,"2017-03-23 20:11:31"),(2013,4,24,"2016-12-28 11:08:48"),(2014,1,48,"2016-05-09 20:06:35"),(2014,2,50,"2017-12-27 09:59:31"),(2014,3,5,"2017-10-18 22:36:16"),(2014,4,98,"2017-01-10 10:00:03");
INSERT INTO issue (pub_year,period_num,total_pages,print_date) VALUES (2015,1,22,"2017-06-10 21:14:10"),(2015,2,50,"2016-08-20 11:13:34"),(2015,3,7,"2018-02-14 16:55:52"),(2015,4,81,"2018-04-16 19:39:25"),(2016,1,39,"2017-05-16 09:42:05"),(2016,2,52,"2017-07-17 01:58:56"),(2016,3,77,"2016-08-12 06:36:24"),(2016,4,95,"2016-05-30 18:06:09"),(2017,1,4,"2017-09-21 21:14:22"),(2017,2,7,"2017-07-02 18:23:34");

INSERT INTO manuscript_final (manuscript_ID,num_pages,order_in_issue,start_page,pub_year,period_num) VALUES (1,9,3,5,2011,3),(2,17,10,63,2014,4),(3,8,10,21,2013,2),(4,5,7,68,2013,2),(5,11,3,38,2016,4),(6,20,8,3,2014,4),(7,8,10,97,2011,2),(8,9,3,66,2017,1),(9,16,3,34,2010,2),(10,14,5,83,2016,3);
INSERT INTO manuscript_final (manuscript_ID,num_pages,order_in_issue,start_page,pub_year,period_num) VALUES (11,9,10,23,2010,1),(12,3,4,83,2010,1),(13,20,9,26,2013,1),(14,11,9,49,2015,3),(15,2,1,13,2013,2),(16,12,9,20,2011,1),(17,6,5,24,2012,4),(18,14,1,12,2010,2),(19,12,8,70,2011,2),(20,19,2,31,2017,1);
INSERT INTO manuscript_final (manuscript_ID,num_pages,order_in_issue,start_page,pub_year,period_num) VALUES (21,8,1,7,2016,2),(22,9,1,64,2014,4),(23,19,5,35,2014,3),(24,6,4,84,2014,4),(25,15,8,69,2015,1),(26,2,8,1,2014,1),(27,3,7,17,2010,4),(28,11,10,70,2010,4),(29,15,3,89,2013,3),(30,12,3,84,2017,2);
INSERT INTO manuscript_final (manuscript_ID,num_pages,order_in_issue,start_page,pub_year,period_num) VALUES (31,16,4,69,2011,3),(32,12,6,47,2012,1),(33,14,1,92,2017,1),(34,7,8,45,2014,3),(35,12,2,98,2017,1),(36,12,7,66,2013,1),(37,12,8,80,2016,4),(38,13,9,47,2013,3),(39,18,7,49,2013,2),(40,18,8,36,2013,2);

-- -----------------------------------------------------
-- MAKE VIEWS
-- -----------------------------------------------------


-- LeadAuthorManuscripts

DROP VIEW IF EXISTS LeadAuthorManuscripts;

CREATE VIEW LeadAuthorManuscripts AS 
      SELECT 
            author.author_id, 
            author.first_name, 
            author.last_name, 
            author.address, 
            author.email, 
            author.affiliation_ID, 
            manuscript.manuscript_ID, 
            manuscript.status, 
            manuscript.status_time_stamp 
            FROM author LEFT JOIN manuscript 
            ON author.author_ID=manuscript.author_ID 
            ORDER BY author.last_name, manuscript.status_time_stamp;

-- AnyAuthorManuscripts

DROP VIEW IF EXISTS AnyAuthorManuscripts;

CREATE VIEW AnyAuthorManuscripts AS
      
      SELECT 
            author.first_name, 
            author.last_name, 
            manuscript.manuscript_ID, 
            manuscript.status, 
            manuscript.status_time_stamp
            FROM 
            author
            LEFT JOIN 
            manuscript
            ON
            author.author_id = manuscript.author_id
            UNION
            (SELECT secondary_author.first_name, secondary_author.last_name, 
                  manuscript.manuscript_ID, 
                  manuscript.status, 
                  manuscript.status_time_stamp 
                  FROM
                  manuscript
                  INNER JOIN
                  secondary_author
                  ON
                  manuscript.manuscript_ID = secondary_author.manuscript_ID)
            ORDER BY 
            last_name, status_time_stamp;


-- PublishedIssues

DROP VIEW IF EXISTS PublishedIssues;
DROP VIEW IF EXISTS PublishedIssuesSub;

CREATE VIEW PublishedIssuesSub AS
      SELECT title, start_page, pub_year, period_num 
            FROM manuscript NATURAL JOIN manuscript_final;


CREATE VIEW PublishedIssues AS
      SELECT 
            issue.pub_year, 
            issue.period_num, 
            man.title, 
            man.start_page
            FROM 
            issue NATURAL JOIN PublishedIssuesSub AS man
      ORDER BY
      issue.pub_year, issue.period_num, man.start_page;


-- ReviewQueue

DROP VIEW IF EXISTS ReviewQueueSub;
DROP VIEW IF EXISTS ReviewQueue;

CREATE VIEW ReviewQueueSub AS
      SELECT * FROM manuscript 
                        NATURAL JOIN manuscript_review 
                WHERE manuscript.status='under review';

CREATE VIEW ReviewQueue AS
      SELECT 
            manrev.author_ID,
        manrev.manuscript_ID,
        manrev.reviewer_ID,
        manrev.status_time_stamp  FROM 
                  ReviewQueueSub 
                AS manrev ORDER BY manrev.status_time_stamp;

-- WhatsLeft

DROP VIEW IF EXISTS WhatsLeft;

CREATE VIEW WhatsLeft AS
	SELECT manuscript_id, status,
			CASE
				WHEN status = 'submitted' THEN 'under review or rejected'
                WHEN status = 'under review' THEN 'accepted or rejected'
                WHEN status = 'accepted' THEN 'in typesetting'
                WHEN status = 'in typesetting' THEN 'scheduled'
                WHEN status = 'scheduled' THEN 'published'
                ELSE 'None'
                END AS remaining
FROM manuscript;


-- ReviewStatus
DROP VIEW IF EXISTS ReviewStatus;

CREATE VIEW ReviewStatus AS
      SELECT manuscript_ID, 
                  title,
            reviewer_ID,
            date_assigned,appropriateness, 
            clarity, 
            methodology, 
            contribution, 
            recommendation, 
            status_time_stamp 
            FROM 
            manuscript 
            NATURAL JOIN 
            ReviewQueue 
            NATURAL JOIN manuscript_review 
            ORDER BY status_time_stamp;
