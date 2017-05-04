-- -----------------------------------------------------
-- Julia Dressel and Max Parker
-- Lab 2c
-- views.sql
-- -----------------------------------------------------

USE jdressel_db;

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
                WHERE manuscript.status=' under review ';

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

