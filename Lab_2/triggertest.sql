USE jdressel_db;

-- RUN LINE BY LINE

-- Testing UnderReview_trigger


-- Deletes the only reviewer assigned to manuscript #2, changes status
SELECT * FROM manuscript_review WHERE manuscript_ID = 2;
SELECT status FROM manuscript WHERE manuscript_ID = 2;

DELETE FROM reviewer WHERE reviewer_ID = 8;

-- show messages
SELECT * FROM message;

-- manuscript no longer in manuscript_review
SELECT * FROM manuscript_review WHERE manuscript_ID = 2;
-- status changed to submitted
SELECT manuscript_ID, status FROM manuscript WHERE manuscript_ID = 2;

-- clear messages
TRUNCATE message;


-- Deletes 1 of 2 reviewers for manuscript #1, status stays the same
SELECT * from manuscript_review WHERE manuscript_ID = 1;
SELECT status FROM manuscript WHERE manuscript_ID = 1;

DELETE FROM reviewer WHERE reviewer_ID = 4;
SELECT * from manuscript_review WHERE manuscript_ID = 1;

SELECT status FROM manuscript WHERE manuscript_ID = 1;

-- show message, should not be a message present about manuscript_ID = 1;
SELECT * FROM message;

-- clear messages
TRUNCATE message;



-- Testing check_ricode trigger

-- Should return an Error
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (9,1,"tortor. Nunc commodo","submitted",NOW(),2);

-- Insert will be successful
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (9,1,"tortor. Nunc commodo","submitted",NOW(),11);




