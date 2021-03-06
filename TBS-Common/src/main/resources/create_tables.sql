-- TODO Update this file
CREATE TABLE ENTITY_VIDEO
(
  ID SERIAL NOT NULL,
  NOM character varying,
  TYPE character varying,
  NUMSAISON integer,
  NUMEPISODE integer,
  CONSTRAINT ENTITYVIDEO_PKEY PRIMARY KEY (ID),
  CONSTRAINT VIDEO_CONSTRAINT UNIQUE (NOM, NUMSAISON, NUMEPISODE)
);


CREATE TABLE ENGLISH
(
  ID serial NOT NULL,
  EXPRESSION character varying,
  TIMEBEGIN integer,
  TIMEEND integer,
  RANK integer,
  ENTITYVIDEOID integer,
  CONSTRAINT ENGLISH_PKEY PRIMARY KEY (ID),
  CONSTRAINT ENGLISH_ENTITYVIDEOID_FKEY FOREIGN KEY (ENTITYVIDEOID)
      REFERENCES ENTITY_VIDEO (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE FRENCH
(
  ID serial NOT NULL,
  EXPRESSION character varying,
  TIMEBEGIN integer,
  TIMEEND integer,
  RANK integer,
  ENTITYVIDEOID integer,
  CONSTRAINT FRENCH_PKEY PRIMARY KEY (id),
  CONSTRAINT FRENCH_ENTITYVIDEOID_FKEY FOREIGN KEY (entityvideoid)
      REFERENCES ENTITY_VIDEO (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE USERS
(
  EMAIL character varying(100) NOT NULL,
  LOGIN character varying(100) NOT NULL,
  PASSWORD character varying(100),
  TOKEN character varying(100),
  CONSTRAINT USERS_PKEY PRIMARY KEY (EMAIL)
);

CREATE TABLE USERS_TRANSLATIONS
(
  ID SERIAL,
  EMAIL character varying(100),
  EXPR1 character varying(1000),
  EXPR2 character varying(1000),
  CONSTRAINT UC_EXPR1EXPR2EMAIL UNIQUE (EMAIL, EXPR1, EXPR2)
);

CREATE TABLE users_personal_translations
(
  id serial NOT NULL,
  email character varying(100),
  expr1 character varying(1000),
  expr2 character varying(1000),
  language_from character varying(100),
  language_to character varying(100),
  CONSTRAINT users_personal_translations_pkey PRIMARY KEY (id),
  CONSTRAINT users_personal_translations_email_fkey FOREIGN KEY (email)
      REFERENCES users (email) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
