package com.vngrs.assignment.controller.unit;

import com.vngrs.assignment.controller.AppointmentController;
import com.vngrs.assignment.model.Appointment;
import com.vngrs.assignment.model.Doctor;
import com.vngrs.assignment.model.Patient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerUnitTest {

    @Autowired
    private AppointmentController appointmentController;


    @Test
    public void testCalculateCoefficient() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, 1);
        assertEquals(7.0, appointmentController.calculateCoefficient(cal.getTime()));
        cal.add(Calendar.DAY_OF_WEEK, 5);
        assertEquals(3.0, appointmentController.calculateCoefficient(cal.getTime()));
    }

    @Test
    public void testCalculateAppointmentPrice() {

        Appointment appointment = Appointment.builder()
                .doctor(Doctor.builder().hourlyRate(3.0).build())
                .patient(Patient.builder().build())
                .lengthOfAppointment(10)
                .appointmentDate(new java.util.Date())
                .build();

        assertEquals(375.0, appointmentController.calculateAppointmentPrice(appointment));
    }

    @Test
    public void testCalculateCancelPenalty() {

        Appointment appointment = Appointment.builder()
                .doctor(Doctor.builder().hourlyRate(3.0).build())
                .patient(Patient.builder().build())
                .lengthOfAppointment(10)
                .appointmentDate(new java.util.Date())
                .build();

        assertEquals(93.75, appointmentController.calculateCancelPenalty(appointment));
    }
}
