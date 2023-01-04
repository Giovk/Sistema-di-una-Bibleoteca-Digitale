-- Quando viene inserita una libreria di un utente, oppure viene modificato il gestore di una libreria, il gestore 
-- deve avere una partitaIVA
CREATE FUNCTION controllo_inserimentoLibreria() RETURNS trigger AS $$
DECLARE
    contatore INTEGER;
BEGIN
    SELECT CONT(U.PartitaIVA) INTO contatore --controlla la partitaIVA del gestore della libreria inserita
    FROM UTENTE AS U JOIN LIBRERIA AS L ON U.Username=NEW.Gestore
    WHERE U.PartitaIVA IS NOT NULL;

    IF contatore=0 THEN --controlla se non è stata trovata nessuna partitaIVA
        DELETE LIBRERIA
        WHERE CodL=NEW.CodL;

        RAISE NOTICE "Per inserire una nuova libreria è necessario specificare la PartitaIVA del gestore";
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T_inserimentoLibreria AFTER INSERT ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_inserimentoLibreria();
CREATE TRIGGER T_modificaGestore AFTER UPDATE OF Gestore ON LIBRERIA
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaGestore();

--Quando viene chiusa la partitaIVA di un gestore bisogna eliminare la sua libreria

-- Quando viene introdotto un articolo scientifico in un fascicolo la data di pubblicazione del fascicolo deve essere
-- precedente a quella dell'articolo
CREATE FUNCTION controllo_introduzioneArticolo(); RETURNS trigger AS $$
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
        DELETE INTRODUZIONE
        WHERE CodF=NEW.CodF AND DOI=NEW.DOI;

        RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T2 AFTER INSERT ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_introduzioneArticolo();
CREATE TRIGGER T3 AFTER UPDATE ON INTRODUZIONE
    FOR EACH ROW EXECUTE FUNCTION controllo_introduzioneArticolo();

CREATE FUNCTION controllo_modificaArticolo(); RETURNS trigger AS $$
DECLARE
    errore_trovato BOOLEAN:=false; --indica se la data modificata è errata
    anno_fascicoloCorrente INTEGER;

    cursore_anniFascicoli CURSOR FOR    --contiene gli anni di pubblicazione dei fascioli con l'articolo modificato
        SELECT EXTRACT(YEAR FROM F.DataPubblicazione)
        FROM FASCICOLO AS F NATURAL JOIN INTRODUZIONE AS I
        WHERE I.DOI=NEW.DOI;
BEGIN
    OPEN  cursore_anniFascicoli;

    LOOP
        FETCH cursore_anniFascicoli INTO anno_fascicoloCorrente;

        EXIT WHEN NOT FOUND OR errore_trovato=true;

        IF NEW.AnnoPubblicazione>anno_fascicoloCorrente THEN --controlla se l'articolo aggiornato è stato pubblicato dopo al fascicolo
            UPDATE ARTICOLO_SCIENTIFICO
            SET AnnoPubblicazione=OLD.AnnoPubblicazione
            WHERE DOI=NEW.DOI;
            
            errore_trovato:=true;

            RAISE NOTICE "Non è possibile inserire in un fascicolo un articolo scientifico pubblicato dopo la pubblicazione dell fascicolo";
        END IF;
    END LOOP;

    CLOSE cursore_anniFascicoli;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T4 AFTER UPDATE OF AnnoPubblicazione ON ARTICOLO_SCIENTIFICO
    WHEN(NEW.AnnoPubblicazione>OLD.AnnoPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaArticolo();

CREATE FUNCTION controllo_modificaFascicolo(); RETURNS trigger AS $$
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
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER T5 AFTER UPDATE OF DataPubblicazione ON FASCICOLO
    WHEN(NEW.DataPubblicazione<OLD.DataPubblicazione)
    FOR EACH ROW EXECUTE FUNCTION controllo_modificaFascicolo();

-- Quando viene introdotto un fascicolo in una rivista la data di pubblicazione del fascicolo deve essere
-- successiva a quella della rivista
