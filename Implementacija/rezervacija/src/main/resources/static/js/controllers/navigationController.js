angular.module('rezervacijaApp.NavigationController', []).controller(
		'NavigationController',
		function($scope, $location, $rootScope, $mdDialog, RezervacijaService,
				$localStorage, $mdToast) {

			$scope.currentNavItem1 = 'Rezervacije';
			if ($localStorage.curNav == null){

		        	$scope.currentNavItem = 'Pocetna';
			}
			else
				$scope.currentNavItem = $localStorage.curNav;

			$scope.saveNav = function(data) {
				$localStorage.curNav = data;
			}

			if($localStorage.tip == 'REGKORISNIK'){
	            $scope.showRegistrovan = true;
	            $scope.showAdministrator = false;
	        }else if($localStorage.tip == 'ADMIN'){
	            $scope.showRegistrovan = false;
	            $scope.showAdministrator = true;
	        }
			
		});