package com.example.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

/**
 * The persistent class for the model database table.
 * 
 */
@Entity
@Table(name = "model")
@NamedQuery(name = "Model.findAll", query = "SELECT m FROM Model m")
public class Model implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "model_code")
	private int modelCode;

	@Column(name = "daily_hire_rate")
	private String dailyHireRate;

	@Column(name = "model_name")
	private String modelName;

	// bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name = "manufacturer_code")
	private Manufacturer manufacturer;

	// bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy = "model", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Vehicle> vehicles;

	public Model() {
	}

	public Model(int modelCode, String dailyHireRate, String modelName, Manufacturer manufacturer) {
		this.modelCode = modelCode;
		this.dailyHireRate = dailyHireRate;
		this.modelName = modelName;
		this.manufacturer = manufacturer;
	}

	public Model(int modelCode, String dailyHireRate, String modelName) {
		this.modelCode = modelCode;
		this.dailyHireRate = dailyHireRate;
		this.modelName = modelName;
	}

	public Model(String dailyHireRate, String modelName, Manufacturer manufacturer) {
		this.dailyHireRate = dailyHireRate;
		this.modelName = modelName;
		this.manufacturer = manufacturer;
	}

	public int getModelCode() {
		return this.modelCode;
	}

	public void setModelCode(int modelCode) {
		this.modelCode = modelCode;
	}

	public String getDailyHireRate() {
		return this.dailyHireRate;
	}

	public void setDailyHireRate(String dailyHireRate) {
		this.dailyHireRate = dailyHireRate;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Set<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setModel(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setModel(null);

		return vehicle;
	}

	@Override
	public String toString() {
		return "Model [modelCode=" + modelCode + ", dailyHireRate=" + dailyHireRate + ", modelName=" + modelName
				+ ", manufacturer=" + manufacturer + "]";
	}

}