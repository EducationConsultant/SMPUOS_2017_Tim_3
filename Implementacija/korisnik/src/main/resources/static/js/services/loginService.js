angular.module('korisnikApp.LoginService', [])
    .factory('LoginService', function($http){
        var factory = {};

        factory.login = function (tempUser) {
            return $http.post('/login', tempUser);
        }

        return factory;
    });