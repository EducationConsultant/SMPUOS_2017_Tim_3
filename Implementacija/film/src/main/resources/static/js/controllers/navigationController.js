angular.module('filmApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, $localStorage, $mdToast) {

        if($localStorage.curNav == null)
            $scope.currentNavItem = 'Pocetna';
        else
            $scope.currentNavItem = $localStorage.curNav;

        $scope.saveNav = function (data) {
        	alert("Value "+data)
            $localStorage.curNav = data;
        }

       
    });

        