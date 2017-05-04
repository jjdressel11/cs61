USE jdressel_db;

-- RUN LINE BY LINE

-- Testing check_ricode trigger

-- Should return an Error
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (9,1,"tortor. Nunc commodo","submitted",NOW(),2);

-- Insert will be successful
INSERT INTO manuscript  (editor_ID,author_ID,title,status,status_time_stamp,RI_Code) VALUES (9,1,"tortor. Nunc commodo","submitted",NOW(),11);


-- Testing UnderReview_trigger


-- Deletes the only reviewer assigned to manuscript #2, changes status
SELECT * FROM manuscript_review WHERE manuscript_ID = 2;
SELECT status FROM manuscript WHERE manuscript_ID = 2;

DELETE FROM reviewer WHERE reviewer_ID = 8;

-- manuscript no longer in manuscript_review
SELECT * FROM manuscript_review WHERE manuscript_ID = 2;
-- status changed to submitted
SELECT status, manuscript_ID FROM manuscript WHERE manuscript_ID = 2;


-- Deletes 1 of 2 reviewers for manuscript #1, status stays the same
SELECT * from manuscript_review WHERE manuscript_ID = 1;
SELECT status FROM manuscript WHERE manuscript_ID = 1;

DELETE FROM reviewer WHERE reviewer_ID = 4;
SELECT * from manuscript_review WHERE manuscript_ID = 1;

SELECT status FROM manuscript WHERE manuscript_ID = 1;




