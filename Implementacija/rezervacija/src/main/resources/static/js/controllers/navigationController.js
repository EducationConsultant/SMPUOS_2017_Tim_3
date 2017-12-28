angular.module('rezervacijaApp.NavigationController', []).controller(
		'NavigationController',
		function($scope, $location, $rootScope, $mdDialog, RezervacijaService,
				$localStorage, $mdToast) {

			if ($localStorage.curNav == null)
				$scope.currentNavItem = 'Pocetna';
			else
				$scope.currentNavItem = $localStorage.curNav;

			$scope.saveNav = function(data) {
				$localStorage.curNav = data;
			}

			$scope.prikaziRezervacije = function() {
				RezervacijaService.findAll()
					.success(
						function(data) {
							$scope.listaRezervacija = data;
							$mdToast.show($mdToast.simple()
									.textContent('Uspešno prikazano!')
									.hideDelay(3000)
									.position('top center')
									.theme('success-toast'));
					})
					.error(
						function(data) {
							$mdToast.show($mdToast.simple()
									.textContent('Greska!')
									.hideDelay(3000)
									.position('top center')
									.theme('success-toast'));
					})
			};
			
			
			$scope.prikaziAktivneRezervacije = function() {
				RezervacijaService.pregledAktivnihRezervacija()
					.success(
						function(data) {
							$scope.listaAktivnihRezervacija = data;
							$mdToast.show($mdToast.simple()
									.textContent('Uspešno prikazano!')
									.hideDelay(3000)
									.position('top center')
									.theme('success-toast'));
					})
					.error(
						function(data) {
							$mdToast.show($mdToast.simple()
									.textContent('Greska!')
									.hideDelay(3000)
									.position('top center')
									.theme('success-toast'));
					})
			};
						
			$scope.prikaziRezervacije();
			$scope.prikaziAktivneRezervacije();

		});