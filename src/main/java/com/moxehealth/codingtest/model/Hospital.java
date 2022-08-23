package com.moxehealth.codingtest.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the hospital database table.
 * 
 */
@Entity
@Table(name="hospital")
@NamedQuery(name="Hospital.findAll", query="SELECT h FROM Hospital h")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=200)
	private String description;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-many association to Provider
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="hospital_provider"
		, joinColumns={
			@JoinColumn(name="hospital_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="provider_id", nullable=false)
			}
		)
	private List<Provider> providers;

	//bi-directional many-to-one association to HospitalProvider
	@JsonIgnore
	@OneToMany(mappedBy="hospital")
	private List<HospitalProvider> hospitalProviders;

	public Hospital() {
	}
	
	

	public Hospital(int id, String description, String name) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<HospitalProvider> getHospitalProviders() {
		return this.hospitalProviders;
	}

	public void setHospitalProviders(List<HospitalProvider> hospitalProviders) {
		this.hospitalProviders = hospitalProviders;
	}

	public HospitalProvider addHospitalProvider(HospitalProvider hospitalProvider) {
		getHospitalProviders().add(hospitalProvider);
		hospitalProvider.setHospital(this);

		return hospitalProvider;
	}

	public HospitalProvider removeHospitalProvider(HospitalProvider hospitalProvider) {
		getHospitalProviders().remove(hospitalProvider);
		hospitalProvider.setHospital(null);

		return hospitalProvider;
	}

}