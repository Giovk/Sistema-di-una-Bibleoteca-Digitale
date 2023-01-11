CREATE DOMAIN partitaiva AS VARCHAR(11)
    CONSTRAINT C15 CHECK (VALUE LIKE '___________');

CREATE TABLE UTENTE
(
    Username VARCHAR(30) PRIMARY KEY,
    Email VARCHAR(319) UNIQUE NOT NULL, -- La lunghezza massima di un indirizzo email sono 319 caratteri.
    Pw VARCHAR(64) NOT NULL, -- La lunghezza massima di una password sono 64 caratteri.
    PartitaIVA partitaiva UNIQUE,
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL
);

CREATE SEQUENCE libreria_seq_id;

CREATE TABLE LIBRERIA
(
    CodL INT NOT NULL DEFAULT NEXTVAL('libreria_seq_id') PRIMARY KEY,
    NumeroTelefonico VARCHAR(10) NOT NULL,
    SitoWeb VARCHAR(128),
    Nome VARCHAR(128) NOT NULL,
    Indirizzo VARCHAR(256),
    Gestore VARCHAR(30) NOT NULL,

    FOREIGN KEY(Gestore) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE DOMAIN issn AS VARCHAR(9)
    CONSTRAINT C10 CHECK (VALUE LIKE '____-____');

CREATE TABLE RIVISTA
(
    ISSN issn PRIMARY KEY,
    Editore VARCHAR(64) NOT NULL,
    Argomento VARCHAR(256) NOT NULL,
    CognomeR VARCHAR(64) NOT NULL,
    NomeR VARCHAR(64) NOT NULL,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    AnnoPubblicazione INT NOT NULL
);

CREATE DOMAIN doi AS VARCHAR(8)
    CONSTRAINT C11 CHECK (VALUE LIKE '10-%');

CREATE TABLE ARTICOLO_SCIENTIFICO
(
    DOI doi DEFAULT '10-00000' PRIMARY KEY,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    AnnoPubblicazione INT NOT NULL 
);

CREATE SEQUENCE fascicolo_seq_id;

CREATE TABLE FASCICOLO
(
    CodF INT NOT NULL DEFAULT NEXTVAL('fascicolo_seq_id') PRIMARY KEY,
    Numero INT NOT NULL DEFAULT 1, 
    Editore VARCHAR(64) NOT NULL,
    DataPubblicazione DATE NOT NULL,
    ISSN issn,

    FOREIGN KEY (ISSN) REFERENCES RIVISTA(ISSN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE INTRODUZIONE
(
    CodF INT,
    DOI doi,

    PRIMARY KEY (CodF, DOI),
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE conferenza_seq_id;

CREATE TABLE CONFERENZA
(
    CodC INT NOT NULL DEFAULT NEXTVAL('conferenza_seq_id') PRIMARY KEY,
    Luogo VARCHAR(256) NOT NULL,
    StrutturaOrganizzatrice VARCHAR(128) NOT NULL,
    DataInizio DATE NOT NULL,
    DataFine DATE NOT NULL
);

CREATE TABLE ESPOSIZIONE
(
    DOI doi,
    CodC INT,

    PRIMARY KEY(DOI, CodC),
    FOREIGN KEY(DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(CodC) REFERENCES CONFERENZA(CodC)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE DOMAIN isbn AS VARCHAR(17)
    CONSTRAINT C2 CHECK (VALUE LIKE '978-%-%-_');

CREATE TABLE SERIE
(
    ISBN isbn PRIMARY KEY,
    Titolo VARCHAR(256) NOT NULL,
    DataPubblicazione DATE NOT NULL,
    NLibri INT NOT NULL
);

CREATE TABLE LIBRO
(
    ISBN isbn PRIMARY KEY,
    Titolo VARCHAR(256) NOT NULL,
    Genere VARCHAR(64) NOT NULL,
    Lingua VARCHAR(64) NOT NULL,
    Editore VARCHAR(64) NOT NULL,
    DataPubblicazione DATE NOT NULL
);

CREATE TABLE INSERIMENTO
(
    Serie isbn,
    Libro isbn,
    Ordine INT,

    PRIMARY KEY(Serie, Libro),
    FOREIGN KEY(Serie) REFERENCES SERIE(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(Libro) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE  
);

CREATE DOMAIN fruizione AS VARCHAR(10)
    CONSTRAINT C3 CHECK (VALUE IN ('Cartaceo', 'Digitale', 'AudioLibro'));

CREATE TABLE POSSESSO_F
(
    CodL INT,
    CodF INT,
    Fruizione fruizione NOT NULL,
    Quantita INT,

    PRIMARY KEY(CodL, CodF, Fruizione),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PREFERITI_F
(
    Username VARCHAR(30),
    CodF INT,
    Recensione VARCHAR(512),
    Valutazione INT,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, CodF),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF) 
        ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE POSSESSO_S
(
    CodL INT,
    ISBN isbn,
    Fruizione fruizione NOT NULL,
    Quantita INT,

    PRIMARY KEY(CodL, ISBN, Fruizione),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(ISBN) REFERENCES SERIE(ISBN)  
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PREFERITI_S
(
    Username VARCHAR(30),
    ISBN isbn,
    Recensione VARCHAR(512),
    Valutazione INT,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, ISBN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(ISBN) REFERENCES SERIE(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE POSSESSO_L
(
    CodL INT,
    ISBN isbn,
    Fruizione fruizione NOT NULL,
    Quantita INT,

    PRIMARY KEY(CodL, ISBN, Fruizione),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(ISBN) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PREFERITI_L
(
    Username VARCHAR(30),
    ISBN isbn,
    Recensione VARCHAR(512),
    Valutazione INT,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, ISBN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(ISBN) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE presentazione_seq_id;

CREATE TABLE PRESENTAZIONE
(
    CodP INT NOT NULL DEFAULT NEXTVAL('presentazione_seq_id'),
    Luogo VARCHAR(256) NOT NULL,
    Struttura VARCHAR(128) NOT NULL,
    DataP DATE NOT NULL,
    Ora TIME NOT NULL,
    ISBN isbn,

    PRIMARY KEY(CodP, ISBN),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE collana_seq_id;

CREATE TABLE COLLANA
(
    CodC INT NOT NULL DEFAULT NEXTVAL('collana_seq_id') PRIMARY KEY,
    Nome VARCHAR(256) NOT NULL,
    ISSN issn UNIQUE,
    Caratteristica VARCHAR(128) NOT NULL
);

CREATE TABLE APPARTENENZA
(
    ISBN isbn,
    CodC INT,
    
    PRIMARY KEY(ISBN, CodC),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CodC) REFERENCES COLLANA(CodC)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE autore_seq_id;
ALTER SEQUENCE autore_seq_id RESTART WITH 1;

CREATE TABLE AUTORE
(
    CodA INT NOT NULL DEFAULT NEXTVAL('autore_seq_id') PRIMARY KEY,
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL,
    Nazionalita VARCHAR(64),
    DataNascita DATE
);

CREATE TABLE SCRITTURA_A
(
    DOI doi,
    CodA INT,

    PRIMARY KEY(DOI, CodA),
    FOREIGN KEY (DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE SCRITTURA_L
(
    ISBN isbn,
    CodA INT,

    PRIMARY KEY(ISBN, CodA),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE VIEW NOTIFICA AS
SELECT PR_S.CodS, PR_S.Username, PO_S.CodL
FROM PREFERITI_S AS PR_S JOIN POSSESSO_S AS PO_S ON PR_S.ISBN=PO_S.ISBN
WHERE PR_S.Preferito=true