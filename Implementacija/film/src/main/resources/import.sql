insert into jezik (jezik_id,naziv) values (1, 'srpski');
insert into jezik (jezik_id,naziv) values (2, 'engleski');
insert into jezik (jezik_id,naziv) values (3, 'nemacki');
insert into jezik (jezik_id,naziv) values (4, 'spanski');
insert into jezik (jezik_id,naziv) values (5, 'italijanski');
insert into jezik (jezik_id,naziv) values (6, 'francuski');

insert into reditelj (reditelj_id,ime,prezime) values (1,'Michael','Gracey');
insert into reditelj (reditelj_id,ime,prezime) values (2,'Jake','Kasdan');
insert into reditelj (reditelj_id,ime,prezime) values (3,'Aaron','Sorkin');
insert into reditelj (reditelj_id,ime,prezime) values (4,'Amanda','Sthers');
insert into reditelj (reditelj_id,ime,prezime) values (5,'M. Najt','Šamalan');
insert into reditelj (reditelj_id,ime,prezime) values (6,'Srdjan','Dragojevic');
insert into reditelj (reditelj_id,ime,prezime) values (7,'Carlos','Saldanha');


insert into glumac (glumac_id,ime,prezime) values (1,'Jessica','Chastain');
insert into glumac (glumac_id,ime,prezime) values (2,'Idris','Elba');
insert into glumac (glumac_id,ime,prezime) values (3,'Kevin','Costner');
insert into glumac (glumac_id,ime,prezime) values (4,'Toni','Collette');
insert into glumac (glumac_id,ime,prezime) values (5,'Harvey','Keitel');
insert into glumac (glumac_id,ime,prezime) values (6,'Rossy','de Palma');
insert into glumac (glumac_id,ime,prezime) values (7,'Nikola','Kojo');
insert into glumac (glumac_id,ime,prezime) values (8,'Srdjan','Todorovic');
insert into glumac (glumac_id,ime,prezime) values (9,'Uros','Djuric');
insert into glumac (glumac_id,ime,prezime) values (10,'Zoran','Cvijanovic');
insert into glumac (glumac_id,ime,prezime) values (11,'Aleksandar','Radojičić');
insert into glumac (glumac_id,ime,prezime) values (12,'Tamara','Krcunović');
insert into glumac (glumac_id,ime,prezime) values (13,'Ivona','Rambosek');



insert into kategorija (kategorija_id,naziv) values (1,'akcija');
insert into kategorija (kategorija_id,naziv) values (2,'avantura');
insert into kategorija (kategorija_id,naziv) values (3,'animirani');
insert into kategorija (kategorija_id,naziv) values (4,'za decu');
insert into kategorija (kategorija_id,naziv) values (5,'komedija');
insert into kategorija (kategorija_id,naziv) values (6,'krimi');
insert into kategorija (kategorija_id,naziv) values (7,'dokumentarni');
insert into kategorija (kategorija_id,naziv) values (8,'drama');
insert into kategorija (kategorija_id,naziv) values (9,'naucna fantastika');
insert into kategorija (kategorija_id,naziv) values (10,'horor');
insert into kategorija (kategorija_id,naziv) values (11,'mjuzikl');
insert into kategorija (kategorija_id,naziv) values (12,'misterija');
insert into kategorija (kategorija_id,naziv) values (13,'romansa');
insert into kategorija (kategorija_id,naziv) values (14,'triler');
insert into kategorija (kategorija_id,naziv) values (15,'vestern');

insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (1, 0, STR_TO_DATE('10-21-2008 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film1', 0, 'Film1 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (2, 0, STR_TO_DATE('12-11-2010 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film2', 0, 'Film2 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (3, 0, STR_TO_DATE('02-08-2007 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film3', 0, 'Film3 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (4, 0, STR_TO_DATE('01-01-2018 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film4', 0, 'Film4 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (5, 0, STR_TO_DATE('01-01-2018 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film5', 0, 'Film5 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (6, 0, STR_TO_DATE('01-01-2018 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film6', 0, 'Film6 opis', 0, 0, 30, 1, 1, 1);
insert into film (film_id, brojac, datum_premijere, naziv, ocena, opis, prosecna_ocena, suma_ocena, trajanje, jezik_id, kategorija_id, reditelj_id ) values (7, 0, STR_TO_DATE('01-01-2018 00:00:00','%m-%d-%Y %H:%i:%s'), 'Film7', 0, 'Film7 opis', 0, 0, 30, 1, 1, 1);

insert into film_glumci(film_id, glumac_id) values (1,1);
insert into film_glumci(film_id, glumac_id) values (1,2);
insert into film_glumci(film_id, glumac_id) values (1,3);
insert into film_glumci(film_id, glumac_id) values (2,1);
insert into film_glumci(film_id, glumac_id) values (2,4);
insert into film_glumci(film_id, glumac_id) values (3,1);
insert into film_glumci(film_id, glumac_id) values (3,3);
insert into film_glumci(film_id, glumac_id) values (3,4);