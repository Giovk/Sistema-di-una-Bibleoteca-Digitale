-- Una libreria può essere gestita solo da utenti con una partitaIVA
CREATE ASSERTION A1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.PartitaIVA IS NULL
    )
);

-- La data di pubblicazione di un fascicolo non deve essere precedente a quello degli articoli che contiene  
CREATE ASSERTION A2
CHECK(
    NOT EXISTS(
        SELECT *
        FROM ((ARTICOLO_SCIENTIFICO AS AR JOIN INTRODUZIONE AS I ON AR.DOI=I.DOI) JOIN FASCICOLO AS F ON F.CodF=I.CodF) 
        WHERE AR.AnnoPubblicazione>EXTRACT(YEAR FROM F.DataPubblicazione)  
    )
);

--  Un fascicolo di una rivista non può essere pubblicato prima della sua rivista
CREATE ASSERTION A3
CHECK(
    NOT EXISTS(
        SELECT *
        FROM FASCICOLO AS F JOIN RIVISTA AS R ON F.ISSN=R.ISSN
        WHERE EXTRACT(YEAR FROM F.DataPubblicazione)<R.AnnoPubblicazione  
    )
);

-- La data d'inizio di una conferenza deve essere minore della data di fine di una conferenza
ALTER TABLE CONFERENZA
ADD CONSTRAINT C1
CHECK(
    DataInizio <= DataFine
);

-- L'ISBN deve essere del formato giusto
CONSTRAINT C2
    CHECK (VALUE LIKE '978-%-%-_');

-- Il numero dei libri associati a una serie non può essere maggiore al numero dei libri totali che la compongono 
--(valore dell'attributo NLibri)
CREATE ASSERTION A4
CHECK(
    NOT EXISTS(
        SELECT *
        FROM INSERIMENTO AS I JOIN SERIE AS S on I.serie=S.isbn
        WHERE S.NLibri<(
        	                SELECT COUNT(Libro)
                            FROM INSERIMENTO
        	                WHERE Serie=S.isbn
        )
    )
);

--La fruizione deve essere nell'insieme ("Cartaceo", "Digitale" e "AudioLibro")
CONSTRAINT C3
    CHECK (VALUE IN ('Cartaceo', 'Digitale', 'AudioLibro'));

--La quantità deve essere 'NULL' solo quando la fruizione è 'Digitale' o 'AudioLibro'  
ALTER TABLE POSSESSO_F
ADD CONSTRAINT C4
CHECK(NOT(Quantita IS NULL AND Fruizione='Cartaceo'));

ALTER TABLE POSSESSO_S
ADD CONSTRAINT C5
CHECK(NOT(Quantita IS NULL AND Fruizione='Cartaceo'));

ALTER TABLE POSSESSO_L
ADD CONSTRAINT C6
CHECK(NOT(Quantita IS NULL AND Fruizione='Cartaceo'));

--La valutazione deve essere in [0,5]
ALTER TABLE RECENSIONE_F
ADD CONSTRAINT C7
CHECK(Valutazione>=0 AND Valutazione<=5); 

ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C8
CHECK(Valutazione>=0 AND Valutazione<=5);

ALTER TABLE RECENSIONE_L
ADD CONSTRAINT C9
CHECK(Valutazione>=0 AND Valutazione<=5); 

-- L'ISSN deve essere del formato giusto
CONSTRAINT C10
CHECK (VALUE LIKE '____-____');

-- Il DOI deve essere del formato giusto
CONSTRAINT C11
CHECK (VALUE LIKE '10-%');

-- In RECENSIONE_F non può esserci un fasncicolo che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_F
ADD CONSTRAINT C12
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- In RECENSIONE_S non può esserci una serie che non è stata valutata, recensita o inserita tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C12
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- In RECENSIONE_L non può esserci un libro che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE RECENSIONE_S
ADD CONSTRAINT C12
CHECK(NOT(Testo IS NULL AND Valutazione IS NULL AND Preferito=false));

-- La partita IVA deve essere del formato giusto
CONSTRAINT C15
CHECK (VALUE LIKE '___________');

-- Una libreria se non ha un sito web deve avere l'indirizzo o viceversa
ALTER TABLE LIBRERIA
ADD CONSTRAINT C16
CHECK(NOT(SitoWeb IS NULL AND Indirizzo IS NULL));

-- La data di una conferenza non deve essere precedente a quella della pubblicazione dell'articolo esposto 
CREATE ASSERTION A5
CHECK(
    NOT EXISTS(
        SELECT *
        FROM ((CONFERENZA AS CO NATURAL JOIN ESPOSIZIONE AS E) NATURAL JOIN ARTICOLO_SCIENTIFICO AS AR) 
        WHERE EXTRACT(YEAR FROM CO.DataInizio)<AR.AnnoPubblicazione
    )
);

-- La data di una presentazione non deve essere precedente a quella della pubblicazione del libro presentato
CREATE ASSERTION A6
CHECK(
    NOT EXISTS(
        SELECT *
        FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS P  
        WHERE P.DataP<L.DataPubblicazione
    )
);

-- Una libreria per possedere una serie deve possedere tutti i lbri inseriti nella serie. Una libreria deve possedere
-- una serie nella stessa modalita di fruizione dei libri che possiede e la quantita disponibile di una serie deve
-- essere uguale al numero minimo di libri disponibili della serie 
CREATE ASSERTION A7
CHECK(
    NOT EXISTS(
        SELECT COUNT(*), 
        FROM ((((POSSESSO_S AS PS JOIN SERIE AS S ON PS.ISBN = S.ISBN) JOIN INSERIMENTO AS I ON S.ISBN = I.Serie) 
                JOIN LIBRO AS L ON L.ISBN = I.Libro) JOIN POSSESSO_L AS PL ON PL.ISBN=L.ISBN )
        WHERE ((PL.Quantita>0 AND PL.Fruizione='Cartaceo') OR PL.Quantita IS NULL) AND PL.CodL = PS.CodL 
        GROUP BY PL.CodL, PL.Fruizione, S.NLibri, PS.Fruizione, PS.Quantita, S.ISBN
        HAVING COUNT(*)<>S.NLibri OR PL.Fruizione<>PS.Fruizione OR (PS.Quantita <>(
                                                                                    SELECT MIN(Quantita)
                                                                                    FROM POSSESSO_L AS PL
                                                                                    WHERE PL.Fruizione='Cartaceo' 
                                                                                    AND ISBN IN(
                                                                                                SELECT Libro
                                                                                                FROM INSERIMENTO AS I
                                                                                                WHERE I.Serie=S.ISBN
                                                                                            )
                                                                                ) AND PS.Quantita IS NOT NULL)
    )
);
