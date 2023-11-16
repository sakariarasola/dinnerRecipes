SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS recipe; 
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS cuisine;
DROP TABLE IF EXISTS recipe_ingredient;
DROP TABLE IF EXISTS users;
SET FOREIGN_KEY_CHECKS=1;

DROP SEQUENCE recipe_seq;
DROP SEQUENCE ingredient_seq;
DROP SEQUENCE cuisine_seq;

CREATE TABLE cuisine (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE ingredient (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
unitprice DOUBLE,
PRIMARY KEY (id)
);

CREATE TABLE recipe (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
allergen VARCHAR(50),
difficulty VARCHAR(50) NOT NULL,
totaltime INT NOT NULL,
servings INT NOT NULL,
ingredient_id BIGINT,
instructions VARCHAR(10000) NOT NULL,
cuisine_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (ingredient_id) REFERENCES ingredient(id),
FOREIGN KEY (cuisine_id) REFERENCES cuisine(id)
);

CREATE TABLE recipe_ingredient (
recipe_id BIGINT,
ingredient_id BIGINT,
FOREIGN KEY (recipe_id) REFERENCES recipe(id),
FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(250) NOT NULL,
  password varchar(250) NOT NULL,
  role varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE SEQUENCE recipe_seq START WITH 3 INCREMENT BY 1;

CREATE SEQUENCE ingredient_seq START WITH 17 INCREMENT BY 1;

CREATE SEQUENCE cuisine_seq START WITH 5 INCREMENT BY 1;

INSERT INTO cuisine (name) VALUES
('suomalainen'), ('italialainen'), ('thai'), ('texmex');

INSERT INTO recipe (name, allergen, difficulty, totaltime, servings, instructions, cuisine_id) VALUES
('Jauhelihakastike', 'laktoosi, pippuri', 'helppo', 15, 4, 'Ruskista jauheliha ja hienonnettu sipuli Oivariini rasvassa pannulla. Ripottele joukkoon vehnäjauho. Paista muutama min.
Murenna päälle liemikuutio. Lisää vesi ja pippurit. Sekoita tasaiseksi ja kiehauta.
Lisää ruokakerma. Keitä 5 min.
Viimeistele kastike halutessasi hienonnetulla persiljalla.
Tarjoa keitettyjen perunoiden ja salaatin kanssa.' , 1)
,('Yhden pannun tonnikalapasta', 'laktoosi, pippuri', 'helppo', 20, 4, 'Valmista ruoka n. 3 l kattilassa tai kasarissa. Kiehauta vesi ja liemikuutio. Lisää pasta, kirsikkatomaatit sekä ruokakerma.
Keitä kannen alla n. 10 min välillä kevyesti sekoittaen. Lisää valutettu tonnikala, pinaatinlehdet sekä kaprikset. Viimeistele mustapippurilla. Kuumenna ja tarjoile juustoraasteen kanssa.',
2);

INSERT INTO ingredient (name, unitprice) VALUES
('paistijauheliha', 6),
('kerma', 1.50),
('suola', 0),
('mustapippuri', 0),
('peruna', 2),
('jäävuorisalaatti', 1.50),
('kurkku', 1.50),
('kirsikkatomaatti', 2),
('oliiviöljy', 0),
('liemikuutio', 0),
('nauhapasta', 4),
('ruokakerma sweet chili', 2),
('tonnikalaa öljyssä', 2),
('babypinaatti', 2.2),
('kapris', 1),
('vesi', 0);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 16),
(2, 16),
(2, 10),
(2, 11),
(2, 2),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 4);


INSERT INTO users (username, password, role)
VALUES
    ("user", "$2a$10$0KjO/Sv1fLDOBXkFuTiRCuAjkhrEn/r8a3jXsCZYT.EmorAuLkHXS", "USER"), -- salasana banaani
    ("admin", "$2a$10$/IF1J8rB2gFOokEkh22y/u2ixa4YsxRzEztplp3Ft9uJN8GbDZ3GS", "ADMIN"); -- salasana omena

SELECT * FROM recipe;
SELECT * FROM ingredient;
SELECT * FROM cuisine;
SELECT * FROM users;