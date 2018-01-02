angular.module('filmApp.FilmoviService', [])
    .factory('FilmoviService', function($http){
        var factory = {};
        
        factory.pregledFilmova = function () {
            return $http.get('/film-service/film/filmovi');
        }
        
        factory.pregledDeaktiviranih = function () {
            return $http.get('/film-service/film/aktuelni');
        }
        
        factory.aktivacija = function (id) {
            return $http.put('/film-service/film/godinaPremijere/' + godina);
        }
        
        factory.deaktivacija = function (id) {
            return $http.delete('/film-service/film/obrisi/' + id);
        }
        
        factory.getKorisnici = function () {
            return $http.get('/film-service/film/kategorija/'+naziv);
        }
        
       

        return factory;
    });