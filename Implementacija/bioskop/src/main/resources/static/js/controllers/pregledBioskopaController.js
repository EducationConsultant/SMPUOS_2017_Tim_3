angular.module('bioskopApp.PregledBioskopaController',[])
    .controller('PregledBioskopaController', function ($scope, $location, $rootScope, $mdDialog, BioskopService, $localStorage, $mdToast) {
    	
    	$scope.bioskopi = [];
    	$scope.bioskopiPretraga = [];
        function prikaziBioskope(){
        	BioskopService.findAll().success(function (data) {
                $scope.bioskopi = data;
            });
        }
        
        prikaziBioskope(); 
        
        $scope.bioskop = null;

        $scope.search = function (bioskop) {
            var searchText = $scope.searchText.toLowerCase();
            name = bioskop.naziv.toLowerCase();

            if (name.indexOf(searchText) != -1) {
                return true;
            }
            
            if (bioskop.id.toString().indexOf(searchText) != -1) {
                return true;
            }
            
            return false;
        };

        $scope.prikaziBioskopePretraga = function(b){
        	if(b != null){
        		$scope.bioskopiPretraga = [];
        		$scope.bioskopiPretraga.push(b);
        	}else{
        		$scope.bioskopiPretraga = [];
        	}
        }
        
        
    });