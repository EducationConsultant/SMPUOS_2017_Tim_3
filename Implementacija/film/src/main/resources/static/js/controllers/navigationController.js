angular.module('filmApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {

        if($localStorage.curNav == null)
            $scope.currentNavItem = 'Pocetna';
        else
            $scope.currentNavItem = $localStorage.curNav;

        $scope.saveNav = function (data) {
            $localStorage.curNav = data;
        }

       
    });

        