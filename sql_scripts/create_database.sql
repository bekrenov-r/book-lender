DROP SCHEMA IF EXISTS booklender;
CREATE SCHEMA booklender;
USE booklender;

CREATE TABLE lend (
   id int NOT NULL AUTO_INCREMENT,
   book_title varchar(255) DEFAULT NULL,
   book_author varchar(255) DEFAULT NULL,
   book_year int DEFAULT NULL,
   img_path varchar(255) DEFAULT NULL,
   person_first_name varchar(255) DEFAULT NULL,
   person_last_name varchar(255) DEFAULT NULL,
   person_email varchar(255) DEFAULT NULL,
   person_phone_number varchar(255) DEFAULT NULL,
   date_of_lend date DEFAULT NULL,
   date_of_return date DEFAULT NULL,
   PRIMARY KEY (id)
   );