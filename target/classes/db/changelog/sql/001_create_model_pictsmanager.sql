CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS public."Albums" (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    owner_id INTEGER NOT NULL,
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES public."Users" (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public."Photos" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    album_id INTEGER NOT NULL,
    owner_id INTEGER NOT NULL,
    CONSTRAINT fk_album_id FOREIGN KEY (album_id) REFERENCES public."Albums" (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_owner_id_photos FOREIGN KEY (owner_id) REFERENCES public."Users" (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public."AccessRights" (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    album_id INTEGER NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public."Users" (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_album_id_accessrights FOREIGN KEY (album_id) REFERENCES public."Albums" (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT unique_user_album UNIQUE (user_id, album_id)
);
