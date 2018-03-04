package com.example.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;


/**
 * The persistent class for the vehicle_category database table.
 * 
 */
@Entity
@Table(name="vehicle_category")
@NamedQuery(name="VehicleCategory.findAll", query="SELECT v FROM VehicleCategory v")
public class VehicleCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="vehicle_category_code")
	private int vehicleCategoryCode;

	@Column(name="vehicle_category_description")
	private String vehicleCategoryDescription;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="vehicleCategory", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Vehicle> vehicles;

	public VehicleCategory() {
	}

	public VehicleCategory(String vehicleCategoryDescription) {
		this.vehicleCategoryDescription = vehicleCategoryDescription;
	}

	public int getVehicleCategoryCode() {
		return this.vehicleCategoryCode;
	}

	public void setVehicleCategoryCode(int vehicleCategoryCode) {
		this.vehicleCategoryCode = vehicleCategoryCode;
	}

	public String getVehicleCategoryDescription() {
		return this.vehicleCategoryDescription;
	}

	public void setVehicleCategoryDescription(String vehicleCategoryDescription) {
		this.vehicleCategoryDescription = vehicleCategoryDescription;
	}

	public Set<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setVehicleCategory(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setVehicleCategory(null);
		return vehicle;
	}

	@Override
	public String toString() {
		return "VehicleCategory [vehicleCategoryCode=" + vehicleCategoryCode + ", vehicleCategoryDescription="
				+ vehicleCategoryDescription + "]";
	}

}