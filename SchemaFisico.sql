CREATE TABLE UTENTE
(
    Username VARCHAR(20) PRIMARY KEY,
    Email VARCHAR(319) UNIQUE, -- La lunghezza massima di un indirizzo email sono 319 caratteri.
    Pw VARCHAR(64), -- La lunghezza massima di una password sono 64 caratteri.
    PartitaIVA VARCHAR(11), -- Aggiungere vincolo Unique o Null.
    Nome VARCHAR(64),
    Cognome VARCHAR(64)
);

CREATE TABLE LIBRERIA
(
    CodL INT PRIMARY KEY,
    NumeroTelefonico VARCHAR(10),
)