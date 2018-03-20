package com.hamsuhi.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * The persistent class for the bookborrowing database table.
 */
@Entity
@NamedQuery(name="Bookborrowing.findAll", query="SELECT b FROM Bookborrowing b")
public class Bookborrowing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookBorrowingId;

	@Temporal(TemporalType.DATE)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateBorrowing;

	@Temporal(TemporalType.DATE)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date datePay;

	//bi-directional many-to-one association to Booking
	@ManyToOne
	@JoinColumn(name="bookingId")
	private Booking booking;

	//bi-directional many-to-one association to Reader
	@ManyToOne
	@JoinColumn(name="numberCard")
	private Reader reader;

	public Bookborrowing() {
	}

	public Bookborrowing(Date dateBorrowing, Date datePay, Booking booking, Reader reader) {
		super();
		this.dateBorrowing = dateBorrowing;
		this.datePay = datePay;
		this.booking = booking;
		this.reader = reader;
	}

	public Bookborrowing(int bookBorrowingId, Date dateBorrowing, Date datePay, Booking booking, Reader reader) {
		super();
		this.bookBorrowingId = bookBorrowingId;
		this.dateBorrowing = dateBorrowing;
		this.datePay = datePay;
		this.booking = booking;
		this.reader = reader;
	}

	public int getBookBorrowingId() {
		return this.bookBorrowingId;
	}

	public void setBookBorrowingId(int bookBorrowingId) {
		this.bookBorrowingId = bookBorrowingId;
	}

	public Date getDateBorrowing() {
		return this.dateBorrowing;
	}

	public void setDateBorrowing(Date dateBorrowing) {
		this.dateBorrowing = dateBorrowing;
	}

	public Date getDatePay() {
		return this.datePay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Reader getReader() {
		return this.reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

}