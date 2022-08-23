package com.moxehealth.codingtest.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the provider database table.
 * 
 */
@Entity
@Table(name = "provider")
@NamedQuery(name = "Provider.findAll", query = "SELECT p FROM Provider p")
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;

	// bi-directional many-to-many association to Hospital
	@JsonIgnore
	@ManyToMany(mappedBy = "providers")
	private List<Hospital> hospitals;

	// bi-directional many-to-one association to HospitalProvider
	@JsonIgnore
	@OneToMany(mappedBy = "provider")
	private List<HospitalProvider> hospitalProviders;

	public Provider() {
	}

	public Provider(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Hospital> getHospitals() {
		return this.hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	public List<HospitalProvider> getHospitalProviders() {
		return this.hospitalProviders;
	}

	public void setHospitalProviders(List<HospitalProvider> hospitalProviders) {
		this.hospitalProviders = hospitalProviders;
	}

	public HospitalProvider addHospitalProvider(HospitalProvider hospitalProvider) {
		getHospitalProviders().add(hospitalProvider);
		hospitalProvider.setProvider(this);

		return hospitalProvider;
	}

	public HospitalProvider removeHospitalProvider(HospitalProvider hospitalProvider) {
		getHospitalProviders().remove(hospitalProvider);
		hospitalProvider.setProvider(null);

		return hospitalProvider;
	}

}