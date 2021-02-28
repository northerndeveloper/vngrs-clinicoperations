package com.vngrs.assignment.controller;

import com.vngrs.assignment.exception.AppointmentNotFoundException;
import com.vngrs.assignment.exception.DoctorNotFoundException;
import com.vngrs.assignment.exception.PatientNotFoundException;
import com.vngrs.assignment.model.*;
import com.vngrs.assignment.repository.AppointmentRepository;
import com.vngrs.assignment.repository.DoctorRepository;
import com.vngrs.assignment.repository.PatientRepository;
import com.vngrs.assignment.repository.TransactionRepository;
import com.vngrs.assignment.util.DateUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author alperkopuz
 * Appointment Rest Controller class created for adding and canceling appointments
 */
@RestController
@AllArgsConstructor
public class AppointmentController {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    // Dependency Injection was added as constructor injection which is better by providing AllAargsConstructor of lombok
    public AppointmentRepository appointmentRepository;

    public DoctorRepository doctorRepository;

    public PatientRepository patientRepository;

    public TransactionRepository transactionRepository;

    /**
     * Gets all appointments
     *
     * @return
     */
    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {

        logger.info("Getting all appointments");

        return appointmentRepository.findAll();
    }

    /**
     * Post appointments with the AppointmentQuery fields that the user provided
     *
     * @param appointmentQuery
     */
    @PostMapping("/appointments")
    public void addAppointment(@RequestBody @Valid AppointmentQuery appointmentQuery) {

        Optional<Doctor> doctor = doctorRepository.findById(appointmentQuery.getDoctorId());
        if ( !doctor.isPresent() ) {
            logger.error("Please make sure if the doctor with id " + appointmentQuery.getDoctorId() + " exits in the system.");
            throw new DoctorNotFoundException("Doctor requested is not recorded in the system.");
        }
        logger.debug("getting doctor from db with the doctor id " + doctor.get().getDoctorId());

        Optional<Patient> patient = patientRepository.findById(appointmentQuery.getPatientId());
        if ( !patient.isPresent() ) {
            logger.error("Please make sure if the patient with id " + appointmentQuery.getPatientId() + " exits in the system.");
            throw new PatientNotFoundException("Patient requested is not recorded in the system.");
        }
        logger.debug("getting patient from db with the patient id " + patient.get().getPatientId());

        logger.info("Adding new appointment with  " + " doctor id " + doctor.get().getDoctorId() +
                " patient id " + patient.get().getPatientId() + "appointment date" + appointmentQuery.getAppointmentDate());

        Appointment appointment = Appointment.builder()
                .appointmentDate(appointmentQuery.getAppointmentDate())
                .lengthOfAppointment(appointmentQuery.getLengthOfAppointment())
                .doctor(doctor.get())
                .patient(patient.get()).build();

        appointmentRepository.save(appointment);

        logger.info("appointment is added successfully");
    }

    /**
     * Cancels appointments with the provided appointment ID and removed appointment from the Appointment Table
     *
     * @param appointmentId
     */
    @PostMapping("/appointments/{appointmentId}/cancel")
    public void cancelAppointment(@PathVariable("appointmentId") String appointmentId) {

        logger.info("Cancelation appointment process is started with the appointment id  " + appointmentId);

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        if ( !appointment.isPresent() ) {
            logger.error("Please make sure if the doctor with id " + appointmentId + " exits in the system.");
            throw new AppointmentNotFoundException("Appointment that wanted to cancel is not recorded in the system.");
        }

        logger.debug("Appointment is received from DB with the doctor ID  " + appointment.get().getDoctor().getDoctorId()
                + " and with the patient ID = " + appointment.get().getPatient().getPatientId() +
                " and for the appointment on  " + appointment.get().getAppointmentDate());


        double cancelationPenalty = calculateCancelPenalty(appointment.get());
        Transaction transaction = Transaction.builder()
                .appointment(appointment.get()).cancellationFee(cancelationPenalty)
                        .build();
        transactionRepository.save(transaction);

        logger.info("Removal of appointment process has started");
        appointmentRepository.delete(appointment.get());
        logger.info("Appointment has been removed successfully");

        logger.info("transaction has been completed successfully");
    }

    /**
     * Delete all appointments
     */
    @DeleteMapping("/deleteappointments")
    public void deleteAppointments() {

        logger.info("Delete all appointments process has been removed started");

        appointmentRepository.deleteAll();

        logger.info("Delete all appointments process has been completed successfully");
    }

    /**
     * Calculates Cancel Penalty which will be paid the patients who cancels the appointment
     *
     * @param appointment
     * @return
     */
    public double calculateCancelPenalty(Appointment appointment) {

        double calculatedCancelPenalty = 0;

        long diff = ChronoUnit.HOURS.
                between(LocalDateTime.now(), DateUtil.convertToLocalDateTimeViaInstant(appointment.getAppointmentDate()));

        logger.debug("total difference of hours between now and the appointment date is" + diff);

        if (diff <= 1) {
            calculatedCancelPenalty = calculateAppointmentPrice(appointment) / 4;
        }

        logger.debug("total cancelation penalty is " + calculatedCancelPenalty);

        return calculatedCancelPenalty;
    }


    /**
     * Calculates Total Appointment Price with the coefficient doctor's hourly rate and length of the appointment
     *
     * @param appointment
     * @return
     */
    public Double calculateAppointmentPrice(Appointment appointment) {


        double coefficient = calculateCoefficient(appointment.getAppointmentDate());
        logger.debug("coefficient by the appointment date " + coefficient);

        return coefficient * appointment.getDoctor().getHourlyRate() * appointment.getLengthOfAppointment();
    }

    /**
     * Calculates coefficient for the penalty whic his calculated by getting difference of appointmentDate and current
     * Date
     *
     * @param appointmentDate
     * @return
     */
    public Double calculateCoefficient(Date appointmentDate) {

        long diff = ChronoUnit.HOURS.
                between(LocalDateTime.now(), DateUtil.convertToLocalDateTimeViaInstant(appointmentDate));
        logger.debug("Difference of hours between current date and appointment date for coefficient " + diff);

        if (diff < 1) {
            return 12.5;
        } else if (diff < 6) {
            return 10.0;
        } else if (diff < 12) {
            return 9.0;
        } else if (diff < 18) {
            return 8.0;
        } else if (diff < 24) {
            return 7.0;
        } else if (diff < 48) {
            return 6.0;
        } else if (diff < 120) {
            return 5.0;
        }
        return 3.0;
    }

}
