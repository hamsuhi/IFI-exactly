package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Model;
import com.example.model.Vehicle;
import com.example.model.VehicleCategory;
import com.example.repository.IVehicleRepository;
import com.example.service.ModelService;
import com.example.service.VehicleCategoryService;
import com.example.service.VehicleService;

@RestController
@RequestMapping("/api")
public class VehicleApiRestController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private IVehicleRepository vehicleRepository;

	@Autowired
	private ModelService modelService;

	@Autowired
	private VehicleCategoryService vehicleCategoryService;

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	private ResponseEntity<List<Vehicle>> getAllVehicle() {
		List<Vehicle> vehicle = vehicleService.findAllVehicle();
		if (vehicle.isEmpty()) {
			return new ResponseEntity<List<Vehicle>>(vehicle, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehicle>>(vehicle, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> getVehicleById(@PathVariable("id") int id) {
		Vehicle vehicle = vehicleService.findVehicleById(id);
		if (vehicle != null) {
			return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
		}
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.POST)
	private ResponseEntity<?> addVehicle(@PathVariable int id, @RequestParam(value = "vehicle_category_code") int vcId,
			@RequestParam(value = "model_code") int modelId,
			@RequestParam(value = "current_mileage") String currentMileage,
			@RequestParam(value = "daily_mot_due") Date dailyMotDue, String engineSize,
			UriComponentsBuilder ucBuilder) {
		Model model = modelService.findModelById(modelId);
		VehicleCategory vc = vehicleCategoryService.findByIdVehicleCategory(vcId);
		Vehicle test = vehicleService.addVehicle(currentMileage, dailyMotDue, engineSize, model, vc);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/vehicle/{id}").buildAndExpand(test.getRegNumber()).toUri());
		return new ResponseEntity<Vehicle>(test, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.PUT)
	private ResponseEntity<?> updateVehicle(@PathVariable int id,
			@RequestParam(value = "vehicle_category_code") int vcId, @RequestParam(value = "model_code") int modelId,
			String currentMileage, Date dailyMotDue, String engineSize) {
		Model model = modelService.findModelById(modelId);
		VehicleCategory vc = vehicleCategoryService.findByIdVehicleCategory(vcId);

		boolean test = vehicleService.updateVehicle(id, currentMileage, dailyMotDue, engineSize, model, vc);
		if (test == true) {
			Vehicle vehicle = vehicleRepository.getOne(id);
			return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
		}
		return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> deleteVehicle(@PathVariable int id) {
		if (vehicleService.deleteVehicle(id) == false) {
			return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
	}

}

