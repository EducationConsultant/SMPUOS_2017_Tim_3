angular.module('filmApp.FilmoviService', [])
    .factory('FilmoviService', function($http){
        var factory = {};
        
        factory.pregledFilmova = function () {
            return $http.get('/film-service/film/filmovi');
        }
        
        factory.pregledAktuelnihFilmova = function () {
            return $http.get('/film-service/film/aktuelni');
        }
        
        factory.aktivacija = function (id) {
            return $http.put('/film-service/film/godinaPremijere/' + godina);
        }
        
        factory.brisanje = function (id) {
            return $http.delete('/film-service/film/obrisi/' + id);
        }
        
        factory.getKorisnici = function () {
            return $http.get('/film-service/film/kategorija/'+naziv);
        }
        
       

        return factory;
    });