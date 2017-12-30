angular.module('korisnikApp.KorisniciService', [])
    .factory('KorisniciService', function($http){
        var factory = {};
        
        factory.pregledAktivnih = function () {
            return $http.get('/korisnik-service/korisnik/pregledAktivnih');
        }
        
        factory.pregledDeaktiviranih = function () {
            return $http.get('/korisnik-service/korisnik/pregledDeaktiviranih');
        }
        
        factory.aktivacija = function (id) {
            return $http.put('/korisnik-service/korisnik/aktivacija/' + id);
        }
        
        factory.deaktivacija = function (id) {
            return $http.put('/korisnik-service/korisnik/deaktivacija/' + id);
        }
        
        factory.getKorisnici = function () {
            return $http.get('/korisnik-service/korisnik/korisnici');
        }

        return factory;
    });