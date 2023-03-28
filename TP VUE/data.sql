CREATE DATABASE tempvue;
\c tempvue

CREATE TABLE Employe(
	NumEmploye SERIAL PRIMARY KEY,
	NomEmploye VARCHAR(20),
	DateNaissance DATE,
	Sexe CHAR,
	DateEmbauche DATE
);

CREATE TABLE Salaire(
	idSalaire SERIAL PRIMARY KEY,
	NumEmploye INT,
	Montant double precision,
	DateDebut DATE,
	DateFin DATE
);

CREATE TABLE Departement(
	NumDept SERIAL PRIMARY KEY,
	NomDept VARCHAR(20)
);

CREATE TABLE DepartementEmploye(
	id SERIAL PRIMARY KEY,
	NumEmploye INT,
	NumDepartement INT,
	DateDebut DATE,
	DateFin DATE,
	isManager INT
);

ALTER TABLE Salaire ADD FOREIGN KEY(NumEmploye) REFERENCES Employe(NumEmploye);
ALTER TABLE DepartementEmploye ADD FOREIGN KEY(NumEmploye) REFERENCES Employe(NumEmploye);
ALTER TABLE DepartementEmploye ADD FOREIGN KEY(NumDepartement) REFERENCES Departement(NumDept);


INSERT INTO Employe VALUES(default,'Antetokounmpo','1993-01-17','M','2018-04-04'),
						(default,'Aliphia','1997-03-15','F','2021-12-29'),
						(default,'Jarvis','1973-06-23','M','2016-03-06'),
						(default,'Chris','1990-12-01','M','2016-03-20'),
						(default,'Scarlett','1985-05-31','F','2021-10-17'),
						(default,'Kaplan','2001-01-11','M','2022-11-25');

INSERT INTO Salaire VALUES(default, 1, 75000, '2018-04-04', '2021-08-30'),
						(default, 1, 110000,'2021-08-31', null),
						(default, 2, 9000, '2021-12-29', null),
						(default, 3, 30000, '2016-03-06', '2018-07-25'),
						(default, 3, 60000, '2018-07-26', '2020-12-31'),
						(default, 3, 90000, '2021-01-01', '2022-06-30'),
						(default, 3, 120000, '2022-07-01', null),
						(default, 4, 15000, '2016-03-20', '2019-07-30'),
						(default, 4, 27000, '2019-07-31', '2021-12-31'),
						(default, 4, 70000, '2023-01-01', null),
						(default, 5, 20000, '2021-10-17', null),
						(default, 6, 9500, '2022-11-25', null);

INSERT INTO Departement VALUES(default, 'Informatique'),
							(default, 'Ressource Humaine'),
							(default, 'Sport');

INSERT INTO DepartementEmploye VALUES(default, 1, 3, '2018-04-04', null, 1),
									(default, 2, 2, '2021-12-29', null, 0),
									(default, 3, 1, '2016-03-06', null, 1),
									(default, 4, 1, '2016-03-20', '2019-07-30', 0),
									(default, 4, 2, '2019-07-31', '2022-12-31', 1),
									(default, 4, 1, '2023-01-01', null, 0),
									(default, 5, 2, '2021-10-17', '2023-01-31', 0),
									(default, 6, 3, '2022-11-25', null, 0);

------------------------------ VIEW ----------------------------------
CREATE OR REPLACE VIEW v_DateDebutPlusRecent AS
SELECT NumEmploye,MAX(DateDebut) DateDebut 
FROM DepartementEmploye 
GROUP BY NumEmploye;

-- 1) Liste employe mbola miasa
CREATE OR REPLACE VIEW v_listEmpActif AS
SELECT * 
FROM Employe 
WHERE NumEmploye IN (SELECT NumEmploye FROM DepartementEmploye WHERE DateFin IS null);

-- 2) Liste employe mbola miasa sy ny dept
CREATE OR REPLACE VIEW v_join_empdept AS
SELECT e.*,d.NomDept,de.DateDebut,de.DateFin,de.isManager
FROM Employe e JOIN DepartementEmploye de
ON e.NumEmploye = de.NumEmploye 
JOIN Departement d ON d.NumDept = de.NumDepartement;

CREATE OR REPLACE VIEW v_listEmpActifDept AS
SELECT * 
FROM v_join_empdept 
WHERE DateFin IS null;

--3)Nb Employe mbola miasa
CREATE OR REPLACE VIEW v_nombreEmpActif AS
SELECT COUNT(*) total
FROM v_listEmpActif;

--4) Liste employe tsy miasa ao intsony (mampiasa left join)
CREATE OR REPLACE VIEW v_listEmpQuitte AS
SELECT *
FROM Employe
WHERE NumEmploye NOT IN (SELECT NumEmploye FROM v_listEmpActif);

--5) nb emp masculin et feminin par dept (apres mbl miasa)
CREATE OR REPLACE VIEW v_nbMascFemParDept AS
SELECT COUNT()
FROM 
--6) nom emp miasa qui a le salaire plus elevee

--7) nom employe qui a eu le plus de changement de salaire

--8) nom employe qui a le plus d'augmentation de salaire en % (sal debut par rpprt sal fin)

--9) total salaire par an

--10) total salaire par an par dept