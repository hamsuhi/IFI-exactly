package com.hamsuhi.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the reader database table.
 * 
 */
@Entity
@NamedQuery(name="Reader.findAll", query="SELECT r FROM Reader r")
public class Reader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numberCard;

	private String address;

	@Temporal(TemporalType.DATE)
	//@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateOfBirth;

	private String fullName;

	private String gender;

	private String tel;

	//bi-directional many-to-one association to Bookborrowing
	@OneToMany(mappedBy="reader", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Bookborrowing> bookborrowings;

	public Reader() {
	}

	public Reader(String address, Date dateOfBirth, String fullName, String gender, String tel) {
		super();
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.fullName = fullName;
		this.gender = gender;
		this.tel = tel;
	}
	
	

	public Reader(int numberCard, String address, Date dateOfBirth, String fullName, String gender, String tel) {
		super();
		this.numberCard = numberCard;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.fullName = fullName;
		this.gender = gender;
		this.tel = tel;
	}

	public int getNumberCard() {
		return this.numberCard;
	}

	public void setNumberCard(int numberCard) {
		this.numberCard = numberCard;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<Bookborrowing> getBookborrowings() {
		return this.bookborrowings;
	}

	public void setBookborrowings(Set<Bookborrowing> bookborrowings) {
		this.bookborrowings = bookborrowings;
	}

	public Bookborrowing addBookborrowing(Bookborrowing bookborrowing) {
		getBookborrowings().add(bookborrowing);
		bookborrowing.setReader(this);

		return bookborrowing;
	}

	public Bookborrowing removeBookborrowing(Bookborrowing bookborrowing) {
		getBookborrowings().remove(bookborrowing);
		bookborrowing.setReader(null);

		return bookborrowing;
	}

}