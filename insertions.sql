-- admins
INSERT INTO admins (name, email, hash_password)
VALUES ('Alice Cohen', 'alice@example.com', '$2a$12$3socJciDOYS4JJoqvPSMA.DSMi75ZXOyLmLyJZv9jxoEwwHRyrqU2'), -- admin123
       ('Benny Levi', 'benny@example.com',
        '$2a$12$2ZBgLLNrUZxSYZpLfoW63uJad3nD4Dmi2.RZ4nfneri0DegitzYny'),                                     -- password456
       ('Chaim Gross', 'chaim@example.com',
        '$2a$12$TmURblW8CJwUewbu77MuWOEikbXwZnmCY69iZC7BFM7fvMf0q8WsO'),                                     -- secret789
       ('Dina Azulay', 'dina@example.com', '$2a$12$sknWgUDtMcdEW4YNtXbkJOiiIPIlVgQxdu70AgoGdPJN/jZ99QSVG'),  -- qwerty111
       ('Eli Regev', 'eli@example.com', '$2a$12$mdmvhA1vErPBGUCjRRYNpOi/qDvi/.PPULTkWLc7x.QILWAVCy/AW'),     -- letmein222
       ('Fanny Bar', 'fanny@example.com', '$2a$12$y1.gcXnu2LftCEhb2ZwWu.i2BBRcUyq/zvNLe/8XR0L.VPhPlEF26'),   -- welcome333
       ('Gadi Noy', 'gadi@example.com', '$2a$12$g/P7eYfppRsEif9bexPLSuuYuSVVkGtA3p97uXQ4vMyXAd3gCYvHW');
-- football444


# | Plain          | hash bcrypt  strength 12                                      |
# admin123 -> $2a$12$3socJciDOYS4JJoqvPSMA.DSMi75ZXOyLmLyJZv9jxoEwwHRyrqU2
# password456 -> $2a$12$2ZBgLLNrUZxSYZpLfoW63uJad3nD4Dmi2.RZ4nfneri0DegitzYny
# secret789 -> $2a$12$TmURblW8CJwUewbu77MuWOEikbXwZnmCY69iZC7BFM7fvMf0q8WsO
# qwerty111 -> $2a$12$sknWgUDtMcdEW4YNtXbkJOiiIPIlVgQxdu70AgoGdPJN/jZ99QSVG
# letmein222 -> $2a$12$mdmvhA1vErPBGUCjRRYNpOi/qDvi/.PPULTkWLc7x.QILWAVCy/AW
# welcome333 -> $2a$12$y1.gcXnu2LftCEhb2ZwWu.i2BBRcUyq/zvNLe/8XR0L.VPhPlEF26
# football444 -> $2a$12$g/P7eYfppRsEif9bexPLSuuYuSVVkGtA3p97uXQ4vMyXAd3gCYvHW


-- students
INSERT INTO students (name, email, special_key)
VALUES ('Yael Mizrahi', 'yael@student.com', 'key1'),
       ('Ronen Mor', 'ronen@student.com', 'key2'),
       ('Lior Hadad', 'lior@student.com', 'key3'),
       ('Tamar Shalev', 'tamar@student.com', 'key4'),
       ('Noa Ziv', 'noa@student.com', 'key5'),
       ('Avi Segal', 'avi@student.com', 'key6'),
       ('Dana Klein', 'dana@student.com', 'key7');

-- courses
INSERT INTO courses (name, description)
VALUES ('Math 101', 'Basic mathematics course'),
       ('History of Art', 'Introduction to art history'),
       ('Physics I', 'Fundamentals of classical mechanics'),
       ('Literature', 'Survey of world literature'),
       ('Computer Science', 'Intro to programming and algorithms'),
       ('Biology', 'Introduction to biological sciences'),
       ('Philosophy', 'Basic philosophical questions and thinkers');


INSERT INTO course_registrations (student_id, course_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (3, 1),
       (4, 5),
       (5, 6),
       (6, 7);
