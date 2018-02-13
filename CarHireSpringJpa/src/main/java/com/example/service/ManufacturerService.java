package com.example.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Manufacturer;
import com.example.repository.IManufacturerRepository;

@Service
public class ManufacturerService {
	private static final Logger log = LogManager.getLogger(ManufacturerService.class);
	@Autowired
	private IManufacturerRepository manufacturerRepository;
	// Get All Notes

	@SuppressWarnings("unchecked")
	public List<Manufacturer> findAllManufacturer() {
		List<Manufacturer> lstManu = (List<Manufacturer>) manufacturerRepository.findAll();
		for (Manufacturer m : lstManu) {
			m.toString();
		}
		return lstManu;
	}

	public Manufacturer findManufactureById(int id) {
		Manufacturer manu = manufacturerRepository.findOne(id);
		if (manu != null) {
			log.info("Manufacture da tim thay: " + manu.toString());
		} else {
			log.info("Khong tim thay Manufacture theo Id.");
		}
		return manu;
	}

	public Manufacturer addManufacture(Manufacturer manu) {
		return manufacturerRepository.save(manu);
	}

	public boolean updateManufacture(int id, String name, String detail) {
		Manufacturer updateManu = manufacturerRepository.findOne(id);
		if (updateManu != null) {
			updateManu.setManufacturerName(name);
			updateManu.setManufacurerDetails(detail);
			manufacturerRepository.saveAndFlush(updateManu);
			log.info("******Update nhà sản xuất thành công! " + updateManu.toString());
			return true;
		} else {
			log.error("@.@________Hahuhu!!!Khong co nha san xuat de ghi de______@.@");
			return false;
		}
	}

	public void deleteManufacture(int id) {
		Manufacturer deleteManu = manufacturerRepository.findOne(id);
		if (deleteManu != null) {
			manufacturerRepository.delete(id);
			log.info("Xóa nhà sản xuất " + deleteManu.getManufacturerName() + " thành công!!!");
		} else {
			log.error("@.@_________<< Không xóa được nhé! Đừng cố quá >>_________@.@");
		}

	}

	/**
	 * Collectin được gọi ra qua hàm ghi đè @Comparable trong tầng Model
	 */
	public void printListSortByName() {
		log.info("\t \tHiển thị thông tin nhà san xuất sắp xếp theo tên: ");
		List<Manufacturer> list = manufacturerRepository.findAll();
		Collections.sort(list);
		for (Manufacturer manu : list) {
			log.info(manu.toString());
		}
	}

	/*
	 * Collection được gọi ra qua hàm ghi đè @Comparator trong tầng Model
	 */
	public void printListSortById() {
		log.info("\t\tHiển thị thông tin nhà sản xuất sắp xếp theo ID: ");
		List<Manufacturer> list = manufacturerRepository.findAll();
		Collections.sort(list, new Manufacturer());
		for (Manufacturer manu : list) {
			log.info(manu.toString());
		}
	}

}
