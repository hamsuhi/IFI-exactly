/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.Custromer;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface ICustomerService {

	/**
	 * @return
	 */
	List<Custromer> findAllCustome();

	/**
	 * @param id
	 * @return
	 */
	Custromer findCustomerById(int id);

	/**
	 * @param customer
	 * @return
	 */
	Custromer addCustomer(Custromer customer);

	/**
	 * @param id
	 * @param addressLine1
	 * @param country
	 * @param customerName
	 * @param emailAddress
	 * @param gendder
	 * @param phoneNumber
	 * @param town
	 * @return
	 */
	boolean updateCustomer(int id, String addressLine1, String addressLine2, String addressLine3,
			String country, String county, String customerDetails, String customerName, String emailAddress,
			String gender, String phoneNumber, String town);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteCustomer(int id);

}
