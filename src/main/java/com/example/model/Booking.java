package com.example.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="booking_id")
	private int bookingId;

	@Column(name="confirmation_letter_sent_yn")
	private String confirmationLetterSentYn;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	@Column(name="payment_recieved_yn")
	private String paymentRecievedYn;

	//bi-directional many-to-one association to BookingStatus
	@ManyToOne
	@JoinColumn(name="booking_status_code")
	private BookingStatus bookingStatus;

	//bi-directional many-to-one association to Custromer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Custromer custromer;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="reg_number")
	private Vehicle vehicle;

	public Booking() {
	}

	public Booking(String confirmationLetterSentYn, Date dateFrom, Date dateTo, String paymentRecievedYn) {
		super();
		this.confirmationLetterSentYn = confirmationLetterSentYn;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.paymentRecievedYn = paymentRecievedYn;
	}

	public Booking(String confirmationLetterSentYn, Date dateFrom, Date dateTo, String paymentRecievedYn,
			BookingStatus bookingStatus, Custromer custromer, Vehicle vehicle) {
		super();
		this.confirmationLetterSentYn = confirmationLetterSentYn;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.paymentRecievedYn = paymentRecievedYn;
		this.bookingStatus = bookingStatus;
		this.custromer = custromer;
		this.vehicle = vehicle;
	}


	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getConfirmationLetterSentYn() {
		return this.confirmationLetterSentYn;
	}

	public void setConfirmationLetterSentYn(String confirmationLetterSentYn) {
		this.confirmationLetterSentYn = confirmationLetterSentYn;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getPaymentRecievedYn() {
		return this.paymentRecievedYn;
	}

	public void setPaymentRecievedYn(String paymentRecievedYn) {
		this.paymentRecievedYn = paymentRecievedYn;
	}

	public BookingStatus getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Custromer getCustromer() {
		return this.custromer;
	}

	public void setCustromer(Custromer custromer) {
		this.custromer = custromer;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", confirmationLetterSentYn=" + confirmationLetterSentYn
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", paymentRecievedYn=" + paymentRecievedYn
				+ ", bookingStatus=" + bookingStatus + ", custromer=" + custromer + ", vehicle=" + vehicle + "]";
	}
	
	

}