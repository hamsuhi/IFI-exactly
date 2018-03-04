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
public class VehicleCategoryService implements IVehicleCatogoryService {
	private Logger log = LogManager.getLogger(VehicleCategoryService.class);
	@Autowired
	private IVehicleCategoryRepository vehicleCategoryRepository;

	@Override
	public List<VehicleCategory> findAllVehicleCategory() {
		List<VehicleCategory> lst = vehicleCategoryRepository.findAll();
		return lst;
	}

	@Override
	public VehicleCategory findByIdVehicleCategory(int id) {
		VehicleCategory vc = vehicleCategoryRepository.findOne(id);
		if (vc != null) {
			log.info("Da tim thay vc" + vc.toString());
		}
		log.info("Khong tim thay Vehicle Category by ID.");
		return vc;
	}

	@Override
	public boolean addVehicleCategory(VehicleCategory type) {
		if (vehicleCategoryRepository.exists(type.getVehicleCategoryCode())) {
			log.info("VehicleCategory is existed: " + type.getVehicleCategoryDescription());
			return false;
		}
		vehicleCategoryRepository.save(type);
		return true;
	}

	@Override
	public boolean updateVehicleCategory(int id, String description) {
		VehicleCategory update = vehicleCategoryRepository.findOne(id);
		if (update != null) {
			update.setVehicleCategoryDescription(description);
			vehicleCategoryRepository.saveAndFlush(update);
			log.info("****Update VEHICLE CATEGORY thành công " + update.toString());
			return true;
		} else {
			log.error("@.@________Don't update VEHICLE CATEGORY!____@.@");
			return false;
		}
	}

	@Override
	public boolean deleteVehicleCategory(int id) {
		if (vehicleCategoryRepository.exists(id)) {
			vehicleCategoryRepository.delete(id);
			return true;
		} 
			return false;
	}

}
