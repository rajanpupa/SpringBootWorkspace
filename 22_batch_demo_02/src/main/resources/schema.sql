drop table if EXISTS PEOPLE;

create Table PEOPLE (
	id 			bigint(10) 		not null AUTO_INCREMENT PRIMARY KEY,
	email 		VARCHAR(255) 	not null,
	age 		INT(3) 			NOT NULL,
	first_name 	VARCHAR(255) 	NOT NULL
	
)