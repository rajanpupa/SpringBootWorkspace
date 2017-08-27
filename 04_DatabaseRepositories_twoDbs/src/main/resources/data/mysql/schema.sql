DROP TABLE IF EXISTS Greeting;

CREATE TABLE Greeting(
	id BIGINT(20) unsigned NOT NULL auto_increment,
	text VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
);

