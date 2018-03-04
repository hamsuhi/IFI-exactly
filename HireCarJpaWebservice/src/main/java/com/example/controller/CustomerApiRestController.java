package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Custromer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerApiRestController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/customer")
	public ResponseEntity<List<Custromer>> findAllCustomer() {
		List<Custromer> lst = customerService.findAllCustome();
		if (lst.isEmpty())
			return new ResponseEntity<List<Custromer>>(lst, HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Custromer>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {
		Custromer c = customerService.findCustomerById(id);
		if (c != null)
			return new ResponseEntity<Custromer>(c, HttpStatus.OK);
		return new ResponseEntity<Custromer>(c, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/customer/")
	public ResponseEntity<?> addCustomer(String addressLine1, String addressLine2, String addressLine3, String country,
			String county, String customerDetails, String customerName, String emailAddress, String gender,
			String phoneNumber, String town, UriComponentsBuilder ucBuider) {
		Custromer customer = new Custromer(addressLine1, addressLine2, addressLine3, country, county, customerDetails,
				customerName, emailAddress, gender, phoneNumber, town);
		if (customerService.addCustomer(customer) == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuider.path("api/customer/{id}").buildAndExpand(customer.getCustomerId()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PostMapping(value = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, String addressLine1, String addressLine2,
			String addressLine3, String country, String county, String customerDetails, String customerName,
			String emailAddress, String gender, String phoneNumber, String town) {
		if (customerService.updateCustomer(id, addressLine1, addressLine2, addressLine3, country, county,
				customerDetails, customerName, emailAddress, gender, phoneNumber, town) == false) {
			return new ResponseEntity<Custromer>(HttpStatus.NO_CONTENT);
		}
		Custromer custom = customerService.findCustomerById(id);
		return new ResponseEntity<Custromer>(custom, HttpStatus.OK);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Custromer> deleteCustomer(@PathVariable("id") int id) {
		if (customerService.deleteCustomer(id)) {
			return new ResponseEntity<Custromer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Custromer>(HttpStatus.NOT_FOUND);
	}

}
