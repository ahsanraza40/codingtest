/**
 * 
 */
package com.moxehealth.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moxehealth.codingtest.model.Hospital;

/**
 * @author Ahsan Raza
 *
 */
public interface HospitalRepository  extends JpaRepository<Hospital, Integer> {

}
