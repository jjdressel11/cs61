USE jdressel_db;

DROP TRIGGER IF EXISTS ManReview_trigger;

DELIMITER /

CREATE TRIGGER ManReview_trigger AFTER DELETE ON manuscript_review
FOR EACH ROW
BEGIN
	DECLARE signal_message VARCHAR(128);
	DECLARE man_count INT DEFAULT 0;
    
    SELECT COUNT(*) INTO man_count FROM manuscript_review where manuscript_ID = OLD.manuscript_ID;
    
    -- If deleting this reviwer means the manuscript no longer has reviewers
    IF (man_count = 0) THEN
		UPDATE manuscript SET status = 'submitted' WHERE manuscript_ID = OLD.manuscript_ID;
        UPDATE manuscript SET status_time_stamp = NOW() WHERE manuscript_ID = OLD.manuscript_ID;
        
        -- THIS IS NOT WORKING - not showing the proper warning
        SET signal_message = CONCAT('Manuscript has no more reviewers: ', CAST(OLD.manuscript_ID AS CHAR));
        SIGNAL SQLSTATE '01000' SET message_text = signal_message;
    END IF;
		
END
/

DELIMITER ;


DROP TRIGGER IF EXISTS UnderReview_trigger;

DELIMITER /

CREATE TRIGGER UnderReview_trigger BEFORE DELETE ON reviewer
FOR EACH ROW
BEGIN
    DECLARE signal_message VARCHAR(128);
    
    -- Remove reviewer from reviewer_interest table
    DELETE FROM reviewer_interest WHERE reviewer_ID = OLD.reviewer_ID;
    
    DELETE FROM manuscript_review WHERE reviewer_ID = OLD.reviewer_ID;
    
    
    -- SELECT manuscript_ID FROM manuscript_review WHERE reviewer_ID = OLD.reviewer_ID;
END
/
-- restore delimiter
DELIMITER ;

DROP TRIGGER IF EXISTS check_ricode;

DELIMITER /

CREATE TRIGGER check_ricode BEFORE INSERT ON manuscript
FOR EACH ROW
BEGIN
	DECLARE signal_message VARCHAR(128);
    DECLARE code_count INT DEFAULT 0;
    
    SELECT COUNT(*) INTO code_count FROM reviewer_interest WHERE RI_Code = new.RI_Code;
    
    IF code_count = 0 THEN
    
		SET signal_message = CONCAT('Error: no reviewer exists to handle the RICode of the submitted manuscript id: ', CAST(new.manuscript_ID AS CHAR)); 
        
        SIGNAL SQLSTATE '45000' SET message_text = signal_message;
	END IF;
END
/

DELIMITER ;




