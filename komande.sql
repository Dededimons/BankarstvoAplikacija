CREATE TABLE korisnik (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    password TEXT,
    ime TEXT,
    prezime TEXT,
    iznos REAL,
    iban TEXT UNIQUE
);

INSERT INTO korisnik (id, username, password, ime, prezime,  iznos, iban)
VALUES (1, "test", "test", "Filip", "Filipovic", 2000, "HR123456789012345");

INSERT INTO korisnik (id, username, password, ime, prezime,  iznos, iban)
VALUES (2, "test2", "test2", "Ivo", "Ivic", 1000, "HR98765432101234");

INSERT INTO korisnik (id, username, password, ime, prezime,  iznos, iban)
VALUES (3, "teststanje", "teststanje", "teststanje", "teststanje", 1000, "HR123");

SELECT * from korisnik


DELETE FROM korisnik WHERE username = 'testuser'

CREATE TABLE placanja (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    placa TEXT,
    prima TEXT,
    iznos REAL,
    datum TEXT
);

DROP TABLE placanja;

SELECT * from placanja



CREATE TABLE korisnik (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    password TEXT,
    ime TEXT,
    prezime TEXT,
    iznos REAL,
    iban TEXT UNIQUE
);


INSERT INTO korisnik (id, username, password, ime, prezime,  iznos, iban)
VALUES (98, "placa", "placa", "placa", "placa", 100, "HR12345678901234");

INSERT INTO korisnik (id, username, password, ime, prezime,  iznos, iban)
VALUES (99, "prima", "prima", "prima", "prima", 100, "HR222222222222222");