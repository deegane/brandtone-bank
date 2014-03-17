DROP TABLE account IF EXISTS;

CREATE TABLE account
(
	id		LONG IDENTITY PRIMARY KEY,
	accNumber 	number,
	name		varchar(30),
	address         varchar(150),
	phone		varchar(30),
	balance		number
);
