-- Quando viene inserita una libreria di un utente il gestore deve avere una partitaIVA
CREATE OR REPLACE FUNCTION controllo_inserimentoLibreria() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT CONT(U.PartitaIVA) INTO contatore --controlla la partitaIVA del gestore della libreria inserita
    FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
    WHERE U.PartitaIVA IS NOT NULL AND U.Username=NEW.Gestore;

    IF contatore=0 THEN --controlla se non è stata trovata nessuna partitaIVA
        DELETE FROM LIBRERIA
        WHERE CodL=NEW.CodL;

        RAISE NOTICE 'Per inserire una nuova libreria è necessario specificare la PartitaIVA del gestore';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoLibreria AFTER INSERT ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoLibreria();

-- Quando viene cambiato il gestore di una libreria il nuovo gestore deve avere una partitaIVA
CREATE OR REPLACE FUNCTION controllo_inserimentoLibreria() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT CONT(U.PartitaIVA) INTO contatore --controlla la partitaIVA del gestore della libreria inserita
    FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
    WHERE U.PartitaIVA IS NOT NULL AND U.Username=NEW.Gestore;

    IF contatore=0 THEN --controlla se non è stata trovata nessuna partitaIVA
        UPDATE LIBRERIA
        SET Gestore=OLD.Gestore
        WHERE CodL=NEW.CodL;

        RAISE NOTICE 'Per inserire una nuova libreria è necessario specificare la PartitaIVA del gestore';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaGestore AFTER UPDATE OF Gestore ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaLibreria();

-- Quando viene chiusa la partitaIVA di un gestore bisogna eliminare la sua libreria
CREATE OR REPLACE FUNCTION chiusuraLibreria() RETURNS trigger AS $$
DECLARE
    libreria_corrente LIBRERIA.CodL%TYPE;

    cursore_librerieChiuse CURSOR FOR --Contiene tutte le librerie dell'utente che ha chiuso la sua partita IVA
        SELECT CodL
        FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=L.Gestore
        WHERE U.Username=NEW.Gestore;
BEGIN
    OPEN cursore_librerieChiuse;

    LOOP
        FETCH cursore_librerieChiuse INTO libreria_corrente;

        EXIT WHEN NOT FOUND;

        DELETE FROM LIBRERIA
        WHERE CodL=libreria_corrente;
    END LOOP;

    CLOSE cursore_librerieChiuse;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_chiusura_partitaIVA AFTER UPDATE OF partitaIVA ON UTENTE
    WHEN(NEW.partitaIVA IS NULL)
    FOR EACH ROW EXECUTE FUNCTION chiusuraLibreria();

-- Quando viene introdotto un articolo scientifico in un fascicolo la data di pubblicazione del fascicolo deve essere
-- precedente a quella dell'articolo
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

        RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoIntroduzione AFTER INSERT ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_introduzioneArticolo();
CREATE TRIGGER T_modificaIntroduzione AFTER UPDATE ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_introduzioneArticolo();

-- Quando viene modificato la data di pubblicazione del fascicolo l'anno della nuova data non deve essere precedente a
-- quello dell'anno di pubblicazione degli articoli nel fascicolo modificato
CREATE OR REPLACE FUNCTION controllo_modificaFascicolo_Articoli() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    anno_articoloCorrente ARTICOLO_SCIENTIFICO.AnnoPubblicazione%TYPE;

    cursore_anniArticolo CURSOR FOR    --contiene gli anni di pubblicazione degli articoli nel fascicolo modificato
        SELECT AnnoPubblicazione
        FROM ARTICOLO_SCIENTIFICO AS AR NATURAL JOIN INTRODUZIONE AS I
        WHERE I.CodF=NEW.CodF;
