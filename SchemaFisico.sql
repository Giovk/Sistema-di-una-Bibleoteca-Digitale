CREATE TABLE UTENTE
(
    Username VARCHAR(30) PRIMARY KEY,
    Email VARCHAR(319) UNIQUE NOT NULL, -- La lunghezza massima di un indirizzo email sono 319 caratteri.
    Pw VARCHAR(64) NOT NULL, -- La lunghezza massima di una password sono 64 caratteri.
    PartitaIVA VARCHAR(11) UNIQUE,
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL
);

CREATE TABLE LIBRERIA
(
    CodL SERIAL PRIMARY KEY,
    NumeroTelefonico VARCHAR(10) NOT NULL,
    SitoWeb VARCHAR(128),
    Nome VARCHAR(128) NOT NULL,
    Indirizzo VARCHAR(256),
    Gestore VARCHAR(30) NOT NULL,

    FOREIGN KEY(Gestore) REFERENCES UTENTE(Username)
);

CREATE DOMAIN issn AS VARCHAR(9)
    CONSTRAINT C10 CHECK (VALUE LIKE '____-____');

CREATE TABLE RIVISTA
(
    ISSN issn PRIMARY KEY, -- Vincolo posizine trattini EX: ####-####
    Editore VARCHAR(64) NOT NULL,
    Argomento VARCHAR(256) NOT NULL,
    CognomeR VARCHAR(64) NOT NULL,
    NomeR VARCHAR(64) NOT NULL,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    AnnoPubblicazione VARCHAR(4) NOT NULL
);

CREATE DOMAIN doi AS VARCHAR(8)
    CONSTRAINT C11 CHECK (VALUE LIKE '10-%');

CREATE TABLE ARTICOLO_SCIENTIFICO
(
    DOI doi PRIMARY KEY,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    AnnoPubblicazione VARCHAR(4) NOT NULL 
);

CREATE TABLE FASCICOLO
(
    CodF SERIAL PRIMARY KEY,
    Numero INT NOT NULL DEFAULT 1, 
    Editore VARCHAR(64) NOT NULL,
    DataPubblicazione DATE NOT NULL,
    ISSN issn(9),

    FOREIGN KEY (ISSN) REFERENCES RIVISTA(ISSN)
);

CREATE TABLE INTRODUZIONE
(
    CodF INT,
    DOI doi,

    PRIMARY KEY (CodF, DOI),
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF),
    FOREIGN KEY(DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI)
);

CREATE TABLE CONFERENZA
(
    CodC SERIAL PRIMARY KEY,
    Luogo VARCHAR(256) NOT NULL,
    StrutturaOrganizzatrice VARCHAR(128) NOT NULL,
    DataInizio DATE NOT NULL,
    DataFine DATE NOT NULL -- Aggiungere vincolo DataInizio <= DataFine
);

CREATE TABLE ESPOSIZIONE
(
    DOI doi,
    CodC INT,

    PRIMARY KEY(DOI, CodC),
    FOREIGN KEY(DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI),
    FOREIGN KEY(CodC) REFERENCES CONFERENZA(CodC)
);

CREATE TABLE SERIE
(
    CodS SERIAL PRIMARY KEY,
    Titolo VARCHAR(256) NOT NULL,
    ISSN issn UNIQUE,
    DataPubblicazione DATE NOT NULL,
    NLibri INT NOT NULL
);

CREATE DOMAIN isbn AS VARCHAR(17)
    CONSTRAINT C2 CHECK (VALUE LIKE '978-%-%-_');

CREATE TABLE LIBRO
(
    ISBN isbn PRIMARY KEY, -- Vincolo posizine trattini EX: ###-##-##-#####-#
    Titolo VARCHAR(256) NOT NULL,
    Genere VARCHAR(64) NOT NULL,
    Lingua VARCHAR(64) NOT NULL,
    Editore VARCHAR(64) NOT NULL,
    DataPubblicazione DATE NOT NULL
);

CREATE TABLE INSERIMENTO
(
    CodS INT,
    ISBN isbn, -- Vincolo posizine trattini EX: ___-__-__-_____-_
    Ordine INT NOT NULL, -- Vincolo (Ordine <= NLibri) && (OrdineUltimoInserito < OrdineAttuale)

    PRIMARY KEY(CodS, ISBN),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS),
    FOREIGN KEY(ISBN) REFERENCES LIBRO(ISBN)  
);

CREATE DOMAIN fruizione AS VARCHAR(10)
    CONSTRAINT C3 CHECK (VALUE IN ('Cartaceo', 'Digitale', 'AudioLibro'));

CREATE TABLE POSSESSO_F
(
    CodL INT,
    CodF INT,
    Fruizione fruizione NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(CodL, CodF),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL),
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF)  
);

CREATE TABLE PREFERITI_F
(
    Username VARCHAR(30),
    CodF INT,
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5
    Preferito BOOLEAN false,

    PRIMARY KEY(Username, CodF),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(CodF) REFERENCES FASCICOLO(CodF)  
);

CREATE TABLE POSSESSO_S
(
    CodL,
    CodS INT,
    Fruizione fruizione NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(CodL, CodS),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS)  
);

CREATE TABLE PREFERITI_S
(
    Username VARCHAR(30),
    CodS INT,
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, CodS),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS)
);

CREATE TABLE POSSESSO_L
(
    CodL INT,
    ISBN isbn, -- Vincolo posizine trattini 
    Fruizione fruizione NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(CodL, ISBN),
    FOREIGN KEY(CodL) REFERENCES LIBRERIA(CodL),
    FOREIGN KEY(ISBN) REFERENCES Libro(ISBN)  
);

CREATE TABLE PREFERITI_L
(
    Username VARCHAR(30),
    ISBN isbn, -- Vincolo posizine trattini
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, ISBN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(ISBN) REFERENCES Libro(ISBN)
);

CREATE TABLE PRESENTAZIONE
(
    CodP INT,
    Luogo VARCHAR(256) NOT NULL,
    Struttura VARCHAR(128) NOT NULL,
    DataP DATE NOT NULL,
    Ora TIME NOT NULL,
    ISBN isbn,  -- Vincolo posizine trattini

    PRIMARY KEY(CodP, ISBN),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN) 
);

CREATE TABLE COLLANA
(
    CodC SERIAL PRIMARY KEY,
    Nome VARCHAR(256) NOT NULL,
    ISSN issn UNIQUE,
    Caratteristica VARCHAR(128) NOT NULL,
);

CREATE TABLE APPARTENENZA
(
    ISBN isbn,  -- Vincolo posizine trattini 
    CodC INT,
    
    PRIMARY KEY(ISBN, CodC),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN),
    FOREIGN KEY (CodC) REFERENCES COLLANA(CodC) 
);

CREATE TABLE AUTORE
(
    CodA SERIAL PRIMARY KEY;
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL;
    Nazionalita VARCHAR(64),
    DataNascita DATE
);

CREATE TABLE SCRITTURA_A
(
    DOI doi,
    CodA INT,

    PRIMARY KEY(DOI, CodA),
    FOREIGN KEY (DOI) REFERENCES ARTICOLO_SCIENTIFICO(DOI),
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA) 
);

CREATE TABLE SCRITTURA_L
(
    ISBN isbn, -- Vincolo posizine trattini 
    CodA INT,

    PRIMARY KEY(ISBN, CodA),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN),
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA) 
);