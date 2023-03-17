CREATE DATABASE systinfo;
\c systinfo

CREATE TABLE entreprise(
	id SERIAL PRIMARY KEY NOT NULL,
	nom VARCHAR(30),
	domaine VARCHAR(30),
	siege VARCHAR(20),
	pdg VARCHAR(30),
	nif VARCHAR(10),
	numstat VARCHAR(10),
	statut VARCHAR(20),
	datedebut DATE,
	datefin DATE,
	idDevise int,
	idDeviseEquiv int
);
ALTER TABLE entreprise ADD FOREIGN KEY idDevise REFERENCES devise(id);
ALTER TABLE entreprise ADD FOREIGN KEY idDeviseEquiv REFERENCES devise(id);

INSERT INTO Entreprise VALUES(1,'Entreprise 1','Restauration','Antananarivo','King','12345','1001','SARL','2023-03-16','2024-03-16',1,2);
INSERT INTO Entreprise VALUES(2,'Entreprise 2','Assurance','Antananarivo','Queen','10012','1010','SA','2023-03-16','2024-03-16',1,2);


CREATE TABLE devise(
	id SERIAL,
	nom VARCHAR(30),
	cours double precision
);

INSERT INTO devise VALUES(default, 'Ariary');
INSERT INTO devise VALUES(default, 'USD');
INSERT INTO devise VALUES(default, 'Euro');
INSERT INTO devise VALUES(default, 'Livre Sterling');

CREATE TABLE coursdechange(
	id SERIAL PRIMARY KEY,
	idDevise INT,
	idDeviseEquiv INT,
	taux double precision
);

ALTER TABLE coursdechange ADD FOREIGN KEY idDevise REFERENCES devise(id);
ALTER TABLE coursdechange ADD FOREIGN KEY idDeviseEquiv REFERENCES devise(id);

CREATE TABLE planComptable(
    compte VARCHAR(6) PRIMARY KEY,
    intitule VARCHAR(50),
    idEntreprise int
);

ALTER TABLE planComptable ADD FOREIGN KEY idEntreprise REFERENCES entreprise(id);