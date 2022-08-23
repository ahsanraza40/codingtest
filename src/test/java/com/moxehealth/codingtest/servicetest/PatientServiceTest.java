/**
 *
 */
package com.moxehealth.codingtest.servicetest;

import com.moxehealth.codingtest.dto.PatientDto;
import com.moxehealth.codingtest.model.HospitalProvider;
import com.moxehealth.codingtest.model.Patient;
import com.moxehealth.codingtest.repository.HospitalProviderRepository;
import com.moxehealth.codingtest.repository.PatientRepository;
import com.moxehealth.codingtest.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * @author Ahsan Raza
 */
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private HospitalProviderRepository hospitalProviderRepository;

    @Test
    public void Successfully_createPatientTest() throws Exception {
        PatientDto patientDto = new PatientDto("Tom", "Hunt", 1);

        HospitalProvider hospitalProvider = new HospitalProvider();
        hospitalProvider.setId(patientDto.getHospitalProviderId());

        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setHospitalProviders(new ArrayList<HospitalProvider>() {{
            add(hospitalProvider);
        }});
        
        given(hospitalProviderRepository.findById(1)).willReturn(Optional.of(hospitalProvider));
        given(patientRepository.save(Mockito.any())).willReturn(patient);

        assertTrue((patientService.createPatient(patientDto)) != null);
    }
}
