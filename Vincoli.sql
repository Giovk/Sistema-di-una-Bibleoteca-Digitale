-- Una libreria può essere gestita solo da utenti con una partitaIVa
CREATE ASSERTION A1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.PartitaIVA IS NULL

    )
);


-- Un articolo scientifico non può essere pubblicato in una 
-- rivista prima della pubblicazione della rivista e dell'articolo
CREATE ASSERTION A2
CHECK(
    NOT EXISTS(
        SELECT *
        FROM ((COMPOSIZIONE AS C JOIN RIVISTA AS R ON C.ISSN = R.ISSN)
            JOIN ARTICOLO_SCIENTIFICO AS AR ON AR.CodAS = C.CodAS)
        WHERE C.DataInserimento < R.DataPubblicazione OR YEAR(C.DataInserimento) < AR.AnnoPubblicazione 
    )
);

-- La data d'inizio di una conferenza deve essere minore della data di fine di una conferenza
ALTER TABLE CONFERENZA
ADD CONSTRAINT C1
CHECK(
    DataInizio <= DataFine
);

-- L'ISBN deve essere del formato ___-__-__-_____-_
CONSTRAINT C2
    CHECK (VALUE LIKE '___-__-_____-_');

-- Ordinamento libri
CREATE ASSERTION A3
CHECK(
    NOT EXISTS(
        SELECT *
        FROM INSERIMENTO AS I
    )
);