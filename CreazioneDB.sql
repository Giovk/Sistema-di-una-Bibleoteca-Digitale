-- CREAZIONE DATABASE --

CREATE DOMAIN partitaiva AS VARCHAR(11)
    CONSTRAINT C15 CHECK (VALUE LIKE '___________');

CREATE TABLE UTENTE
(
    Username VARCHAR(30) PRIMARY KEY,
    Email VARCHAR(319) UNIQUE NOT NULL, -- La lunghezza massima di un indirizzo email sono 319 caratteri.
    PasswordU VARCHAR(64) NOT NULL, -- La lunghezza massima di una password sono 64 caratteri.
    PartitaIVA partitaiva UNIQUE,
    Nome VARCHAR(64) NOT NULL,
    Cognome VARCHAR(64) NOT NULL
);

CREATE DOMAIN numerotelefonico AS VARCHAR(10)
    CONSTRAINT C17 CHECK (VALUE LIKE '__________');

CREATE TABLE LIBRERIA
(
    CodL SERIAL PRIMARY KEY,
    NumeroTelefonico numerotelefonico UNIQUE NOT NULL,
    SitoWeb VARCHAR(128),
    Nome VARCHAR(128) NOT NULL,
    Indirizzo VARCHAR(256),
    Gestore VARCHAR(30) NOT NULL,

    UNIQUE(SitoWeb, Nome, Indirizzo, Gestore),
    FOREIGN KEY(Gestore) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE DOMAIN issn AS VARCHAR(9)
    CONSTRAINT C10 CHECK (VALUE LIKE '____-____');

CREATE TABLE RIVISTA
(
    ISSN issn PRIMARY KEY,
    Argomento VARCHAR(256) NOT NULL,
    CognomeR VARCHAR(64) NOT NULL,
    NomeR VARCHAR(64) NOT NULL,
    Titolo VARCHAR(256) UNIQUE NOT NULL,
    Editore VARCHAR(256) NOT NULL,
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

CREATE TABLE FASCICOLO
(
    CodF SERIAL PRIMARY KEY,
    Numero INT NOT NULL DEFAULT 1, 
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


CREATE TABLE CONFERENZA
(
    CodC SERIAL PRIMARY KEY,
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

CREATE TABLE RECENSIONE_F
(
    Username VARCHAR(30),
    CodF INT,
    Testo VARCHAR(512),
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

CREATE TABLE RECENSIONE_S
(
    Username VARCHAR(30),
    ISBN isbn,
    Testo VARCHAR(512),
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

CREATE TABLE RECENSIONE_L
(
    Username VARCHAR(30),
    ISBN isbn,
    Testo VARCHAR(512),
    Valutazione INT,
    Preferito BOOLEAN DEFAULT false,

    PRIMARY KEY(Username, ISBN),
    FOREIGN KEY(Username) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(ISBN) REFERENCES LIBRO(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE NOTIFICA
(
    Username VARCHAR(30),   
    ISBN isbn,              
    Libreria INT,
    DataInvio DATE,          
    OraInvio TIME,           
    Testo VARCHAR(512),
    Lettura BOOLEAN DEFAULT false,     

    PRIMARY KEY(Username, ISBN, Libreria, OraInvio, DataInvio),
    FOREIGN KEY (Username) REFERENCES UTENTE(Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ISBN) REFERENCES SERIE(ISBN)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PRESENTAZIONE
(
    CodP SERIAL PRIMARY KEY,
    Luogo VARCHAR(256) NOT NULL,
    Struttura VARCHAR(128) NOT NULL,
    DataP DATE NOT NULL,
    Ora TIME NOT NULL,
    ISBN isbn NOT NULL,

    UNIQUE(Luogo, Struttura, DataP, Ora),
    FOREIGN KEY (ISBN) REFERENCES LIBRO(ISBN) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE COLLANA
(
    CodC SERIAL PRIMARY KEY,
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

CREATE TABLE AUTORE
(
    CodA SERIAL PRIMARY KEY,
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

-------------------------------------------------------------------------------------------

-- La data d'inizio di una conferenza deve essere minore della data di fine di una conferenza
ALTER TABLE CONFERENZA
ADD CONSTRAINT C1
CHECK(
    DataInizio <= DataFine
);

--La quantità deve essere 'NULL' solo quando la fruizione è 'Digitale' o 'AudioLibro', altrimenti la quantità deve 
--essere diversa da 'NULL'  
ALTER TABLE POSSESSO_F
ADD CONSTRAINT C4
CHECK((Quantita IS NOT NULL AND Fruizione='Cartaceo') OR (Quantita IS NULL AND Fruizione IN 
        ('AudioLibro', 'Digitale')));

ALTER TABLE POSSESSO_S
ADD CONSTRAINT C5
CHECK((Quantita IS NOT NULL AND Fruizione='Cartaceo') OR (Quantita IS NULL AND Fruizione IN 
        ('AudioLibro', 'Digitale')));

ALTER TABLE POSSESSO_L
ADD CONSTRAINT C6
CHECK((Quantita IS NOT NULL AND Fruizione='Cartaceo') OR (Quantita IS NULL AND Fruizione IN 
        ('AudioLibro', 'Digitale')));

--La valutazione deve essere in [1,5]
ALTER TABLE RECENSIONE_F
ADD CONSTRAINT C7
CHECK(Valutazione>=1 AND Valutazione<=5); 

ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C8
CHECK(Valutazione>=1 AND Valutazione<=5);

ALTER TABLE RECENSIONE_L
ADD CONSTRAINT C9
CHECK(Valutazione>=1 AND Valutazione<=5); 

-- In RECENSIONE_F non può esserci un fasncicolo che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_F
ADD CONSTRAINT C12
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- In RECENSIONE_S non può esserci una serie che non è stata valutata, recensita o inserita tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C13
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- In RECENSIONE_L non può esserci un libro che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C14
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- Una libreria se non ha un sito web deve avere l'indirizzo o viceversa
ALTER TABLE LIBRERIA
ADD CONSTRAINT C16
CHECK(NOT(SitoWeb IS NULL AND Indirizzo IS NULL));

-----------------------------------------------------------------------------------------------------------------------

-- Quando viene inserita una libreria di un utente il gestore deve avere una Partita IVA
CREATE OR REPLACE FUNCTION controllo_inserimentoLibreria() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(U.PartitaIVA) INTO contatore --controlla la Partita IVA del gestore della libreria inserita
    FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
    WHERE U.PartitaIVA IS NOT NULL AND U.Username=NEW.Gestore;

    IF contatore=0 OR controlla_formato_numeri(NEW.NumeroTelefonico)=false THEN --controlla se non è stata trovata nessuna Partita IVA oppure se il numero telefonico contiene caratteri non numerici
        DELETE FROM LIBRERIA
        WHERE CodL=NEW.CodL;

        RAISE NOTICE 'Partita IVA del gestore o numero telefonico non valido';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoLibreria AFTER INSERT ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoLibreria();

-- Quando viene cambiato il gestore di una libreria il nuovo gestore deve avere una partitaIVA
CREATE OR REPLACE FUNCTION controllo_modificaLibreria() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(U.PartitaIVA) INTO contatore --controlla la partitaIVA del gestore della libreria inserita
    FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
    WHERE U.PartitaIVA IS NOT NULL AND U.Username=NEW.Gestore;

    IF contatore=0 OR controlla_formato_numeri(NEW.NumeroTelefonico)=false THEN --controlla se non è stata trovata nessuna partitaIVA
        UPDATE LIBRERIA
        SET Gestore=OLD.Gestore, NumeroTelefonico=OLD.NumeroTelefonico
        WHERE CodL=NEW.CodL;

        RAISE NOTICE 'Partita IVA del gestore o numero telefonico non valido';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaGestore AFTER UPDATE OF Gestore, NumeroTelefonico ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaLibreria();

-- Quando viene modificata la Partita IVA di un gestore bisogna controllare se quella nuova contiene solo caratteri
-- numerici. In oltre nel caso in cui venga chiusa allora bisogna eliminare anche la libreria del gestore aggiornato  
CREATE OR REPLACE FUNCTION controllo_modifica_PartitaIVA() RETURNS trigger AS $$
DECLARE
    libreria_corrente LIBRERIA.CodL%TYPE;

    cursore_librerieChiuse CURSOR FOR --Contiene tutte le librerie dell'utente che ha chiuso la sua partita IVA
        SELECT CodL
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.Username=NEW.Username;
BEGIN
    IF NEW.partitaIVA IS NOT NULL THEN  --controlla se non è statat chiusa la partita IVA
        IF controlla_formato_numeri(NEW.PartitaIVA)=false THEN   --controlla se nella nuova partita IVA ci sono dei caratteri che non sono numerici
            UPDATE UTENTE
            SET PartitaIVA=OLD.PartitaIVA
            WHERE PartitaIVA=NEW.PartitaIVA;

            RAISE NOTICE 'Partita IVA errata'; 
        END IF;
    ELSE
        OPEN cursore_librerieChiuse;

        LOOP
            FETCH cursore_librerieChiuse INTO libreria_corrente;

            EXIT WHEN NOT FOUND;

            DELETE FROM LIBRERIA
            WHERE CodL=libreria_corrente;
        END LOOP;

        CLOSE cursore_librerieChiuse;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_partitaIVA AFTER UPDATE OF partitaIVA ON UTENTE
    FOR EACH ROW EXECUTE FUNCTION controllo_modifica_PartitaIVA();

-- Quando viene introdotto un articolo scientifico in un fascicolo la data di pubblicazione del fascicolo non deve 
-- essere precedente a quella dell'articolo
CREATE OR REPLACE FUNCTION controllo_introduzioneArticolo() RETURNS trigger AS $$
DECLARE
    pubblicazione_articolo_scientifico ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;
    pubblicazione_fascicolo INTEGER;
BEGIN
    SELECT AnnoPubblicazione INTO pubblicazione_articolo_scientifico --trova l'anno di pubblicazione dell'articolo inserito
    FROM ARTICOLO_SCIENTIFICO
    WHERE DOI=NEW.DOI;

    SELECT EXTRACT(YEAR FROM DataPubblicazione) INTO pubblicazione_fascicolo --trova l'anno di pubblicazione del fasciolo inserito
    FROM FASCICOLO
    WHERE CodF=NEW.CodF;

    IF pubblicazione_fascicolo<pubblicazione_articolo_scientifico THEN --controlla se il fascicolo è stato pubblicato prima dell'articolo
        DELETE FROM INTRODUZIONE
        WHERE CodF=NEW.CodF AND DOI=NEW.DOI;

        RAISE NOTICE 'Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoIntroduzione AFTER INSERT ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_introduzioneArticolo();

-- Quando viene moodificata un'introduzione di un articolo scientifico in un fascicolo la data di pubblicazione del 
-- fascicolo non deve essere precedente a quella dell'articolo
CREATE OR REPLACE FUNCTION controllo_modificaIntroduzione() RETURNS trigger AS $$
DECLARE
    pubblicazione_articolo_scientifico ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;
    pubblicazione_fascicolo INTEGER;
BEGIN
    SELECT AnnoPubblicazione INTO pubblicazione_articolo_scientifico --trova l'anno di pubblicazione dell'articolo inserito
    FROM ARTICOLO_SCIENTIFICO
    WHERE DOI=NEW.DOI;

    SELECT EXTRACT(YEAR FROM DataPubblicazione) INTO pubblicazione_fascicolo --trova l'anno di pubblicazione del fasciolo inserito
    FROM FASCICOLO
    WHERE CodF=NEW.CodF;

    IF pubblicazione_fascicolo<pubblicazione_articolo_scientifico THEN --controlla se il fascicolo è stato pubblicato prima dell'articolo
        UPDATE INTRODUZIONE
        SET CodF=OLD.CodF, DOI=OLD.DOI
        WHERE CodF=NEW.CodF AND DOI=NEW.DOI;

        RAISE NOTICE 'Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaIntroduzione AFTER UPDATE ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaIntroduzione();

-- Quando viene modificata la data di pubblicazione o l'ISSN della rivista del fascicolo, l'anno della nuova data
-- non deve essere precedente a quello dell'anno di pubblicazione degli articoli nel fascicolo modificato e non deve
-- essere precedente all'anno di pubblicazione della rivista in cui è contenuto
CREATE OR REPLACE FUNCTION controllo_modificaFascicolo() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    anno_articoloCorrente ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;
    DOI_articoloCorrente ARTICOLO_SCIENTIFICO.DOI%TYPE;
    anno_pubblicazioneRivista RIVISTA.AnnoPubblicazione%TYPE;
    
    cursore_articoli CURSOR FOR    --contiene gli anni di pubblicazione degli articoli nel fascicolo modificato
        SELECT AR.AnnoPubblicazione, AR.DOI
        FROM ARTICOLO_SCIENTIFICO AS AR NATURAL JOIN INTRODUZIONE AS I
        WHERE I.CodF=NEW.CodF;
BEGIN
    SELECT R.AnnoPubblicazione INTO anno_pubblicazioneRivista --trova l'anno di pubblicazione della rivista del nuovo fascicolo
    FROM RIVISTA AS R JOIN FASCICOLO AS F ON R.ISSN=F.ISSN
    WHERE F.CodF=NEW.CodF;

    IF anno_pubblicazioneRivista>EXTRACT(YEAR FROM NEW.DataPubblicazione)THEN --controlla se la rivista è stata pubblicata dopo al nuovo fascicolo
        UPDATE FASCICOLO
        SET DataPubblicazione=OLD.DataPubblicazione, ISSN=OLD.ISSN
        WHERE CodF=NEW.CodF;

        errore_trovato:=true;
    ELSE
        OPEN cursore_articoli;

        LOOP
            FETCH cursore_articoli INTO anno_articoloCorrente, DOI_articoloCorrente;

            EXIT WHEN NOT FOUND OR errore_trovato=true;

            IF anno_articoloCorrente>EXTRACT(YEAR FROM NEW.DataPubblicazione)THEN --controlla se l'articolo è stato pubblicato dopo al fascicolo modificato
                UPDATE FASCICOLO
                SET DataPubblicazione=OLD.DataPubblicazione
                WHERE CodF=NEW.CodF;

                errore_trovato:=true;

                RAISE NOTICE 'Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo';
            END IF;
        END LOOP;

        CLOSE cursore_articoli;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaFascicolo AFTER UPDATE OF DataPubblicazione, ISSN ON FASCICOLO
    FOR EACH ROW WHEN(NEW.DataPubblicazione<=OLD.DataPubblicazione)
    EXECUTE FUNCTION controllo_modificaFascicolo();

-- Quando viene introdotto un fascicolo in una rivista la data di pubblicazione del fascicolo deve essere successiva
-- a quella della rivista
CREATE OR REPLACE FUNCTION controllo_inserimentoFascicolo() RETURNS trigger AS $$
DECLARE
    anno_pubblicazioneRivista RIVISTA.AnnoPubblicazione%TYPE;
BEGIN
    SELECT R.AnnoPubblicazione INTO anno_pubblicazioneRivista --trova l'anno di pubblicazione della rivista del nuovo fascicolo
    FROM RIVISTA AS R JOIN FASCICOLO AS F ON R.ISSN=F.ISSN
    WHERE F.CodF=NEW.CodF;

    IF anno_pubblicazioneRivista>EXTRACT(YEAR FROM NEW.DataPubblicazione)THEN --controlla se la rivista è stata pubblicata dopo al nuovo fascicolo
        DELETE FROM FASCICOLO
        WHERE CodF=NEW.CodF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoFascicolo AFTER INSERT ON FASCICOLO
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoFascicolo();

-- Quando viene modificata la data di pubblicazione di una rivista, la nuova data di pubblicazione non deve 
-- essere successiva a quella dei fascicoli.
-- Quando viene modificato l'ISSN di una rivista, l'ISSN deve contenere numeri e il carattere '-' 
CREATE OR REPLACE FUNCTION controllo_modificaRivista() RETURNS trigger AS $$
DECLARE
    contatore INTEGER:=0;
BEGIN
    IF controlla_formato(NEW.ISSN)=false THEN   --controlla se nel nuovo issn ci sono dei caratteri che non sono numeri
        UPDATE RIVISTA
        SET ISSN=OLD.ISSN
        WHERE ISSN=NEW.ISSN;

        RAISE NOTICE 'ISSN errato';
    ELSE
        SELECT COUNT(*) INTO contatore --conta i fascicoli della rivista modificata con la data di pubblicazione precedente a quella della rivista
        FROM FASCICOLO 
        WHERE ISSN=NEW.ISSN AND EXTRACT(YEAR FROM DataPubblicazione)<NEW.AnnoPubblicazione;

        IF contatore>0 THEN --controlla se sono stati trovati fascicoli della rivista modificata con la data di pubblicazione precedente a quella della rivista
            UPDATE RIVISTA
            SET AnnoPubblicazione=OLD.AnnoPubblicazione
            WHERE ISSN=NEW.ISSN;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaRivista AFTER UPDATE OF AnnoPubblicazione, ISSN ON RIVISTA
    FOR EACH ROW WHEN(NEW.AnnoPubblicazione>OLD.AnnoPubblicazione OR NEW.ISSN!=OLD.ISSN)
    EXECUTE FUNCTION controllo_modificaRivista();

-- Il numero di libri in una serie non deve essere maggiore al numero dei libri totali della serie (specificati in
-- SERIE.NLibri)
CREATE OR REPLACE FUNCTION inserimento_LibroSerie() RETURNS trigger AS $$
DECLARE
    cont_libri INTEGER; --numero di libri attualmente inseriti nella serie
    libri_tot SERIE.NLibri%TYPE; --numero dei libri totali della serie
BEGIN
    SELECT COUNT(Libro) INTO cont_libri --trova il numero di libri attualmente inseriti nella serie
    FROM INSERIMENTO 
    WHERE Serie=NEW.Serie;

    SELECT NLibri INTO libri_tot --trova il numero dei libri totali della serie
    FROM SERIE
    WHERE ISBN=NEW.Serie;

    IF libri_tot<cont_libri THEN --controlla se la serie è completa
        DELETE FROM INSERIMENTO
        WHERE Libro=NEW.Libro AND Serie=NEW.Serie;  

        RAISE NOTICE 'La serie è già completa';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoLibroSerie AFTER INSERT ON INSERIMENTO
    FOR EACH ROW EXECUTE FUNCTION inserimento_LibroSerie();

-- Quando viene esposto un articolo scientifico in una conferenza la data della conferenza non deve essere precedente
-- quella dell'articolo
CREATE OR REPLACE FUNCTION controllo_inserimentoEsposizione() RETURNS trigger AS $$
DECLARE
    pubblicazione_articolo_scientifico ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;
    inizio_conferenza INTEGER;
BEGIN
    SELECT AnnoPubblicazione INTO pubblicazione_articolo_scientifico --trova l'anno di pubblicazione dell'articolo inserito
    FROM ARTICOLO_SCIENTIFICO
    WHERE DOI=NEW.DOI;

    SELECT EXTRACT(YEAR FROM DataInizio) INTO inizio_conferenza --trova l'anno di inizio della conferenza inserita
    FROM CONFERENZA
    WHERE CodC=NEW.CodC;

    IF inizio_conferenza<pubblicazione_articolo_scientifico THEN --controlla se la conferenza inizia prima della pubblicazione dell'articolo
        DELETE FROM ESPOSIZIONE
        WHERE CodC=NEW.CodC AND DOI=NEW.DOI;

        RAISE NOTICE 'Non è possibile inserire in una conferenza un articolo scientifico non ancora pubblicato';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoEsposizione AFTER INSERT ON ESPOSIZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoEsposizione();

-- Quando viene modificta un'esposizione la data della conferenza non deve essere precedente quella dell'articolo
CREATE OR REPLACE FUNCTION controllo_modificaEsposizione() RETURNS trigger AS $$
DECLARE
    pubblicazione_articolo_scientifico ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;
    inizio_conferenza INTEGER;
BEGIN
    SELECT AnnoPubblicazione INTO pubblicazione_articolo_scientifico --trova l'anno di pubblicazione dell'articolo inserito
    FROM ARTICOLO_SCIENTIFICO
    WHERE DOI=NEW.DOI;

    SELECT EXTRACT(YEAR FROM DataInizio) INTO inizio_conferenza --trova l'anno di inizio della conferenza inserita
    FROM CONFERENZA
    WHERE CodC=NEW.CodC;

    IF inizio_conferenza<pubblicazione_articolo_scientifico THEN --controlla se la conferenza inizia prima della pubblicazione dell'articolo
        UPDATE ESPOSIZIONE
        SET CodC=OLD.CodC, DOI=OLD.DOI
        WHERE CodC=NEW.CodC AND DOI=NEW.DOI;

        RAISE NOTICE 'Non è possibile inserire in una conferenza un articolo scientifico non ancora pubblicato';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaEsposizione AFTER UPDATE ON ESPOSIZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaEsposizione();

-- Quando viene modificato l'anno di pubblicazione dell'articolo il nuovo anno non deve essere successivo a quello
-- di inizio delle conferenze in cui è stato esposto
CREATE OR REPLACE FUNCTION controllo_modificaArticolo() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    anno_conferenzaCorrente INTEGER;

    cursore_anniConferenze CURSOR FOR    --contiene gli anni delle conferenze in cui è stato esposto l'articolo modificato
        SELECT EXTRACT(YEAR FROM C.DataInizio)
        FROM CONFERENZA AS C NATURAL JOIN ESPOSIZIONE AS E
        WHERE E.DOI=NEW.DOI;
BEGIN
    OPEN cursore_anniConferenze;

    LOOP
        FETCH cursore_anniConferenze INTO anno_conferenzaCorrente;

        EXIT WHEN NOT FOUND OR errore_trovato=true;

        IF NEW.AnnoPubblicazione>anno_conferenzaCorrente THEN --controlla se l'articolo aggiornato è stato pubblicato dopo al fascicolo
            UPDATE ARTICOLO_SCIENTIFICO
            SET AnnoPubblicazione=OLD.AnnoPubblicazione
            WHERE DOI=NEW.DOI;
            
            errore_trovato:=true;

            RAISE NOTICE 'Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo';
        END IF;
    END LOOP;

    CLOSE cursore_anniConferenze;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_pubblicazioneArticolo AFTER UPDATE OF AnnoPubblicazione ON ARTICOLO_SCIENTIFICO
    FOR EACH ROW WHEN(NEW.AnnoPubblicazione>OLD.AnnoPubblicazione)
    EXECUTE FUNCTION controllo_modificaArticolo();

-- Quando viene modificata la data di inizio della conferenza l'anno della nuova data non deve essere precedente a
-- quello dell'anno di pubblicazione degli articoli esposti
CREATE OR REPLACE FUNCTION controllo_modificaConferenza() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(*) INTO contatore
    FROM ((CONFERENZA AS CO NATURAL JOIN ESPOSIZIONE AS E) NATURAL JOIN ARTICOLO_SCIENTIFICO AS AR) 
    WHERE EXTRACT(YEAR FROM CO.DataInizio)<AR.AnnoPubblicazione AND CO.CodC=NEW.CodC;

    IF contatore>0 THEN --controlla se l'anno della nuova data non è compresa tra quella della pubblicazione dell'articolo e quella del fascicolo
        UPDATE CONFERENZA
        SET DataInizio=OLD.DataInizio
        WHERE CodC=NEW.CodC;
        
        RAISE NOTICE 'Non è possibile esporre in una conferenza un articolo che non è stato ancora pubbliato';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_inizioConferenza AFTER UPDATE OF DataInizio ON CONFERENZA
    FOR EACH ROW WHEN(NEW.DataInizio<OLD.DataInizio)
    EXECUTE FUNCTION controllo_modificaConferenza();

-- Quando viene inserita una presentazione la data della nuova presentazione non puo essere precedente a quella della 
-- pubblicazione del libro
CREATE OR REPLACE FUNCTION controllo_inserimentoPresentazione() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(*) INTO contatore 
    FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS PR  
    WHERE PR.DataP<L.DataPubblicazione AND PR.CodP=NEW.CodP;

    IF contatore>0 THEN --controlla se la data della presentazione e precedente a quella della pubblicazion del libro
        DELETE FROM PRESENTAZIONE
        WHERE CodP=NEW.CodP;

        RAISE NOTICE 'Non è possibile inserire una data di una presentazione di un libro non ancora pubblicato';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoPresentazione AFTER INSERT ON PRESENTAZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoPresentazione();

-- Quando viene modificata una presentazione la nuova data non puo essere precedente a quella della pubblicazione del
-- libro
CREATE OR REPLACE FUNCTION controllo_modificaPresentazione() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(*) INTO contatore 
    FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS PR  
    WHERE PR.DataP<L.DataPubblicazione AND PR.CodP=NEW.CodP;

    IF contatore>0 THEN --controlla se la data della presentazione e precedente a quella della pubblicazion del libro
        UPDATE PRESENTAZIONE
        SET DataP=OLD.DataP, ISBN=OLD.ISBN
        WHERE CodP=NEW.CodP;

        RAISE NOTICE 'Non è possibile inserire una data di una presentazione di un libro non ancora pubblicato';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaPresentazione AFTER UPDATE OF DataP, ISBN ON PRESENTAZIONE
    FOR EACH ROW WHEN(NEW.DataP<=OLD.DataP)
    EXECUTE FUNCTION controllo_modificaPresentazione();

-- Quando viene modificata la data di pubblicazione del libro la nuova data non deve essere successiva a quelle delle
-- date di tutte le presentazioni del libro.
-- Quando viene modificato l'ISBN di un libro, il nuovo ISBN deve contenere numeri e il carattere '-' 
CREATE OR REPLACE FUNCTION controllo_Libro() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    dataCorrente PRESENTAZIONE.DataP%TYPE;

    cursore_datePresentazioni CURSOR FOR
        SELECT PR.DataP  --trova le date delle presentazioni del libro modificato
        FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS PR
        WHERE PR.DataP<L.DataPubblicazione AND L.ISBN=NEW.ISBN;
BEGIN
    IF controlla_formato(NEW.ISBN)=false THEN   --controlla se nel nuovo issn ci sono dei caratteri che non sono numeri
        UPDATE LIBRO
        SET ISBN=OLD.ISBN
        WHERE ISBN=NEW.ISBN;

        RAISE NOTICE 'ISSN errato';
    ELSE
        OPEN cursore_datePresentazioni;

        LOOP
            FETCH cursore_datePresentazioni INTO dataCorrente;

            EXIT WHEN NOT FOUND OR errore_trovato=true;

            IF dataCorrente<NEW.DataPubblicazione THEN --controlla se la data della presentazione e precedente a quella della pubblicazion del libro
                UPDATE LIBRO
                SET DataPubblicazione=OLD.DataPubblicazione
                WHERE ISBN=NEW.ISBN;

                errore_trovato:=true;
                RAISE NOTICE 'Non è possibile inserire una data di pubblicazione di un libro precedente a quella delle sue presentazioni';
            END IF;
        END LOOP;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaLibro AFTER UPDATE OF DataPubblicazione, ISBN ON LIBRO
    FOR EACH ROW WHEN(NEW.DataPubblicazione>OLD.DataPubblicazione OR NEW.ISBN!=OLD.ISBN)
    EXECUTE FUNCTION controllo_Libro();

-- Quando avviene un inserimento in 'POSSESSO_L' bisogna controllare se il libro appartiene a una serie e se la
-- libreria possiede tutti i libri della serie del libro inserito, in tal caso bisogna effettuare l'inserimento in 
-- 'POSSESSO_S' specificando la quantità e la modalità di fruizione correttamente.
CREATE OR REPLACE FUNCTION controllo_inserimentoPossesso_L() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
    CodS INSERIMENTO.Serie%TYPE:=NULL; --codice della serie del libro inserito
    LibriSerie SERIE.NLibri%TYPE:=NULL; --numero di libri inseriti nella serie del nuovo libro
    QuantitaDisponibile POSSESSO_S.Quantita%TYPE:=NULL; --quantità disponibile della serie del libro inserito
BEGIN
    SELECT I.Serie, S.NLibri INTO CodS, LibriSerie  --trova il codice della serie del libro inserito
    FROM ((INSERIMENTO AS I JOIN LIBRO AS L ON I.Libro=L.ISBN) JOIN SERIE AS S ON I.Serie=S.ISBN)
    WHERE L.ISBN=NEW.ISBN;
    
    IF Cods IS NOT NULL THEN --controlla se il libro inserito appartiene a una serie
        SELECT COUNT(*) INTO contatore  --calcola il numero di libri della serie del libro inserito posseduti dalla libreria 'NEW.Codl'
        FROM POSSESSO_L 
        WHERE Codl=NEW.Codl AND Fruizione=NEW.Fruizione AND ISBN IN(
                                                                    SELECT Libro
                                                                    FROM INSERIMENTO
                                                                    WHERE Serie=CodS
                                                                );

        IF contatore=LibriSerie THEN --controlla se la libreria 'NEW.Codl' possiede tutta la serie del libro inserito            
            SELECT MIN(Quantita) INTO QuantitaDisponibile --calcola la quantità minima dei libri disponibili della serie 'CodS' 
            FROM POSSESSO_L AS PL
            WHERE PL.Fruizione=NEW.Fruizione AND ISBN IN(
                                                            SELECT Libro
                                                            FROM INSERIMENTO AS I
                                                            WHERE I.Serie=CodS
                                                        );
        
            INSERT INTO POSSESSO_S(Codl, ISBN, Fruizione, Quantita)
            VALUES(NEW.Codl, CodS, NEW.Fruizione, QuantitaDisponibile);
        END IF;
    END IF;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoPossesso_L AFTER INSERT ON POSSESSO_L
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoPossesso_L();

-- Quando si modifica la quantità disponibile di un libro di una serie bisogna modificare anche la quantita 
-- disponibile della serie
CREATE OR REPLACE FUNCTION controllo_modificaPossesso_L() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
    CodS INSERIMENTO.Serie%TYPE:=NULL; --codice della serie del libro modificato
    LibriSerie SERIE.NLibri%TYPE:=NULL; --numero di libri inseriti nella serie del libro modificato
    QuantitaDisponibile POSSESSO_S.Quantita%TYPE:=NULL; --quantità disponibile della serie del libro modificato
BEGIN
    SELECT I.Serie, S.NLibri INTO CodS, LibriSerie  --trova il codice della serie del libro modificato
    FROM ((INSERIMENTO AS I JOIN LIBRO AS L ON I.Libro=L.ISBN) JOIN SERIE AS S ON I.Serie=S.ISBN)
    WHERE L.ISBN=NEW.ISBN;
    
    IF Cods IS NOT NULL THEN --controlla se il libro modificato appartiene a una serie
        SELECT COUNT(*) INTO contatore  --calcola il numero di libri della serie del libro inserito posseduti dalla libreria 'NEW.Codl'
        FROM POSSESSO_L 
        WHERE Codl=NEW.Codl AND Fruizione=NEW.Fruizione AND ISBN IN(
                                                                        SELECT Libro
                                                                        FROM INSERIMENTO
                                                                        WHERE Serie=CodS
                                                                    );

        IF contatore=LibriSerie THEN --controlla se la libreria 'NEW.Codl' possiede tutta la serie del libro inserito            
            SELECT MIN(Quantita) INTO QuantitaDisponibile --calcola la quantità minima dei libri disponibili della serie 'CodS' 
            FROM POSSESSO_L AS PL
            WHERE PL.Fruizione=NEW.Fruizione AND ISBN IN(
                                                            SELECT Libro
                                                            FROM INSERIMENTO AS I
                                                            WHERE I.Serie=CodS
                                                        );

            UPDATE POSSESSO_S
            SET Quantita=QuantitaDisponibile
            WHERE Codl=NEW.Codl AND ISBN=CodS AND Fruizione=NEW.Fruizione;
        END IF;
    END IF;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaPossesso_L AFTER UPDATE OF Quantita ON POSSESSO_L
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaPossesso_L();

-- Quando viene eliminato un libro da 'POSSESSO_L' bisogna eliminare la relativa tupla in 'POSSESSO_S'
CREATE OR REPLACE FUNCTION controllo_eliminazionePossesso_L() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
    CodS INSERIMENTO.Serie%TYPE:=NULL; --codice della serie del libro eliminato
    LibriSerie SERIE.NLibri%TYPE:=NULL; --numero di libri inseriti nella serie del libro eliminato
    QuantitaDisponibile POSSESSO_S.Quantita%TYPE:=NULL; --quantità disponibile della serie del libro eliminato
BEGIN
    SELECT I.Serie, S.NLibri INTO CodS, LibriSerie  --trova il codice della serie del libro eliminato
    FROM ((INSERIMENTO AS I JOIN LIBRO AS L ON I.Libro=L.ISBN) JOIN SERIE AS S ON I.Serie=S.ISBN)
    WHERE L.ISBN=OLD.ISBN;
    
    IF Cods IS NOT NULL THEN --controlla se il libro eliminato appartiene a una serie
        DELETE FROM POSSESSO_S
        WHERE ISBN=CodS AND CodL=OLD.CodL AND Fruizione=OLD.Fruizione;
    END IF;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_eliminazionePossesso_L AFTER DELETE ON POSSESSO_L
    FOR EACH ROW EXECUTE FUNCTION controllo_eliminazionePossesso_L();

-- Quando viene inserito un nuovo articolo scientifico bisogna seguire l'ordine del doi
CREATE OR REPLACE FUNCTION inserimento_DOIArticolo() RETURNS trigger AS $$
DECLARE
    DOI_out ARTICOLO_SCIENTIFICO.DOI%TYPE; --DOI del nuovo articolo
    n_articoli INTEGER; --Numero di articoli presenti in 'ARTICOLO_SCIENTIFICO'
BEGIN
    SELECT COUNT(*) INTO n_articoli
    FROM ARTICOLO_SCIENTIFICO;

    IF n_articoli BETWEEN 1 AND 9 THEN
        DOI_out='10-0000'||n_articoli;
    ELSIF n_articoli BETWEEN 10 AND 99 THEN
        DOI_out='10-000'||n_articoli;
    ELSIF n_articoli BETWEEN 100 AND 999 THEN
        DOI_out='10-00'||n_articoli;
    ELSIF n_articoli BETWEEN 1000 AND 9999 THEN
        DOI_out='10-0'||n_articoli;
    ELSE
        DOI_out='10-'||n_articoli;
    END IF;

    UPDATE ARTICOLO_SCIENTIFICO
    SET DOI=DOI_out
    WHERE DOI=NEW.DOI;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoArticolo AFTER INSERT ON ARTICOLO_SCIENTIFICO
    FOR EACH ROW EXECUTE FUNCTION inserimento_DOIArticolo();

-- La procedura invia una notifia con i parametri presi in input
CREATE OR REPLACE PROCEDURE invia_notifica(utente IN UTENTE.Username%TYPE, codS IN SERIE.ISBN%TYPE, 
    cod_libreria IN LIBRERIA.CodL%TYPE, fruizione IN POSSESSO_S.Fruizione%TYPE)
LANGUAGE plpgsql
AS $$
DECLARE
    testo_notifica NOTIFICA.testo%TYPE;
    titolo_serie SERIE.titolo%TYPE;
    nome_libreria LIBRERIA.nome%TYPE;
    indirizzo_libreria LIBRERIA.indirizzo%TYPE;
    sito LIBRERIA.SitoWeb%TYPE;
BEGIN
    SELECT DISTINCT Titolo INTO titolo_serie --trova il titolo della serie inserita
    FROM SERIE
    WHERE ISBN=codS;

    SELECT Nome, Indirizzo, SitoWeb INTO nome_libreria, indirizzo_libreria, sito --trova il nome della libreria inserita
    FROM LIBRERIA
    WHERE CodL=cod_libreria;

    testo_notifica='NOTIFICA: La serie '||titolo_serie||' è completamente disponibile in formato '||fruizione||' alla libreria '||nome_libreria;

    IF indirizzo_libreria IS NOT NULL AND sito IS NOT NULL THEN
        testo_notifica=testo_notifica||' presso '||indirizzo_libreria||' oppure al sito: '||sito;
    ELSIF indirizzo_libreria IS NULL THEN
       testo_notifica=testo_notifica||' al sito: '||sito;
    ELSE
        testo_notifica=testo_notifica||' presso '||indirizzo_libreria;
    END IF;

    testo_notifica=testo_notifica||'.';

    INSERT INTO NOTIFICA(Username, ISBN, Libreria, DataInvio, OraInvio, Testo)
    VALUES(utente, codS, cod_libreria, current_date, current_time(0), testo_notifica);
END; 
$$;

-- Quando una serie è posseduta da una libreria deve essere inviata una notifica a tutti gli utenti che hanno nei 
-- preferiti quella serie
CREATE OR REPLACE FUNCTION invia_notifica_possesso_S() RETURNS trigger AS $$
DECLARE
    utente_corrente UTENTE.Username%TYPE;

    cursore_utenti CURSOR FOR --contiene tutti gli utenti che devono ricevere la notifica
        SELECT Username
        FROM RECENSIONE_S
        WHERE ISBN=NEW.ISBN AND Preferito=true;
BEGIN
    OPEN cursore_utenti;

    LOOP 
        FETCH cursore_utenti INTO utente_corrente;

        EXIT WHEN NOT FOUND;

        CALL invia_notifica(utente_corrente, NEW.ISBN, NEW.CodL, NEW.Fruizione);        
    END LOOP;

    CLOSE cursore_utenti;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoPossesso_S AFTER INSERT ON POSSESSO_S
    FOR EACH ROW  WHEN(NEW.Quantita>0)
    EXECUTE FUNCTION invia_notifica_possesso_S();
CREATE TRIGGER T_modificaPossesso_S AFTER UPDATE OF Quantita ON POSSESSO_S
    FOR EACH ROW WHEN(NEW.Quantita>0 AND OLD.Quantita=0)
    EXECUTE FUNCTION invia_notifica_possesso_S();

-- Quando una serie viene messa nei preferiti da un utente (tramite inserimento e o modifica in 'PREFERITI_S') se la
-- serie è disponibile, allora deve essere inviata la notifica
CREATE OR REPLACE FUNCTION invia_notifica_preferiti_S() RETURNS trigger AS $$
DECLARE
    libreria_corrente LIBRERIA.CodL%TYPE;
    fruizione_corrente POSSESSO_S.Fruizione%TYPE;

    cursore_librerie CURSOR FOR --Contiene tutte le librerie e le modalità di fruizione in cui è disponibile la serie inserita nei preferiti
        SELECT CodL, Fruizione
        FROM POSSESSO_S
        WHERE ISBN=NEW.ISBN AND Quantita>0;
BEGIN
    OPEN cursore_librerie;

    LOOP 
        FETCH cursore_librerie INTO libreria_corrente, fruizione_corrente;

        EXIT WHEN NOT FOUND;

        CALL invia_notifica(NEW.Username, NEW.ISBN, libreria_corrente, fruizione_corrente);
    END LOOP;

    CLOSE cursore_librerie;

    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoPreferiti_S AFTER INSERT ON RECENSIONE_S
    FOR EACH ROW WHEN(NEW.Preferito=true)
    EXECUTE FUNCTION invia_notifica_preferiti_S();
CREATE TRIGGER T_modificaPreferiti_S AFTER UPDATE OF Preferito ON RECENSIONE_S
    FOR EACH ROW WHEN(NEW.Preferito=true)
    EXECUTE FUNCTION invia_notifica_preferiti_S();

-- La funzione controlla se la stringa ricevuta in input non contiene caratteri che non sono numeri o il simbolo '-'
CREATE OR REPLACE FUNCTION controlla_formato(stringa_in IN VARCHAR) 
RETURNS BOOLEAN AS $$
DECLARE    
	risultato BOOLEAN:=true;    --indica se non è stato trovato un carattere che non è un numero
    cont INTEGER:=1;    --contatore
    carattere INTEGER;  --codice ascii del carattere letto
BEGIN
    WHILE risultato=true AND cont<=LENGTH(stringa_in) LOOP --scorre la stringa presa in input

        carattere=ascii(substr(stringa_in, cont, 1)); --inserisce in 'carattere' il codice ascii del cont-esimo carattere letto da 'stringa_in'
        
        IF (carattere<48 OR carattere>57) AND carattere!=45 THEN  --controlla se il carattere letto non è un numero
            risultato=false;
        END IF;

        cont=cont+1;    --incrementa il contatore di 1
    END LOOP;

    RETURN risultato;
END;
$$ LANGUAGE plpgsql;

-- La funzione controlla se la stringa ricevuta in input non contiene caratteri che non sono numeri
CREATE OR REPLACE FUNCTION controlla_formato_numeri(stringa_in IN VARCHAR) 
RETURNS BOOLEAN AS $$
DECLARE    
	risultato BOOLEAN:=true;    --indica se non è stato trovato un carattere che non è un numero
    cont INTEGER:=1;    --contatore
    carattere INTEGER;  --codice ascii del carattere letto
BEGIN
    WHILE risultato=true AND cont<=LENGTH(stringa_in) LOOP --scorre la stringa presa in input

        carattere=ascii(substr(stringa_in, cont, 1)); --inserisce in 'carattere' il codice ascii del cont-esimo carattere letto da 'stringa_in'
        
        IF (carattere<48 OR carattere>57) THEN  --controlla se il carattere letto non è un numero
            risultato=false;
        END IF;

        cont=cont+1;    --incrementa il contatore di 1
    END LOOP;

    RETURN risultato;
END;
$$ LANGUAGE plpgsql;

-- Quando viene inserita una rivista l'ISSN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_inserimentoRivista() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISSN)=false THEN   --controlla se nel nuovo ISSN ci sono dei caratteri che non sono numeri
        DELETE FROM RIVISTA
        WHERE ISSN=NEW.ISSN;

        RAISE NOTICE 'ISSN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoRivista AFTER INSERT ON RIVISTA
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoRivista();

-- Quando viene inserita una rivista l'ISSN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_inserimentoCollana() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISSN)=false THEN   --controlla se nel nuovo ISSN ci sono dei caratteri che non sono numeri
        DELETE FROM COLLANA
        WHERE ISSN=NEW.ISSN;

        RAISE NOTICE 'ISSN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoCollana AFTER INSERT ON COLLANA
    FOR EACH ROW WHEN(NEW.ISSN IS NOT NULL)
    EXECUTE FUNCTION controllo_inserimentoCollana();

-- Quando viene modificato l'ISSN di una collana l'ISSN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_modificaCollana() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISSN)=false THEN   --controlla se nel nuovo ISSN ci sono dei caratteri che non sono numeri
        UPDATE COLLANA
        SET ISSN=OLD.ISSN
        WHERE ISSN=NEW.ISSN;

        RAISE NOTICE 'ISSN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaCollana AFTER UPDATE OF ISSN ON COLLANA
    FOR EACH ROW WHEN(NEW.ISSN IS NOT NULL)
    EXECUTE FUNCTION controllo_modificaCollana();

-- Quando viene inserito un libro l'ISBN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_inserimentoLibro() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISBN)=false THEN   --controlla se nel nuovo ISBN ci sono dei caratteri che non sono numeri
        DELETE FROM LIBRO
        WHERE ISBN=NEW.ISBN;

        RAISE NOTICE 'ISBN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoLibro AFTER INSERT ON LIBRO
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoLibro();

-- Quando viene inserita una serie l'ISBN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_inserimentoSerie() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISBN)=false THEN   --controlla se nel nuovo ISBN ci sono dei caratteri che non sono numeri
        DELETE FROM SERIE
        WHERE ISBN=NEW.ISBN;

        RAISE NOTICE 'ISBN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoSerie AFTER INSERT ON SERIE
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoSerie();

-- Quando viene modificato l'ISBN di una serie l'ISBN deve contenere numeri e il carattere '-'
CREATE OR REPLACE FUNCTION controllo_modificaSerie() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato(NEW.ISBN)=false THEN   --controlla se nel nuovo ISBN ci sono dei caratteri che non sono numeri
        UPDATE SERIE
        SET ISBN=OLD.ISBN
        WHERE ISBN=NEW.ISBN;

        RAISE NOTICE 'ISBN errato';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaSerie AFTER UPDATE OF ISBN ON SERIE
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaSerie();

-- Quando viene inserito un utente l'eventuale Partita IVA deve contenere numeri
CREATE OR REPLACE FUNCTION controllo_inserimentoUtente() RETURNS trigger AS $$
DECLARE
BEGIN
    IF controlla_formato_numeri(NEW.PartitaIVA)=false THEN   --controlla se nella nuova Partita IVA ci sono dei caratteri che non sono numeri
        DELETE FROM UTENTE
        WHERE PartitaIVA=NEW.PartitaIVA;

        RAISE NOTICE 'Partita IVA errata';
    END IF;
        
    RETURN NEW;
END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoUtente AFTER INSERT ON UTENTE
    FOR EACH ROW WHEN(NEW.PartitaIVA IS NOT NULL)
    EXECUTE FUNCTION controllo_inserimentoUtente();
--------------------------------------------------------------------------------------------------------------------