-- Una libreria può essere gestita solo da utenti con una partitaIVA
CREATE ASSERTION A1
CHECK(
    NOT EXISTS(
        SELECT *
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.PartitaIVA IS NULL

    )
);

-- Un articolo scientifico pubblicato in un fascicolo di una rivista non può avere una anno 
-- di pubblicazione maggiore dell'anno della data di pubblicazione del fascicolo che lo contiene  
CREATE ASSERTION A2
CHECK(
    NOT EXISTS(
        SELECT *
        FROM ((ARTICOLO_SCIENTIFICO AS AR JOIN INTRODUZIONE AS I ON AR.DOI=I.DOI) JOIN FASCICOLO AS F ON F.CodF=I.CodF) 
        WHERE AR.AnnoPubblicazione>YEAR(F.DataPubblicazione)  
    )
);

--  Un fascicolo di una rivista non può essere pubblicato prima della sua rivista
CREATE ASSERTION A3
CHECK(
    NOT EXISTS(
        SELECT *
        FROM FASCICOLO AS F JOIN RIVISTA AS R ON F.ISSN=R.ISSN
        WHERE YEAR(F.DataPubblicazione)<R.AnnoPubblicazione  
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

-- L'ordine dei libri non può essere maggiore del numero dei libri che compongono la serie
CREATE ASSERTION A4
CHECK(
    NOT EXISTS(
        SELECT *
        FROM INSERIMENTO AS I NATURAL JOIN SERIE AS S
        WHERE I.Ordine > S.NLibri
    )
);

-- Se per una serie esiste una istanza con INSERIMENTO.Ordine>0, devono esistere istanze della 
-- serie per tutti i valori di INSERIMENTO.Ordine compresi tra 0 e INSERIMENTO.Ordine
CREATE ASSERTION A5
CHECK(
    NOT EXISTS(
        SELECT SUM(I.Ordine) AS N1, (MAX(I.Ordine)*(MAX(I.Ordine)+1)/2)
        FROM INSERIMENTO AS I
        GROUP BY I.CodS
        HAVING SUM(I.Ordine)<>(MAX(I.Ordine)*(MAX(I.Ordine)+1)/2)
    )
);

--La fruizione deve essere nell'insime ("Cartaceo", "Digitale" e "AudioLibro")
CONSTRAINT C3
    CHECK (VALUE IN ('Cartaceo', 'Digitale', 'AudioLibro'));

--La quantità deve essere 'NULL' solo quando la fruizione è 'Digitale' o 'AudioLibro'  
ALTER TABLE POSSESSO_F
ADD CONSTRAINT C4
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR 
        (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

ALTER TABLE POSSESSO_S
ADD CONSTRAINT C5
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR 
        (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

ALTER TABLE POSSESSO_L
ADD CONSTRAINT C6
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR
    (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

--La valutazione deve essere in [0,5]
ALTER TABLE PREFERITI_F
ADD CONSTRAINT C7
CHECK(Valutazione>=0 AND Valutazione<=5); 

ALTER TABLE PREFERITI_S
ADD CONSTRAINT C8
CHECK(Valutazione>=0 AND Valutazione<=5);

ALTER TABLE PREFERITI_L
ADD CONSTRAINT C9
CHECK(Valutazione>=0 AND Valutazione<=5); 

-- L'ISSN deve essere del formato giusto
CONSTRAINT C10
    CHECK (VALUE LIKE '____-____');

-- Il DOI deve essere del formato giusto
CONSTRAINT C11
    CHECK (VALUE LIKE '10-%');

-- In PREFERITI_F non può esserci un fascicolo che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_F
ADD CONSTRAINT C12
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));

-- In PREFERITI_S non può esserci una serie che non è stata valutata, recensita o inserita tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_S
ADD CONSTRAINT C13
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));

-- In PREFERITI_L non può esserci un libro che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_L
ADD CONSTRAINT C14
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));