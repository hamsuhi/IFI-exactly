/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 25, 2018, Nguyen Thanh Huong 
 */
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@RestController
@RequestMapping(value = "/api")
public class ManufactureRestApiController {

	@Autowired
	private ManufacturerService manufacturerService;

	@GetMapping(value = "/manu/")
	public ResponseEntity<List<Manufacturer>> findAllManufacturer() {
		List<Manufacturer> lstManu = (List<Manufacturer>) manufacturerService.findAllManufacturer();
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

	@RequestMapping(value = "/manu/", method = RequestMethod.POST)
	public ResponseEntity<?> addManufacture(@RequestBody Manufacturer manu, UriComponentsBuilder ucBuilder) {
		if (manufacturerService.addManufacture(manu) == false) {
			return new ResponseEntity<Manufacturer>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/manu/{id}").buildAndExpand(manu.getManufacturerCode()).toUri());
		return new ResponseEntity<Manufacturer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/manu", method = RequestMethod.PUT)
	public ResponseEntity<?> updateManufacture(@RequestBody Manufacturer manu) {
		if (manufacturerService.updateManufacture(manu) == false) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Manufacturer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/manu", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteManufacture(@PathVariable int id) {
		if (manufacturerService.deleteManufacture(id) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
