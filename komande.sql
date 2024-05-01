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

SELECT * from korisnik