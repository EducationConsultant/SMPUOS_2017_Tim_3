angular.module('rezervacijaApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, RezervacijaService, $localStorage, $mdToast) {

    	if($localStorage.curNav == null)
            $scope.currentNavItem = 'Pocetna';
        else
            $scope.currentNavItem = $localStorage.curNav;

        $scope.saveNav = function (data) {
            $localStorage.curNav = data;
        }
    
        
        function RezervacijaController($scope, $mdDialog) {
        	
        };
    });