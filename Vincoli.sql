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