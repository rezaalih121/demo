INSERT INTO `pays` (`nom`)  VALUES('FRANCE'),('USA'), ('IRAN'), ('GERMANY'), ('INDIA');

INSERT INTO `competence` (`nom`)  VALUES('JAVA'),('C#'), ('JavaScript'), ('SQL'), ('Angular');

INSERT INTO `utilisateur` (`firstname`, `lastname` , `pays_id`)  VALUES('Reza ALi' , 'Ir' , 3),('SMITH', 'Steeve' ,1), ('SNOW', 'Jon' ,3), ('DOE', 'John',4);


INSERT INTO `utilisateur_competence` (`utilisateur_id` , `competence_id`)  VALUES(1 , 1) , (1 , 2), (1 , 3), (1 , 4), (2 , 3), (2 , 4), (3 , 1), (3 , 2), (4 , 1), (4 , 3), (4 , 4);
