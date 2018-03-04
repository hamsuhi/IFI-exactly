/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 25, 2018, Nguyen Thanh Huong 
 * Class one-to-many sample 
 */
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;

/**
 * @author Nguyễn Thanh Hương
 */
@RestController
@RequestMapping(value = "/api")
public class ManufactureRestApiController {
	@Autowired
	private ManufacturerService manufacturerService;

	@GetMapping(value = "/manu")
	public ResponseEntity<List<Manufacturer>> findAllManufacturer() {
		List<Manufacturer> lstManu = manufacturerService.findAllManufacturer();
		if (lstManu.isEmpty()) {
			return new ResponseEntity<List<Manufacturer>>(lstManu, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Manufacturer>>(lstManu, HttpStatus.OK);
	}

	@RequestMapping(value = "/manu/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findManufactureById(@PathVariable("id") int id) {
		Manufacturer manu = manufacturerService.findManufactureById(id);
		if (manu != null) {
			return new ResponseEntity<Manufacturer>(manu, HttpStatus.OK);
		}
		return new ResponseEntity<Manufacturer>(manu, HttpStatus.NOT_FOUND);
	}

	// -------------------------------
	// add in form-data && x-www-form
	@PostMapping(value = "/manu/", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<?> addManufactureUseFormData(String manufacturerName, String manufacurerDetails,
			UriComponentsBuilder ucBulder) {
		Manufacturer man = new Manufacturer(manufacturerName, manufacurerDetails);
		if (manufacturerService.addManufacture(man) == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBulder.path("/api/manu/{id}").buildAndExpand(man.getManufacturerCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// add in raw ---- Change in PostMan element Header is =>Content-Type :
	// application/json
	@PostMapping(value = "/manuu//")
	public ResponseEntity<Manufacturer> addManufactureUseRaw(@RequestBody Manufacturer manu,
			UriComponentsBuilder ucBuilder) {
		boolean test = manufacturerService.addManufacture(manu);
		if (test == false) {
			return new ResponseEntity<Manufacturer>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/manuu//{id}").buildAndExpand(manu.getManufacturerCode()).toUri());
		return new ResponseEntity<Manufacturer>(headers, HttpStatus.CREATED);
	}

	// add in param
	@PostMapping(value = "/manuu///")
	public ResponseEntity<Manufacturer> addManufactureUseParams(
			@RequestParam(value = "manufacturerName") String manufacturerName,
			@RequestParam(value = "manufacurerDetails", required = false) String manufacurerDetails,
			UriComponentsBuilder ucBuilder) {
		Manufacturer manu = new Manufacturer(manufacturerName, manufacurerDetails);
		boolean test = manufacturerService.addManufacture(manu);
		if (test == false) {
			return new ResponseEntity<Manufacturer>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/manuu///{id}").buildAndExpand(manu.getManufacturerCode()).toUri());
		return new ResponseEntity<Manufacturer>(headers, HttpStatus.CREATED);
	}

	// -----------------------------------
	// update use form-data && x-www-form
	@RequestMapping(value = "/manuuu///", method = RequestMethod.POST, headers = "Accept=*/*")
	public ResponseEntity<?> updatewManufactureUseForm(String manufacturerCode, String manufacturerName,
			String manufacurerDetails) {
		Manufacturer manu = new Manufacturer(Integer.parseInt(manufacturerCode), manufacturerName, manufacurerDetails);
		if (manufacturerService.updateManufacture(manu)) {
			return new ResponseEntity<Manufacturer>(manu, HttpStatus.OK);
		}
		return new ResponseEntity<Manufacturer>(manu, HttpStatus.NO_CONTENT);
	}

	// update use param
	@RequestMapping(value = "/manu", method = RequestMethod.PUT)
	public ResponseEntity<Manufacturer> updatewManufactureUseParams(
			@RequestParam(value = "manufacturerCode") int manufacturerCode,
			@RequestParam(value = "manufacturerName") String manufacturerName,
			@RequestParam(value = "manufacurerDetails", required = false) String manufacurerDetails) {
		Manufacturer manu = new Manufacturer(manufacturerCode, manufacturerName, manufacurerDetails);
		if (manufacturerService.updateManufacture(manu)) {
			return new ResponseEntity<Manufacturer>(manu, HttpStatus.OK);
		}
		return new ResponseEntity<Manufacturer>(manu, HttpStatus.NO_CONTENT);
	}

	// update use raw-- Change in PostMan element Header is
	// =>Content-Type : application/json
	@PutMapping(value = "/manuu//")
	public ResponseEntity<Manufacturer> updateManufactureUseRaw(@RequestBody Manufacturer manu) {
		manufacturerService.updateManufacture(manu);
		return new ResponseEntity<Manufacturer>(manu, HttpStatus.OK);
	}

	@RequestMapping(value = "/manu/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteManufacture(@PathVariable int id) {
		if (manufacturerService.deleteManufacture(id) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}