CREATE TABLE korisnik (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    password TEXT,
    iznos REAL,
    iban TEXT UNIQUE
);

INSERT INTO korisnik(
VALUES( 01 , test, test, 2000, HR123456789012345);
);

SELECT *