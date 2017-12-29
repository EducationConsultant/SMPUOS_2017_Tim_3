angular.module('rezervacijaApp.RezervacijaController',[])
    .controller('RezervacijaController', function ($scope, $location, $rootScope, $mdDialog, 
    		RezervacijaService, BioskopService, $localStorage, $mdToast) {
 
		$scope.statusRezervacije="Aktivne";
		$scope.statusFilter="Aktivne";
		$scope.showOtkazane = false;
		$scope.listaAktivnihIliOtkazanihRezervacija = [];
    
		$scope.filter = {};
		$scope.filterRezervacija = [];
		$scope.projekcije = [];
		
    	$scope.prikaziAktivneRezervacije = function() {
			RezervacijaService.pregledAktivnihRezervacija()
				.success(
					function(data) {
						$scope.listaAktivnihIliOtkazanihRezervacija = data;
				})
		};
				

		$scope.prikaziAktivneRezervacije();
		
		$scope.promenaStatusa = function(){
			if($scope.statusRezervacije == "Aktivne"){
        		RezervacijaService.pregledAktivnihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    $scope.showOtkazane = false;
                });
        	}else{
        		RezervacijaService.pregledOtkazanihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    $scope.showOtkazane = true;
                });
        	}
        }
		
		$scope.dobaviBioskope = function(){
			BioskopService.getBioskopi().success(function (data) {
				$scope.bioskopi = data;
			});
		}
		
		$scope.odaberiBioskop = function(){
			BioskopService.getSaleZaBioskop($scope.filter.bioskop).success(function (data) {
				$scope.sale = data;
			});
			$scope.projekcije = [];
			$scope.filterRezervacija = [];
			$scope.filter.datum = null;
			$scope.filter.projekcija = null;
		}
    
		$scope.odaberiSalu = function(){
			RezervacijaService.getProjekcije().success(function(data) {
				$scope.sveProjekcije = data;
			});
			$scope.projekcije = [];
			$scope.filterRezervacija = [];
			$scope.filter.datum = null;
			$scope.filter.projekcija = null;
		}
		
		$scope.odaberiDatum = function(){
			$scope.projekcije = [];
			$scope.filterRezervacija = [];
			$scope.filter.projekcija = null;
			for (i = 0; i < $scope.sveProjekcije.length; i++) { 
				var p = $scope.sveProjekcije[i];
		    	if(p.idBioskopa == $scope.filter.bioskop && p.idSale == $scope.filter.sala){
		    		var dbDate = new Date(p.datumProjekcije);
		    		var filterDate = new Date($scope.filter.datum);
		    		filterDate.setHours(1);
		    		dbDate.setHours(1);
		    		//ovde ce biti potrebno za dbDate podesiti 01:00:00
		    		if(dbDate.getTime()===filterDate.getTime()){
		    			$scope.projekcije.push($scope.sveProjekcije[i]);
		    		}
		    	}
		    }
		}
				
		$scope.promenaStatusaFilter = function(){
			if($scope.filter.projekcija == null)
				return;
			if($scope.statusFilter == "Aktivne"){
				RezervacijaService.getAktivneRezervacijeZaProjekciju($scope.filter.projekcija).success(function(data) {
					$scope.filterRezervacija = data;
				});
        	}else{
        		RezervacijaService.getOtkazaneRezervacijeZaProjekciju($scope.filter.projekcija).success(function(data) {
    				$scope.filterRezervacija = data;
    			});
        	}
        }
		
		$scope.dobaviBioskope();
});