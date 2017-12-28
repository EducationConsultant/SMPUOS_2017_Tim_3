angular.module('korisnikApp.LoginService', [])
    .factory('LoginService', function($http){
        var factory = {};

        factory.login = function (tempUser) {
            return $http.put('/korisnik-service/korisnik/login', tempUser);
        }

        return factory;
    });