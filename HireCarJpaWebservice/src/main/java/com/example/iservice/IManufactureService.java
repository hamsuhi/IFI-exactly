/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.Manufacturer;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IManufactureService {

	/**
	 * @return
	 */
	List<Manufacturer> findAllManufacturer();

	/**
	 * @param id
	 * @return
	 */
	Manufacturer findManufactureById(int id);

	/**
	 * @param manu
	 * @return
	 */
	boolean addManufacture(Manufacturer manu);

	/**
	 * @param id
	 * @param name
	 * @param detail
	 * @return
	 */
	boolean updateManufacture(Manufacturer manu);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteManufacture(int id);

}
