package com.vngrs.assignment.controller.integration;

import com.vngrs.assignment.controller.AppointmentController;
import com.vngrs.assignment.model.Appointment;
import com.vngrs.assignment.model.AppointmentQuery;
import com.vngrs.assignment.model.Doctor;
import com.vngrs.assignment.model.Patient;
import com.vngrs.assignment.repository.AppointmentRepository;
import com.vngrs.assignment.repository.DoctorRepository;
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
public class AppointmentControllerIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentController appointmentController;

    @After
    public void tearDown() {
        doctorRepository.deleteAll();
        patientRepository.deleteAll();
        appointmentRepository.deleteAll();
    }

    @Test
    public void testGetAllAppointments() {

        assertEquals(appointmentRepository.findAll().size(), 0);

        Appointment appointment = Appointment.builder()
                .doctor(Doctor.builder().build())
                .patient(Patient.builder().build())
                .lengthOfAppointment(10)
                .appointmentDate(new java.util.Date())
                .build();

        assertEquals(appointment, appointmentRepository.save(appointment));
        assertEquals(appointmentRepository.findAll().size(), 1);
        assertEquals(1, appointmentController.getAppointments().size());

    }

    @Test
    public void testPostAppointment() {

        doctorRepository.save(Doctor.builder().doctorId("6027fef945a417bdda5379f3").build());
        patientRepository.save(Patient.builder().patientId("6027feee45a417bdda5379f2").build());

        AppointmentQuery appointmentQuery = AppointmentQuery.builder()
                .doctorId("6027fef945a417bdda5379f3")
                .patientId("6027feee45a417bdda5379f2")
                .lengthOfAppointment(20)
                .appointmentDate(new java.util.Date())
                .build();

        appointmentController.addAppointment(appointmentQuery);

        Appointment appointment = appointmentRepository.findAll().get(0);

        assertEquals("6027fef945a417bdda5379f3", appointment.getDoctor().getDoctorId());
        assertEquals("6027feee45a417bdda5379f2", appointment.getPatient().getPatientId());
        assertEquals(20, appointment.getLengthOfAppointment());
    }

    @Test
    public void testCancelAppointment() {

        doctorRepository.save(Doctor.builder().doctorId("6027fef945a417bdda5379f3").hourlyRate(15.0).build());
        patientRepository.save(Patient.builder().patientId("6027feee45a417bdda5379f2").build());

        AppointmentQuery appointmentQuery = AppointmentQuery.builder()
                .doctorId("6027fef945a417bdda5379f3")
                .patientId("6027feee45a417bdda5379f2")
                .lengthOfAppointment(20)
                .appointmentDate(new java.util.Date())
                .build();

        appointmentController.addAppointment(appointmentQuery);

        assertEquals(appointmentRepository.findAll().size(), 1);

        appointmentController.cancelAppointment(appointmentRepository.findAll().get(0).getAppointmentId());

        assertEquals(appointmentRepository.findAll().size(), 0);

        //TODO add transaction tests there


    }

    @Test
    public void testDeleteAppointments() {

        doctorRepository.save(Doctor.builder().doctorId("6027fef945a417bdda5379f3").build());
        patientRepository.save(Patient.builder().patientId("6027feee45a417bdda5379f2").build());

        AppointmentQuery appointmentQuery = AppointmentQuery.builder()
                .doctorId("6027fef945a417bdda5379f3")
                .patientId("6027feee45a417bdda5379f2")
                .lengthOfAppointment(20)
                .appointmentDate(new java.util.Date())
                .build();

        appointmentController.addAppointment(appointmentQuery);

        assertEquals(appointmentRepository.findAll().size(), 1);

        appointmentController.deleteAppointments();
        assertEquals(0, appointmentRepository.findAll().size());
    }
}
