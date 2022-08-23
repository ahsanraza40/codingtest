package com.moxehealth.codingtest.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the hospital_provider database table.
 * 
 */
@Entity
@Table(name="hospital_provider")
@NamedQuery(name="HospitalProvider.findAll", query="SELECT h FROM HospitalProvider h")
public class HospitalProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Hospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hospital_id", nullable=false)
	private Hospital hospital;

	//bi-directional many-to-one association to Provider
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_id", nullable=false)
	private Provider provider;

	//bi-directional many-to-many association to Patient
	@JsonIgnore
	@ManyToMany(mappedBy="hospitalProviders")
	private List<Patient> patients;

	public HospitalProvider() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}