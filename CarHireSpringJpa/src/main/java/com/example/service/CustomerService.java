/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 8, 2018, Nguyen Thanh Huong 
 */
package com.example.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Custromer;
import com.example.repository.ICustomerRepository;

/**
 * @author Nguyễn Thanh Hương
 * 
 */
@Service
public class CustomerService {
	private Logger log = LogManager.getLogger(CustomerService.class);

	@Autowired
	private ICustomerRepository customerRepository;

	public List<Custromer> findAllCustome() {
		List<Custromer> lst = customerRepository.findAll();
		for (Custromer c : lst) {
			c.toString();
		}
		return lst;
	}

	public Custromer findCustomerById(int id) {
		Custromer c = customerRepository.findOne(id);
		if(c!=null) {
			log.info("Da tim thay customer by id:"+ c.toString());
		}else {
			log.error("Khong tim thay customer by Id");
		}
		return c;
	}

	public Custromer addCustomer(Custromer customer) {
		Custromer c = customerRepository.save(customer);
		log.info("****Add Customer succedd! " + c.toString());
		return c;
	}

	public void updtateCustomer(int id, String addressLine1, String country, String customerName, String emailAddress,
			String gendder, String phoneNumber, String town) {
		Custromer c = customerRepository.findOne(id);
		if (c != null) {
			c.setAddressLine1(addressLine1);
//			c.setAddressLine2(customer.getAddressLine2());
//			c.setAddressLine3(customer.getAddressLine3());
			c.setCountry(country);
//			c.setCounty(customer.getCounty());
//			c.setCustomerDetails(customer.getCustomerDetails());
//			c.setCustomerDetails(customer.getCustomerDetails());
			c.setCustomerName(customerName);
			c.setEmailAddress(emailAddress);
			c.setGender(gendder);
			c.setPhoneNumber(phoneNumber);
			c.setTown(town);
			customerRepository.saveAndFlush(c);
			log.info("****Update customer scceed!!! " + c.toString());
		} else {
			log.error("@.@_________Update customer failed!________@.@");
		}
	}

	public void deleteCustomer(int id) {
		Custromer c = customerRepository.findOne(id);
		if (c != null) {
			customerRepository.delete(id);
			log.info("****Delete customer succedd!  *****");
		} else {
			log.error("@.@_________Delete customer failed!_______@.@");
		}
	}
}
