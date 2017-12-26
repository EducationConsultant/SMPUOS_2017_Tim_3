angular.module('korisnikApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, LoginService, $localStorage, $mdToast) {

        if($localStorage.curNav == null)
            $scope.currentNavItem = 'Pocetna';
        else
            $scope.currentNavItem = $localStorage.curNav;
    });