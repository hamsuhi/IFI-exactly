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

import com.example.model.Manufacturer;
import com.example.model.VehicleCategory;
import com.example.repository.IVehicleCategoryRepository;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@Service
public class VehicleCategoryService {
	private Logger log = LogManager.getLogger(VehicleCategoryService.class);
	@Autowired
	private IVehicleCategoryRepository vehicleCategoryRepository;

	public List<VehicleCategory> findAllVehicleCategory() {
		List<VehicleCategory> lst = vehicleCategoryRepository.findAll();
		for (VehicleCategory v : lst) {
			v.toString();
		}
		return lst;
	}
	
	public VehicleCategory findByIdVehicleCategory(int id) {
		VehicleCategory vc =vehicleCategoryRepository.findOne(id);
		if(vc!=null) {
			log.info("Da tim thay vc"+ vc.toString());
		}else {
			log.info("Khong tim thay Vehicle Category by ID.");
		}
		return vc;		
	}
	
	public VehicleCategory addVehicleCategory(VehicleCategory type) {
		VehicleCategory vehicle = vehicleCategoryRepository.save(type);
		log.info("**** Add vehicle category CODE "+vehicle.getVehicleCategoryCode()+" succedd!!!");
		return  vehicle;
	}
	
	public void updateVehicleCategory(int id, String description) {
		VehicleCategory update = vehicleCategoryRepository.findOne(id);
		if(update!=null) {
			update.setVehicleCategoryDescription(description);;
			vehicleCategoryRepository.saveAndFlush(update);
			log.info("****Update VEHICLE CATEGORY thành công "+ update.toString());
		}else {
			log.error("@.@________Don't update VEHICLE CATEGORY!____@.@");
		}
	}
	
	public void deleteModel(int id) {
		VehicleCategory v = vehicleCategoryRepository.findOne(id);
		if(v!= null) {
			vehicleCategoryRepository.delete(id);
			log.info("Delete Vehicle Category "+ v.getVehicleCategoryCode() +"succedd!");
		}else {
			log.error("@.@________Delete  Vehicle Category failed");
		}
	}
	
}
