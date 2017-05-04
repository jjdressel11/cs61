-- -----------------------------------------------------
-- Julia Dressel and Max Parker
-- Lab 2c
-- tables.sql
-- -----------------------------------------------------


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
  affiliation_ID INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (affiliation_ID)
);


-- -----------------------------------------------------
-- Table author
-- -----------------------------------------------------
CREATE TABLE author (
  author_ID INT NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  address VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  affiliation_ID INT NOT NULL,
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
  editor_ID INT NOT NULL,
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
  manuscript_ID INT NOT NULL,
  editor_ID INT NOT NULL,
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
  reviewer_ID INT NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  affiliation_ID INT NOT NULL,
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
  pub_year INT NOT NULL,
  period_num INT NOT NULL,
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

