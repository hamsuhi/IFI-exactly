package com.example.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Model;
import com.example.model.Vehicle;
import com.example.model.VehicleCategory;
import com.example.repository.IVehicleRepository;

@Service
public class VehicleService {
	private static final Logger log = LogManager.getLogger(VehicleService.class);

	@Autowired
	private IVehicleRepository vehicleRepository;

	public List<Vehicle> findAllVehicle() {
		List<Vehicle> lst = vehicleRepository.findAll();
		for (Vehicle v : lst) {
			v.toString();
		}
		return lst;
	}

	public Vehicle findVehicleById(int id) {
		Vehicle vehicle = vehicleRepository.findOne(id);
		if(vehicle!=null) {
			log.info("Da tim thay Vehicle By Id: "+ vehicle.toString() );
		}else {
			log.info("Khong tim thay Vehicle By Id" );
		}
		return vehicle;
	}

	public Vehicle addVehicle(String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory) {
		Vehicle v = new  Vehicle(currentMileage,dailyMotDue,engineSize,model, vehicleCategory);
		vehicleRepository.save(v);
		log.info("**** Add a Vehicle have name is succedd");
		return v;
	}

	public void updateVehicle(int id, String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory) {
		Vehicle update = vehicleRepository.findOne(id);
		if (update != null) {
			update.setCurrentMileage(currentMileage);
			update.setDailyMotDue(dailyMotDue);
			update.setEngineSize(engineSize);
			update.setModel(model);
			update.setVehicleCategory(vehicleCategory);
			vehicleRepository.saveAndFlush(update);
			log.info("****UPDATE vehicle " + update.toString() + " succedd!****");
		} else {
			log.info("@.@_________Don't update vehicle!________@.@");
		}
	}

	public void deleteVehicle(int id) {
		Vehicle v = vehicleRepository.findOne(id);
		if (v != null) {
			vehicleRepository.delete(id);
			log.info("****Delete succedd!*****");
		} else {
			log.info("@.@___________Failed delete Vehicle_______@.@");
		}
	}

}
