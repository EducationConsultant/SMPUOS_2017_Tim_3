angular.module('rezervacijaApp.NavigationController', []).controller(
		'NavigationController',
		function($scope, $location, $rootScope, $mdDialog, RezervacijaService,
				$localStorage, $mdToast) {

			$scope.currentNavItem1 = 'Rezervacije';
			//if ($localStorage.curNavR == null){

		        	$scope.currentNavItemR = 'Pocetna';
			//}
			//else
			//	$scope.currentNavItemR = $localStorage.curNavR;

			/*$scope.saveNav = function(data) {
				$localStorage.curNavR = data;
			}*/

			if($localStorage.tip == 'REGKORISNIK'){
	            $scope.showRegistrovan = true;
	            $scope.showAdministrator = false;
	        }else if($localStorage.tip == 'ADMIN'){
	            $scope.showRegistrovan = false;
	            $scope.showAdministrator = true;
	        }
			
		});