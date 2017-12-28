package com.bioskop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.models.Bioskop;
import com.bioskop.models.Sala;
import com.bioskop.services.BioskopService;
import com.bioskop.services.SalaService;

@RestController
@RequestMapping("sala")
public class SalaController {

	@Autowired
	private SalaService salaService;

	// unos sale u okviru bioskopa
	// izostaviti kapacitet, sam se izracunava!
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Bioskop> insertSala(@PathVariable Long id, @RequestBody Sala sala) {
		Bioskop bioskopZaIzmenu = salaService.saveSala(id, sala);
		return new ResponseEntity<Bioskop>(bioskopZaIzmenu, HttpStatus.CREATED);
	}

	// brisanje sale u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.DELETE)
	public ResponseEntity<Bioskop> deleteSala(@PathVariable Long idBioskopa, @PathVariable Long idSale) {
		Bioskop bioskopZaIzmenu = salaService.deleteSala(idBioskopa, idSale);
		return new ResponseEntity<Bioskop>(bioskopZaIzmenu, HttpStatus.OK);
	}

	// update sale u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.PUT)
	public ResponseEntity<Bioskop> updateSala(@PathVariable Long idBioskopa, @PathVariable Long idSale,
			@Valid @RequestBody Sala sala) {
		Bioskop savedBioskop = salaService.updateSala(idBioskopa, idSale, sala);
		return new ResponseEntity<Bioskop>(savedBioskop, HttpStatus.OK);
	}

	// pregled svih sala u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>> getSveSale(@PathVariable Long idBioskopa) {
		List<Sala> sale = salaService.findSveSalePoBioskopu(idBioskopa);
		return new ResponseEntity<List<Sala>>(sale, HttpStatus.OK);
	}

	// pregled sale u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.GET)
	public ResponseEntity<Sala> getSala(@PathVariable Long idBioskopa, @PathVariable Long idSale) {
		Sala sala = salaService.findSalaPoBioskopu(idBioskopa, idSale);
		return new ResponseEntity<Sala>(sala, HttpStatus.OK);
	}

	// na osnovu idSale vraca njenu oznaku, potrebno za uvezivanje sa
	// rezervacijom
	// localhost:8765/sala-service/sala/checkSala?bioskopId=1&salaId=2
	@RequestMapping(value = "/checkSala", method = RequestMethod.GET)
	public String checkSala(@RequestParam(name = "bioskopId") Long bioskopId,
			@RequestParam(name = "salaId") Long salaId) {
		Sala sala = salaService.findSalaPoBioskopu(bioskopId, salaId);
		return sala.getOznakaSale();
	}


		                        /* SEDISTA   */
	// vraca broj sedista po redovima u sali u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.GET)
	public int getBrojPoRedu(@PathVariable Long idBioskopa, @PathVariable Long idSale) {
		Sala sala = salaService.findSalaPoBioskopu(idBioskopa, idSale);
		return sala.getBrojSedistaRedovi();
	}
	
	// vraca broj sedista po kolonama u sali u okviru bioskopa
	@RequestMapping(value = "/{idBioskopa}/{idSale}", method = RequestMethod.GET)
	public int getBrojPoKoloni(@PathVariable Long idBioskopa, @PathVariable Long idSale) {
		Sala sala = salaService.findSalaPoBioskopu(idBioskopa, idSale);
		return sala.getBrojSedistaKolone();
	}
	
	
	

}
