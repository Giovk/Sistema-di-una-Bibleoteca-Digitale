
-- C1 --

ALTER TABLE CONFERENZA
ADD CONSTRAINT C1
CHECK(
    DataInizio <= DataFine
);

-- C4 --

--La quantità deve essere 'NULL' solo quando la fruizione è 'Digitale' o 'AudioLibro'  
ALTER TABLE POSSESSO_F
ADD CONSTRAINT C4
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR 
        (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

-- C5 --

ALTER TABLE POSSESSO_S
ADD CONSTRAINT C5
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR 
        (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

-- C6 --

ALTER TABLE POSSESSO_L
ADD CONSTRAINT C6
CHECK((Quantita IS NULL AND Fruizione IN('Digitale', 'AudioLibro')) OR
    (Quantita>=0 AND Quantita IS NOT NULL AND Fruizione='Cartaceo'));

-- C7 --

--La valutazione deve essere in [0,5]
ALTER TABLE PREFERITI_F
ADD CONSTRAINT C7
CHECK(Valutazione>=0 AND Valutazione<=5); 

-- C8 --

ALTER TABLE PREFERITI_S
ADD CONSTRAINT C8
CHECK(Valutazione>=0 AND Valutazione<=5);

-- C9 --

ALTER TABLE PREFERITI_L
ADD CONSTRAINT C9
CHECK(Valutazione>=0 AND Valutazione<=5); 

-- C12 --

-- In PREFERITI_F non può esserci un fascicolo che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_F
ADD CONSTRAINT C12
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));

-- C13 --

-- In PREFERITI_S non può esserci una serie che non è stata valutata, recensita o inserita tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_S
ADD CONSTRAINT C13
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));

-- C14 --

-- In PREFERITI_L non può esserci un libro che non è stato valutato, recensito o inserito tra i preferiti 
-- dall'utente
ALTER TABLE PREFERITI_L
ADD CONSTRAINT C14
CHECK((Recensione IS NULL AND (Valutazione IS NOT NULL OR Preferito=true)) OR 
        (Valutazione IS NULL AND(Recensione IS NOT NULL OR Preferito=true)) OR 
        (Preferito=false AND (valutazione IS NOT NULL OR Recensione IS NOT NULL)) OR 
        (Recensione IS NOT NULL AND Valutazione IS NOT NULL AND Preferito=true));

-- C16 --

-- Una libreria se non ha un sito web deve avere l'indirizzo o viceversa
ALTER TABLE LIBRERIA
ADD CONSTRAINT C16
CHECK((SitoWeb IS NULL AND indirizzo IS NOT NULL) OR (SitoWeb IS NOT NULL AND indirizzo IS NULL) OR 
        (SitoWeb IS NOT NULL AND indirizzo IS NOT NULL));