DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS CLIENT;

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
	STATE BOOLEAN DEFAULT TRUE NOT NULL,
	PASSWORD VARCHAR(250) NOT NULL,
	PERSON INTEGER NOT NULL,
	CONSTRAINT FK_CLIENT_PERSON FOREIGN KEY (PERSON) REFERENCES PERSON(ID)
);

INSERT INTO PERSON (ID, NAME, GENRE, AGE, ADDRESS, PHONE_NUMBER) VALUES (1, 'testclient', 'testclient', '30', 'testclient', '55512345');
INSERT INTO CLIENT (STATE, PASSWORD, PERSON) VALUES (1, '1234', 1);