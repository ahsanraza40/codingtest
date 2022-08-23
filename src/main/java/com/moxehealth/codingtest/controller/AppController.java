/**
 *
 */
package com.moxehealth.codingtest.controller;

import com.moxehealth.codingtest.dto.PatientDto;
import com.moxehealth.codingtest.model.Hospital;
import com.moxehealth.codingtest.model.Patient;
import com.moxehealth.codingtest.model.Provider;
import com.moxehealth.codingtest.repository.HospitalRepository;
import com.moxehealth.codingtest.repository.PatientRepository;
import com.moxehealth.codingtest.repository.ProviderRepository;
import com.moxehealth.codingtest.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ahsan Raza
 */
@RestController
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    private final HospitalRepository hospitalRepository;
    private final ProviderRepository providerRepository;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public AppController(HospitalRepository hospitalRepository, ProviderRepository providerRepository,
                         PatientRepository patientRepository, PatientService patientService) {
        this.hospitalRepository = hospitalRepository;
        this.providerRepository = providerRepository;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @GetMapping("/hospitalProviders")
    public ResponseEntity<List<Provider>> getProvidersByHospitalIds(@RequestParam List<Integer> ids) {
        List<Provider> providers = providerRepository.findByHospitalsIdIn(ids);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/patientsByProviderId")
    public ResponseEntity<List<Patient>> getPatientsByProviderId(@RequestParam List<Integer> ids) {
        List<Patient> patients = patientRepository.findByHospitalProvidersProviderIdIn(ids);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping("/createPatient")
    public ResponseEntity<Patient> savePatient(@RequestBody PatientDto patientDto) {
        Patient result = patientService.createPatient(patientDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        Patient result = patientRepository.save(patient);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
