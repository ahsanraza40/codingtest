/**
 * 
 */
package com.moxehealth.codingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moxehealth.codingtest.dto.PatientDto;
import com.moxehealth.codingtest.exception.CustomBadRequestException;
import com.moxehealth.codingtest.model.HospitalProvider;
import com.moxehealth.codingtest.model.Patient;
import com.moxehealth.codingtest.repository.HospitalProviderRepository;
import com.moxehealth.codingtest.repository.PatientRepository;

/**
 * @author Ahsan Raza
 *
 */
@Service
@Transactional
public class PatientService {

	private final PatientRepository patientRepository;
	private final HospitalProviderRepository hospitalProviderRepository;

	public PatientService(PatientRepository patientRepository, HospitalProviderRepository hospitalProviderRepository) {
		this.patientRepository = patientRepository;
		this.hospitalProviderRepository = hospitalProviderRepository;
	}

	public Patient createPatient(PatientDto patientDto) throws CustomBadRequestException {
		Patient patient = new Patient();
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());

		Optional<HospitalProvider> hospitalProvider = hospitalProviderRepository
				.findById(patientDto.getHospitalProviderId());
		if (hospitalProvider.isPresent()) {
			List<HospitalProvider> hospitalProviders = new ArrayList<>();
			hospitalProviders.add(hospitalProvider.get());
			patient.setHospitalProviders(hospitalProviders);
		} else {
			throw new CustomBadRequestException("Invalid hospitalprovider id");
		}

		return patientRepository.save(patient);
	}

}
