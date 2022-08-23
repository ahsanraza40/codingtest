/**
 * 
 */
package com.moxehealth.codingtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moxehealth.codingtest.model.Provider;

/**
 * @author Ahsan Raza
 *
 */
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

	public List<Provider> findByHospitalsIdIn(List<Integer> ids);
}
