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

import com.example.iservice.IVehicleCatogoryService;
import com.example.model.VehicleCategory;
import com.example.repository.IVehicleCategoryRepository;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@Service
public class VehicleCategoryService implements IVehicleCatogoryService{
	private Logger log = LogManager.getLogger(VehicleCategoryService.class);
	@Autowired
	private IVehicleCategoryRepository vehicleCategoryRepository;

	@Override
	public List<VehicleCategory> findAllVehicleCategory() {
		List<VehicleCategory> lst = vehicleCategoryRepository.findAll();
		for (VehicleCategory v : lst) {
			v.toString();
		}
		return lst;
	}
	
	@Override
	public VehicleCategory findByIdVehicleCategory(int id) {
		VehicleCategory vc =vehicleCategoryRepository.findOne(id);
		if(vc!=null) {
			log.info("Da tim thay vc"+ vc.toString());
		}else {
			log.info("Khong tim thay Vehicle Category by ID.");
		}
		return vc;		
	}
	
	@Override
	public VehicleCategory addVehicleCategory(VehicleCategory type) {
		VehicleCategory vehicle = vehicleCategoryRepository.save(type);
		log.info("**** Add vehicle category CODE "+vehicle.getVehicleCategoryCode()+" succedd!!!");
		return  vehicle;
	}
	
	@Override
	public boolean updateVehicleCategory(int id, String description) {
		VehicleCategory update = vehicleCategoryRepository.findOne(id);
		if(update!=null) {
			update.setVehicleCategoryDescription(description);;
			vehicleCategoryRepository.saveAndFlush(update);
			log.info("****Update VEHICLE CATEGORY thành công "+ update.toString());
			return true;
		}else {
			log.error("@.@________Don't update VEHICLE CATEGORY!____@.@");
			return false;
		}
	}
	
	@Override
	public boolean deleteModel(int id) {
		VehicleCategory v = vehicleCategoryRepository.findOne(id);
		if(v!= null) {
			vehicleCategoryRepository.delete(id);
			log.info("Delete Vehicle Category "+ v.getVehicleCategoryCode() +"succedd!");
			return true;
		}else {
			log.error("@.@________Delete  Vehicle Category failed");
			return false;
		}
	}
	
}
