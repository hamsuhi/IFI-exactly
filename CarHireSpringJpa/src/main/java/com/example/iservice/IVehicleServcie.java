/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.Date;
import java.util.List;

import com.example.model.Model;
import com.example.model.Vehicle;
import com.example.model.VehicleCategory;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IVehicleServcie {

	/**
	 * @return
	 */
	List<Vehicle> findAllVehicle();

	/**
	 * @param id
	 * @return
	 */
	Vehicle findVehicleById(int id);

	/**
	 * @param currentMileage
	 * @param dailyMotDue
	 * @param engineSize
	 * @param model
	 * @param vehicleCategory
	 * @return
	 */
	Vehicle addVehicle(String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory);

	/**
	 * @param id
	 * @param currentMileage
	 * @param dailyMotDue
	 * @param engineSize
	 * @param model
	 * @param vehicleCategory
	 * @return
	 */
	boolean updateVehicle(int id, String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteVehicle(int id);

}
