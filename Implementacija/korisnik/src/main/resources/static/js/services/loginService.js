angular.module('korisnikApp.LoginService', [])
    .factory('LoginService', function($http){
        var factory = {};

        factory.login = function (tempUser) {
            return $http.put('/korisnik-service/korisnik/login', tempUser);
        }
        
        factory.logout = function (tempUser) {
            return $http.put('/korisnik-service/korisnik/logout', tempUser);
        }
        
        factory.registrovanje = function (tempUser) {
            return $http.post('/korisnik-service/korisnik/registracija', tempUser);
        }

        return factory;
    });