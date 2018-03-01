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
@RequestMapping("/api")
public class ModelRestApiController {
	@Autowired
	private ModelService modelService;
	@Autowired
	private ManufacturerService manufacturerService;

	@RequestMapping(value = "/model/", method = RequestMethod.GET)
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
			return new ResponseEntity<Model>(model,HttpStatus.OK);
		}
		return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/model")
	public ResponseEntity<Model> addModel(@RequestParam(value="manufacturer_code", required=true)int id,String dailyHireRate, String modelName, HttpServletRequest request, UriComponentsBuilder ucBuilder) {
		Manufacturer manu = manufacturerService.findManufactureById(id);
		Model model = modelService.addModel(dailyHireRate, modelName, manu);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/model/{id}").buildAndExpand(model.getModelCode()).toUri());
		return new ResponseEntity<Model>(header, HttpStatus.OK);
	}

	@PutMapping(value = "/model/{id}")
	public ResponseEntity<Model> updateModel(@PathVariable int id, Model modelL) {
		Manufacturer manu = manufacturerService.findManufactureById(modelL.getModelCode());
		Model model = new Model();
		 model.setDailyHireRate(modelL.getDailyHireRate());
		 model.setModelName(modelL.getDailyHireRate());
		boolean md = modelService.updateModel(id, model.getDailyHireRate(), model.getModelName(),
				manu);
		if (md) {
			return new ResponseEntity<Model>(HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "{account_type_code}", method = RequestMethod.DELETE)
	public ResponseEntity<Model> delete(@PathVariable("id") int id) {
		boolean model = modelService.deleteModel(id);
		if (model == false) {
			return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Model>(HttpStatus.NO_CONTENT);
	}

}
