package com.vngrs.assignment.controller.integration;

import com.vngrs.assignment.controller.DoctorController;
import com.vngrs.assignment.model.Doctor;
import com.vngrs.assignment.repository.DoctorRepository;
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
public class DoctorControllerIT {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorController doctorController;

    @After
    public void tearDown() {
        doctorRepository.deleteAll();
    }

    @Test
    public void testGetAllDoctors() {

        assertEquals(doctorRepository.findAll().size(), 0);

        Doctor doctor = Doctor.builder().doctorId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("123123"))
                .hourlyRate(150.0)
                .name("John")
                .surname("Anderson")
                .build();

        assertEquals(doctor, doctorRepository.save(doctor));
        assertEquals(doctorRepository.findAll().size(), 1);
        assertEquals(1, doctorController.getAllDoctors().size());

    }

    @Test
    public void testPostDoctor() {

        Doctor doctor = Doctor.builder().doctorId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("123123"))
                .hourlyRate(150.0)
                .name("John")
                .surname("Anderson")
                .build();

        doctorController.addDoctor(doctor);

        doctor = doctorRepository.findAll().get(0);

        assertEquals("6027fef945a417bdda5379f3", doctor.getDoctorId());
        assertEquals("John", doctor.getName());
        assertEquals("Anderson", doctor.getSurname());
    }

    @Test
    public void testDeleteDoctors() {

        Doctor doctor = Doctor.builder().doctorId("6027fef945a417bdda5379f3")
                .citizenshipNumber(new Long("123123"))
                .hourlyRate(150.0)
                .name("John")
                .surname("Anderson")
                .build();

        assertEquals(doctor, doctorRepository.save(doctor));
        assertEquals(doctorRepository.findAll().size(), 1);

        doctorController.deleteDoctors();
        assertEquals(0, doctorRepository.findAll().size());
    }
}
