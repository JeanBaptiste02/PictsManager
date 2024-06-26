CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS albums (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    owner_id INTEGER NOT NULL,
    CONSTRAINT fk_owner_id_albums FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS photos (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    description TEXT,
    date TIMESTAMP NOT NULL,
    album_id INTEGER NOT NULL,
    owner_id INTEGER NOT NULL,
    visibility BOOLEAN NOT NULL DEFAULT false,
    CONSTRAINT fk_album_id_photos FOREIGN KEY (album_id) REFERENCES albums (id),
    CONSTRAINT fk_owner_id_photos FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS access_rights (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    album_id INTEGER NOT NULL,
    CONSTRAINT fk_user_id_access_rights FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_album_id_access_rights FOREIGN KEY (album_id) REFERENCES albums (id),
    CONSTRAINT unique_user_album UNIQUE (user_id, album_id)
);