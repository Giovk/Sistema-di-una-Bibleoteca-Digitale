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
    CodL INT PRIMARY KEY,
    NumeroTelefonico VARCHAR(10) NOT NULL,
    SitoWeb VARCHAR(128),
    Nome VARCHAR(128) NOT NULL,
    Indirizzo VARCHAR(256),
    Gestore VARCHAR(30) NOT NULL,

    FOREIGN KEY(Gestore) REFERENCES UTENTE(Username)
);

CREATE TABLE RIVISTA
(
    ISSN VARCHAR(9) PRIMARY KEY,
    Editore VARCHAR(64) NOT NULL,
    Argomento VARCHAR(256) NOT NULL,
    CognomeR VARCHAR(64) NOT NULL,
    NomeR VARCHAR(64) NOT NULL,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    DataPubblicazione DATE NOT NULL
);

CREATE TABLE ARTICOLO_SCIENTIFICO
(
    CodAS INT PRIMARY KEY,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    AnnoPubblicazione VARCHAR(4) NOT NULL
);

CREATE TABLE COMPOSIZIONE
(
    ISSN VARCHAR(9),
    CodAS INT,
    DataInserimento DATE NOT NULL,
    NumeroRivista VARCHAR(4) NOT NULL,

    PRIMARY KEY (ISSN, CodAS),
    FOREIGN KEY(ISSN) REFERENCES RIVISTA(ISSN),
    FOREIGN KEY(CodAS) REFERENCES ARTICOLO_SCIENTIFICO(CodAS)
);

CREATE TABLE CONFERENZA
(
    CodC INT PRIMARY KEY,
    Luogo VARCHAR(256) NOT NULL,
    StrutturaOrganizzatrice VARCHAR(128) NOT NULL,
    DataInizio DATE NOT NULL,
    DataFine DATE NOT NULL-- Aggiungere vincolo DataInizio <= DataFine
);

CREATE TABLE ESPOSIZIONE
(
    CodAS INT,
    CodC INT,

    PRIMARY KEY(CodAS, CodC),
    FOREIGN KEY(CodAS) REFERENCES ARTICOLO_SCIENTIFICO(CodAS),
    FOREIGN KEY(CodC) REFERENCES CONFERENZA(CodC)
);

CREATE TABLE SERIE
(
    CodS INT PRIMARY KEY,
    Titolo VARCHAR(256) NOT NULL,
    ISSN VARCHAR(9) UNIQUE,
    DataPubblicazione DATE NOT NULL,
    NLibri INT NOT NULL
);

CREATE TABLE LIBRO
(
    ISBN VARCHAR(18) PRIMARY KEY, -- Vincolo posizine trattini EX: ###-##-##-#####-#
    Titolo VARCHAR(256) NOT NULL,
    Genere VARCHAR(64) NOT NULL,
    Lingua VARCHAR(64) NOT NULL,
    Editore VARCHAR(64) NOT NULL,
    DataPubblicazione DATE NOT NULL
);

CREATE TABLE INSERIMENTO
(
    CodS INT,
    ISBN VARCHAR(18), -- Vincolo posizine trattini EX: ###-##-##-#####-#
    Ordine INT NOT NULL, -- Vincolo (Ordine <= NLibri) && (OrdineUltimoInserito < OrdineAttuale)

    PRIMARY KEY(CodS, ISBN),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS),
    FOREIGN KEY(ISBN) REFERENCES LIBRO(ISBN)  
);

CREATE TABLE POSSESSO_R
(
    Username VARCHAR(30),
    ISSN VARCHAR(9),
    Fruizione VARCHAR(10) NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(Username, ISSN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(ISSN) REFERENCES RIVISTA(ISSN)  
);

CREATE TABLE PREFERITI_R
(
    Username VARCHAR(30),
    ISSN VARCHAR(9),
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5,

    PRIMARY KEY(Username, ISSN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(ISSN) REFERENCES RIVISTA(ISSN)  
);

CREATE TABLE POSSESSO_S
(
    Username VARCHAR(30),
    CodS INT,
    Fruizione VARCHAR(10) NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(Username, CodS),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS)  
);

CREATE TABLE PREFERITI_S
(
    Username VARCHAR(30),
    CodS VARCHAR(9),
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5,

    PRIMARY KEY(Username, CodS),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(CodS) REFERENCES SERIE(CodS)
);

CREATE TABLE POSSESSO_L
(
    Username VARCHAR(30),
    ISBN VARCHAR(18), -- Vincolo posizine trattini EX: ###-##-##-#####-#
    Fruizione VARCHAR(10) NOT NULL, -- Vincolo i valori possibili possono essere: "Cartaceo", "Digitale" e "AudioLibro".
    Quantita INT, -- Vincolo NOT NULL if (Fruizione == ("Digitale" || "AudioLibro")).

    PRIMARY KEY(Username, ISBN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username),
    FOREIGN KEY(ISBN) REFERENCES Libro(ISBN)  
);

CREATE TABLE PREFERITI_L
(
    Username VARCHAR(30),
    ISBN VARCHAR(18), -- Vincolo posizine trattini EX: ###-##-##-#####-#
    Recensione VARCHAR(512),
    Valutazione INT, -- Vincolo 0 <= Valutazione <= 5,

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
    ISBN VARCHAR(18),  -- Vincolo posizine trattini EX: ###-##-##-#####-#

    PRIMARY KEY(CodP, ISBN),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN) 
);

CREATE TABLE COLLANA
(
    CodC INT PRIMARY KEY,
    Nome VARCHAR(256) NOT NULL,
    ISSN VARCHAR(9) UNIQUE,
);

CREATE TABLE APPARTENENZA
(
    ISBN VARCHAR(18),  -- Vincolo posizine trattini EX: ###-##-##-#####-#
    CodC INT,
    Caratteristica VARCHAR(128) NOT NULL,

    PRIMARY KEY(ISBN, CodC),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN),
    FOREIGN KEY (CodC) REFERENCES COLLANA(CodC) 
);

CREATE TABLE AUTORE
(
    CodA INT PRIMARY KEY;
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL;
    Nazionalita VARCHAR(64),
    DataNascita DATE
);

CREATE TABLE SCRITTURA_A
(
    CodAS INT,
    CodA INT,

    PRIMARY KEY(CodAS, CodA),
    FOREIGN KEY (CodAS) REFERENCES ARTICOLO_SCIENTIFICO(CodAS),
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA) 
);

CREATE TABLE SCRITTURA_L
(
    ISBN VARCHAR(18), -- Vincolo posizine trattini EX: ###-##-##-#####-#
    CodA INT,

    PRIMARY KEY(ISBN, CodA),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN),
    FOREIGN KEY (CodA) REFERENCES AUTORE(CodA) 
);

