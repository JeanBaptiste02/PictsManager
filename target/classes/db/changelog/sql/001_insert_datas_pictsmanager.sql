-- Insérer des utilisateurs
INSERT INTO public."Users" (nom, email, password) VALUES
                                                      ('John Doe', 'john@example.com', 'password123'),
                                                      ('Alice Smith', 'alice@example.com', 'securepass'),
                                                      ('Bob Johnson', 'bob@example.com', 'bobspassword');

-- Insérer des albums
INSERT INTO public."Albums" (title, owner_id) VALUES
                                                  ('Vacances été 2023', 1),
                                                  ('Photos de famille', 2),
                                                  ('Voyage à Paris', 3);

-- Insérer des photos
INSERT INTO public."Photos" (name, album_id, owner_id) VALUES
                                                           ('Plage', 1, 1),
                                                           ('Anniversaire', 2, 2),
                                                           ('Tour Eiffel', 3, 3);

-- Insérer des droits d'accès
INSERT INTO public."AccessRights" (user_id, album_id) VALUES
                                                          (2, 1), -- Alice a accès aux photos de John
                                                          (3, 2), -- Bob a accès aux photos d'Alice
                                                          (1, 3); -- John a accès aux photos de Bob
