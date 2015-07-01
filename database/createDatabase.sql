CREATE TABLE messages (
	identifier VARCHAR(50),
	message VARCHAR(1000),
	username VARCHAR(50),
	privateMessage BIT DEFAULT 0
);