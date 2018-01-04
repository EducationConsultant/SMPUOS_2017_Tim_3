angular.module('filmApp.FilmoviService', [])
    .factory('FilmoviService', function($http){
        var factory = {};
        
        factory.pregledFilmova = function () {
            return $http.get('/film-service/film/filmovi');
        }
        
        factory.pregledAktuelnihFilmova = function () {
            return $http.get('/film-service/film/aktuelni');
        }
        
        factory.pregledPoGodiniPremijere = function (id) {
            return $http.put('/film-service/film/godinaPremijere/' + godina);
        }
        
        factory.brisanje = function (id) {
            return $http.delete('/film-service/film/obrisi/' + id);
        }
        
        factory.filmoviPoKategorijama = function (naziv) {
            return $http.get('/film-service/film/kategorija/'+naziv);
        }
        factory.filmoviPoReditelju= function(ime, prezime){
        	 return $http.get('/film-service/film/reditelj/'+ime+prezime);
        }
        factory.dodavanjeFilma = function(noviFilm){
        	return $http.post('/film-service/film/dodaj', noviFilm);
        }
        
       factory.jezici=function(){
    	   return $http.get("/film-service/filmPomocni/jezici");
       }
       
       factory.kategorije=function(){
    	   return $http.get("/film-service/filmPomocni/kategorije")
       }
       
       factory.reditelji=function(){
    	   return $http.get("/film-service/filmPomocni/reditelji")
       }

       factory.glumci=function(){
    	   return $http.get("/film-service/filmPomocni/glumci")
       }
        return factory;
    });