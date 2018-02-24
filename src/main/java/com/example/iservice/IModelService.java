/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.Manufacturer;
import com.example.model.Model;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IModelService {

	/**
	 * @return
	 */
	List<Model> findAllModel();

	/**
	 * @param id
	 * @return
	 */
	Model findModelById(int id);

	/**
	 * @param dailyHireRate
	 * @param modelName
	 * @param manufacturer
	 * @return
	 */
	Model addModel(String dailyHireRate, String modelName, Manufacturer manufacturer);

	/**
	 * @param id
	 * @param dailyHireRate
	 * @param modelName
	 * @param manufacturer
	 * @return
	 * @throws Exception 
	 */
	boolean updateModel(int id, String dailyHireRate, String modelName, Manufacturer manufacturer) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	boolean deleteModel(int id);

}
