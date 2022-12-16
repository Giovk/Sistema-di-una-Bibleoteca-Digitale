-- Una libreria può essere gestita solo da utenti con una partitaIVa
CREATE ASSERTION A1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.PartitaIVA IS NULL

    )
);