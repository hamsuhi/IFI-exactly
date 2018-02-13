package com.example.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the custromer database table.
 * 
 */
@Entity
@Table(name="custromer")
@NamedQuery(name="Custromer.findAll", query="SELECT c FROM Custromer c")
public class Custromer implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="customer_id")
	private int customerId;

	@Column(name="address_line_1")
	private String addressLine1;

	@Column(name="address_line_2")
	private String addressLine2;

	@Column(name="address_line_3")
	private String addressLine3;

	private String country;

	private String county;

	@Column(name="customer_details")
	private String customerDetails;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="email_address")
	private String emailAddress;

	private String gender;

	@Column(name="phone_number")
	private String phoneNumber;

	private String town;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="custromer", fetch=FetchType.EAGER)
	private Set<Booking> bookings;

	public Custromer() {
	}

	
	public Custromer(String addressLine1, String addressLine2, String addressLine3, String country, String county,
			String customerDetails, String customerName, String emailAddress, String gender, String phoneNumber,
			String town) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.country = country;
		this.county = county;
		this.customerDetails = customerDetails;
		this.customerName = customerName;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.town = town;
	}


	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCustomerDetails() {
		return this.customerDetails;
	}

	public void setCustomerDetails(String customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Set<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setCustromer(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setCustromer(null);

		return booking;
	}


	@Override
	public String toString() {
		return "Custromer [customerId=" + customerId + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", addressLine3=" + addressLine3 + ", country=" + country + ", county=" + county
				+ ", customerDetails=" + customerDetails + ", customerName=" + customerName + ", emailAddress="
				+ emailAddress + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", town=" + town
				;
	}

}