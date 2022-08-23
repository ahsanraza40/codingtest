/**
 * 
 */
package com.moxehealth.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moxehealth.codingtest.model.HospitalProvider;

/**
 * @author Ahsan Raza
 *
 */
public interface HospitalProviderRepository extends JpaRepository<HospitalProvider, Integer> {

}
