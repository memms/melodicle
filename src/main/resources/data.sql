-- SQL file for dummy data

-- Inserting data into UserEntity table
INSERT INTO users (username, password_hash, fname, lname, email)
VALUES ('testUser1', 'passwordHash1', 'Test', 'User1', 'testUser1@example.com'),
       ('testUser2', 'passwordHash2', 'Test', 'User2', 'testUser2@example.com'),
       ('testUser3', 'passwordHash3', 'Test', 'User3', 'testUser3@example.com');

-- Inserting data into UserRoleEntity table
INSERT INTO auth_roles (role_name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

-- Assigning roles to users
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 1);

-- Inserting data into ArtistEntity table
INSERT INTO artists (id, name)
VALUES (1, 'Artist1'),
       (2, 'Artist2'),
       (3, 'Artist3');

-- Inserting data into SongEntity table
INSERT INTO songs (song_id, name, artist_id)
VALUES (1, 'Song1', 1),
       (2, 'Song2', 2),
       (3, 'Song3', 3);

-- Inserting data into PlaylistEntity table
INSERT INTO playlists (name, date_created, song_count, user_id)
VALUES ('Playlist1', '2022-01-01', 3, 1),
       ('Playlist2', '2022-02-01', 2, 2),
       ('Playlist3', '2022-03-01', 1, 3);

-- Assigning songs to playlists
INSERT INTO playlist_songs (playlist_id, song_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 3);