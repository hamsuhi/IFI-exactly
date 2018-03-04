package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.VehicleCategory;
import com.example.service.VehicleCategoryService;

@RestController
@RequestMapping(value = "/api")
public class VehicleCategoryApiRestController {
	@Autowired
	private VehicleCategoryService vehicleCategoryService;

	@GetMapping(value = "/vehiclecategory")
	public ResponseEntity<List<VehicleCategory>> findAllVehicleCategory() {
		List<VehicleCategory> lst = vehicleCategoryService.findAllVehicleCategory();
		if (lst == null) {
			return new ResponseEntity<List<VehicleCategory>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<VehicleCategory>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/vehiclecategory/{id}")
	public ResponseEntity<VehicleCategory> findByIdVehicleCategory(@PathVariable("id") int id) {
		VehicleCategory vc = vehicleCategoryService.findByIdVehicleCategory(id);
		if (vc != null) {
			return new ResponseEntity<VehicleCategory>(vc, HttpStatus.OK);
		}
		return new ResponseEntity<VehicleCategory>(HttpStatus.NO_CONTENT);
	}

	//Add 
	@PostMapping(value = "/vehiclecategory/")
	public ResponseEntity<?> addVehicleCategory(String vehicleCategoryDescription, UriComponentsBuilder ucBuilder) {
		VehicleCategory vehicle = new VehicleCategory(vehicleCategoryDescription);
		if (vehicleCategoryService.addVehicleCategory(vehicle) == false) {
			return new ResponseEntity<VehicleCategory>(vehicle, HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(
				ucBuilder.path("/api/vehiclecategory/{id}").buildAndExpand(vehicle.getVehicleCategoryCode()).toUri());
		return new ResponseEntity<VehicleCategory>(vehicle, HttpStatus.OK);
	}

	@PostMapping(value = "/vehiclecategory/{id}")
	public ResponseEntity<?> updateVehicleCategory(@PathVariable("id") int id,
			String vehicleCategoryDescription) {
		if (vehicleCategoryService.updateVehicleCategory(id, vehicleCategoryDescription) == false) {			
			return new ResponseEntity<String>(vehicleCategoryDescription, HttpStatus.NO_CONTENT);
		}
		VehicleCategory vehicle = vehicleCategoryService.findByIdVehicleCategory(id);
		return new ResponseEntity<VehicleCategory>(vehicle, HttpStatus.OK);
	}

//	@DeleteMapping(value = "/vehiclecategory/{id}")
//	public ResponseEntity<?> deleteVehicleCategory(@PathVariable("id") int id) {
//		boolean v = vehicleCategoryService.deleteVehicleCategory(id);
//		if (v) {
//			return new ResponseEntity<VehicleCategory>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<VehicleCategory>(HttpStatus.NOT_FOUND);
//	}
}
