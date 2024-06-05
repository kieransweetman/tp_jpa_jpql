--
-- Table structure for table "acteur"
--

DROP TABLE IF EXISTS "acteur";
CREATE TABLE "acteur" (
  "id" SERIAL PRIMARY KEY,
  "anniversaire" DATE,
  "href" VARCHAR(255),
  "id_imdb" VARCHAR(255),
  "identite" VARCHAR(255)
);


--
-- Table structure for table "film"
--

DROP TABLE IF EXISTS "film";
CREATE TABLE "film" (
  "id" SERIAL PRIMARY KEY,
  "annee" INTEGER,
  "id_imdb" VARCHAR(255),
  "imdb_href" VARCHAR(255),
  "nom" VARCHAR(255),
  "traite" CHAR(1) NOT NULL DEFAULT 'N'
);


--
-- Table structure for table "film_genre"
--

DROP TABLE IF EXISTS "film_genre";
CREATE TABLE "film_genre" (
  "id_film" INTEGER NOT NULL,
  "id_genre" INTEGER NOT NULL
);


--
-- Table structure for table "film_pays"
--

DROP TABLE IF EXISTS "film_pays";
CREATE TABLE "film_pays" (
  "id_film" INTEGER NOT NULL,
  "id_pays" INTEGER NOT NULL
);


--
-- Table structure for table "film_realisateur"
--

DROP TABLE IF EXISTS "film_realisateur";
CREATE TABLE "film_realisateur" (
  "id_film" INTEGER NOT NULL,
  "id_realisateur" INTEGER NOT NULL
);


--
-- Table structure for table "genre"
--

DROP TABLE IF EXISTS "genre";
CREATE TABLE "genre" (
  "id" SERIAL PRIMARY KEY,
  "nom" VARCHAR(100) NOT NULL
);


--
-- Table structure for table "pays"
--

DROP TABLE IF EXISTS "pays";
CREATE TABLE "pays" (
  "id" SERIAL PRIMARY KEY,
  "nom" VARCHAR(100) NOT NULL
);


--
-- Table structure for table "realisateur"
--

DROP TABLE IF EXISTS "realisateur";
CREATE TABLE "realisateur" (
  "id" SERIAL PRIMARY KEY,
  "anniversaire" DATE,
  "href" VARCHAR(255) NOT NULL,
  "id_imdb" VARCHAR(255) NOT NULL,
  "identite" VARCHAR(255) NOT NULL
);


--
-- Table structure for table "role"
--

DROP TABLE IF EXISTS "role";
CREATE TABLE "role" (
  "id" SERIAL PRIMARY KEY,
  "nom" VARCHAR(255),
  "id_acteur" INTEGER,
  "id_film" INTEGER,
  CONSTRAINT "FKqci566nxqijcpn1fxa1mfrj60" FOREIGN KEY ("id_acteur") REFERENCES "acteur" ("id"),
  CONSTRAINT "FKdphj0qhdfu7vg8lr42x6vq71h" FOREIGN KEY ("id_film") REFERENCES "film" ("id")
);
