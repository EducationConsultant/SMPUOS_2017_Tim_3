angular.module('rezervacijaApp.RezervacijaController',[])
    .controller('RezervacijaController', function ($scope, $location, $rootScope, $mdDialog, $window,
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
		
		/**Prikazuje sve rezervacije i dodaje korisnicka imena*/
		$scope.prikaziRezervacije = function() {
			RezervacijaService.findAll()
				.success(function(data) {
						$scope.listaRezervacija = data;
						
						KorisnikService.getKorisnici().success(function(data) {
							$scope.korisnici = data;
							for(i=0; i<$scope.listaRezervacija.length; i++){
								for(j=0; j<$scope.korisnici.length; j++){
									var r = $scope.listaRezervacija[i];
									if(r.idKorisnika == $scope.korisnici[j].id){
										$scope.listaRezervacija[i].korisnickoIme = 
											$scope.korisnici[j].korisnickoIme;
									}
								}
							}
						})
				})
		};
		
		/**Prikazuje sve aktivne rezervacije i dodaje korisnicka imena*/
    	$scope.prikaziAktivneRezervacije = function() {
    		
			RezervacijaService.pregledAktivnihRezervacija().success(function(data) {
						
				$scope.listaAktivnihIliOtkazanihRezervacija = data;
				KorisnikService.getKorisnici().success(function(data) {
					$scope.korisnici = data;
					for(i=0; i<$scope.listaAktivnihIliOtkazanihRezervacija.length; i++){
						for(j=0; j<$scope.korisnici.length; j++){
							var r = $scope.listaAktivnihIliOtkazanihRezervacija[i];
							if(r.idKorisnika == $scope.korisnici[j].id){
								$scope.listaAktivnihIliOtkazanihRezervacija[i].korisnickoIme = 
									$scope.korisnici[j].korisnickoIme;
							}
						}
					}
				})
			})
		};
		
		/**Klik na radio dugme na pregledu aktivnih i otkazanih*/
		$scope.promenaStatusa = function(){
			if($scope.statusRezervacije == "Aktivne"){
        		RezervacijaService.pregledAktivnihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    KorisnikService.getKorisnici().success(function(data) {
						$scope.korisnici = data;
						for(i=0; i<$scope.listaAktivnihIliOtkazanihRezervacija.length; i++){
							for(j=0; j<$scope.korisnici.length; j++){
								var r = $scope.listaAktivnihIliOtkazanihRezervacija[i];
								if(r.idKorisnika == $scope.korisnici[j].id){
									$scope.listaAktivnihIliOtkazanihRezervacija[i].korisnickoIme = 
										$scope.korisnici[j].korisnickoIme;
								}
							}
						}
					})
                    $scope.showOtkazane = false;
                });
        	}else{
        		RezervacijaService.pregledOtkazanihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    KorisnikService.getKorisnici().success(function(data) {
						$scope.korisnici = data;
						for(i=0; i<$scope.listaAktivnihIliOtkazanihRezervacija.length; i++){
							for(j=0; j<$scope.korisnici.length; j++){
								var r = $scope.listaAktivnihIliOtkazanihRezervacija[i];
								if(r.idKorisnika == $scope.korisnici[j].id){
									$scope.listaAktivnihIliOtkazanihRezervacija[i].korisnickoIme = 
										$scope.korisnici[j].korisnickoIme;
								}
							}
						}
					})
                    $scope.showOtkazane = true;
                });
        	}
        }
		
		$scope.prikaziAktivneRezervacijeKorisnika = function() {
			var id=$localStorage.logged.id;
			
			KorisnikService.getAktivneRezervacijeKorisnika(id).success(function(data){
				$scope.listaRezervacijaKorisnika = data;
				
				if($scope.listaRezervacijaKorisnika.length > 0){
					$scope.korisnikImaRezervacija = true;
				} else {
					$scope.korisnikImaRezervacija = false;
				}
			})
		}
		
		/**Klik na radio dugme kod rezervacija korisnika*/
		$scope.promenaStatusaZaKorisnika = function() {
			if($scope.statusRezervacijaKorisnika == "Aktivne"){
				var id=$localStorage.logged.id;
				KorisnikService.getAktivneRezervacijeKorisnika(id).success(function(data){
					$scope.listaRezervacijaKorisnika = data;
				})
        	} else {
        		var id=$localStorage.logged.id;
    			KorisnikService.getSveRezervacijeKorisnika(id).success(function(data){
    				$scope.listaRezervacijaKorisnika = data;
    			})
        	}
		}
		
		$scope.prikaziRezervacije();
		$scope.prikaziAktivneRezervacije();
		$scope.prikaziAktivneRezervacijeKorisnika();
		
		
		/**Funkcije za filtriranje kod prikaza po projekciji*/
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
			
			$scope.filterRezervacija = [];
			$scope.projekcije = [];
			$scope.filter.projekcija = null;
			$scope.filter.datum = null;
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

			$scope.filter.projekcija = null;
			$scope.projekcije = [];
			$scope.filterRezervacija = [];
			
			for (i = 0; i < $scope.sveProjekcije.length; i++) { 
				var p = $scope.sveProjekcije[i];
		    	if(p.idBioskopa == $scope.filter.bioskop && p.idSale == $scope.filter.sala){
		    		var dbDate = new Date(p.datumProjekcije);
		    		var filterDate = new Date($scope.filter.datum);
		    		filterDate.setHours(1);
		    		dbDate.setHours(1);
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
				RezervacijaService.getAktivneRezervacijeZaProjekciju($scope.filter.projekcija)
				.success(function(data) {
					$scope.filterRezervacija = data;
				});
        	} else {
        		RezervacijaService.getOtkazaneRezervacijeZaProjekciju($scope.filter.projekcija)
        		.success(function(data) {
    				$scope.filterRezervacija = data;
    			});
        	}
        }
		
		$scope.izmeniRezervaciju = function(rez, e) {
			var temp=angular.copy(rez);
			$scope.izmenaRez = rez.projekcija.idBioskopa;
			
			$mdDialog.show({
				locals:{data: temp},
                controller: IzmenaController,
                templateUrl: 'html/izmenaRezervacije.html',
                parent: angular.element(document.body),
                targetEvent: e,
                clickOutsideToClose:false
            })
            .then(function(menjanaRezervacija){
            	
            	if(menjanaRezervacija != null) {
            		for(i=0; i<$scope.korisnici.length; i++){
						if(menjanaRezervacija.idKorisnika == $scope.korisnici[i].id){
							menjanaRezervacija.korisnickoIme = 
								$scope.korisnici[i].korisnickoIme;
						}
					}
            		
    				for(i=0; i<$scope.bioskopi.length;i++){
    					if($scope.bioskopi[i].id == menjanaRezervacija.projekcija.idBioskopa){
    						menjanaRezervacija.projekcija.nazivBioskopa = 
    							$scope.bioskopi[i].naziv;
    					}
    				}
    				
    				BioskopService.getSaleZaBioskop(menjanaRezervacija.projekcija.idBioskopa)
    				.success(function (data) {
    					$scope.saleKodIzmene = data;
        				for(i=0; i<$scope.saleKodIzmene.length;i++){
        					if($scope.saleKodIzmene[i].id == menjanaRezervacija.projekcija.idSale){
        						menjanaRezervacija.projekcija.oznakaSale = 
        							$scope.saleKodIzmene[i].oznakaSale;
        					}
        				}
    				});

					for(i=0; i<$scope.listaRezervacija.length; i++){
            			if($scope.listaRezervacija[i].id == menjanaRezervacija.id){
            				var date = new Date(menjanaRezervacija.projekcija.datumProjekcije);
            				var year = date.getFullYear();
            				
            				var month = (1 + date.getMonth()).toString();
            				month = month.length > 1 ? month : '0' + month;

            				var day = date.getDate().toString();
            				day = day.length > 1 ? day : '0' + day;
            				var dateFormatted = year+'-'+month+'-'+day;
            				
            				var date2 = new Date(menjanaRezervacija.datumIstekaRezervacije);
            				var year2 = date2.getFullYear();
            				
            				var month2 = (1 + date2.getMonth()).toString();
            				month2 = month2.length > 1 ? month2 : '0' + month2;

            				var day2 = date2.getDate().toString();
            				day2 = day2.length > 1 ? day2 : '0' + day2;
            				var dateFormatted2 = year2+'-'+month2+'-'+day2;
            				
            				menjanaRezervacija.projekcija.datumProjekcije = dateFormatted;
            				menjanaRezervacija.datumIstekaRezervacije = dateFormatted2;
            				$scope.listaRezervacija[i] = menjanaRezervacija;
            				break;
            			}
            		}
            	}
            });
		}
		

        function IzmenaController($scope, $mdDialog, data) {
        	
        	$scope.menjanaRezervacija = data;
        	$scope.projekcijeIzmena = [];
        	$scope.salaObjekatIzmena = {};
        	$scope.sveProjekcijeIzmena = [];
        	$scope.iniciraoIzmenuProjekcije = true;
        	
        	//Popunjavanje combo box-eva i postavljanje inicijalnih vrednosti
        	BioskopService.getBioskopi().success(function (data) {
				$scope.bioskopiIzmena = data;
			});
        	
			BioskopService.getSaleZaBioskop($scope.menjanaRezervacija.projekcija.idBioskopa).success(function (data) {
				
				$scope.saleIzmena = data;
				
				for(i=0; i<$scope.saleIzmena.length; i++){
					if($scope.saleIzmena[i].id==$scope.menjanaRezervacija.projekcija.idSale){
						$scope.salaObjekatIzmena = $scope.saleIzmena[i];
						break;
					}
				}
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
				for(i=0; i<$scope.saleIzmena.length; i++){
					if($scope.saleIzmena[i].id==$scope.menjanaRezervacija.projekcija.idSale){
						$scope.salaObjekatIzmena = $scope.saleIzmena[i];
						break;
					}
				}
							
				$scope.salaObjekatIzmena = null;
				
				$scope.menjanaRezervacija.projekcija.idSale = null;
				$scope.menjanaRezervacija.brojRedaSedista = null;
				$scope.menjanaRezervacija.brojSedista = null;
				
				$scope.menjanaRezervacija.projekcija.datumProjekcije = null;
				$scope.menjanaRezervacija.projekcija.id = null;
				
				$scope.projekcijeIzmena = [];
			}
			
			$scope.odaberiSaluIzmena = function() {
				$scope.salaObjekatIzmena = null;
				
				$scope.menjanaRezervacija.projekcija.id = null;
				$scope.menjanaRezervacija.brojRedaSedista = null;
				$scope.menjanaRezervacija.brojSedista = null;
				
				for(i=0; i<$scope.saleIzmena.length; i++){
					if($scope.saleIzmena[i].id==$scope.menjanaRezervacija.projekcija.idSale){
						$scope.salaObjekatIzmena = $scope.saleIzmena[i];
						break;
					}
				}
				
				$scope.projekcijeIzmena = [];
				
				for (i = 0; i < $scope.sveProjekcijeIzmena.length; i++) { 
					var p = $scope.sveProjekcijeIzmena[i];
					if(p.idBioskopa == $scope.menjanaRezervacija.projekcija.idBioskopa 
			    			&& p.idSale == $scope.menjanaRezervacija.projekcija.idSale){
						var dbDate = new Date(p.datumProjekcije);
			    		var filterDate = new Date($scope.menjanaRezervacija.projekcija.datumProjekcije);
			    		filterDate.setHours(1);
			    		dbDate.setHours(1);
			    		if(dbDate.getTime()===filterDate.getTime()){
			    			$scope.projekcijeIzmena.push($scope.sveProjekcijeIzmena[i]);
			    		}
			    	}
			    }
			}
			
			$scope.odaberiProjekcijuIzmena = function() {
				$scope.menjanaRezervacija.brojSedista = null;
				$scope.menjanaRezervacija.brojRedaSedista = null;
			}
						
        	$scope.prihvatiIzmenu = function(){
        		
        		if(	$scope.menjanaRezervacija.projekcija == null 
        		 || $scope.menjanaRezervacija.projekcija.idBioskopa == null
        	   	 || $scope.menjanaRezervacija.projekcija.idSale == null
        		 || $scope.menjanaRezervacija.brojRedaSedista == null
        		 || $scope.menjanaRezervacija.brojSedista == null)
        		{
        			$mdToast.show(
	                        $mdToast.simple()
	                            .textContent('Molimo Vas da popunite sve parametre.')
	                            .hideDelay(3000)
	                            .position('top center')
	                            .theme('warning-toast')
	                    );
        			return;
        		}
        		
        		ProjekcijaService.getBrojZauzetihMestaPoReduIzmena
        				    ($scope.menjanaRezervacija.id,
        					 $scope.menjanaRezervacija.projekcija.id, 
        					 $scope.menjanaRezervacija.brojRedaSedista)
					.success(function(data){
						
						var brojSlobodnihMesta = $scope.salaObjekatIzmena.brojSedistaKolone - data;
												
						if(brojSlobodnihMesta < $scope.menjanaRezervacija.brojSedista){
							$mdToast.show(
			                   $mdToast.simple()
			                       .textContent('Nema dovoljno mesta u željenom redu!')
			                       .hideDelay(3000)
			                       .position('top center')
			                       .theme('warning-toast')
			                 );
						} else {
			        		RezervacijaService.izmeniRezervaciju($scope.menjanaRezervacija)
			        		 .success(function(data){
			                	$mdDialog.hide($scope.menjanaRezervacija);
			                })
						}
					});
            };

            $scope.izmeniProjekciju = function() {
            	$scope.iniciraoIzmenuProjekcije = true;
            }
            
            $scope.cancel = function() {
            	$mdDialog.cancel();
            };
        }
		
		$scope.dobaviBioskope = function(){
			BioskopService.getBioskopi().success(function (data) {
				$scope.bioskopi = data;
			});
		}
		
		$scope.dobaviBioskope();
				
		/**Funkcije za rezervisanje*/
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
			
			if($scope.filter.zeljeniBrojSedista==null ||
				$scope.filter.bioskop==null ||
				$scope.filter.sala == null ||
				$scope.filter.datum == null ||
				$scope.filter.projekcija == null ||
				$scope.filter.brojRedaSedista == null ) 
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
			
			var rezervacija = {};
			rezervacija.idKorisnika = $localStorage.logged.id;
			rezervacija.datumRezervacije = new Date();
			
			KorisnikService.proveriKorisnika(rezervacija.idKorisnika).success(function(data){
				if(data.response=="DEAKTIVIRAN"){
					$mdToast.show(
			                   $mdToast.simple()
			                      .textContent('Vaš nalog je deaktiviran!')
			                      .hideDelay(3000)
			                      .position('bottom center')
			                      .theme('warning-toast')
			                );
					return;
				} else {
					
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
							$window.scrollTo(0, 0);
							$scope.filter={};
							$scope.brojSlobodnihMesta = null;
							$mdToast.show(
			                        $mdToast.simple()
			                            .textContent('Uspešno kreirana rezervacija!')
			                            .hideDelay(3000)
			                            .position('top center')
			                            .theme('success-toast')
			                    );
						});
					
					
					
				}
			})
			
			
		}
		

		$scope.deaktivirajRezervaciju = function(id){
			
			RezervacijaService.deaktivirajRezervaciju(id).success(function(data){
				for(i=0;i<$scope.listaRezervacijaKorisnika.length;i++){
					var r = $scope.listaRezervacijaKorisnika[i];
					if(r.id == id){
						r.tip = 'OTKAZANA';
						break;
					}
				}
			})
		}
		
		$scope.obrisiRezervaciju = function(id) {
			RezervacijaService.obrisiRezervaciju(id).success(function(data){
				$scope.prikaziRezervacije();
			})
		}
		
});