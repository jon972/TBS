DROP ALL OBJECTS;

create sequence ENTITY_VIDEO_ID_SEQ;
create sequence SUBTITLE_ID_SEQ;
create sequence USERS_TRANSLATIONS_ID_SEQ;

CREATE TABLE ENTITY_VIDEO
(
  ID bigint default entity_video_id_seq.nextval,
  NOM character varying,
  TYPE character varying,
  NUMSAISON integer,
  NUMEPISODE integer,
  CONSTRAINT ENTITYVIDEO_PKEY PRIMARY KEY (ID),
  CONSTRAINT VIDEO_CONSTRAINT UNIQUE (NOM, NUMSAISON, NUMEPISODE)
);


CREATE TABLE SUBTITLE
(
  ID bigint default subtitle_id_seq.nextval,
  expression character varying,
  timebegin integer,
  timeend integer,
  rank integer,
  language character varying(100),
  entityvideoid bigint,
  CONSTRAINT subtitle_pkey PRIMARY KEY (ID),
  CONSTRAINT subtitle_entityvideoid_fkey FOREIGN KEY (entityvideoid) REFERENCES ENTITY_VIDEO (ID)
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
  ID bigint default users_translations_id_seq.nextval,
  EMAIL character varying(100),
  EXPR1 character varying(1000),
  EXPR2 character varying(1000),
  CONSTRAINT UC_EXPR1EXPR2EMAIL UNIQUE (EMAIL, EXPR1, EXPR2)
);

INSERT INTO USERS VALUES('hage.jonathan@gmail.com', 'Jon', 'hello', 'a9910d3a050d456cabcda315b2e78015');
