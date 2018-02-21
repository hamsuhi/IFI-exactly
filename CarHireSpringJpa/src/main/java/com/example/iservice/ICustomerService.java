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
	boolean updtateCustomer(int id, String addressLine1, String country, String customerName, String emailAddress,
			String gendder, String phoneNumber, String town);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteCustomer(int id);

}
