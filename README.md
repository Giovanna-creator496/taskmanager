**TASK MANAGER API**
Progetto Backend svilupatto con Spring Boot e Docker.
L'applicazione gestisce un sistema di task con persistenza
su database relazionale e sicurezza avanzata.

**ARCHITETTURA DEL PROGETTO**
Il codice e' stato strutturato seguendo i principi di 
**Clean Architecture** e **Separation of Concerns**:
-**Controller & DTO**: utilizzo di Data Transfer Objects per
disaccoppiare l'entità del database dall'interfaccia API,
migliorando sicurezza e manutenibilità.
-**Service Layer**: tutta la logica di business é isolata nei
servizi, garantendo un codice testabile e riutilizzabile.
-**Repository Pattern**: interfaccia diretta con MySQL tramite
Spring Data JPA.
-**Global Exception Handling**: implementato un sistema
centralizzato ('@ControllerAdvice') per gestire gli errori
in modo consistente, restituendo risposte JSON standardizzate
(ErrorResponse).
-**Soft Delete**: configurato il sistema per non eliminare
mai definitivamente i dati dal database (hard delete), ma 
marcati come eliminati tramite una colonna 'deletedAt'.
Questo garantisce l'integrità dei dati e permette il recupero
storico, rendendo i task ""invisibili" all'utente ma 
presistenti per l'amministratore.

**TECNOLOGIE UTILIZZATE**
-Java && Spring Boot: per la logica applicativa.
-MySQL 8: database per le gestione dei dati.
-Docker & Docker Compose: per l'orchestrazione dei servizi.
-PhpMyAdmin: incluso per agevolare il testing e la visualizzazione
del database.

**SICUREZZA E BEST PRACTICES**
-BCrypt: le password degli utenti sono salvate esclusivamente
tramite hashing.
-Variabili d'ambiente: i dati sensibili sono isolati in un file 
.env escluso dal versionamento per proteggere le credenziali.

**COME AVVIARE IL PROGETTO**
1.Clona la repository.
2.Copia il file '.env.example' creando un nuovo file '.env' 
3.Inserisci le tue credenziali.
4.Avvia il sistema con: '''bash docker compose up --build'''

**ACCESSO AI SERVIZI**
-Swagger UI(Documentazione API): 
http://localhost:8080/swagger-ui.html)
-PhpMyAdmin(Interfaccia DB): [http://localhost:8081]
(http://localhost:8081)

