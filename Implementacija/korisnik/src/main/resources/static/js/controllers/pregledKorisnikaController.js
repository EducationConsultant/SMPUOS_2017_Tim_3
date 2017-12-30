angular.module('korisnikApp.PregledKorisnikaController',[])
    .controller('PregledKorisnikaController', function ($scope, $location, $rootScope, $mdDialog, KorisniciService, $localStorage, $mdToast) {
    	
    	$scope.korisnici = [];
    	$scope.korisniciPretraga = [];
        function getKorisnici(){
        	KorisniciService.getKorisnici().success(function (data) {
                $scope.korisnici = data;
            });
        }
        getKorisnici();
        $scope.korisnik = null;

        $scope.search = function (user) {
            var searchText = $scope.searchText.toLowerCase();
            fullname = user.ime.toLowerCase() + ' ' + user.prezime.toLowerCase();
            fullnameInverse = user.prezime.toLowerCase() + ' ' + user.ime.toLowerCase();

            if (fullname.indexOf(searchText) != -1) {
                return true;
            }
            if (fullnameInverse.indexOf(searchText) != -1) {
                return true;
            }
            if (user.id.toString().indexOf(searchText) != -1) {
                return true;
            }
            
            return false;
        };

        $scope.getKorisniciPretraga = function(k){
        	if(k != null){
        		$scope.korisniciPretraga = [];
        		$scope.korisniciPretraga.push(k);
        	}else{
        		$scope.korisniciPretraga = [];
        	}
        }
    });