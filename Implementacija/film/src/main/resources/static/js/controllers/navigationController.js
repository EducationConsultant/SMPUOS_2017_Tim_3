angular.module('filmApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {

        //if($localStorage.curNavF == null)
            $scope.currentNavItemF = 'Pocetna';
        //else{
            //$scope.currentNavItemF = $localStorage.curNavF;

        /*$scope.saveNav = function (data) {
            $localStorage.curNavF = data;
        }*/

        $scope.isAdmin=false;
        $scope.currentNavItem1 = 'Filmovi';
        isAdmin=function(){
    		$scope.isAdmin= $localStorage.tip =='ADMIN';
    		var value=$scope.isAdmin;
    	};
    	isAdmin();
    });

        