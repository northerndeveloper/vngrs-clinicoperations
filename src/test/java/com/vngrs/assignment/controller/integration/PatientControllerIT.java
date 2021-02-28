package com.vngrs.assignment.controller.integration;

import com.vngrs.assignment.controller.PatientController;
import com.vngrs.assignment.model.Patient;
import com.vngrs.assignment.repository.PatientRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientController patientController;

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    public void testGetAllDoctors() {

        assertEquals(patientRepository.findAll().size(), 0);

        Patient patient = Patient.builder().patientId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("1213123"))
                .name("Michael")
                .surname("Johanna")
                .build();

        assertEquals(patient, patientRepository.save(patient));
        assertEquals(patientRepository.findAll().size(), 1);
        assertEquals(1, patientController.getAllPatients().size());

    }

    @Test
    public void testPostDoctor() {

        Patient patient = Patient.builder().patientId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("1213123"))
                .name("Michael")
                .surname("Johanna")
                .build();

        patientController.addPatient(patient);

        patient = patientRepository.findAll().get(0);

        assertEquals("6027fef945a417bdda5379f3", patient.getPatientId());
        assertEquals("Michael", patient.getName());
        assertEquals("Johanna", patient.getSurname());
    }

    @Test
    public void testDeletePatients() {

        Patient patient = Patient.builder().patientId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("1213123"))
                .name("Michael")
                .surname("Johanna")
                .build();

        assertEquals(patient, patientRepository.save(patient));
        assertEquals(patientRepository.findAll().size(), 1);

        patientController.deletePatients();
        assertEquals(0, patientRepository.findAll().size());
    }
}
