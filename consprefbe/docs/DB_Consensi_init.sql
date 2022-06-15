
-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "conspref_dev"."cons_d_fonte_fonte_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_d_informativa_informativa_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_d_operatore_operatore_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_d_s_tipo_consenso_s_tipo_cons_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_d_tipo_consenso_tipo_cons_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_d_tipo_fonte_tipo_fonte_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_t_consenso_cons_id_seq"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."csi_log_audit_audit_id_seq"
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

CREATE SEQUENCE "conspref_dev"."cons_s_consenso_conss_id_seq"
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table conspref_dev.cons_d_asr

CREATE TABLE "conspref_dev"."cons_d_asr"(
 "cod_asr" Text NOT NULL,
 "desc_asr" Text,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp,
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;

-- Create indexes for table conspref_dev.cons_d_asr

CREATE UNIQUE INDEX "idx_cons_d_asr_1" ON "conspref_dev"."cons_d_asr" ("cod_asr")
;

-- Add keys for table conspref_dev.cons_d_asr

ALTER TABLE "conspref_dev"."cons_d_asr" ADD CONSTRAINT "PK_cons_d_asr" PRIMARY KEY ("cod_asr")
;

ALTER TABLE "conspref_dev"."cons_d_asr" ADD CONSTRAINT "cod_asr" UNIQUE ("cod_asr")
;

-- Table conspref_dev.cons_d_fonte

CREATE TABLE "conspref_dev"."cons_d_fonte"(
 "fonte_id" Integer DEFAULT nextval('cons_d_fonte_fonte_id_seq'::regclass) NOT NULL,
 "fonte_cod" Text NOT NULL,
 "fonte_desc" Text,
 "tipo_fonte_id" Integer DEFAULT nextval('cons_d_tipo_fonte_tipo_fonte_id_seq'::regclass) NOT NULL,
 "data_decorrenza" Timestamp,
 "data_scadenza" Timestamp,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_fonte"."fonte_cod" IS 'codice fonte'
;
COMMENT ON COLUMN "conspref_dev"."cons_d_fonte"."fonte_desc" IS 'descrizione fonte'
;

-- Create indexes for table conspref_dev.cons_d_fonte

CREATE UNIQUE INDEX "idx_cons_d_fonte_1" ON "conspref_dev"."cons_d_fonte" ("fonte_cod")
;

CREATE INDEX "IX_Relationship93" ON "conspref_dev"."cons_d_fonte" ("tipo_fonte_id")
;

-- Add keys for table conspref_dev.cons_d_fonte

ALTER TABLE "conspref_dev"."cons_d_fonte" ADD CONSTRAINT "pk_cons_d_fonte" PRIMARY KEY ("fonte_id")
;

-- Table conspref_dev.cons_d_informativa

CREATE TABLE "conspref_dev"."cons_d_informativa"(
 "d_informativa_id" Integer DEFAULT nextval('cons_d_informativa_informativa_id_seq'::regclass) NOT NULL,
 "tipo_consenso" Text,
 "sotto_tipo_consenso" Text,
 "pdf_informativa" Text,
 "data_decorrenza" Timestamp DEFAULT now(),
 "data_scadenza" Timestamp DEFAULT now(),
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;

-- Create indexes for table conspref_dev.cons_d_informativa

CREATE UNIQUE INDEX "idx_cons_t_informativa" ON "conspref_dev"."cons_d_informativa" ("d_informativa_id")
;

CREATE INDEX "IX_Relationship104" ON "conspref_dev"."cons_d_informativa" ("tipo_consenso")
;

CREATE INDEX "IX_Relationship105" ON "conspref_dev"."cons_d_informativa" ("sotto_tipo_consenso")
;

-- Add keys for table conspref_dev.cons_d_informativa

ALTER TABLE "conspref_dev"."cons_d_informativa" ADD CONSTRAINT "pk_cons_t_informativa" PRIMARY KEY ("d_informativa_id")
;

-- Table conspref_dev.cons_d_operatore

CREATE TABLE "conspref_dev"."cons_d_operatore"(
 "operatore_id" Integer DEFAULT nextval('cons_d_operatore_operatore_id_seq'::regclass) NOT NULL,
 "tipo_operatore" Text NOT NULL,
 "cod_operatore" Text NOT NULL,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;

-- Create indexes for table conspref_dev.cons_d_operatore

CREATE UNIQUE INDEX "idx_cons_d_operatore_1" ON "conspref_dev"."cons_d_operatore" ("operatore_id")
;

CREATE UNIQUE INDEX "idx_cons_d_operatore_2" ON "conspref_dev"."cons_d_operatore" ("cod_operatore","tipo_operatore")
;

-- Add keys for table conspref_dev.cons_d_operatore

ALTER TABLE "conspref_dev"."cons_d_operatore" ADD CONSTRAINT "pk_operatore_id" PRIMARY KEY ("operatore_id")
;

-- Table conspref_dev.cons_d_sotto_tipo_cons

CREATE TABLE "conspref_dev"."cons_d_sotto_tipo_cons"(
 "sotto_tipo_consenso" Text NOT NULL,
 "desc_sotto_tipo_cons" Text,
 "data_decorrenza" Timestamp,
 "data_scadenza" Timestamp,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_sotto_tipo_cons"."sotto_tipo_consenso" IS 'rol'
;
COMMENT ON COLUMN "conspref_dev"."cons_d_sotto_tipo_cons"."desc_sotto_tipo_cons" IS 'aziendale,regionale'
;

-- Add keys for table conspref_dev.cons_d_sotto_tipo_cons

ALTER TABLE "conspref_dev"."cons_d_sotto_tipo_cons" ADD CONSTRAINT "PK_cons_d_sotto_tipo_cons" PRIMARY KEY ("sotto_tipo_consenso")
;

-- Table conspref_dev.cons_d_stato

CREATE TABLE "conspref_dev"."cons_d_stato"(
 "tipo_stato" Text NOT NULL,
 "desc_stato" Text,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_stato"."tipo_stato" IS 'A,R,S'
;
COMMENT ON COLUMN "conspref_dev"."cons_d_stato"."desc_stato" IS 'Attivo, Revocato, Scaduto'
;

-- Create indexes for table conspref_dev.cons_d_stato

CREATE UNIQUE INDEX "idx_cons_d_stato_1" ON "conspref_dev"."cons_d_stato" ("tipo_stato")
;

-- Add keys for table conspref_dev.cons_d_stato

ALTER TABLE "conspref_dev"."cons_d_stato" ADD CONSTRAINT "PK_cons_d_stato" PRIMARY KEY ("tipo_stato")
;

ALTER TABLE "conspref_dev"."cons_d_stato" ADD CONSTRAINT "tipo_stato" UNIQUE ("tipo_stato")
;

-- Table conspref_dev.cons_d_tipo_cons

CREATE TABLE "conspref_dev"."cons_d_tipo_cons"(
 "tipo_consenso" Text NOT NULL,
 "desc_tipo_cons" Text,
 "data_decorrenza" Timestamp,
 "data_scadenza" Timestamp,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_tipo_cons"."tipo_consenso" IS 'A,R'
;
COMMENT ON COLUMN "conspref_dev"."cons_d_tipo_cons"."desc_tipo_cons" IS 'aziendale,regionale'
;

-- Add keys for table conspref_dev.cons_d_tipo_cons

ALTER TABLE "conspref_dev"."cons_d_tipo_cons" ADD CONSTRAINT "PK_cons_d_tipo_cons" PRIMARY KEY ("tipo_consenso")
;

-- Table conspref_dev.cons_d_tipo_fonte

CREATE TABLE "conspref_dev"."cons_d_tipo_fonte"(
 "tipo_fonte_id" Integer DEFAULT nextval('cons_d_tipo_fonte_tipo_fonte_id_seq'::regclass) NOT NULL,
 "tipofonte_cod" Text NOT NULL,
 "tipofonte_desc" Text,
 "data_decorrenza" Timestamp,
 "data_scadenza" Timestamp,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp DEFAULT now(),
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_tipo_fonte"."tipofonte_cod" IS 'codice tipo fonte'
;
COMMENT ON COLUMN "conspref_dev"."cons_d_tipo_fonte"."tipofonte_desc" IS 'descrizione tipo fonte'
;

-- Create indexes for table conspref_dev.cons_d_tipo_fonte

CREATE UNIQUE INDEX "idx_cons_d_tipo_fonte_1" ON "conspref_dev"."cons_d_tipo_fonte" ("tipofonte_cod")
;

-- Add keys for table conspref_dev.cons_d_tipo_fonte

ALTER TABLE "conspref_dev"."cons_d_tipo_fonte" ADD CONSTRAINT "pk_cons_d_tipo_fonte" PRIMARY KEY ("tipo_fonte_id")
;

-- Table conspref_dev.cons_d_valore_cons

CREATE TABLE "conspref_dev"."cons_d_valore_cons"(
 "valore_consenso" Text NOT NULL,
 "desc_consenso" Text,
 "data_creazione" Timestamp DEFAULT now(),
 "data_modifica" Timestamp,
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_d_valore_cons"."valore_consenso" IS 'si,no,ne'
;

-- Create indexes for table conspref_dev.cons_d_valore_cons

CREATE UNIQUE INDEX "idx_cons_d_valore_cons_1" ON "conspref_dev"."cons_d_valore_cons" ("valore_consenso")
;

-- Add keys for table conspref_dev.cons_d_valore_cons

ALTER TABLE "conspref_dev"."cons_d_valore_cons" ADD CONSTRAINT "PK_cons_d_valore_cons" PRIMARY KEY ("valore_consenso")
;

ALTER TABLE "conspref_dev"."cons_d_valore_cons" ADD CONSTRAINT "cod_tipo_cons" UNIQUE ("valore_consenso")
;

-- Table conspref_dev.cons_t_consenso

CREATE TABLE "conspref_dev"."cons_t_consenso"(
 "cons_id" Integer DEFAULT nextval('cons_t_consenso_cons_id_seq'::regclass) NOT NULL,
 "operatore_id" Integer,
 "fonte_id" Integer NOT NULL,
 "tipo_stato" Text NOT NULL,
 "audit_id" Integer NOT NULL,
 "d_informativa_id" Integer,
 "cod_asr" Text,
 "valore_consenso" Text NOT NULL,
 "cf_cittadino" Text NOT NULL,
 "id_aura" Text,
 "nome" Text,
 "cognome" Text,
 "cf_delegato" Text,
 "data_acquisizione" Timestamp DEFAULT now() NOT NULL,
 "data_fine" Timestamp,
 "uuid" UUID,
 "data_creazione" Timestamp,
 "data_modifica" Timestamp,
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_t_consenso"."cf_cittadino" IS 'cf_cittadino'
;
COMMENT ON COLUMN "conspref_dev"."cons_t_consenso"."data_acquisizione" IS 'verificare se può essere diversa da data decorrenza'
;

-- Create indexes for table conspref_dev.cons_t_consenso

CREATE UNIQUE INDEX "idx_cons_t_consenso_1" ON "conspref_dev"."cons_t_consenso" ("cf_cittadino","tipo_stato","cod_asr","data_fine")
;

CREATE INDEX "d_operatore_rel_d_consenso" ON "conspref_dev"."cons_t_consenso" ("operatore_id")
;

CREATE INDEX "d_informativa_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("d_informativa_id")
;

CREATE INDEX "d_fonte_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("fonte_id")
;

CREATE INDEX "log_audit_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("audit_id")
;

CREATE INDEX "d_stato_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("tipo_stato")
;

CREATE INDEX "cod_asr_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("cod_asr")
;

CREATE INDEX "valore_consenso_rel_t_consenso" ON "conspref_dev"."cons_t_consenso" ("valore_consenso")
;

-- Add keys for table conspref_dev.cons_t_consenso

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "pk_cons_t_consenso" PRIMARY KEY ("cons_id")
;

-- Table conspref_dev.cons_s_consenso

CREATE TABLE "conspref_dev"."cons_s_consenso"(
 "conss_id" Integer DEFAULT nextval('cons_s_consenso_conss_id_seq'::regclass) NOT NULL,
 "cons_id" Integer NOT NULL,
 "operatore_id" Integer,
 "fonte_id" Integer NOT NULL,
 "tipo_stato" Text NOT NULL,
 "audit_id" Integer NOT NULL,
 "d_informativa_id" Integer,
 "cod_asr" Text,
 "valore_consenso" Text NOT NULL,
 "cf_cittadino" Text NOT NULL,
 "id_aura" Text,
 "nome" Text,
 "cognome" Text,
 "cf_delegato" Text,
 "data_acquisizione" Timestamp DEFAULT now() NOT NULL,
 "data_fine" Timestamp,
 "uuid" UUID,
 "data_creazione" Timestamp,
 "data_modifica" Timestamp,
 "data_cancellazione" Timestamp,
 "login_operazione" Text,
 "ruoloop_id" Integer
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."cons_s_consenso"."cf_cittadino" IS 'cf_cittadino'
;
COMMENT ON COLUMN "conspref_dev"."cons_s_consenso"."data_acquisizione" IS 'verificare se può essere diversa da data decorrenza'
;

-- Add keys for table conspref_dev.cons_s_consenso

ALTER TABLE "conspref_dev"."cons_s_consenso" ADD CONSTRAINT "pk_cons_s_consenso" PRIMARY KEY ("conss_id")
;

-- Table conspref_dev.csi_log_audit

CREATE TABLE "conspref_dev"."csi_log_audit"(
 "audit_id" Integer DEFAULT nextval('csi_log_audit_audit_id_seq'::regclass) NOT NULL,
 "data_ora" Timestamp NOT NULL,
 "id_app" Character varying(100) NOT NULL,
 "ip_address" Character varying(40) NOT NULL,
 "utente" Character varying(100) NOT NULL,
 "proprietario" Character varying(100),
 "ruolo" Character varying(100),
 "operazione" Character varying(50) NOT NULL,
 "ogg_oper" Character varying(500) NOT NULL,
 "key_oper" Character varying(500),
 "idrequest" Text,
 "uuid" UUID NOT NULL
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."data_ora" IS 'Data e ora dell''evento'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."id_app" IS 'Codice identificativo dell''applicazione utilizzata dall''utente; da comporre con i valori presenti in Anagrafica Prodotti: <codice prodotto>_<codice linea cliente>_<codice ambiente>_<codice Unità di Installazione>'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."ip_address" IS 'Ip del client utente (non necessario per i servizi oggetto di questo sviluppo)'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."utente" IS 'Identificativo univoco dell''utente che ha effettuato l''operazione (codice fiscale)'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."proprietario" IS 'Identificativo univoco dell''utente proprietario del dato. Se coincidente con utente valorizzarlo con stesso valore (codice fiscale)'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."ruolo" IS 'Ruolo dell''utente: delegante o punto assistito se l''evento è stato scatenato da un punto assistito'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."operazione" IS 'Questo campo dovrà contenere l''informazione circa l''operazione effettuata; utilizzare uno dei seguenti valori: login / logout / read / insert / update / delete / merge Nei casi in cui il nome dell''operazione di business sia significativo e non riconducibile all''elenco di cui sopra, è possibile indicare il nome dell''operazione.'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."ogg_oper" IS 'Questa campo consentirà di identificare i dati e le informazioni trattati dall''operazione. Se la funzionalità lo permette inserire il nome delle tabelle (o in alternativa degli oggetti/entità) su cui viene eseguita l''operazione; l''indicazione della colonna è opzionale e andrà indicata nel formato tabella.colonna. Se l''applicativo prevede accessi a schemi dati esterni premettere il nome dello schema proprietario al nome della tabella.'
;
COMMENT ON COLUMN "conspref_dev"."csi_log_audit"."key_oper" IS 'Questo campo dovrà contenere l''identificativo univoco dell''oggetto dell''operazione oppure nel caso di aggiornamenti multipli del valore che caratterizza l''insieme di oggetti (es: modifica di un dato in tutta una categoria merceologica)'
;

-- Add keys for table conspref_dev.csi_log_audit

ALTER TABLE "conspref_dev"."csi_log_audit" ADD CONSTRAINT "csi_log_audit_pkey" PRIMARY KEY ("audit_id")
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "d_operatore_rel_d_consenso" FOREIGN KEY ("operatore_id") REFERENCES "conspref_dev"."cons_d_operatore" ("operatore_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "log_audit_rel_t_consenso" FOREIGN KEY ("audit_id") REFERENCES "conspref_dev"."csi_log_audit" ("audit_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "d_stato_rel_t_consenso" FOREIGN KEY ("tipo_stato") REFERENCES "conspref_dev"."cons_d_stato" ("tipo_stato") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "d_fonte_rel_t_consenso" FOREIGN KEY ("fonte_id") REFERENCES "conspref_dev"."cons_d_fonte" ("fonte_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "d_informativa_rel_t_consenso" FOREIGN KEY ("d_informativa_id") REFERENCES "conspref_dev"."cons_d_informativa" ("d_informativa_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_d_fonte" ADD CONSTRAINT "d_tipo_fonte_rel_d_fonte" FOREIGN KEY ("tipo_fonte_id") REFERENCES "conspref_dev"."cons_d_tipo_fonte" ("tipo_fonte_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_d_informativa" ADD CONSTRAINT "Relationship104" FOREIGN KEY ("tipo_consenso") REFERENCES "conspref_dev"."cons_d_tipo_cons" ("tipo_consenso") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_d_informativa" ADD CONSTRAINT "Relationship105" FOREIGN KEY ("sotto_tipo_consenso") REFERENCES "conspref_dev"."cons_d_sotto_tipo_cons" ("sotto_tipo_consenso") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "cod_asr_rel_t_consenso" FOREIGN KEY ("cod_asr") REFERENCES "conspref_dev"."cons_d_asr" ("cod_asr") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "conspref_dev"."cons_t_consenso" ADD CONSTRAINT "valore_consenso_rel_t_consenso" FOREIGN KEY ("valore_consenso") REFERENCES "conspref_dev"."cons_d_valore_cons" ("valore_consenso") ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER SEQUENCE "conspref_dev"."cons_d_informativa_informativa_id_seq" OWNED BY "conspref_dev"."cons_d_informativa"."d_informativa_id"
;
ALTER SEQUENCE "conspref_dev"."cons_d_operatore_operatore_id_seq" OWNED BY "conspref_dev"."cons_d_operatore"."operatore_id"
;
ALTER SEQUENCE "conspref_dev"."cons_t_consenso_cons_id_seq" OWNED BY "conspref_dev"."cons_t_consenso"."cons_id"
;
ALTER SEQUENCE "conspref_dev"."csi_log_audit_audit_id_seq" OWNED BY "conspref_dev"."csi_log_audit"."audit_id"
;
ALTER SEQUENCE "conspref_dev"."cons_s_consenso_conss_id_seq" OWNED BY "conspref_dev"."cons_s_consenso"."conss_id"
;