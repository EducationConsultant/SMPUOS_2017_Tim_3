INSERT INTO `db_bioskop`.`adresa`
(`adresa_id`,`broj`,`geo_duzina`,`geo_sirina`,`naziv_naseljenog_mesta`,`naziv_ulice`)
VALUES
(1,1,12.34,234.5,'Beograd','Pupinova');


INSERT INTO `db_bioskop`.`adresa`
(`adresa_id`,`broj`,`geo_duzina`,`geo_sirina`,`naziv_naseljenog_mesta`,`naziv_ulice`)
VALUES
(2,12,122.34,734.5,'Novi Sad','Balzakova');

INSERT INTO `db_bioskop`.`bioskop`
(`bioskop_id`,`brojac`,`naziv`,`ocena`,`prosecna_ocena`,`suma_ocena`,`adresa_bioskopa_id`)
VALUES(1, 1, 'Bioskop za mlade', 8, 8, 8, 1);

INSERT INTO `db_bioskop`.`sala`
(`sala_id`,`broj_sedista_kolone`,`broj_sedista_redovi`,`kapacitet`,`oznaka_sale`,`tip`,`bioskop_id`)
VALUES(1,12,13,25,'Sala 1', 'OBICNA', 1);


INSERT INTO `db_bioskop`.`sala`
(`sala_id`,`broj_sedista_kolone`,`broj_sedista_redovi`,`kapacitet`,`oznaka_sale`,`tip`,`bioskop_id`)
VALUES(2,12,13,25,'Sala 2', 'OBICNA', 1);



