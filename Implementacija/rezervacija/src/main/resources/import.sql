insert into projekcija
('projekcija_id','broj_aktivnih_rezervacija','broj_otkazanih_rezervacija','datum_projekcije',
'id_bioskopa','id_filma','id_sale','naziv_bioskopa','naziv_filma','oznaka_sale')
VALUES
(1,0,0,'2012-01-01',1,1,1,'Bioskop za mlade','Ferdinand - Sinh','Sala 1');


insert into rezervacija
('rezervacija_id','broj_reda_sedista','broj_sedista','datum_isteka_rezervacije','datum_rezervacije',
'id_korisnika','tip','projekcija_id')
VALUES
(1,1,1,'2012-01-01',STR_TO_DATE('12-01-2016 00:00:00','%m-%d-%Y %H:%i:%s'), 1,'AKTIVNA', 1);
