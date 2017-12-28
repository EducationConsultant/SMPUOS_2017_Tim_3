# Projektovanje i implementacija sistema za podršku rada objekata bioskopa 
## Podsistem za rezervaciju karata
###### SMPUOS 2017 Tim 3

## Sadržaj
Repozitorijum sadrži projekat i implementaciju podsistema za rezervaciju karata koji obuhvata funkcionalnosti rezervacije karata za pojedinačne projekcije filmova.

Projekat sadrži:
- UML dijagrame slučajeva korišćenja,
- UML dijagrame klasa,
- BMPN dijagrame.

Implementacija sadrži sledeće funkcionalnosti:
- evidencija korisnika,
- evidencija bioskopa,
- evidencija filmova,
- rezervacija karata.

## Implementacija
Realizacija je izvršena korišćenjem REST mikroservisnih softverskih arhitektura. Za implementaciju pojedinačnih mikroservisa korišćeni su:
- Programski jezik: Java
- Razvojna okruženja i alati: Spring, Spring Data, Spring Cloud i Netflix OSS
- SUBP: MySQL
- Komunikacija: HTTP protokol i REST API

## Pokretanje modela i aplikacije
Modeli su rađeni u aplikacijama:
- PowerDesigner 15: UML dijagrami slučajeva korišćenja i UML dijagrami klasa
- Yaoqiang 5.3.12: BPMN dijagrami

Aplikacija je implementirana u Eclipse Java EE Neon.3 Release 4.6.3 sa Spring Tool Suite 3.9.2 za Windows. Korišćen je MySQL 6.3.9 sistem za upravljanje bazom podataka.

Pre pokretanja aplikacije, potrebno je dodati sledeće šeme u MySQL bazu podataka: db_korisnik, db_rezervacija, db_film, db_bioskop.

Neophodno je obezbediti Internet konekciju i pokrenuti servise aplikacije u sledećem redosledu:
1. EurekaService
2. ZuulService
3. ConfigurationService
4. Korisnik, Bioskop, Film
5. Rezervacija
