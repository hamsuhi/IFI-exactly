package com.hamsuhi.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;

	private String bookingCol;

	private String bookName;

	@Temporal(TemporalType.DATE)
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateImport;

	private String publisher;

	//bi-directional many-to-one association to Bookborrowing
	@OneToMany(mappedBy="booking", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Bookborrowing> bookborrowings;

	public Booking() {
	}
	
	public Booking(String bookingCol, String bookName, Date dateImport, String publisher) {
		super();
		this.bookingCol = bookingCol;
		this.bookName = bookName;
		this.dateImport = dateImport;
		this.publisher = publisher;
	}
	
	public Booking(int bookingId, String bookingCol, String bookName, Date dateImport, String publisher,
			Set<Bookborrowing> bookborrowings) {
		super();
		this.bookingId = bookingId;
		this.bookingCol = bookingCol;
		this.bookName = bookName;
		this.dateImport = dateImport;
		this.publisher = publisher;
		this.bookborrowings = bookborrowings;
	}

	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingCol() {
		return this.bookingCol;
	}

	public void setBookingCol(String bookingCol) {
		this.bookingCol = bookingCol;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getDateImport() {
		return this.dateImport;
	}

	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Set<Bookborrowing> getBookborrowings() {
		return this.bookborrowings;
	}

	public void setBookborrowings(Set<Bookborrowing> bookborrowings) {
		this.bookborrowings = bookborrowings;
	}

	public Bookborrowing addBookborrowing(Bookborrowing bookborrowing) {
		getBookborrowings().add(bookborrowing);
		bookborrowing.setBooking(this);

		return bookborrowing;
	}

	public Bookborrowing removeBookborrowing(Bookborrowing bookborrowing) {
		getBookborrowings().remove(bookborrowing);
		bookborrowing.setBooking(null);

		return bookborrowing;
	}

}