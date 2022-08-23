/**
 *
 */
package com.moxehealth.codingtest.controllertest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxehealth.codingtest.controller.AppController;
import com.moxehealth.codingtest.dto.PatientDto;
import com.moxehealth.codingtest.model.Hospital;
import com.moxehealth.codingtest.model.HospitalProvider;
import com.moxehealth.codingtest.model.Patient;
import com.moxehealth.codingtest.model.Provider;
import com.moxehealth.codingtest.repository.HospitalProviderRepository;
import com.moxehealth.codingtest.repository.HospitalRepository;
import com.moxehealth.codingtest.repository.PatientRepository;
import com.moxehealth.codingtest.repository.ProviderRepository;
import com.moxehealth.codingtest.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ahsan Raza
 */
@WebMvcTest(AppController.class)
public class AppControllerTest {

    @MockBean
    private HospitalRepository hospitalRepository;
    @MockBean
    private HospitalProviderRepository hospitalProviderRepository;
    @MockBean
    private ProviderRepository providerRepository;
    @MockBean
    private PatientRepository patientRepository;
    @MockBean
    private PatientService patientService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllHospitals() throws Exception {
        given(hospitalRepository.findAll()).willReturn(getHospitals());

        mvc.perform(get("/hospitals").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is("Metro Hospital")));
    }

    @Test
    public void testGetHospitalProviders() throws Exception {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        given(providerRepository.findByHospitalsIdIn(ids)).willReturn(getProviders());

        mvc.perform(get("/hospitalProviders?ids=1,2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Jon")));
    }

    @Test
    public void testGetPatientsByProviderId() throws Exception {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        given(patientRepository.findByHospitalProvidersProviderIdIn(ids)).willReturn(getPatients());

        mvc.perform(get("/patientsByProviderId?ids=1,2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Jon")));
    }

    @Test
    public void testCreatePatient() throws Exception {
        mvc.perform(post("/createPatient").content(toJson(beforeCreateNewPatient())).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", is("Tom")));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setFirstName("Jon");
        patient.setLastName("Smith");

        given(patientRepository.save(Mockito.any())).willReturn(patient);

        mvc.perform(put("/updatePatient").content(toJson(patient)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Jon")));
    }

    private List<Hospital> getHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        Hospital hospital1 = new Hospital(1, "Metro City main hospital", "Metro Hospital");
        Hospital hospital2 = new Hospital(2, "Off maxwell rd", "Cathy Memorial Hospital");
        hospitals.add(hospital1);
        hospitals.add(hospital2);
        return hospitals;
    }

    private List<Provider> getProviders() {
        List<Provider> providers = new ArrayList<>();
        Provider provider1 = new Provider(1, "Jon", "Smith");
        Provider provider2 = new Provider(2, "Jeny", "Richards");
        providers.add(provider1);
        providers.add(provider2);
        return providers;
    }

    private List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setFirstName("Jon");
        patient1.setLastName("Smith");
        Patient patient2 = new Patient();
        patient2.setId(2);
        patient2.setFirstName("Jeny");
        patient2.setLastName("Richards");
        patients.add(patient1);
        patients.add(patient2);
        return patients;
    }

    private PatientDto beforeCreateNewPatient() {
        PatientDto patientDto = new PatientDto("Tom", "Hunt", 1);

        HospitalProvider hospitalProvider = new HospitalProvider();
        hospitalProvider.setId(patientDto.getHospitalProviderId());

        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setHospitalProviders(new ArrayList<HospitalProvider>() {{
            add(hospitalProvider);
        }});
        given(hospitalProviderRepository.findById(patientDto.getHospitalProviderId()))
                .willReturn(Optional.of(hospitalProvider));
        given(patientRepository.save(Mockito.any())).willReturn(patient);
        given(patientService.createPatient(Mockito.any())).willReturn(patient);

        return patientDto;
    }

    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}
