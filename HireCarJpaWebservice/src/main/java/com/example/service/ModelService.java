package com.example.service;

/**
 * Chua xu ly duoc: manufacturerCode ko ton tai --> ko update duoc: in ra exception va loi luon toan bo chuong trinh
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.iservice.IModelService;
import com.example.model.Manufacturer;
import com.example.model.Model;
import com.example.repository.IManufacturerRepository;
import com.example.repository.IModelRepository;

//@Transactional(rollbackFor = Exception.class) 
//@Resource
@Service
public class ModelService implements IModelService {
	private static final Logger log = LogManager.getLogger(Model.class);
	@Autowired
	private IModelRepository modelRepository;

	@Autowired
	private IManufacturerRepository manufacturerRepository;

	@Autowired
	private ManufacturerService manufacturerService;

	@Override
	// @SuppressWarnings("unchecked")
	public List<Model> findAllModel() {
		List<Model> lstModel = modelRepository.findAll();
		return lstModel;
	}

	@Override
	public Model findModelById(int id) {
		Model model = modelRepository.findOne(id);
		if (model != null) {
			log.info("Model da tim thay: " + model.toString());
		} else {
			log.info("Khong tim thay model theo Id");
		}
		return model;
	}

	@Override
	public Model addModel(String dailyHireRate, String modelName, Manufacturer manufacturer) {
		Model md = new Model(dailyHireRate, modelName, manufacturer);
		if (modelRepository.exists(md.getModelCode())) {
			log.info("Don't add because model is existed: " + md.getModelName());
		} else {
			modelRepository.save(md);
		}
		return md;
	}

	/*
	 * Vi Lop Manufacturer thuoc Lop Model nen: TH1: idModel != null => -TH1:
	 * manufacturerCode ko ton tai --> ko update duoc -Th2: manufacturerCode ton tai
	 * (!=null) --> saveAndFlush Model Th2: idModel == null => tao moi model
	 */
	@Override
	public boolean updateModel(int id,int idManu, String dailyHireRate, String modelName) {
		Manufacturer manu = manufacturerRepository.getOne(idManu);
		Model update = this.findModelById(id);
		boolean manuExis = manufacturerService.findManufactureById(manu.getManufacturerCode()) != null;
		if (update != null) {
			update.setModelName(dailyHireRate);
			update.setDailyHireRate(modelName);
			update.setManufacturer(manu);
			//modelRepository.save(update);
			if (manuExis) {
				modelRepository.saveAndFlush(update);
				log.info("****Update MODEL: manuExist thi ghi de -->  " + update.toString());
				return true;
			} else {
				log.info("****Help me! Tôi không update được! ");
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteModel(int id) {
		if (modelRepository.exists(id)) {
			modelRepository.delete(id);
			return true;
		} else {
			log.error("@.@________Delete Model false! Don't model ID. Let you add!!!_______@.@");
			return false;
		}
	}

}
