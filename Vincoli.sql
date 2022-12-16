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

-- L'ordine dei libri non può essere maggiore del numero dei libri che compongono la serie
CREATE ASSERTION A3
CHECK(
    NOT EXISTS(
        SELECT *
        FROM INSERIMENTO AS I NATURAL JOIN SERIE AS S
        WHERE I.Ordine > S.NLibri
    )
);