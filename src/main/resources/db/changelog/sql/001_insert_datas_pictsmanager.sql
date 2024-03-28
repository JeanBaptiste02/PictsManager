INSERT INTO users (nom, email, password) VALUES
                                                      ('Ganesh', 'john@example.com', 'password123'),
                                                      ('Ramesh', 'alice@example.com', 'securepass'),
                                                      ('Sri Ram', 'bob@example.com', 'bobspassword');

INSERT INTO albums (title, owner_id) VALUES
                                                  ('Vacances été 2023', 1),
                                                  ('Photos de famille', 2),
                                                  ('Voyage à Mumbai', 3);

INSERT INTO photos (name, path, description, date, album_id, owner_id) VALUES
                                                                           ('New Delhi', '/chemin/vers/plage.jpg', 'Delhie pic', NOW(), 1, 1),
                                                                           ('Mumbai beach', '/chemin/vers/plage.jpg', 'Mumbai pic', NOW(), 2, 2),
                                                                           ('Kashmir', '/chemin/vers/plage.jpg', 'Kashmir pic', NOW(), 3, 3);

INSERT INTO access_rights (user_id, album_id) VALUES
                                                           (2, 1),
                                                           (3, 2),
                                                           (1, 3);
