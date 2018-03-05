package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.example.model.Model;
import com.example.service.ManufacturerService;
import com.example.service.ModelService;

@RestController
@RequestMapping(value = "/api")
public class ModelRestApiController {
	@Autowired
	private ModelService modelService;
	@Autowired
	private ManufacturerService manufacturerService;

	@RequestMapping(value = "/model", method = RequestMethod.GET)
	public ResponseEntity<List<Model>> findAllModel() {
		List<Model> model = modelService.findAllModel();
		if (model != null) {
			return new ResponseEntity<List<Model>>(model, HttpStatus.OK);
		}
		return new ResponseEntity<List<Model>>(model, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/model/{id}")
	public ResponseEntity<Model> findModelById(@PathVariable int id) {
		Model model = modelService.findModelById(id);
		if (model != null) {
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		}
		return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
	}

	// add use param
	@PostMapping(value = "/model")
	public ResponseEntity<?> addModelUseParams(@RequestParam(value = "manufacturerCode", required = true) int id,
			@RequestParam(value = "dailyHireRate", required = false) String dailyHireRate,
			@RequestParam(value = "modelName", required = false) String modelName, UriComponentsBuilder ucBuilder) {
		Manufacturer manu = manufacturerService.findManufactureById(id);
		Model model = modelService.addModel(dailyHireRate, modelName, manu);
		if (model == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/model/{id}").buildAndExpand(model.getModelCode()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	// add form-data
	@PostMapping(value = "/modell/")
	public ResponseEntity<?> addModelFormData(String manufacturerCode, String dailyHireRate, String modelName,
			UriComponentsBuilder ucBuilder) {
		Manufacturer manu = manufacturerService.findManufactureById(Integer.parseInt(manufacturerCode));
		Model model = modelService.addModel(dailyHireRate, modelName, manu);
		if (model == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/model/{id}").buildAndExpand(model.getModelCode()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	// add auto a manu from table model --> is being error
	@PostMapping(value = "/modell///")
	public ResponseEntity<?> addAutoManuByModel(String dailyHireRate, String modelName, String manufacturerName,
			String manufacurerDetails, UriComponentsBuilder ucBuilder) {
		Manufacturer manufacture = new Manufacturer(manufacturerName, manufacurerDetails);
		manufacturerService.addManufacture(manufacture);
		Model model = modelService.addModel(dailyHireRate, modelName, manufacture);

		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/model/{id}").buildAndExpand(model.getModelCode()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	// update form-data 
	@PostMapping(value = "/model/") 
	public ResponseEntity<?> updateModelFormData(String modelCode,String manufacturerCode, String dailyHireRate, String modelName
			) {
		Manufacturer manu = manufacturerService.findManufactureById(Integer.parseInt(manufacturerCode));
		Model model = new Model(Integer.parseInt(modelCode), dailyHireRate, modelName, manu);
		boolean md = modelService.updateModel(Integer.parseInt(modelCode), Integer.parseInt(manufacturerCode),
				dailyHireRate, modelName);
		if (md) {
			return new ResponseEntity<Model>(model, HttpStatus.OK);
		}
		return new ResponseEntity<Model>(model, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Model> delete(@PathVariable("id") int id) {
		boolean model = modelService.deleteModel(id);
		if (model == false) {
			return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Model>(HttpStatus.NO_CONTENT);
	}
}
