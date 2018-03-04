/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.VehicleCategory;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IVehicleCatogoryService {

	/**
	 * @return
	 */
	List<VehicleCategory> findAllVehicleCategory();

	/**
	 * @param id
	 * @return
	 */
	VehicleCategory findByIdVehicleCategory(int id);

	/**
	 * @param type
	 * @return
	 */
	boolean addVehicleCategory(VehicleCategory type);

	/**
	 * @param id
	 * @param description
	 * @return
	 */
	boolean updateVehicleCategory(int id, String description);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteVehicleCategory(int id);

}
