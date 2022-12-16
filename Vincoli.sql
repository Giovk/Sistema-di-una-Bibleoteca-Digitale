-- PartitaIVA: Null || Unique
ALTER TABLE UTENTE
ADD CONSTRAINT C1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE
        WHERE PartitaIVA IS NOT NULL AND PartitaIVA IN (
            SELECT DISTINCT PartitaIVA
            FROM UTENTE 
            WHERE partitaIVA IS NOT NULL 
        )
    )
)

-- Una libreria può essere gestita solo da utenti con una partitaIVa
CREATE ASSERTION A1
CHECK(NOT EXISTS(

));