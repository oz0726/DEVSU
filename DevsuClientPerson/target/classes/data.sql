DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS client;

CREATE TABLE PERSON (
	ID INT PRIMARY KEY,
	NAME VARCHAR(100) NOT NULL,
	GENRE VARCHAR(50) NOT NULL,
	AGE VARCHAR(20) NOT NULL,
	ADDRESS VARCHAR(250) NOT NULL,
	PHONE_NUMBER VARCHAR(100) NOT NULL
);

CREATE TABLE CLIENT (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	STATE BINARY(100) DEFAULT 1 NOT NULL,
	PASSWORD VARCHAR(250) NOT NULL,
	PERSON INTEGER NOT NULL,
	CONSTRAINT FK_CLIENT_PERSON FOREIGN KEY (PERSON) REFERENCES PERSON(ID)
);

INSERT INTO PERSON (ID, NAME, GENRE, AGE, ADDRESS, PHONE_NUMBER) VALUES (976431, 'José Lema', 'male', '30', 'Otaval sn y principal', '098254785');
INSERT INTO CLIENT (STATE, PASSWORD, PERSON) VALUES (1, '1234', 976431);