package com.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Manufacturer;
import com.example.model.Model;
import com.example.repository.IModelRepository;
@Transactional(rollbackFor = Exception.class)
@Resource
@Service
public class ModelService {
	private static final Logger log = LogManager.getLogger(Model.class);
	@Autowired
	private IModelRepository modelRepository;

	//@SuppressWarnings("unchecked")
	public List<Model> findAllModel() {
		List<Model> lstModel = modelRepository.findAll();
		for (Model m : lstModel) {
			m.toString();
		}
		return lstModel;
	}

	public Model findModelById(int id) {
		Model model = modelRepository.findOne(id);
		if(model!=null) {
			log.info("Model da tim thay: "+model.toString());
		}else {
			log.info("Khong tim thay model theo Id");
		}
		return model;		
	}

	public Model addModel(String dailyHireRate, String modelName, Manufacturer manufacturer) {
		Model md = new Model(dailyHireRate,modelName,manufacturer);
			return modelRepository.save(md);		
	}

	public void updateModel(int id,String dailyHireRate,String modelName,Manufacturer manufacturer ) {
		Model update = modelRepository.findOne(id);
		if (update != null) {
			update.setModelName(dailyHireRate);
			update.setDailyHireRate(modelName);
			update.setManufacturer(manufacturer);
			modelRepository.saveAndFlush(update);
			log.info("****Update MODEL thành công rực rỡ! " + update.toString());
		} else {
			log.error("@.@__________Help me! Tôi không update được! ___________@.@");
		}
	}

	public void deleteModel(int id) {
		Model model = modelRepository.findOne(id);
		if (model != null) {
			modelRepository.delete(id);
			log.info("*****Delete MODEL have name is " + model.getModelName() + " succedd!******");
		} else {
			log.error("@.@________Delete Model false! Don't model ID. Let you add!!!_______@.@");
		}
	}

}
