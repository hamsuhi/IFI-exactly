package com.example.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iservice.IVehicleServcie;
import com.example.model.Model;
import com.example.model.Vehicle;
import com.example.model.VehicleCategory;
import com.example.repository.IVehicleRepository;

@Service
public class VehicleService implements IVehicleServcie{
	private static final Logger log = LogManager.getLogger(VehicleService.class);

	@Autowired
	private IVehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> findAllVehicle() {
		List<Vehicle> lst = vehicleRepository.findAll();
		for (Vehicle v : lst) {
			v.toString();
		}
		return lst;
	}

	@Override
	public Vehicle findVehicleById(int id) {
		Vehicle vehicle = vehicleRepository.findOne(id);
		if(vehicle!=null) {
			log.info("Da tim thay Vehicle By Id: "+ vehicle.toString() );
		}else {
			log.info("Khong tim thay Vehicle By Id" );
		}
		return vehicle;
	}

	@Override
	public Vehicle addVehicle(String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory) {
		Vehicle v = new  Vehicle(currentMileage,dailyMotDue,engineSize,model, vehicleCategory);
		vehicleRepository.save(v);
		log.info("**** Add a Vehicle have name is succedd");
		return v;
	}

	@Override
	public boolean updateVehicle(int id, String currentMileage, Date dailyMotDue, String engineSize, Model model,
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
			return true;
		} else {
			log.info("@.@_________Don't update vehicle!________@.@");
			return false;
		}
	}

	@Override
	public boolean deleteVehicle(int id) {
		Vehicle v = vehicleRepository.findOne(id);
		if (v != null) {
			vehicleRepository.delete(id);
			log.info("****Delete succedd!*****");
			return true;
		} else {
			log.info("@.@___________Failed delete Vehicle_______@.@");
			return false;
		}
	}
	

}
