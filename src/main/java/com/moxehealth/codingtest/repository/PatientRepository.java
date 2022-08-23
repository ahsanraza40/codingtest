/**
 * 
 */
package com.moxehealth.codingtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moxehealth.codingtest.model.Patient;

/**
 * @author Ahsan Raza
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public List<Patient> findByHospitalProvidersProviderIdIn(List<Integer> ids);
}
