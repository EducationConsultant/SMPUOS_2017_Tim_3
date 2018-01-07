angular.module('bioskopApp.BioskopController',[])
.controller('BioskopController', function ($scope, $location, $rootScope, $mdDialog, BioskopService, $localStorage, $mdToast, SalaService) {
	
	
	$scope.prikaziBioskope = function() {
		BioskopService.findAll()
			.success(
				function(data) {
					$scope.listaBioskopa = data;
			})
	}; 
	
	$scope.prikaziBioskope();
	
	
	$scope.obrisiBioskop = function(id){
		
		var confirm = $mdDialog.confirm() 
        .title('Da li ste sigurni da želite obrisati bioskop?')
        .ok('Da')
        .cancel('Ne');
     $mdDialog.show(confirm).then(function() {
        $scope.status = 'Record deleted successfully!';
        var foundElement=-1;
		angular.forEach($scope.listaBioskopa, function(value,index){
			if(value.id==id){
				foundElement=index;
			}
		});
		
		if(foundElement!=-1){
			$scope.listaBioskopa.splice(foundElement,1);
		}
     }, function() {
        $scope.status = 'You decided to keep your record.';
     });
	}
	
	$scope.izmeniBioskop = function(b, e) {
		var temp=angular.copy(b);
		$mdDialog.show({
			locals:{data: temp},
            controller: IzmenaController,
            templateUrl: 'html/izmenaBioskopa.html',
            parent: angular.element(document.body),
            targetEvent: e,
            clickOutsideToClose:false
        });
	}
	
    function IzmenaController($scope, $mdDialog, data) {
    	$scope.menjanBioskop = data;
        $scope.prihvatiIzmenu = function(){
            BisokopService.izmeniBioskop($scope.menjanBioskop).success(function(data){
            	$mdDialog.cancel();
            })
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };
    }
    
    
    
    
    
    
    
    
    $scope.prihvatiIzmenu = function(){
		BioskopService.izmeniBioskop($scope.menjanBioskop).success(function(data){
        	$mdDialog.cancel();
        })
    };

    $scope.cancel = function() {
    	$mdDialog.cancel();
    };
	
  //ocjenjivanje
    idRegUser=function(){
		$scope.isRegular = $localStorage.tip == 'REGKORISNIK';
	}
	
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	$scope.ocjeniBioskop=function(bioskop){
		BioskopService.ocjeniBioskop(bioskop)
			.success(function(data){
			/*	$mdDialog.show (
		                  $mdDialog.alert()
		                     .parent(angular.element(document.querySelector('#dialogContainer')))
		                     .clickOutsideToClose(true)
		                     .title('Uspiješno ste ocjenili bioskop.')
		                     .ok('Ok!')
		               ); */
				 $scope.prikaziIzmjenjenSadrzaj(data);
				 
			});
	}
	
	
	 $scope.prikaziIzmjenjenSadrzaj=function(data){
		 var foundIndex=-1;
		 angular.forEach($scope.listaBioskopa, function(value, index){
			 
			 if(value.id==data.id){
				 foundIndex=index;
			 }
		 });
		 
		 if(foundIndex!=-1){
			 $scope.listaBioskopa.splice(foundIndex,1);
			 $scope.listaBioskopa.splice(foundIndex,0, data);
			 $scope.$applay;
			// $scope.$applay();
		 }
	 }
	idRegUser();
	isAdmin();
	
	$scope.obrisiSalu = function(bioskopId, salaId){
		SalaService.obrisiSalu(bioskopId, salaId)
			.success(function(data){
				var bioskopIndex = 0;
				var salaIndex = 0;
				angular.forEach($scope.listaBioskopa, function(value, index){
					 if(value.id==bioskopId){
						 bioskopIndex=index;	 
					 }
				 });
				
				angular.forEach($scope.listaBioskopa[bioskopIndex].sale, function(value, index){
					 if(value.id==salaId){
						 salaIndex=index;	 
					 }
				 });
				$scope.listaBioskopa[bioskopIndex].sale.splice(salaIndex,1);
			});
	}
	
	$scope.izmeniSalu = function(bioskopId, sala, e) {
		var temp = angular.copy(sala);
		
		$mdDialog.show({
			locals:{sala: temp, bioskopId : bioskopId},
            controller: IzmenaSaleController,
            templateUrl: 'html/izmenaSale.html',
            parent: angular.element(document.body),
            targetEvent: e,
            clickOutsideToClose:false
        })
        .then(function(menjanaSala){

        });
	}
	
	function IzmenaSaleController($scope, $mdDialog, sala, bioskopId) {
		$scope.menjanaSala = sala;
		$scope.menjanaSala.novaOznakaSale = sala.oznakaSale;
		$scope.bioskopId = bioskopId;
		
		$scope.prihvatiIzmenu = function(){
			$scope.menjanaSala.oznakaSale = $scope.menjanaSala.novaOznakaSale;
			SalaService.izmeniSalu($scope.bioskopId, $scope.menjanaSala)
				.success(function(){
					$mdDialog.hide($scope.menjanaSala);
				});
		}
		
        $scope.cancel = function() {
        	$mdDialog.cancel();
        };
	}
});