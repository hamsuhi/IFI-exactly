package com.example.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the manufacturer database table.
 * 
 */
@Entity
@Table(name="manufacturer")
@NamedQuery(name="Manufacturer.findAll", query="SELECT m FROM Manufacturer m")
public class Manufacturer implements Serializable,Comparator<Manufacturer>, Comparable<Manufacturer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="manufacturer_code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int manufacturerCode;

	@Column(name="manufacturer_name")
	private String manufacturerName;

	@Column(name="manufacurer_details")
	private String manufacurerDetails;

	//bi-directional many-to-one association to Model
	@OneToMany(mappedBy="manufacturer", fetch=FetchType.EAGER)
	private Set<Model> models;

	public Manufacturer() {
	}
	
	public Manufacturer(String manufacturerName, String manufacurerDetails) {
		this.manufacturerName = manufacturerName;
		this.manufacurerDetails = manufacurerDetails;
	}

	public int getManufacturerCode() {
		return this.manufacturerCode;
	}

	public void setManufacturerCode(int manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getManufacturerName() {
		return this.manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacurerDetails() {
		return this.manufacurerDetails;
	}

	public void setManufacurerDetails(String manufacurerDetails) {
		this.manufacurerDetails = manufacurerDetails;
	}

	public Set<Model> getModels() {
		return this.models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

//	public Model addModel(Model model) {
//		getModels().add(model);
//		model.setManufacturer(this);
//
//		return model;
//	}
//
//	public Model removeModel(Model model) {
//		getModels().remove(model);
//		model.setManufacturer(null);
//
//		return model;
//	}
	
	/*
	 * (non-Javadoc)
	 * Sử dụng comparable sắp xếp tên
	 */
	@Override
	public int compareTo(Manufacturer manu) {
		return (this.manufacturerName).compareTo(manu.manufacturerName);
	}

	/* (non-Javadoc)
	 * Sử dụng compartor sắp xếp id
	 */
	@Override
	public int compare(Manufacturer o1, Manufacturer o2) {
		return o1.manufacturerCode - o2.manufacturerCode;
	}
	
//	public void enterInfo() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Name manufacturer: ");
//		manufacturerName = sc.nextLine();
//		System.out.println("Detail manufacturer: ");
//		manufacurerDetails = sc.nextLine();	
//	}
//
//	public void showInfo() {
//		System.out.println("Name manufacturer: "+ this.manufacturerName);
//		System.out.println("Detail manufacturer: "+this.manufacurerDetails);		
//	}

}