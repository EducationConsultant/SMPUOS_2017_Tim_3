angular.module('bioskopApp.BioskopController',[])
.controller('BioskopController', function ($scope, $location, $rootScope, $mdDialog, BioskopService, $localStorage, $mdToast, SalaService, $q) {
	
	
	$scope.prikaziBioskope = function() {
		BioskopService.findAll()
			.success(
				function(data) {
					$scope.listaBioskopa = data;
			})
	}; 
	
	$scope.prikaziBioskope();
	
	
	$scope.obrisiBioskop = function(b){
		
		var confirm = $mdDialog.confirm() 
        .title('Da li ste sigurni da želite obrisati bioskop?')
        .ok('Da')
        .cancel('Ne');
     $mdDialog.show(confirm).then(function() {
    	 
    	 var requestPromise = [];
    	 for(var i = 0; i < b.sale.length; i++){
    		 var httpPromise = SalaService.obrisiSalu(b.id, b.sale[i].id).success(function(data){
    			 
    		 })
    		 
    		 requestPromise.push(httpPromise);
    	 }
    	 
    	 $q.all(requestPromise).then(function () {
			 BioskopService.obrisiBioskop(b.id).success(function(data){
		         $scope.status = 'Record deleted successfully!';
		         var foundElement=-1;
				 angular.forEach($scope.listaBioskopa, function(value,index){
				 	if(value.id==b.id){
				 		foundElement=index;
				 	}
				 });
				 
				 if(foundElement!=-1){
				 	$scope.listaBioskopa.splice(foundElement,1);
				 }
			})
		})
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
        }).then(function(menjanBioskop){
        	var bioskopIndex = 0;
        	
			angular.forEach($scope.listaBioskopa, function(value, index){
				 if(value.id==menjanBioskop.id){
					 bioskopIndex=index;	 
				 }
			 });

			$scope.listaBioskopa[bioskopIndex] = menjanBioskop;
        });
	}
	
    function IzmenaController($scope, $mdDialog, data) {
    	$scope.menjanBioskop = data;
    	
        $scope.prihvatiIzmenu = function(){
        	if($scope.menjanBioskop.naziv == null ||
     		   $scope.menjanBioskop.ocena == null ||
     		   $scope.menjanBioskop.adresaBioskopa.nazivNaseljenogMesta == null ||
     		   $scope.menjanBioskop.adresaBioskopa.nazivUlice == null ||
     		   $scope.menjanBioskop.adresaBioskopa.broj == null ||
     		   $scope.menjanBioskop.adresaBioskopa.geoDuzina == null ||
     		   $scope.menjanBioskop.adresaBioskopa.geoSirina == null) 
     		{
     			$mdToast.show(
                   $mdToast.simple()
     		             .textContent('Molimo Vas da popunite sva polja!')
     		             .hideDelay(3000)
     		             .position('top center')
     		             .theme('warning-toast')
     		       );
     			return;
     		}
            BioskopService.izmeniBioskop($scope.menjanBioskop).success(function(data){
            	$mdDialog.hide(data);
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
		var korisnickoIme = $localStorage.logged.korisnickoIme;
		BioskopService.ocjeniBioskop(bioskop, korisnickoIme)
			.success(function(data){
				$mdDialog.show (
		                  $mdDialog.alert()
		                     .parent(angular.element(document.querySelector('#dialogContainer')))
		                     .clickOutsideToClose(true)
		                     .title('Uspiješno ste ocjenili bioskop.')
		                     .ok('Ok!')
		               ); 
				 $scope.prikaziIzmjenjenSadrzaj(data);
				 
			});
	}
	
	  $scope.ocjenjen=function(bioskop){
   		 var indexFound=-1;
   		
   		 angular.forEach(bioskop.ocjene, function(value, index){
   			 if($localStorage.logged.korisnickoIme==value.username){
   				 indexFound=index;
   			 }
   		 });
   		 
   		 if(indexFound!=-1){
   			
   			 return false;
   		 }else{
   			
   			 return true;
   		 }
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
        	var bioskopIndex = 0;
			var salaIndex = 0;
			angular.forEach($scope.listaBioskopa, function(value, index){
				 if(value.id==bioskopId){
					 bioskopIndex=index;	 
				 }
			 });
			
			angular.forEach($scope.listaBioskopa[bioskopIndex].sale, function(value, index){
				 if(value.id==menjanaSala.id){
					 salaIndex=index;	 
				 }
			 });
			$scope.listaBioskopa[bioskopIndex].sale[salaIndex]=menjanaSala;
        });
	}
	
	function IzmenaSaleController($scope, $mdDialog, sala, bioskopId) {
		$scope.menjanaSala = sala;
		$scope.menjanaSala.novaOznakaSale = sala.oznakaSale;
		$scope.bioskopId = bioskopId;
		
		$scope.prihvatiIzmenu = function(){
			if($scope.menjanaSala.oznakaSale == null 
					|| $scope.menjanaSala.oznakaSale == "" 
					|| $scope.menjanaSala.tip == null
					|| $scope.menjanaSala.brojSedistaRedovi == null
					|| $scope.menjanaSala.brojSedistaKolone == null
					|| $scope.menjanaSala.brojSedistaRedovi == 0
					|| $scope.menjanaSala.brojSedistaKolone == 0) 
				{
					$mdToast.show(
			                   $mdToast.simple()
			                      .textContent('Molimo Vas da popunite sva polja!')
			                      .hideDelay(3000)
			                      .position('top center')
			                      .theme('warning-toast')
			         );
					 return;
				}
			
			
			$scope.menjanaSala.oznakaSale = $scope.menjanaSala.novaOznakaSale;
			SalaService.izmeniSalu($scope.bioskopId, $scope.menjanaSala)
				.success(function(data){
					$scope.menjanaSala.kapacitet = 
						$scope.menjanaSala.brojSedistaRedovi 
							* $scope.menjanaSala.brojSedistaKolone;
					$mdDialog.hide($scope.menjanaSala);
				});
		}
		
        $scope.cancel = function() {
        	$mdDialog.cancel();
        };
	}
	
	$scope.dodajSalu = function(bioskop, e) {
		
		$mdDialog.show({
			locals:{bioskop : bioskop},
            controller: DodavanjeSaleController,
            templateUrl: 'html/dodavanjeSale.html',
            parent: angular.element(document.body),
            targetEvent: e,
            clickOutsideToClose:false
        })
        .then(function(bioskopIzmenjen){
        	bioskop=bioskopIzmenjen;
        });
	}
	
	function DodavanjeSaleController($scope, $mdDialog, bioskop) {
		$scope.novaSala = {};
		
		$scope.dodajSalu = function(){
			
			if($scope.novaSala.oznakaSale == null 
				|| $scope.novaSala.oznakaSale == "" 
				|| $scope.novaSala.tip == null
				|| $scope.novaSala.brojSedistaRedovi == null
				|| $scope.novaSala.brojSedistaKolone == null
				|| $scope.novaSala.brojSedistaRedovi == 0
				|| $scope.novaSala.brojSedistaKolone == 0) 
			{
				$mdToast.show(
		                   $mdToast.simple()
		                      .textContent('Molimo Vas da popunite sva polja!')
		                      .hideDelay(3000)
		                      .position('top center')
		                      .theme('warning-toast')
		         );
				 return;
			}
			SalaService.dodajSalu(bioskop.id, $scope.novaSala).success(function(data){
				bioskop.sale.push(data);
				$mdDialog.hide(bioskop);
			});
		}
		
        $scope.cancel = function() {
        	$mdDialog.cancel();
        };
	}
	
	$scope.dodajBioskop = function(e) {
		$mdDialog.show({
            controller: DodavanjeBioskopaController,
            templateUrl: 'html/dodavanjeBioskopa.html',
            parent: angular.element(document.body),
            targetEvent: e,
            clickOutsideToClose:false
        })
        .then(function(noviBioskop){
        	$scope.listaBioskopa.push(noviBioskop);
        });
	}
	
	function DodavanjeBioskopaController($scope, $mdDialog) {
		$scope.noviBioskop = {};
		$scope.noviBioskop.adresaBioskopa = {};
		
		$scope.dodajBioskop = function(){
			if($scope.noviBioskop.naziv == null ||
			   $scope.noviBioskop.ocena == null ||
			   $scope.noviBioskop.adresaBioskopa.nazivNaseljenogMesta == null ||
			   $scope.noviBioskop.adresaBioskopa.nazivUlice == null ||
			   $scope.noviBioskop.adresaBioskopa.broj == null ||
			   $scope.noviBioskop.adresaBioskopa.geoDuzina == null ||
			   $scope.noviBioskop.adresaBioskopa.geoSirina == null) 
			{
				$mdToast.show(
		                   $mdToast.simple()
		                      .textContent('Molimo Vas da popunite sva polja!')
		                      .hideDelay(3000)
		                      .position('top center')
		                      .theme('warning-toast')
		         );
				 return;
			}
			
			
			$scope.noviBioskop.prosecnaOcena = $scope.noviBioskop.ocena;
			$scope.noviBioskop.sumaOcena = $scope.noviBioskop.ocena;
			
			BioskopService.dodajBioskop($scope.noviBioskop).success(function(data){
				$scope.noviBioskop.sale = [];
				$mdDialog.hide(data);
			});
		}
		
        $scope.cancel = function() {
        	$mdDialog.cancel();
        };
        
      
	}
});