BEGIN;


CREATE TABLE IF NOT EXISTS public."Users"
(
    id integer NOT NULL,
    nom character varying(255)[] COLLATE pg_catalog."default" NOT NULL,
    email character varying(255)[] COLLATE pg_catalog."default" NOT NULL,
    password character varying(255)[] COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Users_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Albums"
(
    id integer NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    owner_id integer NOT NULL,
    CONSTRAINT "Albums_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Photos"
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    album_id integer NOT NULL,
    owner_id integer NOT NULL,
    CONSTRAINT "Photos_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."AccessRights"
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    album_id integer NOT NULL,
    CONSTRAINT "AccessRights_pkey" PRIMARY KEY (id),
    CONSTRAINT "AccessRights_user_id_album_id_user_id1_album_id1_key" UNIQUE (user_id, album_id)
        INCLUDE(user_id, album_id)
);

ALTER TABLE IF EXISTS public."Albums"
    ADD CONSTRAINT owner_id FOREIGN KEY (owner_id)
    REFERENCES public."Users" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."Photos"
    ADD CONSTRAINT album_id FOREIGN KEY (album_id)
    REFERENCES public."Albums" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."Photos"
    ADD CONSTRAINT owner_id FOREIGN KEY (owner_id)
    REFERENCES public."Users" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."AccessRights"
    ADD CONSTRAINT album_id FOREIGN KEY (album_id)
    REFERENCES public."Albums" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."AccessRights"
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public."Users" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

END;