BEGIN
    OPEN  cursore_anniFascicoli;

    LOOP
        FETCH cursore_anniArticolo INTO anno_articoloCorrente;

        EXIT WHEN NOT FOUND OR errore_trovato=true;

        IF  anno_articoloCorrent>EXTRACT(YEAR FROM NEW.DataPubblicazione)THEN --controlla se l'articolo è stato pubblicato dopo al fascicolo modificato
            UPDATE FASCICOLO
            SET DataPubblicazione=OLD.DataPubblicazione
            WHERE DOI=NEW.DOI;
            
            errore_trovato:=true;

            RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
        END IF;
    END LOOP;

    CLOSE cursore_anniFascicoli;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_pubblicazioneFascicolo AFTER UPDATE OF DataPubblicazione ON FASCICOLO
    WHEN(NEW.DataPubblicazione<OLD.DataPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaFascicolo();

-- Quando viene introdotto un fascicolo in una rivista, oppure viene modificata la data di pubblicazione o l'ISSN di
-- un fascicolo, la data di pubblicazione del fascicolo deve essere successiva a quella della rivista
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
    THEN;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoFascicolo AFTER INSERT FASCICOLO
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoFascicolo();
CREATE TRIGGER T_modificaFascicolo AFTER UPDATE OF DataPubblicazione, ISSN  ON FASCICOLO
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoFascicolo();

-- Quando viene modificata la data di pubblicazione di una rivista, la nuova data di pubblicazione non deve 
-- essere successiva a quella dei fascicoli
CREATE OR REPLACE FUNCTION controllo_modificaRivista() RETURNS trigger AS $$
DECLARE
    contatore INTEGER:=0;
BEGIN
    SELECT COUNT(*) INTO contatore --conta i fascicoli della rivista modificata con la data di pubblicazione precedente a quella della rivista
    FROM FASCICOLO 
    WHERE ISSN=NEW.ISSN AND EXTRACT(YEAR FROM DataPubblicazione)<NEW.AnnoPubblicazione;

    IF contatore>0 THEN --controlla se sono stati trovati fascicoli della rivista modificata con la data di pubblicazione precedente a quella della rivista
        UPDATE RIVISTA
        SET AnnoPubblicazione=OLD.AnnoPubblicazione;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaRivista AFTER UPDATE OF AnnoPubblicazione ON RIVISTA
    WHEN(NEW.AnnoPubblicazione>OLD.AnnoPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaRivista();

-- L'ordine di un libro in una serie non deve essere maggiore al numero dei libri totali della serie (specificati in
-- SERIE.NLibri) e ogni libro inserito deve rispettare la sequenza del campo 'ordine'
CREATE OR REPLACE FUNCTION T_inserimentoLibroSerie RETURNS trigger AS $$
DECLARE
    cont_libri INTEGER; --numero di libri attualmente inseriti nella serie
    libri_tot SERIE.NLibri%TYPE --numero dei libri totali della serie
BEGIN
    SELECT COUNT(Libro) INTO cont_libri --trova il numero di libri attualmente inseriti nella serie
    FROM INSERIMENTO 
    WHERE Serie=NEW.Serie;

    SELECT NLibri INTO libri_tot --trova il numero dei libri totali della serie
    FROM SERIE
    WHERE ISBN=NEW.Serie;

    IF libri_tot>=cont_libri THEN --controlla se la serie non è completa
        UPDATE INSERIMENTO
        SET Ordine=cont_libri;
    ELSE
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
CREATE OR REPLACE FUNCTION controllo_introduzioneArticolo() RETURNS trigger AS $$
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
        WHERE CodF=NEW.CodF AND DOI=NEW.DOI;

        RAISE NOTICE "Non è possibile inserire in una conferenza un articolo scientifico non ancora pubblicato";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoEsposizione AFTER INSERT ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_esposizioneArticolo();
CREATE TRIGGER T_modificaEsposizione AFTER UPDATE ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_esposizioneArticolo();

-- Quando viene modificato l'anno di pubblicazione dell'articolo il nuovo anno non deve essere successivo a quello
-- di inizio della conferenza
CREATE OR REPLACE FUNCTION controllo_modificaArticolo() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    anno_conferenzaCorrente INTEGER;

    cursore_anniConferenze CURSOR FOR    --contiene gli anni di pubblicazione dei fascioli con l'articolo modificato
        SELECT EXTRACT(YEAR FROM F.DataPubblicazione)
        FROM CONFERENZA AS C NATURAL JOIN ESPOSIZIONE AS E
        WHERE E.DOI=NEW.DOI;
BEGIN
    OPEN anno_conferenzaCorrente;

    LOOP
        FETCH cursore_anniConferenze INTO anno_conferenzaCorrente;

        EXIT WHEN NOT FOUND OR errore_trovato=true;

        IF NEW.AnnoPubblicazione>anno_conferenzaCorrente THEN --controlla se l'articolo aggiornato è stato pubblicato dopo al fascicolo
            UPDATE ARTICOLO_SCIENTIFICO
            SET AnnoPubblicazione=OLD.AnnoPubblicazione
            WHERE DOI=NEW.DOI;
            
            errore_trovato:=true;

            RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
        END IF;
    END LOOP;

    CLOSE cursore_anniConferenze;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_pubblicazioneArticolo AFTER UPDATE OF AnnoPubblicazione ON ARTICOLO_SCIENTIFICO
    WHEN(NEW.AnnoPubblicazione>OLD.AnnoPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaArticolo();

-- Quando viene modificato la data di inizio della conferenza l'anno della nuova data non deve essere precedente a
-- quello dell'anno di pubblicazione degli articoli esposti o successiva a quella dei fascicoli
CREATE OR REPLACE FUNCTION controllo_modificaConferenza() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;

    SELECT COUNT(*) INTO contatore
    FROM ((((CONFERENZA AS CO NATURAL JOIN ESPOSIZIONE AS E) NATURAL JOIN ARTICOLO_SCIENTIFICO AS AR) 
            NATURAL JOIN INTRODUZIONE AS I) NATURAL JOIN FASCICOLO AS F)  
    WHERE (EXTRACT(YEAR FROM CO.DataInizio)<AR.AnnoPubblicazione OR CO.DataInizio>F.DataPubblicazione) AND 
            CO.CodC=NEW.CodC;
BEGIN
    IF contatore>0 THEN --controlla se l'anno della nuova data non è compresa tra quella della pubblicazione dell'articolo e quella del fascicolo
        UPDATE CONFERENZA
        SET DataInizio=OLD.DataInizio
        WHERE CodF=NEW.CodF;
        
        RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_inizioConferenza AFTER UPDATE OF DataInizio ON CONFEREZA
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaConferenza();

-- Quando viene modificato la data di pubblicazione del fascicolo l'anno della nuova data non deve essere precedente a
-- quello dell'anno di inizio delle conferenze degli articoli nel fascicolo modificato
CREATE OR REPLACE FUNCTION controllo_modificaFascicolo_Conferenze() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;

    SELECT COUNT(*) INTO contatore
    FROM ((((CONFERENZA AS CO NATURAL JOIN ESPOSIZIONE AS E) NATURAL JOIN ARTICOLO_SCIENTIFICO AS AR) 
            NATURAL JOIN INTRODUZIONE AS I) NATURAL JOIN FASCICOLO AS F)  
    WHERE CO.DataInizio>F.DataPubblicazione AND CO.CodF=NEW.CodF;
BEGIN
   
    IF contatore>0 THEN --controlla se l'anno della nuova data è deve essere precedente a quello dell'anno di pubblicazione degli articoli esposti o è successiva a quella dei fascicoli
        UPDATE FASCICOLO
        SET DataPubblicazione=OLD.DataPubblicazione
        WHERE CodF=NEW.CodF;
        
        RAISE NOTICE "Non è possibile inserire una data di pubblicazione di un fascicolo precedente a quella delle conferenze dei suoi articoli";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modifica_pubblicazioneFascicolo AFTER UPDATE OF DataPubblicazione ON FASCICOLO
    WHEN(NEW.DataPubblicazione<OLD.DataPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaFascicolo();

--Quando viene creata o modificata una presentazione la nuova data non puo essere precedente a quella della 
--pubblicazione del libro
CREATE OR REPLACE FUNCTION controllo_Presentazione() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT COUNT(*) INTO contatore 
    FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS P  
    WHERE P.DataP<L.DataPubblicazione AND P.CodP=NEW.CodP;

    IF contatore>0 THEN --controlla se la data della presentazione e precedente a quella della pubblicazion del libro
        UPDATE PRESENTAZIONE
        SET DataP=OLD.DataP, ISBN=OLD.ISBN
        WHERE  P.CodP=NEW.CodP;

        RAISE NOTICE "Non è possibile inserire una data di una presentazione di un libro non ancora pubblicato";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoPresentazione AFTER INSERT ON PRESENTAZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_Presentazione();
CREATE TRIGGER T_modificaPresentazione AFTER UPDATE OF CodP, ISBN ON PRESENTAZIONE
    WHEN(NEW.DataPubblicazione<=OLD.DataPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_Presentazione();

--Quando viene modificata la data di pubblicazione del libro la nuova data non deve essere successiva a quelle delle
--date di tutte le presentazioni del libro 
CREATE OR REPLACE FUNCTION controllo_Libro() RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    dataCorrente PRESENTAZIONE.DataP%TYPE;

    cursore_datePresentazioni CURSOR FOR
        SELECT P.DataP  --trova le date delle presentazioni del libro modificato
        FROM LIBRO AS L NATURAL JOIN PRESENTAZIONE AS P  
        WHERE P.DataP<L.DataPubblicazione AND L.ISBN=NEW.ISBN;
BEGIN
    OPEN cursore_datePresentazioni;

    LOOP
        FETCH cursore_datePresentazioni INTO dataCorrente;

        EXIT WHEN NOT FOUND OR errore_trovato=true;

        IF dataCorrente<NEW.DataPubblicazione THEN --controlla se la data della presentazione e precedente a quella della pubblicazion del libro
            UPDATE LIBRO
            SET DataP=OLD.DataP
            WHERE ISBN=NEW.ISBN;

            errore_trovato:=true;
            RAISE NOTICE "Non è possibile inserire una data di pubblicazione di un libro precedente a quella delle sue presentazioni";
        END IF;
    END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_modificaLibro AFTER UPDATE OF DataPubblicazione ON LIBRO
    WHEN(NEW.DataPubblicazione>OLD.DataPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_Libro();

--Quando avviene un inserimento in 'POSSESSO_L' bisogna controllare se il libro appartiene a una serie e se la
--libreria possiede tutti i libri della serie del libro inserito, in tal caso bisogna effettuare l'inserimento in 
--'POSSESSO_S' specificando la quantità e la modalità di fruizione correttamente.
CREATE OR REPLACE FUNCTION controllo_Possesso_L() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
    CodS INSERIMENTO.Serie%TYPE:=NULL; --codice della serie del libro inserito
    LibriSerie SERIE.NLibri%TYPE:=NULL; --numero di libri inseriti nella serie del nuovo libro
    QuantitaDisponibile POSSESSO_S.Quantita%TYPE:=NULL; --quantità disponibile della serie del libro inserito
BEGIN
    SELECT I.Serie INTO CodS, LibriSerie  --trova il codice della serie del libro inserito
    FROM ((INSERIMENTO AS I JOIN LIBRO AS L ON I.Libro=L.ISBN) JOIN SERIE AS S ON I.Serie=S.ISBN)
    WHERE L.ISBN=NEW.Libro;
    
    IF Cods IS NOT NULL THEN --controlla se il libro inserito appartiene a una serie

        SELECT COUNT(*) INTO contatore  --calcola il numero di libri della serie del libro inserito posseduti dalla libreria 'NEW.Codl'
        FROM POSSESSO_L 
        WHERE Codl=NEW.Codl AND ((Quantita>0 AND NEW.Fruizione='Cartaceo') OR 
                (Quantita IS NULL AND NEW.Fruizione<>'Cartaceo')) AND Fruizione=NEW.Fruizione 
                AND ISBN IN(
                                SELECT Libro
                                FROM INSERIMENTO
                                WHERE Serie=CodS
                            );

        IF contatore=LibriSerie THEN --controlla se la libreria 'NEW.Codl' possiede tutta la serie del libro inserito
            
            SELECT MIN(Quantita) INTO QuantitaDisponibile --calcola la quantità minima dei libri disponibili della serie 'CodS' 
            FROM POSSESSO_L AS PL
            WHERE PL.Fruizione='Cartaceo' AND ISBN IN(
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
    FOR EACH ROW EXECUTE FUNCTION controllo_Possesso_L();
CREATE TRIGGER T_modificaPossesso_T AFTER UPDATE ON POSSESSO_L
    FOR EACH ROW EXECUTE FUNCTION controllo_Possesso_L();