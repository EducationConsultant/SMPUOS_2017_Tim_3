angular.module('korisnikApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, LoginService, $localStorage, $mdToast) {

        //if($localStorage.curNavK == null)
            $scope.currentNavItemK = 'Pocetna';
        /*else{
            $scope.currentNavItemK = $localStorage.curNavK;
            $location.path("/" + $localStorage.curNavK);
        }*/

        /*$scope.saveNav = function (data) {
            $localStorage.curNavK = data;
        }*/
        
        $scope.currentNavItem1 = 'Korisnici';

        if($localStorage.tip == 'REGKORISNIK'){
            $scope.showRegistrovan = true;
            $scope.showAdministrator = false;
        }else if($localStorage.tip == 'ADMIN'){
            $scope.showRegistrovan = false;
            $scope.showAdministrator = true;
        }

        if($localStorage.tip != null)
            $scope.showOdjaviSe = true;

        $scope.odjaviSe = function () {
        	$scope.user = {korisnickoIme:$localStorage.logged.korisnickoIme, lozinka:$localStorage.logged.lozinka};
            LoginService.logout($scope.user).success(function(data){
                if(data){
                    $localStorage.logged = null;
                    $localStorage.tip = null;
                    $localStorage.curNavK = 'Pocetna';
                    $scope.currentNavItemK = 'Pocetna'
                    $location.path("/");

                    $scope.showOdjaviSe = false;
                    $scope.showRegistrovan = false;
                    $scope.showAdministrator = false;

                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Uspešno ste se odjavili!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('success-toast')
                    );
                }})
                .error(function(data){
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Neuspešna odjava sa sistema!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('error-toast')
                    );
                });
        };

        $scope.openPrijavaDijalog = function(ev) {
            $mdDialog.show({
                controller: LoginController,
                templateUrl: 'html/login.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true
            })
            .then(function(answer) {
                if(answer == 'REGKORISNIK')
                    $scope.showRegistrovan = true;
                else if(answer == 'ADMIN')
                    $scope.showAdministrator = true;

                $scope.showOdjaviSe = true;
            });
        }


        function LoginController($scope, $mdDialog) {
            $scope.user = {korisnickoIme:null, lozinka:null};
            $scope.login = function(){
                LoginService.login($scope.user).success(function(data){
                    if(data){
                    	if(data.statusKorisnika == 'AKTIVIRAN'){
	                        $localStorage.logged = data;
	                        $localStorage.tip = data.tipKorisnika;
	
	                        $mdToast.show(
	                            $mdToast.simple()
	                                .textContent('Uspešno ste se prijavili!')
	                                .hideDelay(3000)
	                                .position('top center')
	                                .theme('success-toast')
	                        );
	
	                        $mdDialog.hide(data.tipKorisnika);
                    	}else{
                    		$mdToast.show(
                                    $mdToast.simple()
                                        .textContent('Nalog nije aktivan!')
                                        .hideDelay(3000)
                                        .position('top center')
                                        .theme('error-toast')
                                );
                    	}
                    }})
                    .error(function(data){
                        $scope.user.lozinka = '';
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('Pogrešno korisničko ime ili lozinka!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('error-toast')
                        );
                    });
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
        
        /*$scope.korisnici = [];
        function getKorisnici(){
        	LoginService.korisnici().success(function (data) {
                $scope.korisnici = data;
            });
        }

        getKorisnici();*/
        
        $scope.openRegistracijaDijalog = function(ev) {
            $mdDialog.show({
                controller: RegistracijaController,
                templateUrl: 'html/registracija.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true
            })
            .then(function(answer) {

            });
        }
        
        function RegistracijaController($scope, $mdDialog) {
            $scope.user = {
            		id:null,
            		ime:null,
            		prezime:null,
            		datumRodjenja:null,
            		pol:null,
            		adresaStanovanja:{
            			id:null,
            			nazivNaseljenogMesta:null,
            			nazivUlice:null,
            			broj:null,
            			geoDuzina:null,
            			geoSirina:null
            		},
            		korisnickoIme:null,
            		lozinka:null,
            		tipKorisnika:null,
            		datumRegistracije:null,
            		statusKorisnika:null,
            		ulogovan:null
            };
            
            $scope.proveraImena = function(ime){
            	LoginService.findByKorisnickoIme(ime).success(function(data){
                    if(data){
                    	$mdToast.show(
                            $mdToast.simple()
                                .textContent('Korisničko ime već postoji!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('error-toast')
                        );
                    }})
            };
         
            $scope.registrovanje = function(){
            	$scope.user.tipKorisnika='REGKORISNIK';
            	$scope.user.datumRegistracije=Date.now();
            	$scope.user.statusKorisnika='AKTIVIRAN';
            	$scope.user.ulogovan=false;
                LoginService.registrovanje($scope.user).success(function(data){
                    if(data){
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('Uspešno ste se registrovali!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('success-toast')
                        );

                        $mdDialog.hide(data.tipKorisnika);
                    }})
                    .error(function(data){
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('Proverite unesene podatke!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('error-toast')
                        );
                    });
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
    });