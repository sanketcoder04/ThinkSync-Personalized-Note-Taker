-- Database related operations for Testing
CREATE DATABASE thinksync;
USE thinksync;

SELECT * FROM user_details;
SELECT * FROM feedback_details;

SELECT * FROM category_details;
SELECT * FROM note_details;
SELECT * FROM note_category;


-- Schema for QR Code file storing
CREATE TABLE qrfile (
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    content LONGTEXT,
    filename VARCHAR(100),
    filedata LONGBLOB,
    userid INT
);

SELECT * FROM qrfile;
SET SQL_SAFE_UPDATES = 0;
DROP TABLE qrfile;
SET SQL_SAFE_UPDATES = 1;
