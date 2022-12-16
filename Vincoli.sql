-- Non possono esserci, quando specificate, valori duplicati dell'attributo 'partitaIVA'
ALTER TABLE UTENTE
ADD CONSTRAINT C1
CHECK(
    NOT EXISTS(
        SELECT COUNT(Username)
        FROM UTENTE
        GROUP BY PartitaIVA
        HAVING COUNT(Username) > 1
    )
);

-- Una libreria può essere gestita solo da utenti con una partitaIVa
CREATE ASSERTION A1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.PartitaIVA IS NULL

    )
);