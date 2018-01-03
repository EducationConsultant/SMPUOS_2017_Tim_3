angular.module('korisnikApp.KorisniciController',[])
    .controller('KorisniciController', function ($scope, $location, $rootScope, $mdDialog, KorisniciService, $localStorage, $mdToast) {
    	
    	if($localStorage.tip != "ADMIN")
            $location.path("/");
    	
    	$scope.statusKorisnika = "Aktivirani";
    	$scope.showAktivirani = function(k){
    		if($scope.statusKorisnika == "Aktivirani" && $localStorage.logged.korisnickoIme != k.korisnickoIme)
    			return true;
    		else
    			return false;
    	}
    	$scope.showDeaktivirani = false;
    	
    	$scope.korisnici = [];
        function pregledAktivnih(){
        	KorisniciService.pregledAktivnih().success(function (data) {
                $scope.korisnici = data;
            });
        }

        pregledAktivnih();
        
        $scope.promenaStatusa = function(){
        	if($scope.statusKorisnika == "Aktivirani"){
        		KorisniciService.pregledAktivnih().success(function (data) {
                    $scope.korisnici = data;
                    $scope.showDeaktivirani = false;
                });
        	}else{
        		KorisniciService.pregledDeaktiviranih().success(function (data) {
                    $scope.korisnici = data;
                    $scope.showDeaktivirani = true;
                });
        	}
        }
        
        $scope.aktivacija = function(k){
        	var index = $scope.korisnici.indexOf(k);
        	KorisniciService.aktivacija(k.id).success(function (data) {
        		if(index > -1)
                    $scope.korisnici.splice(index, 1);
            });
        }
        
        $scope.deaktivacija = function(k){
        	var index = $scope.korisnici.indexOf(k);
        	KorisniciService.deaktivacija(k.id).success(function (data) {
        		if(index > -1)
                    $scope.korisnici.splice(index, 1);
            });
        }
    });