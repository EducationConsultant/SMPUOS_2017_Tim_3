angular.module('rezervacijaApp.RezervacijaController',[])
    .controller('RezervacijaController', function ($scope, $location, $rootScope, $mdDialog, 
    		RezervacijaService, BioskopService, KorisnikService, ProjekcijaService, $localStorage, $mdToast) {
 
		$scope.statusRezervacije="Aktivne";
		$scope.statusFilter="Aktivne";
		$scope.statusRezervacijaKorisnika="Aktivne";
		$scope.showOtkazane = false;
		$scope.listaAktivnihIliOtkazanihRezervacija = [];
    
		$scope.filter = {};
		$scope.filterRezervacija = [];
		$scope.filter.brojRedaSedista=1;
		$scope.filter.salaObjekat = null;
		$scope.projekcije = [];
		$scope.listaRezervacijaKorisnika = [];
		$scope.ogranicenjeBrojSedista = 1;
		
		$scope.prikaziRezervacije = function() {
			RezervacijaService.findAll()
				.success(
					function(data) {
						$scope.listaRezervacija = data;
				})
		};
		
		$scope.getKorisnici = function() {
			KorisnikService.getKorisnici().success(function(data) {
				$scope.korisnici = data;
			})
		}
		
    	$scope.prikaziAktivneRezervacije = function() {
			RezervacijaService.pregledAktivnihRezervacija()
				.success(
					function(data) {
						$scope.listaAktivnihIliOtkazanihRezervacija = data;
				})
		};
				
		$scope.prikaziAktivneRezervacijeKorisnika = function() {
			var id=$localStorage.logged.id;
			KorisnikService.getAktivneRezervacijeKorisnika(id).success(function(data){
				$scope.listaRezervacijaKorisnika = data;
			})
		}
		

		$scope.promenaStatusaZaKorisnika = function() {
			if($scope.statusRezervacijaKorisnika == "Aktivne"){
				var id=$localStorage.logged.id;
				KorisnikService.getAktivneRezervacijeKorisnika(id).success(function(data){
					$scope.listaRezervacijaKorisnika = data;
				})
        	}else{
        		var id=$localStorage.logged.id;
    			KorisnikService.getSveRezervacijeKorisnika(id).success(function(data){
    				$scope.listaRezervacijaKorisnika = data;
    			})
        	}
		}
		
		$scope.prikaziRezervacije();
		$scope.getKorisnici();
		$scope.prikaziAktivneRezervacije();
		$scope.prikaziAktivneRezervacijeKorisnika();
		
		$scope.obrisiRezervaciju = function(id) {
			RezervacijaService.obrisiRezervaciju(id).success(function(data){
				$scope.prikaziRezervacije();
			})
		}
		
		$scope.izmeniRezervaciju = function(rez, e) {
			$scope.izmenaRez = rez.projekcija.idBioskopa;
			//alert($scope.filter.bioskop);
			//$scope.filter.sala = rez.projekcija.idSale;
			//$scope.filter.datum = rez.projekcija.datumProjekcije;
			//$scope.filter.film = rez.projekcija.idFilma;
			var temp=angular.copy(rez);
			$mdDialog.show({
				locals:{data: temp},
                controller: IzmenaController,
                templateUrl: 'html/izmenaRezervacije.html',
                parent: angular.element(document.body),
                targetEvent: e,
                clickOutsideToClose:false
            });
		}
		
        function IzmenaController($scope, $mdDialog, data) {
			$scope.iniciraoIzmenuProjekcije = true;
        	$scope.menjanaRezervacija = data;
        	$scope.sveProjekcijeIzmena = [];
        	
        	$scope.projekcijeIzmena = [];
			
        	//Popunjavanje combo box-eva i postavljanje inicijalnih vrednosti
        	BioskopService.getBioskopi().success(function (data) {
				$scope.bioskopiIzmena = data;
			});
        	
			BioskopService.getSaleZaBioskop($scope.menjanaRezervacija.projekcija.idBioskopa).success(function (data) {
				$scope.saleIzmena = data;
			});
			
			RezervacijaService.getProjekcije().success(function(data) {
				$scope.sveProjekcijeIzmena = data;
				for (i = 0; i < $scope.sveProjekcijeIzmena.length; i++) { 
					var p = $scope.sveProjekcijeIzmena[i];
					if(p.idBioskopa == $scope.menjanaRezervacija.projekcija.idBioskopa 
			    			&& p.idSale == $scope.menjanaRezervacija.projekcija.idSale){
			    		var dbDate = new Date(p.datumProjekcije);
			    		var filterDate = new Date($scope.menjanaRezervacija.projekcija.datumProjekcije);
			    		filterDate.setHours(1);
			    		dbDate.setHours(1);
			    		//ovde ce biti potrebno za dbDate podesiti 01:00:00
			    		if(dbDate.getTime()===filterDate.getTime()){
			    			$scope.projekcijeIzmena.push($scope.sveProjekcijeIzmena[i]);
			    		}
			    	}
			    }
			});

			$scope.odaberiBioskopIzmena = function(){
				BioskopService.getSaleZaBioskop($scope.menjanaRezervacija.projekcija.idBioskopa).success(function (data) {
					$scope.saleIzmena = data;
				});
				$scope.menjanaRezervacija.projekcija.idSale = null;
				$scope.menjanaRezervacija.projekcija.datumProjekcije = null;
				$scope.menjanaRezervacija.projekcija.id = null;
				$scope.projekcijeIzmena = [];
			}
			
			$scope.odaberiSaluIzmena = function() {
				$scope.menjanaRezervacija.projekcija.id = null;
				$scope.projekcijeIzmena = [];
				for (i = 0; i < $scope.sveProjekcijeIzmena.length; i++) { 
					var p = $scope.sveProjekcijeIzmena[i];
					if(p.idBioskopa == $scope.menjanaRezervacija.projekcija.idBioskopa 
			    			&& p.idSale == $scope.menjanaRezervacija.projekcija.idSale){
						var dbDate = new Date(p.datumProjekcije);
			    		var filterDate = new Date($scope.menjanaRezervacija.projekcija.datumProjekcije);
			    		filterDate.setHours(1);
			    		dbDate.setHours(1);
			    		//ovde ce biti potrebno za dbDate podesiti 01:00:00
			    		if(dbDate.getTime()===filterDate.getTime()){
			    			$scope.projekcijeIzmena.push($scope.sveProjekcijeIzmena[i]);
			    		}
			    	}
			    }
			}
						
        	$scope.prihvatiIzmenu = function(){
        		RezervacijaService.izmeniRezervaciju($scope.menjanaRezervacija).success(function(data){
                	$mdDialog.cancel();
                })
            };

            $scope.cancel = function() {
            	$mdDialog.cancel();
            };
            
            $scope.izmeniProjekciju = function() {
            	$scope.iniciraoIzmenuProjekcije = true;
            }
        }
		
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
			$scope.filter.salaObjekat = null;
			$scope.ogranicenjeBrojSedista = 1;
			$scope.filter.brojRedaSedista = 1;
		}
    
		$scope.odaberiSalu = function(){
			RezervacijaService.getProjekcije().success(function(data) {
				$scope.sveProjekcije = data;
			});
			$scope.projekcije = [];
			$scope.filterRezervacija = [];
			$scope.filter.datum = null;
			$scope.filter.projekcija = null;
			$scope.filter.brojRedaSedista = 1;
			for(i=0; i<$scope.sale.length; i++){
				if($scope.sale[i].id==$scope.filter.sala){
					$scope.filter.salaObjekat = $scope.sale[i];
					$scope.ogranicenjeBrojSedista = $scope.sale[i].brojSedistaRedovi;
					break;
				}
			}
			
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
		
		$scope.deaktivirajRezervaciju = function(id){
			RezervacijaService.deaktivirajRezervaciju(id).success(function(data){
				for(i=0;i<$scope.listaRezervacijaKorisnika.length;i++){
					var r = $scope.listaRezervacijaKorisnika[i];
					if(r.id==id){
						r.tip='OTKAZANA';
						break;
					}
				}
			})
		}
		
		$scope.pronadjiSlobodnaMesta = function(){
			ProjekcijaService.getBrojZauzetihMestaPoRedu($scope.filter.projekcija, $scope.filter.brojRedaSedista)
				.success(function(data){
					$scope.brojSlobodnihMesta = $scope.filter.salaObjekat.brojSedistaKolone - data;
			});
		}
		
		$scope.rezervisi = function() {
			if($scope.filter.zeljeniBrojSedista == 0 || $scope.filter.zeljeniBrojSedista == null){
				$mdToast.show(
                        $mdToast.simple()
                            .textContent('Unesite broj sedišta!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('warning-toast')
                    );
				return;
			}
			
			
			var rezervacija = {};
			rezervacija.idKorisnika = $localStorage.logged.id;
			rezervacija.datumRezervacije = new Date();
			
			rezervacija.tip = "AKTIVNA";
			rezervacija.brojSedista = $scope.filter.zeljeniBrojSedista;
			rezervacija.brojRedaSedista = $scope.filter.brojRedaSedista;
			
			var p;
			
			for(i=0; i<$scope.projekcije.length; i++){
				if($scope.projekcije[i].id == $scope.filter.projekcija){
					p = $scope.projekcije[i];
					break;
				}
			}
			rezervacija.projekcija = p;
			rezervacija.datumIstekaRezervacije = new Date("2018-12-12"); //???
			
			RezervacijaService.kreirajRezervaciju(rezervacija).success(function(data) {
				$mdToast.show(
                        $mdToast.simple()
                            .textContent('Uspešno kreirana rezervacija!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('success-toast')
                    );
			});
		}
});