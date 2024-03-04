# Sistema di una Biblioteca Digitale
### Repository
La repository è composta da due branches:
1. **[Object Orientation][OOP]:** In questa branch sono presenti i file principali relativi al progetto di Object Orientation.
2. **[Basi di Dati][BDP]:** In questa branch sono presenti i file principali relativi al progetto di Basi di Dati.

### Branch: Main
Il branch '[Object Orientation][GIT]' del progetto contiene i seguenti file:
- File [_"Manuale.pdf"_][CDB]
- File [_"Codice_sorgente.rar"_][PDB]
- File [_"Documentazione.pdf"_][Doc] 

### File
Qui viene descritto il contenuto dei file citati precedentemente.

| File | Contenuto |
| ------ | ------ |
| [CreazioneDB.sql][CDB] | Questo file contiene il codice sql per la creazione **delle tabelle, dei vincoli intrarelazionali, dei trigger, delle funzioni e delle procedure** utili per il funzionamento della base di Dati |
| [Popolazione.sql][PDB] | Questo file contiene il codice sql per **popolare** (inserire dati) **il database** |
| [Documentazione.pdf][DOC] | Questo file contiene **la descrizione** della realizzazione e del funzionamento della base di dati. In esso vi sono contenuti tutte le fasi della **proggettazione concettuale** e sono rappresentate **le entità, le relazioni, i vincoli, i trigger, le funzioni e le procedure utilizzate** |

### Configurazione Database

La configurazione del database consiste nelle seguenti fasi che prevedono l'utilizzo di PostgreSQL:

1. Creare il database utilizzando: `CREATE DATABASE nome_database`.

2. Inserire il contenuto del file [CreazioneDB.sql][CDB] nel terminale ed eseguire il comando.

3. Se tutto è andato a buon fine, la base di dati è pronta per essere popolata e quindi e possibile inserire il contenuto del file [Popolazione.sql][PDB] nel terminale ed eseguire il comando.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO)

[CDB]: <https://github.com/Giovk/Sistema-di-una-Bibleoteca-Digitale/blob/main/CreazioneDB.sql>
[PDB]: <https://github.com/Giovk/Sistema-di-una-Bibleoteca-Digitale/blob/main/Popolazione.sql>
[DOC]: <https://github.com/Giovk/Sistema-di-una-Bibleoteca-Digitale/blob/main/Documentazione.pdf>
[GIT]: <https://github.com/Giovk/Sistema-di-una-Bibleoteca-Digitale>
[OFB]: <https://github.com/Giovk/Sistema-di-una-Bibleoteca-Digitale/tree/Old-File>