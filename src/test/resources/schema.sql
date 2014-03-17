DROP TABLE account IF EXISTS;
DROP TABLE transaction IF EXISTS;

CREATE TABLE account
(
	id		LONG IDENTITY PRIMARY KEY,
	accNumber 	number unique,
	name		varchar(30),
	address         varchar(150),
	phone		varchar(30),
	balance		number
);

CREATE TABLE transaction
(
	id		LONG IDENTITY PRIMARY KEY,
	amount		number,
	fromAccount LONG,
	toAccount   LONG,
	transactionType varchar(30),
	transactionDate		timestamp
);