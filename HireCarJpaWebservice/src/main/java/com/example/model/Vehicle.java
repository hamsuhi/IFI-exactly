package com.example.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@Table(name="vehicle")
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="reg_number")
	private int regNumber;

	@Column(name="current_mileage")
	private String currentMileage;

	@Temporal(TemporalType.DATE)
	@Column(name="daily_mot_due")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-mm-yyyy" )
	private Date dailyMotDue;

	@Column(name="engine_size")
	private String engineSize;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="vehicle", fetch=FetchType.EAGER)
	@JsonBackReference
	private Set<Booking> bookings;

	//bi-directional many-to-one association to Model
	@ManyToOne
	@JoinColumn(name="model_code")
	private Model model;

	//bi-directional many-to-one association to VehicleCategory
	@ManyToOne
	@JoinColumn(name="vehicle_categorry_code")
	private VehicleCategory vehicleCategory;

	public Vehicle() {
	}

	public Vehicle(String currentMileage, Date dailyMotDue, String engineSize, Model model,
			VehicleCategory vehicleCategory) {
		this.currentMileage = currentMileage;
		this.dailyMotDue = dailyMotDue;
		this.engineSize = engineSize;
		this.model = model;
		this.vehicleCategory = vehicleCategory;
	}

	public int getRegNumber() {
		return this.regNumber;
	}

	public void setRegNumber(int regNumber) {
		this.regNumber = regNumber;
	}

	public String getCurrentMileage() {
		return this.currentMileage;
	}

	public void setCurrentMileage(String currentMileage) {
		this.currentMileage = currentMileage;
	}

	public Date getDailyMotDue() {
		return this.dailyMotDue;
	}

	public void setDailyMotDue(Date dailyMotDue) {
		this.dailyMotDue = dailyMotDue;
	}

	public String getEngineSize() {
		return this.engineSize;
	}

	public void setEngineSize(String engineSize) {
		this.engineSize = engineSize;
	}

	public Set<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setVehicle(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setVehicle(null);

		return booking;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public VehicleCategory getVehicleCategory() {
		return this.vehicleCategory;
	}

	public void setVehicleCategory(VehicleCategory vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

}